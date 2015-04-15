@ECHO OFF
SET BINDIR=%~dp0
CD /D "%BINDIR%"
java -jar "./../../dist/lost_essence.jar" s
pause