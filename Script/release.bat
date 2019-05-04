@echo off
set fulicentPath=G:\work\fulicent
cd "%fulicentPath%\fulicentUI"
rem cml web build

xcopy "%fulicentPath%\fulicentUI\dist\web\*.*" "%fulicentPath%\fulicentApi\src\main\resources" /s/h/c/y
cd "%fulicentPath%\fulicentApi"
rem mvn package
xcopy "target\fulicent-0.0.1-SNAPSHOT.jar" "%fulicentPath%\release\"  /s/h/c/y
xcopy "%fulicentPath%\Script\Dockerfile" "%fulicentPath%\release\"  /s/h/c/y
cd "%fulicentPath%\release\"
docker build -t fulicent .
