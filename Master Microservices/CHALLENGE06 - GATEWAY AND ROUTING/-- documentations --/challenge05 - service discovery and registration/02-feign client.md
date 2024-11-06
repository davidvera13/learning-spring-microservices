## Feign client

if we want to call other web services using web client in a microservice context, 
openfeign is the solution. 

Add maven dependency to account microservice: 

    <dependency>
        <groupId>org.springframework.cloud</groupId>
        <artifactId>spring-cloud-starter-openfeign</artifactId>
    </dependency>

In the main application class, we have to enable feign clients:

    @SpringBootApplication
    @EnableFeignClients
    ...
    public class AccountsApplication {
    
        public static void main(String[] args) {
            SpringApplication.run(AccountsApplication.class, args);
        }
    }


We can update controller method: 



We need to implement clients using abstract interface, feign client name matches with application name: 

    @FeignClient("cards")
    public interface CardsFeignClient {
        @GetMapping(
            value = "/api",
            consumes = MediaType.APPLICATION_JSON_VALUE)
        ResponseEntity<CardDto> fetchCardDetails(@RequestParam String mobileNumber);
    }