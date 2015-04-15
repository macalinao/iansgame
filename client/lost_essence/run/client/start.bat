@ECHO OFF
SET BINDIR=%~dp0
CD /D "%BINDIR%"
java -Djava.library.path="./libs/native/windows" -jar "./../../dist/lost_essence.jar" c
pause