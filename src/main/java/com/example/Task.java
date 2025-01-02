package com.example;

public record Task(int id, String name, String description) {
	public Task(String name, String description) {
		this(-1, name, description);
	}
}
