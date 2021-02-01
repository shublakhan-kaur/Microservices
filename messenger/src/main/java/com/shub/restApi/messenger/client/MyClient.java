package com.shub.restApi.messenger.client;

import com.shub.restApi.messenger.model.Message;

import jakarta.ws.rs.client.Client;
import jakarta.ws.rs.client.ClientBuilder;
import jakarta.ws.rs.core.Response;

public class MyClient {

	public static void main(String[] args) {
		Client client = ClientBuilder.newClient();
		Response response = client.target("http://localhost:8080/messenger/webapi/messages/1").request().get();
		Message message = response.readEntity(Message.class);
		System.out.println(message.getMessage());
	}
}
