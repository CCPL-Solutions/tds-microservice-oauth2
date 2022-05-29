FROM openjdk:11
VOLUME [ "/tmp" ]
EXPOSE 9100
ARG JAR_FILE=target/*.jar
COPY ${JAR_FILE} oauth2-server.jar
ENTRYPOINT [ "java", "-jar", "/oauth2-server.jar" ]