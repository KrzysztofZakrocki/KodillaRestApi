call runcrud.bat
if "%ERRORLEVEL%" == "0" goto openWebBrowser
echo.
echo runcrud.bat has errors - breaking work
goto fail

:openWebBrowser
start http://localhost:8080/crud/v1/task/getTasks
if "%ERRORLEVEL%" == "0" goto end
echo.
echo Cannot open web browser
goto fail

:fail
echo.
echo There were errors

:end
echo.
echo Work is finished.


