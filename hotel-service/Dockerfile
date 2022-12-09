FROM openjdk:17
EXPOSE 8083
ADD /target/hotel-service.jar hotel-service.jar
ENTRYPOINT [ "sh", "-c", "java $JAVA_OPTS -Djava.security.egd=file:/dev/./urandom -jar /hotel-service.jar"]
