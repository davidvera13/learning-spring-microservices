@echo off

cd "./configserver"
call mvn clean
call docker build . -t davidvera/configserver:0.0.5-RELEASE
cd ".."

cd "./accounts"
call mvn clean
call docker build . -t davidvera/accounts:0.0.5-RELEASE
cd ".."

cd "./cards"
call mvn clean
call docker build . -t davidvera/cards:0.0.5-RELEASE
cd ".."

cd "./loans"
call mvn clean
call docker build . -t davidvera/loans:0.0.5-RELEASE
cd ".."

@REM we do not push for the moment ..
@REM call docker image push docker.io/davidvera/accounts:0.0.5-RELEASE
@REM call docker image push docker.io/davidvera/cards:0.0.5-RELEASE
@REM call docker image push docker.io/davidvera/loans:0.0.5-RELEASE

@REM if we remove local image, we can get them with pull
@REM call docker pull davidvera/accounts:0.0.5-RELEASE
@REM call docker pull davidvera/cards:0.0.5-RELEASE
@REM call docker pull davidvera/loans:0.0.5-RELEASE

@REM Running directly, will pull image if not exists
@REM call docker run -d -p 8080:8080 davidvera/accounts:0.0.5-RELEASE
@REM call docker run -d -p 9000:9000 davidvera/cards:0.0.5-RELEASE
@REM call docker run -d -p 8090:8090 davidvera/loans:0.0.5-RELEASE

cd "./-- docker --/services/prod"
call docker compose up -d
cd "../../.."