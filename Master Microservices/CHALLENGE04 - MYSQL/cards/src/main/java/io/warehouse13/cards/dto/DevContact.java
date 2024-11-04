package io.warehouse13.cards.dto;

import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties(prefix = "contact")
public record DevContact(
		String name,
		String city) {
}
