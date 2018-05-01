package com.jrafael.mc.config;

import java.text.ParseException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import com.jrafael.mc.services.DBService;

@Configuration
@Profile("test")
public class TestConfig {
	
	@Autowired
	DBService dbservice;
	
	@Bean
	public boolean instantiateDatabase() throws ParseException  {
		dbservice.instantiateTestDatabase();
		return true;
	}
}
