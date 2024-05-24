#!/usr/bin/python3
import sys
import csv
import telnetlib
from datetime import datetime

def run_telnet(host, password, destination_path):
    try:
        port = 23
        screen = "screen-length disable"
        discurrent = "show running-config"
        disversion = "show version"
        disdevmanu = "show inventory"
        disclock = "display clock"        

        tn = telnetlib.Telnet(host, port, 2)
        tn.open(host, 23, 2)

        response = tn.read_until(b"10", 1)
        print(response.decode("utf-8"))
        response = tn.read_until(b"10", 1)
        print(response.decode("utf-8"))
        response = tn.read_until(b"10", 1)
        print(response.decode("utf-8"))

        # Running the code in order
        tn.write(password.encode("utf-8") + b"\n")
        tn.write(screen.encode("utf-8") + b"\n\n")
        tn.write(disclock.encode("utf-8") +  b"\n\n\n\n\n\n")
        tn.write(discurrent.encode("utf-8") + b"\n\n\n\n\n\n")
        tn.write(disversion.encode("utf-8") + b"\n\n\n\n\n\n")
        tn.write(disdevmanu.encode("utf-8") + b"\n")

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

    csv_file = sys.argv[2]
    destination_path = sys.argv[3]

    try:
        with open(csv_file, "r") as file:
            csv_reader = csv.reader(file)
            for row in csv_reader:
                if len(row) == 2:
                    host, password = row
                    run_telnet(host, password, destination_path)
                else:
                    print(f"Skipping invalid line in CSV file: {','.join(row)}")

    except FileNotFoundError:
        print(f"Data file not found: {csv_file}")

if __name__ == "__main__":
    main()
