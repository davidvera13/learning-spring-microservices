package io.warehouse13.accounts.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import io.warehouse13.accounts.dto.CustomerDto;
import io.warehouse13.accounts.dto.ErrorResponseDto;
import io.warehouse13.accounts.dto.ResponseDto;
import io.warehouse13.accounts.service.AccountService;
import io.warehouse13.accounts.shared.AccountsConstants;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Pattern;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;


@Tag(
		name = "CRUD REST APIs for Accounts in BankApp",
		description = "CRUD REST APIs in BankApp to CREATE, UPDATE, FETCH AND DELETE account details")
@RestController
@RequestMapping(
		path = "/api",
		produces = MediaType.APPLICATION_JSON_VALUE)
@RequiredArgsConstructor
@Validated
public class AccountController {
	private final AccountService accountService;

	@Operation(
			summary = "Create Account REST API",
			description = "REST API to create new Customer &  Account inside BankApp")
	@ApiResponses({
			@ApiResponse(
					responseCode = "201",
					description = "HTTP Status CREATED"),
			@ApiResponse(
					responseCode = "500",
					description = "HTTP Status Internal Server Error",
					content = @Content(
							schema = @Schema(implementation = ErrorResponseDto.class)))
	})
	@PostMapping
	public ResponseEntity<ResponseDto> createAccount(@RequestBody CustomerDto customerDto) {
		accountService.createAccount(customerDto);
		return ResponseEntity
				.status(HttpStatus.CREATED)
				.body(new ResponseDto(
						AccountsConstants.STATUS_201,
						AccountsConstants.MESSAGE_201));
	}

	@Operation(
			summary = "Fetch Account Details REST API",
			description = "REST API to fetch Customer &  Account details based on a mobile number")
	@ApiResponses({
			@ApiResponse(
					responseCode = "200",
					description = "HTTP Status OK"),
			@ApiResponse(
					responseCode = "500",
					description = "HTTP Status Internal Server Error",
					content = @Content(
							schema = @Schema(implementation = ErrorResponseDto.class)))
	})
	@GetMapping //("/fetch")
	public ResponseEntity<CustomerDto> fetchAccount(
			@RequestParam @Pattern(regexp="(^$|[0-9]{10})",message = "Mobile number must be 10 digits") String mobileNumber) {
		CustomerDto customerDto = accountService.fetchAccount(mobileNumber);
		return ResponseEntity.status(HttpStatus.OK).body(customerDto);
	}

	@Operation(
			summary = "Update Account Details REST API",
			description = "REST API to update Customer &  Account details based on a account number")
	@ApiResponses({
			@ApiResponse(
					responseCode = "200",
					description = "HTTP Status OK"),
			@ApiResponse(
					responseCode = "417",
					description = "Expectation Failed"),
			@ApiResponse(
					responseCode = "500",
					description = "HTTP Status Internal Server Error",
					content = @Content(
							schema = @Schema(implementation = ErrorResponseDto.class)))
	})
	@PutMapping //("/update")
	public ResponseEntity<ResponseDto> updateAccount(@Valid @RequestBody CustomerDto customerDto) {
		// version 1: shorter
		return accountService.updateAccount(customerDto) ?
				ResponseEntity
						.status(HttpStatus.OK)
						.body(new ResponseDto(AccountsConstants.STATUS_200, AccountsConstants.MESSAGE_200)) :
				ResponseEntity
						.status(HttpStatus.EXPECTATION_FAILED)
						.body(new ResponseDto(AccountsConstants.STATUS_417, AccountsConstants.MESSAGE_417_UPDATE));
	}

	@Operation(
			summary = "Delete Account & Customer Details REST API",
			description = "REST API to delete Customer &  Account details based on a mobile number")
	@ApiResponses({
			@ApiResponse(
					responseCode = "200",
					description = "HTTP Status OK"),
			@ApiResponse(
					responseCode = "417",
					description = "Expectation Failed"),
			@ApiResponse(
					responseCode = "500",
					description = "HTTP Status Internal Server Error",
					content = @Content(
							schema = @Schema(implementation = ErrorResponseDto.class)))
	})
	@DeleteMapping //("/delete")
	public ResponseEntity<ResponseDto> deleteAccount(
			@RequestParam @Pattern(regexp="(^$|[0-9]{10})",message = "Mobile number must be 10 digits") String mobileNumber) {
		return accountService.deleteAccount(mobileNumber) ?
				ResponseEntity
						.status(HttpStatus.OK)
						.body(new ResponseDto(AccountsConstants.STATUS_200, AccountsConstants.MESSAGE_200)) :
				ResponseEntity
						.status(HttpStatus.EXPECTATION_FAILED)
						.body(new ResponseDto(AccountsConstants.STATUS_417, AccountsConstants.MESSAGE_417_DELETE));
	}
}

