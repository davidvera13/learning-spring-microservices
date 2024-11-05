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

# Use adduser (lighter alternative to useradd)
RUN adduser --home /home/appuser --disabled-password appuser
USER appuser

WORKDIR /opt/app

COPY --from=builder /opt/app/target/accounts.jar /opt/app/accounts.jar

ENTRYPOINT ["java", "-jar", "/opt/app/accounts.jar"]