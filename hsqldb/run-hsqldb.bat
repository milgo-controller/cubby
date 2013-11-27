@echo off
if "%HSQLDB_HOME%" == "" goto NotInstalled

:Installed
java -classpath %HSQLDB_HOME%/lib/hsqldb.jar org.hsqldb.server.Server -database.0 file:database/cubby -dbname.0 cubby
exit /b 0

:NotInstalled
echo Zmienna HSQLDB_HOME niezdefiniowana!
exit /b 0