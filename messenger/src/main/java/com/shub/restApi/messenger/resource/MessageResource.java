package com.shub.restApi.messenger.resource;

import java.net.URI;
import java.util.List;

import com.shub.restApi.messenger.model.Message;
import com.shub.restApi.messenger.resource.beans.MessageFilterBean;
import com.shub.restApi.messenger.service.MessageService;

import jakarta.ws.rs.BeanParam;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.DELETE;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.PUT;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.Context;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.core.UriInfo;

@Path("/messages")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(value = { MediaType.APPLICATION_JSON, MediaType.TEXT_XML })
public class MessageResource {

	public MessageResource() {
	}

	MessageService messageService = new MessageService();

	@GET
	public List<Message> getAllMesssages(@BeanParam MessageFilterBean filterBean) {
		if (filterBean.getYear() > 0) {
			return messageService.getAllMessagesByYear(filterBean.getYear());
		}
		if (filterBean.getStart() > 0 && filterBean.getSize() > 0) {
			return messageService.getAllMessagesPaginated(filterBean.getStart(), filterBean.getSize());
		}
		return messageService.getAllMessages();
	}

	@GET
	@Path("/{messageId}")
	public Message getMessage(@PathParam("messageId") long messageId, @Context UriInfo uriInfo) {
		Message message = messageService.getMessage(messageId);
		message.addLink(getUriForSelf(message, uriInfo), "self");
		message.addLink(getUriForProfile(message, uriInfo), "profile");
		message.addLink(getUriForComments(message, uriInfo), "comments");
		return message;
	}

	private String getUriForComments(Message message, UriInfo uriInfo) {
		URI uri = uriInfo.getBaseUriBuilder().path(MessageResource.class)
				.path(MessageResource.class, "getCommentResource").path(CommentResource.class)
				.resolveTemplate("messageId", message.getId()).build();
		return uri.toString();
	}

	private String getUriForProfile(Message message, UriInfo uriInfo) {
		URI uri = uriInfo.getBaseUriBuilder().path(ProfileResource.class).path(message.getAuthor()).build();
		return uri.toString();
	}

	private String getUriForSelf(Message message, UriInfo uriInfo) {
		URI uri = uriInfo.getBaseUriBuilder().path(MessageResource.class).path(String.valueOf(message.getId())).build();
		return uri.toString();
	}

	@POST
	public Response addMessage(Message message, @Context UriInfo uriInfo) {
//		TO send additional information along with the value use Response class
		Message newMessage = messageService.addMessage(message);
		URI uri = uriInfo.getAbsolutePathBuilder().path(String.valueOf(newMessage.getId())).build();

		return Response.created(uri).entity(newMessage).build();
//		return messageService.addMessage(message);
	}

	@PUT
	@Path("/{messageId}")
	public Message updateMessage(@PathParam("messageId") long messageId, Message message) {
		message.setId(messageId);
		return messageService.updateMessage(message);
	}

	@DELETE
	@Path("/{messageId}")
	public void deleteMessage(@PathParam("messageId") long id) {
		messageService.removeMessage(id);
	}

	@Path("/{messageId}/comments")
	public CommentResource getCommentResource() {
		return new CommentResource();
	}
}