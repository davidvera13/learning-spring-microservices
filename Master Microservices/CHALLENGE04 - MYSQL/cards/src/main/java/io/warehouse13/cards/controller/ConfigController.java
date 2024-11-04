package io.warehouse13.cards.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import io.warehouse13.cards.dto.CardsInfo;
import io.warehouse13.cards.dto.DevContact;
import io.warehouse13.cards.dto.ErrorResponseDto;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@Tag(
		name = "REST APIs for reading properties",
		description = "Get data from properties")
@RestController
@RequestMapping(
		path = "/api/configs",
		produces = MediaType.APPLICATION_JSON_VALUE)
@RequiredArgsConstructor
@Validated
public class ConfigController {
	private final Environment environment;
	private final CardsInfo cardsInfos;
	private final DevContact devContact;

	@Value("${build.version}")
	private String buildVersion;


	@Operation(
			summary = "Get build version information from cards microservice",
			description = "Get build information that is deployed in cards microservice"
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
	@GetMapping(value = "build-info")
	public ResponseEntity<String> getBuildInfo() {
		return ResponseEntity.status(HttpStatus.OK).body(buildVersion);
	}

	@Operation(
			summary = "Get java version used for application",
			description = "Get java version used in cards microservice"
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
	@GetMapping(value = "java-version")
	public ResponseEntity<String> getJavaVersion() {
		return ResponseEntity.status(HttpStatus.OK).body(environment.getProperty("JAVA_HOME"));
	}

	@Operation(
			summary = "Fetch information",
			description = "Get loans microservice information"
	)
	@ApiResponses({
			@ApiResponse(
					responseCode = "200",
					description = "HTTP Status OK"
			),
			@ApiResponse(
					responseCode = "500",
					description = "HTTP Status Internal Server Error",
					content = @Content(
							schema = @Schema(implementation = ErrorResponseDto.class)
					)
			)
	})
	@GetMapping("/contact-infos")
	public ResponseEntity<CardsInfo> getInfos() {
		return ResponseEntity.ok(cardsInfos);
	}

	@Operation(
			summary = "Fetch dev Contact",
			description = "Get loans microservice dev contact"
	)
	@ApiResponses({
			@ApiResponse(
					responseCode = "200",
					description = "HTTP Status OK"
			),
			@ApiResponse(
					responseCode = "500",
					description = "HTTP Status Internal Server Error",
					content = @Content(
							schema = @Schema(implementation = ErrorResponseDto.class)
					)
			)
	})
	@GetMapping("/contact-devs")
	public ResponseEntity<DevContact> getDevContact() {
		return ResponseEntity.ok(devContact);
	}
}


