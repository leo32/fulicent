@echo off
set fulicentPath=D:\GIT\My\fulicent
cd "%fulicentPath%\fulicentUI"
call cml web build
cd "%fulicentPath%\Script\"
sleep 120
rename "%fulicentPath%\fulicentUI\dist\web\fulicentUI.html index.html
xcopy "%fulicentPath%\fulicentUI\dist\web\*.*" "%fulicentPath%\fulicentApi\src\main\resources\static" /s/h/c/y
cd "%fulicentPath%\fulicentApi"
call mvn package
cd "%fulicentPath%\Script\"
sleep 120
xcopy "%fulicentPath%\fulicentApi\target\fulicent-0.0.1-SNAPSHOT.jar" "%fulicentPath%\release\"  /s/h/c/y
xcopy "%fulicentPath%\Script\Dockerfile" "%fulicentPath%\release\"  /s/h/c/y
cd "%fulicentPath%\release\"
docker build -t fulicent .
