FROM adoptopenjdk/openjdk11:alpine

LABEL maintainer="zakhar.borisov@mail.ru"

ARG JAR_FILE=/target/ecommerce-0.0.1-SNAPSHOT.jar

ADD ${JAR_FILE} ecommerce.jar

# команда запуска джарника
ENTRYPOINT ["java", "-jar", "/ecommerce.jar"]