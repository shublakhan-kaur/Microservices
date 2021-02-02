package com.shub.restApi.messenger.client;

import jakarta.ws.rs.client.Client;
import jakarta.ws.rs.client.ClientBuilder;
import jakarta.ws.rs.client.Invocation;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

public class InvocationDemo {

	public static void main(String[] args) {
		InvocationDemo demo = new InvocationDemo();
		Invocation invocation = demo.prepareRequestByYear(2021);
		Response response = invocation.invoke();
		System.out.println(response.getStatus());
	}

	public Invocation prepareRequestByYear(int year) {
		Client client = ClientBuilder.newClient();
		return client.target("http://localhost:8080/messenger/webapi/").path("messages").queryParam("year", year)
				.request(MediaType.APPLICATION_JSON).buildGet();
	}

}
