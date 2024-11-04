package io.warehouse13.loans.controller;


import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import io.warehouse13.loans.dto.ErrorResponseDto;
import io.warehouse13.loans.dto.LoanDto;
import io.warehouse13.loans.dto.ResponseDto;
import io.warehouse13.loans.service.LoanService;
import io.warehouse13.loans.shared.LoansConstants;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Pattern;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@Tag(
		name = "CRUD REST APIs for Loans in BankApp",
		description = "CRUD REST APIs in BankApp to CREATE, UPDATE, FETCH AND DELETE loan details")
@RestController
@RequestMapping(path = "/api", produces = {MediaType.APPLICATION_JSON_VALUE})
@RequiredArgsConstructor
@Validated
public class LoanController {

	private final LoanService loanService;

	@Operation(
			summary = "Create Loan REST API",
			description = "REST API to create new loan inside BankApp")
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
	@PostMapping //("/create")
	public ResponseEntity<ResponseDto> createLoan(
			@RequestParam @Pattern(regexp="(^$|[0-9]{10})",message = "Mobile number must be 10 digits") String mobileNumber) {
		loanService.createLoan(mobileNumber);
		return ResponseEntity
				.status(HttpStatus.CREATED)
				.body(new ResponseDto(LoansConstants.STATUS_201, LoansConstants.MESSAGE_201));
	}

	@Operation(
			summary = "Fetch Loan Details REST API",
			description = "REST API to fetch loan details based on a mobile number"
	)
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
	public ResponseEntity<LoanDto> fetchLoanDetails(
			@RequestParam @Pattern(regexp="(^$|[0-9]{10})",message = "Mobile number must be 10 digits") String mobileNumber) {
		LoanDto loansDto = loanService.fetchLoan(mobileNumber);
		return ResponseEntity.status(HttpStatus.OK).body(loansDto);
	}

	@Operation(
			summary = "Update Loan Details REST API",
			description = "REST API to update loan details based on a loan number")
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
	public ResponseEntity<ResponseDto> updateLoanDetails(
			@Valid @RequestBody LoanDto loanDto) {
		return loanService.updateLoan(loanDto) ?
				ResponseEntity
					.status(HttpStatus.OK)
					.body(new ResponseDto(LoansConstants.STATUS_200, LoansConstants.MESSAGE_200)) :
				ResponseEntity
					.status(HttpStatus.EXPECTATION_FAILED)
					.body(new ResponseDto(LoansConstants.STATUS_417, LoansConstants.MESSAGE_417_UPDATE));
	}

	@Operation(
			summary = "Delete Loan Details REST API",
			description = "REST API to delete Loan details based on a mobile number")
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
	public ResponseEntity<ResponseDto> deleteLoanDetails(
			@RequestParam @Pattern(regexp="(^$|[0-9]{10})",message = "Mobile number must be 10 digits") String mobileNumber) {
		return loanService.deleteLoan(mobileNumber) ?
				ResponseEntity
						.status(HttpStatus.OK)
						.body(new ResponseDto(LoansConstants.STATUS_200, LoansConstants.MESSAGE_200)) :
				ResponseEntity
						.status(HttpStatus.EXPECTATION_FAILED)
						.body(new ResponseDto(LoansConstants.STATUS_417, LoansConstants.MESSAGE_417_DELETE));
	}
}