#!/usr/bin/python3
import sys
import csv
import telnetlib
import time
from datetime import datetime

def run_telnet(host, password, destination_path):
    try:

        port = 23
        screen = "screen-length disable"
        discurrent = "display current-configuration"
        disversion = "display version"
        disdevmanu = "display device manuinfo"
        save = "save"
        yes = "y"

        tn = telnetlib.Telnet(host, port, 2)
        tn.open(host, 23, 2)

        response = tn.read_until(b"10", 1)
        print(response.decode("utf-8"))

        # Running the code in order
        tn.write(password.encode("utf-8") + b"\n")
        tn.write(screen.encode("utf-8") + b"\n\n")
        tn.write(save.encode("utf-8")+ b"\n")
        tn.write(yes.encode("utf-8")+ b"\n\n")        
        tn.write(yes.encode("utf-8")+ b"\n")


        print(f"Save Complete")

    except Exception as e:
        print(f"An error occurred for {host}: {str(e)}")

def main():
    if len(sys.argv) < 1:
        print("Invalid arguments provided. Usage: telnet.py <csv_file> <destination_path>")
        return
    print("g")
    print(sys.argv)
    csv_file = sys.argv[1]
    destination_path = sys.argv[2]

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
