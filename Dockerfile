FROM openjdk:11
COPY target/chat-box-1.0-SNAPSHOT-jar-with-dependencies.jar /tmp
WORKDIR /tmp
CMD java -jar chat-box-1.0-SNAPSHOT-jar-with-dependencies.jar