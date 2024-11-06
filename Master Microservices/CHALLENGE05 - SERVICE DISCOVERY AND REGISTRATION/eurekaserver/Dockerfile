FROM eclipse-temurin:21-jdk-alpine AS builder

# Use adduser (lighter alternative to useradd)
RUN adduser --home /home/appuser --disabled-password appuser
USER appuser

WORKDIR /opt/app
# copy required files
COPY ./.mvn/ ./.mvn
COPY ./mvnw ./mvnw
COPY ./pom.xml ./pom.xml

RUN ./mvnw dependency:go-offline
COPY ./src ./src
RUN ./mvnw clean install -DskipTests


FROM eclipse-temurin:21-jre-alpine

# Install curl for health checks
RUN apk add --no-cache curl

# Use adduser (lighter alternative to useradd)
RUN adduser --home /home/appuser --disabled-password appuser
USER appuser

WORKDIR /opt/app

COPY --from=builder /opt/app/target/eurekaserver.jar /opt/app/eurekaserver.jar

ENTRYPOINT ["java", "-jar", "/opt/app/eurekaserver.jar"]