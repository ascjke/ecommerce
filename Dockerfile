FROM openjdk:17

# указываем ярлык. Например, разработчика образа и проч. Необязательный пункт.
LABEL maintainer="borisov.za@rcitsakha.ru"

# указываем, где в нашем приложении лежит джарник
ARG JAR_FILE=/target/mother-forum-1.0-SNAPSHOT.jar

# добавляем джарник в образ под именем motherforum-backend.jar
ADD ${JAR_FILE} motherforum-backend.jar

# команда запуска джарника
ENTRYPOINT ["java", "-jar", "/motherforum-backend.jar"]