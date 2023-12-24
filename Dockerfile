FROM openjdk
WORKDIR job4j_forum
ADD target/job4j_forum-1.0.jar app.jar
ENTRYPOINT java -jar app.jar
