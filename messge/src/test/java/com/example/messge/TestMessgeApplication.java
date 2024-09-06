package com.example.messge;

import org.springframework.boot.SpringApplication;

public class TestMessgeApplication {

	public static void main(String[] args) {
		SpringApplication.from(MessgeApplication::main).with(TestcontainersConfiguration.class).run(args);
	}

}
