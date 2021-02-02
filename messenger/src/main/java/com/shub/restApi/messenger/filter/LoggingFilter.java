package com.shub.restApi.messenger.filter;

import java.io.IOException;

import jakarta.ws.rs.container.ContainerRequestContext;
import jakarta.ws.rs.container.ContainerRequestFilter;
import jakarta.ws.rs.container.ContainerResponseContext;
import jakarta.ws.rs.container.ContainerResponseFilter;
import jakarta.ws.rs.ext.Provider;

@Provider
public class LoggingFilter implements ContainerRequestFilter, ContainerResponseFilter {

	@Override
	public void filter(ContainerRequestContext requestContext) throws IOException {
		System.out.println("Request Filter");
		System.out.println("Header:" + requestContext.getHeaders());

	}

	@Override
	public void filter(ContainerRequestContext requestContext, ContainerResponseContext responseContext)
			throws IOException {
		System.out.println("Response Filter");
		System.out.println(responseContext.getHeaders());
	}

}
