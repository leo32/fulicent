FROM openjdk:10-jre-slim
COPY D:/GIT/My/fulicent/release/fulicent-0.0.1-SNAPSHOT.jar /tmp/fulicent.jar
COPY D:/GIT/My/fulicent/release/start.sh /tmp/start.sh

EXPOSE 8080

WORKDIR /tmp/

RUN apt-get update -y && apt-get install -y curl python && curl -O https://bootstrap.pypa.io/get-pip.py && python get-pip.py && pip install awscli

ENTRYPOINT ["/bin/bash", "start.sh"]

