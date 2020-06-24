call runcrud

if "%ERRORLEVEL%" == "0" goto startchrome
echo Problem with Chrome
goto fail

:startchrome
start chrome http://localhost:8080/crud/v1/task/getTasks
goto end

:fail
echo.
echo There were errors

:end
echo.
echo showtask has finished