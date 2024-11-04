package io.warehouse13.accounts.dto;

import org.springframework.boot.context.properties.ConfigurationProperties;

import java.util.List;
import java.util.Map;

@ConfigurationProperties(prefix = "contact")
public record DevContact(
		String name,
		String city) {
}
