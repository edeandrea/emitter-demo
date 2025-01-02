package com.example;

import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;

import org.eclipse.microprofile.reactive.messaging.Channel;
import org.eclipse.microprofile.reactive.messaging.Emitter;

import io.smallrye.mutiny.Multi;

@Path("/data")
public class ExampleResource {
	private final Multi<String> channel;
	private final Emitter<String> emitter;

	public ExampleResource(@Channel("channel") Multi<String> channel, @Channel("channel") Emitter<String> emitter) {
		this.channel = channel;
		this.emitter = emitter;
	}

	@POST
	@Consumes(MediaType.TEXT_PLAIN)
	public void addString(String string) {
		this.emitter.send(string);
	}

	@GET
	@Produces(MediaType.SERVER_SENT_EVENTS)
	public Multi<String> getStrings() {
		return this.channel;
	}
}
