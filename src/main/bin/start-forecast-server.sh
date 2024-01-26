#!/bin/bash
APP_NAME="SecForecastServer"

# Check if the App is already running
if pgrep -x "$APP_NAME" > /dev/null; then
    # App is already running
    pid=$(pgrep -x "$APP_NAME")
    echo "Application '$APP_NAME' is already running (PID: $pid)."
else
  JAVA_HOME=/home/m4031/java/jdk-17
  echo "JAVA_HOME=$JAVA_HOME"
  APP_ROOT_DIR=/home/m4031/secforecast
  JAR_FILE=$APP_ROOT_DIR/current/sec-forecast-server-0.0.1.jar
  echo "JAR_FILE=JAR_FILE"
  LOG_DIR=$APP_ROOT_DIR/logs
  LOG_FILE=SecForecastServer.log
  echo "LOG_DIR=$LOG_DIR/$LOG_FILE"
  nohup $JAVA_HOME/bin/java -jar -Dspring.profiles.active=local -Dlog.dir=$LOG_DIR -Dlog.file=$LOG_FILE "$JAR_FILE" "$APP_NAME" 2>&1 &

  sleep 3

  if [ $? -eq 0 ]; then
    pid=$(pgrep -x "$APP_NAME")
    echo "Successfully started Application '$APP_NAME' (PID: $pid)."
  else
    echo "Failed to start Application '$APP_NAME'."
  fi
fi