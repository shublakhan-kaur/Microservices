package com.shub.restApi.messenger.exception;

import com.shub.restApi.messenger.model.ErrorMessage;

import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.core.Response.Status;
import jakarta.ws.rs.ext.ExceptionMapper;
import jakarta.ws.rs.ext.Provider;

//@Provider
public class GenericExceptionMapper implements ExceptionMapper<Throwable> {

	public GenericExceptionMapper() {
	}

	@Override
	public Response toResponse(Throwable exception) {
		ErrorMessage errorMessage = new ErrorMessage(exception.getMessage(), 500, exception.getCause().toString());
		return Response.status(Status.INTERNAL_SERVER_ERROR).entity(errorMessage).build();
	}

}
