## Adding Mysql 
We may need to replace h2 using mysql. for this, we need to replace dependency in pom.xml: 

    <dependency>
        <groupId>com.h2database</groupId>
        <artifactId>h2</artifactId>
        <scope>runtime</scope>
    </dependency>

with: 

    <!-- https://mvnrepository.com/artifact/com.mysql/mysql-connector-j -->
    <dependency>
        <groupId>com.mysql</groupId>
        <artifactId>mysql-connector-j</artifactId>
        <version>9.1.0</version>
    </dependency>

In application.yaml we need to define connection: 

    
    spring:
        datasource:
            url: jdbc:mysql://localhost:3306/db_cards?createDatabaseIfNotExist=true&useSSL=true
            username: root
            password: root
