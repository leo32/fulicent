@echo off
set fulicentPath=G:\work\fulicent
cd "%fulicentPath%\fulicentWebUI"
call npm run build
G:\work\fulicent\Script\sleep.exe 7
rmdir /s/q "%fulicentPath%\fulicentApi\src\main\resources\static"
mkdir "%fulicentPath%\fulicentApi\src\main\resources\static"
xcopy "%fulicentPath%\fulicentWebUI\dist\*.*" "%fulicentPath%\fulicentApi\src\main\resources\static" /s/h/c/y
cd G:\work\fulicent\Script\
