#!/usr/bin/python3
import sys
import csv
import telnetlib
import time
from datetime import datetime

def run_telnet(host, password, destination_path, commands):
    try:
        port = 23
        tn = telnetlib.Telnet(host, port, 2)
        tn.open(host, 23, 2)

        response = tn.read_until(b"10", 1)
        print(response.decode("utf-8"))
        response = tn.read_until(b"10", 1)
        print(response.decode("utf-8"))
        response = tn.read_until(b"10", 1)
        print(response.decode("utf-8"))
        
        tn.write(password.encode("utf-8") + b"\n")
        screen = "screen-length disable"
        tn.write(screen.encode("utf-8") + b"\n\n")

        for i in range (len(commands)):
            tn.write(commands[i].encode("utf-8") + b"\n")
            time.sleep(2)
            

        response = tn.read_until(b">")
        response = tn.read_until(b">")
        response = tn.read_until(b"1000", 5)

        # Remove empty lines and leading/trailing spaces
        cleaned_response = "\n".join(line.strip() for line in response.decode("utf-8").split("\n") if line.strip())

        sysname = ""
        for line in cleaned_response.split('\n'):
            if line.startswith("sysname "):
                sysname = line[len("sysname "):]
                break

        current_time = datetime.now().strftime("(%Y-%m-%d-[%H：%M：%S])")
        filename = f"{destination_path}/{current_time}-{sysname}.txt"
        with open(filename, "w") as file:
            file.write(cleaned_response)
        print(f"Response for {host} saved to: {filename}")

    except Exception as e:
        print(f"An error occurred for {host}: {str(e)}")

def main():
    if len(sys.argv) < 1:
        print("Invalid arguments provided. Usage: telnet.py <csv_file> <destination_path>")
        return

    csv_file = sys.argv[1]
    destination_path = sys.argv[2]
    commands = sys.argv[3]

    try:
        with open(csv_file, "r") as file:
            csv_reader = csv.reader(file)
            for row in csv_reader:
                if len(row) > 1:
                    host, password = row
                    run_telnet(host, password, destination_path, commands.split('\n'))
                else:
                    print(f"Skipping invalid line in CSV file: {','.join(row)}")

    except FileNotFoundError:
        print(f"Data file not found: {csv_file}")

if __name__ == "__main__":
    main()
