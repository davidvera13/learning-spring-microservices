package io.warehouse13.accounts.dto;

import org.springframework.boot.context.properties.ConfigurationProperties;

import java.util.List;
import java.util.Map;

@ConfigurationProperties(prefix = "accounts")
public record AccountsInfo(
		String message,
		Map<String, String> contactDetails,
		List<String> callSupport) {
}
