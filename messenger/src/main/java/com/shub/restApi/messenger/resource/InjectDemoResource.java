package com.shub.restApi.messenger.resource;

import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.CookieParam;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.HeaderParam;
import jakarta.ws.rs.MatrixParam;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;

@Path("/injectDemo")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class InjectDemoResource {

	@GET
	@Path("/anotations")
	public String getParamAnnotation(@MatrixParam("param") String martixParam,
			@HeaderParam("headerParam") String headerParam, @CookieParam("cookieParam") String cookieParam) {
		return "Martix Param: " + martixParam + " Header Pram ::" + headerParam + " Cookie Param:" + cookieParam;
	}
}
