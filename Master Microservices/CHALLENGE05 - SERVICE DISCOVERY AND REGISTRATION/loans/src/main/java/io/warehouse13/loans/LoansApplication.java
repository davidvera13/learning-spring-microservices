package io.warehouse13.loans;

import io.swagger.v3.oas.annotations.ExternalDocumentation;
import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.info.License;
import io.warehouse13.loans.dto.DevContact;
import io.warehouse13.loans.dto.LoansInfo;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableJpaAuditing(auditorAwareRef = "auditAwareImpl")
@EnableConfigurationProperties(value = {LoansInfo.class, DevContact.class} )
@OpenAPIDefinition(
		info = @Info(
				title = "Loans microservice REST API Documentation",
				description = "BankApp Loans microservice REST API Documentation",
				version = "v1",
				contact = @Contact(
						name = "Uncle Scrooge",
						email = "scrooge@bank.com",
						url = "https://www.bank.com"),
				license = @License(
						name = "Apache 2.0",
						url = "https://www.bank.com")),
		externalDocs = @ExternalDocumentation(
				description = "BankApp Loans microservice REST API Documentation",
				url = "https://www.bank.com/swagger-ui.html"))
public class LoansApplication {
	public static void main(String[] args) {
		SpringApplication.run(LoansApplication.class, args);
	}

}
