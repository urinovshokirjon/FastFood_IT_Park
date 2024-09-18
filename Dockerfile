FROM amazoncorretto:17
ARG JAR_FILE=target/*.jar
COPY target/FastFood-0.0.1-SNAPSHOT.jar fast_food.jar
ENTRYPOINT ["java", "-Xmx2048M", "-jar", "/fast_food.jar"]
