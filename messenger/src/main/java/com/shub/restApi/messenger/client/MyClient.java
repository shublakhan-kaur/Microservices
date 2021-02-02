package com.shub.restApi.messenger.client;

import com.shub.restApi.messenger.model.Message;

import jakarta.ws.rs.client.Client;
import jakarta.ws.rs.client.ClientBuilder;
import jakarta.ws.rs.client.Entity;
import jakarta.ws.rs.client.WebTarget;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

public class MyClient {

	public static void main(String[] args) {
		Client client = ClientBuilder.newClient();
		WebTarget baseTarget = client.target("http://localhost:8080/messenger/webapi/");
		WebTarget messageTarget = baseTarget.path("messages");
		WebTarget singleMessageTarget = messageTarget.path("{messageId}");

//		get Response from JAX RS client
		getClientResponse(singleMessageTarget);

//		post Reponse from jax-rs client
		postClientResponse(messageTarget);

	}

	public static void postClientResponse(WebTarget messageTarget) {
		Message newMessage = new Message(4, "Post method from JAX-RS client", "Shub");
		Response postResponse = messageTarget.request().post(Entity.json(newMessage));
		if (postResponse.getStatus() != 201) {
			System.out.println("Error");
		}
		Message createdMessage = postResponse.readEntity(Message.class);
		System.out.println(createdMessage.getMessage());
	}

	public static void getClientResponse(WebTarget singleMessageTarget) {
//		WebTarget target = client.target("http://localhost:8080/messenger/webapi/messages/1");
		/*
		 * Builder builder =
		 * singleMessageTarget.resolveTemplate("messageId","1").request(); Response
		 * response = builder.get(); Message message =
		 * response.readEntity(Message.class);
		 */
		Message message1 = singleMessageTarget.resolveTemplate("messageId", "1").request(MediaType.APPLICATION_JSON)
				.get(Message.class);

		Message message2 = singleMessageTarget.resolveTemplate("messageId", "2").request(MediaType.APPLICATION_JSON)
				.get(Message.class);
		System.out.println(message1.getMessage());
		System.out.println(message2.getMessage());
//		System.out.println("Author :: " + message.getAuthor());
//		System.out.println("Created :: " + message.getCreated());
//		System.out.println(message.getLinks().get(0).getLink() + ":::" + message.getLinks().get(0).getRel());
		/*
		 * for (Link link : message.getLinks()) { System.out.println("Link: " +
		 * link.getLink() + "  Rel::" + link.getRel()); }
		 */
	}
}
