package io.warehouse13.accounts.clients;

import io.warehouse13.accounts.clients.dto.LoanDto;
import jakarta.validation.constraints.Pattern;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient("loans")
public interface LoansFeignClient {

	@GetMapping(
			value = "/api",
			consumes = MediaType.APPLICATION_JSON_VALUE)
	ResponseEntity<LoanDto> fetchLoanDetails(
			@RequestParam String mobileNumber);
}
