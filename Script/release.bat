@echo off
set fulicentPath=G:\work\fulicent
cd "%fulicentPath%\fulicentUI"
call cml web build
cd "%fulicentPath%\Script\"
sleep 60
rename "%fulicentPath%\fulicentUI\dist\web\fulicentUI.html index.html
xcopy "%fulicentPath%\fulicentUI\dist\web\*.*" "%fulicentPath%\fulicentApi\src\main\resources\static" /s/h/c/y
cd "%fulicentPath%\fulicentApi"
call mvn package
cd "%fulicentPath%\Script\"
sleep 60
xcopy "%fulicentPath%\fulicentApi\target\fulicent-0.0.1-SNAPSHOT.jar" "%fulicentPath%\release\"  /s/h/c/y
xcopy "%fulicentPath%\Script\Dockerfile" "%fulicentPath%\release\"  /s/h/c/y
cd "%fulicentPath%\release\"
rem docker build -t fulicent .
