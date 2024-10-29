@echo off

cd "./accounts"
call mvn clean
call docker build . -t davidvera/accounts:0.0.1-SNAPSHOT
cd ".."

cd "./cards"
call mvn clean
call docker build . -t davidvera/cards:0.0.1-SNAPSHOT
cd ".."

cd "./loans"
call mvn clean
call docker build . -t davidvera/loans:0.0.1-SNAPSHOT
cd ".."

@REM we do not push for the moment ..
@REM call docker image push docker.io/davidvera/accounts:0.0.1-SNAPSHOT
@REM call docker image push docker.io/davidvera/cards:0.0.1-SNAPSHOT
@REM call docker image push docker.io/davidvera/loans:0.0.1-SNAPSHOT

@REM if we remove local image, we can get them with pull
@REM call docker pull davidvera/accounts:0.0.1-SNAPSHOT
@REM call docker pull davidvera/cards:0.0.1-SNAPSHOT
@REM call docker pull davidvera/loans:0.0.1-SNAPSHOT

@REM Running directly, will pull image if not exists
call docker run -d -p 8080:8080 davidvera/accounts:0.0.1-SNAPSHOT
call docker run -d -p 9000:9000 davidvera/cards:0.0.1-SNAPSHOT
call docker run -d -p 8090:8090 davidvera/loans:0.0.1-SNAPSHOT
