package com.example;

import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;

import org.eclipse.microprofile.reactive.messaging.Channel;
import org.eclipse.microprofile.reactive.messaging.Emitter;

import io.smallrye.common.annotation.RunOnVirtualThread;
import io.smallrye.mutiny.Multi;

@Path("/data")
public class ExampleResource {
	private final Multi<Task> channel;
	private final Emitter<Task> emitter;

	public ExampleResource(@Channel("channel") Multi<Task> channel, @Channel("channel") Emitter<Task> emitter) {
		this.channel = channel;
		this.emitter = emitter;
	}

	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	@RunOnVirtualThread
	public void addTask(Task task) {
		this.emitter.send(task);
	}

	@GET
	@Produces(MediaType.SERVER_SENT_EVENTS)
	public Multi<Task> getTasks() {
		return this.channel;
	}
}
