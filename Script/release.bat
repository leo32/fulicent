@echo off
set fulicentPath=D:\GIT\My\fulicent
cd "%fulicentPath%\fulicentUI"
cml web build
pause
xcopy "%fulicentPath%\fulicentUI\dist\web\*.*" "%fulicentPath%\fulicentApi\src\main\resources" /s/h/c/y
cd "%fulicentPath%\fulicentApi"
mvn package
