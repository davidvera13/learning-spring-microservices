## Using properties 
3 solutions: 
>  Separation of configuration & properties
> 
> Inject configuration & properties
>  
> Implementing a configuration server with Spring Cloud Config Server

### How to configure Springboot 
We can use 
1. Application.properties
2. OS environment varoames
3. java system properties
4. JNDI attributes
5. ServletContext init params
6. ServletConfig init params
7. Command line params 

***How to read properties ?*** 
1. using @Value: eg. `@Value("${property.name}) private String propertyName;`
2. using Environment bean: `@Autowired Environment env; String prop = env.getProperty("property.name")`
3. using @ConfigurationProperties: with a POJO annotated `@ConfigurationProperties("property")`

Last solution requires annotation : `@EnableConfigurationProperties(value = {AccountsInfo.class} )

### Using application profiles
we can use different profiles with different setups on different environements. 
In application.yaml, we can define a profile to use

    spring:
        profiles:
            active: "prod"
        config:
            import:
                - "classpath:application_qa.yaml"
                - "classpath:application_prod.yaml"

In application_prod.yaml, we name the property file to be for prod configuration

    spring:
        config:
            activate:
                on-profile: 'prod'


### Using command lines / jvm & environment options
command lines arguments are converted into key value pairs used as environment parameters.

example: 

    java -jar accounts/jar --build.version=3.14
    java -jar accounts/jar --spring.profiles.active=qa

with JVM system properties : 

    java -Dbuild.version=3.14.159 -jar accounts.jar
    java -Dspring.profiles.active=qa -Dbuild.version=3.14.159 -jar accounts.jar

with environment variables

    set BUILD_VERSION=1.56 && java -jar accounts.jar
    set BUILD_VERSION=1.57 && set SPRING_PROFILES_ACTIVE=default && java -jar accounts.jar


