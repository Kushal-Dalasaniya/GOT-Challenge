package com.demandfarm.got.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;

@Configuration
@Profile("!local")
@PropertySource("classpath:./messages/got-exceptions.properties")
@PropertySource("classpath:./messages/url.properties")
public class MessageConfig {

	@Bean
	private static PropertySourcesPlaceholderConfigurer placeHolderConfigurer() {
		return new PropertySourcesPlaceholderConfigurer();
	}

}
