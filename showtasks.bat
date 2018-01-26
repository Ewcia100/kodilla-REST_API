call runcrud.bat
if "%ERRORLEVEL%"=="0" goto browser
echo.
echo You have a problem with runcrud.bat
goto fail

:browser
explorer "http://localhost:8080/crud/v1/task/getTasks"
goto end

:fail
echo.
echo There were errors.

:end
echo.
echo Enjoy the view :).