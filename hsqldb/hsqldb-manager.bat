@echo off
if "%HSQLDB_HOME%" == "" goto NotInstalled

:Installed
java -classpath %HSQLDB_HOME%/lib/hsqldb.jar org.hsqldb.util.DatabaseManager
exit /b 0

:NotInstalled
echo Zmienna HSQLDB_HOME niezdefiniowana!
exit /b 0