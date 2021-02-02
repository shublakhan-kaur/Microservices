package com.shub.restApi.messenger.filter;

import java.io.IOException;
import java.util.Base64;
import java.util.List;
import java.util.StringTokenizer;

import jakarta.ws.rs.container.ContainerRequestContext;
import jakarta.ws.rs.container.ContainerRequestFilter;
import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.core.Response.Status;
import jakarta.ws.rs.ext.Provider;

@Provider
public class SecurityFilter implements ContainerRequestFilter {

	private static final String AUTHORIZATION_HEADER_KEY = "Authorization";
	private static final String AUTHORIZATION_HEADER_PREFIX = "Basic ";
	private static final String SECURED_URL_PREFIX = "messages";

	@Override
	public void filter(ContainerRequestContext requestContext) throws IOException {
		System.out.println(requestContext.getUriInfo().getPath() + "security");
		if (requestContext.getUriInfo().getPath().contains(SECURED_URL_PREFIX)) {
			List<String> authHeader = requestContext.getHeaders().get(AUTHORIZATION_HEADER_KEY);
			if (authHeader!=null && authHeader.size() > 0) {
				String authToken = authHeader.get(0);
				authToken = authToken.replaceFirst(AUTHORIZATION_HEADER_PREFIX, "");
				byte[] decodedByte = Base64.getDecoder().decode(authToken);
				String decodedString = new String(decodedByte);
				StringTokenizer decodedToken = new StringTokenizer(decodedString, ":");
				String userName = decodedToken.nextToken();
				String password = decodedToken.nextToken();
				if ("username".equals(userName) && "password".equals(password)) {
					return;
				}
			}
			Response unauthorizedStatus = Response.status(Status.UNAUTHORIZED).entity("Unauthorized").build();
			requestContext.abortWith(unauthorizedStatus);
		}
	}
}