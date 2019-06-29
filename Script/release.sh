docker stop $(docker ps -aq)
docker rm $(docker ps -aq)
docker rmi -f fulicent
docker build -t fulicent .
docker run -p 80:8080 -t fulicent