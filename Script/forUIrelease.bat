@echo off
set fulicentPath=G:\work\fulicent
cd "%fulicentPath%\fulicentUI"
call cml web build
G:\work\fulicent\Script\sleep.exe 10
xcopy "%fulicentPath%\fulicentUI\dist\web\*.*" "%fulicentPath%\fulicentApi\src\main\resources\static" /s/h/c/y
