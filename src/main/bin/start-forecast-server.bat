setlocal enabledelayedexpansion
set APP_NAME="SecForecastServer"

REM Check if the program is already running
tasklist /FI "IMAGENAME eq java.exe" /FO CSV | findstr /I /C:"%APP_NAME%" > nul
if %errorlevel% equ 0 (
    echo Program '%APP_NAME%' is already running.
) else (
    set JAVA_HOME="C:\SOFTWARES\JAVA\openjdk-17-winx64\jdk-17"
    echo JAVA_HOME='%JAVA_HOME%'
    set JAR_FILE="sec-forecast-server-0.0.1.jar"
    set LOG_DIR="C:\temp\secforecast\logs"
    set LOG_FILE="SecForecastServer.log"
    echo LOG_DIR='%LOG_DIR%/%LOG_FILE%'
    if not exist %LOG_DIR% mkdir %LOG_DIR%
    %JAVA_HOME%\bin\java -jar -Dspring.profiles.active=local -Dlog.dir=%LOG_DIR% -Dlog.file=%LOG_FILE% "%JAR_FILE%" "%APP_NAME%"
    REM Program is not running, start it
    start "MyJavaApp" javaw -jar "%JAR_FILE%"
    timeout /t 5 /nobreak > nul

    REM Check if the program started successfully
    tasklist /FI "IMAGENAME eq java.exe" /FO CSV | findstr /I /C:"%APP_NAME%" > nul
    if %errorlevel% equ 0 (
        echo Successfully started program '%APP_NAME%'.
    ) else (
        echo Failed to start program '%APP_NAME%'.
    )
)

endlocal