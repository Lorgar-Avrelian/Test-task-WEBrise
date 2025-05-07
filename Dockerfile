FROM maven:3.9.9-amazoncorretto-17 AS builder
WORKDIR /opt/app
COPY pom.xml ./
COPY ./src ./src
RUN mvn clean install -DskipTests

FROM amazoncorretto:17
WORKDIR /opt/app
EXPOSE 8080
COPY --from=builder /opt/app/target/*.jar /opt/app/*.jar
ENTRYPOINT ["java", "-jar", "/opt/app/*.jar"]