#!/bin/bash

APP_NAME="SecForecastServer"

# Check if the program is running
if pgrep -x "$APP_NAME" > /dev/null; then
    # Get the process ID of the program
    pid=$(pgrep -x "$APP_NAME")

    # Program is running, stop it
    pkill -x "$APP_NAME"

    # Introduce a delay (e.g., 3 seconds) after starting the program
    sleep 3

    # Double check if stoped then print success otherwise failure
    if pgrep -x "$APP_NAME" > /dev/null; then
      echo "Successfully stopped the Application '$APP_NAME' (PID: $pid)"
    else
      echo "Failed to stop the Application '$APP_NAME' (PID: $pid)"
else
    # Program is not running
    echo "No such Application ('$APP_NAME') running."
fi