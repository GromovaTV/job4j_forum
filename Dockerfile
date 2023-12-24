FROM openjdk
WORKDIR job4j_forum
ADD target/forum-1.jar app.jar
ENTRYPOINT java -jar app.jar
