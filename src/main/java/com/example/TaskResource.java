package com.example;

import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;

import org.eclipse.microprofile.reactive.messaging.Channel;

import io.quarkus.logging.Log;

import io.smallrye.mutiny.Multi;
import io.smallrye.mutiny.Uni;
import io.smallrye.reactive.messaging.MutinyEmitter;

@Path("/tasks")
public class TaskResource {
	private final Multi<Task> channel;
	private final MutinyEmitter<Task> emitter;

	public TaskResource(@Channel("channel") Multi<Task> channel, @Channel("channel") MutinyEmitter<Task> emitter) {
		this.channel = channel;
		this.emitter = emitter;
	}

	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	public Uni<Void> addTask(Task task) {
		Log.infof("Adding new task: %s", task);
		return this.emitter.send(task);
	}

	@GET
	@Produces(MediaType.SERVER_SENT_EVENTS)
	public Multi<Task> getTasks() {
		return this.channel;
	}
}
