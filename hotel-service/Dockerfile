FROM openjdk:17
EXPOSE 8761
ADD /target/service-registery.jar app.jar
ENTRYPOINT [ "sh", "-c", "java $JAVA_OPTS -Djava.security.egd=file:/dev/./urandom -jar /app.jar"]
