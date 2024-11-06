package io.warehouse13.accounts.clients;

import io.warehouse13.accounts.clients.dto.CardDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient("cards")
public interface CardsFeignClient {
	@GetMapping(
			value = "/api",
			consumes = MediaType.APPLICATION_JSON_VALUE)
	ResponseEntity<CardDto> fetchCardDetails(@RequestParam String mobileNumber);
}
