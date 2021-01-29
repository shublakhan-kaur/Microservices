package com.shub.restApi.messenger.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import com.shub.restApi.messenger.database.DatabaseClass;
import com.shub.restApi.messenger.model.Comment;
import com.shub.restApi.messenger.model.ErrorMessage;
import com.shub.restApi.messenger.model.Message;

import jakarta.ws.rs.NotFoundException;
import jakarta.ws.rs.WebApplicationException;
import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.core.Response.Status;

public class CommentService {

	private Map<Long, Message> messageMap = DatabaseClass.getMessages();

	/*
	 * public CommentService() {
	 * 
	 * for (Message message : messageMap.values()) { Map<Long, Comment> commentMap =
	 * message.getComments(); commentMap.put(commentMap.size() + 1L, new
	 * Comment(commentMap.size() + 1, message.getMessage() + "Comment",
	 * message.getAuthor())); message.setComments(commentMap);
	 * messageMap.put(message.getId(), message); }
	 * 
	 * }
	 */

	public List<Comment> getAllComments(long messageId) {
		Map<Long, Comment> comments = messageMap.get(messageId).getComments();
		return new ArrayList<Comment>(comments.values());
	}

	public Comment getComments(long messageId, long commentId) {
		Message message = messageMap.get(messageId);
		ErrorMessage errorMessage = new ErrorMessage("Data not Found", 404, "documentation");
		Response response = Response.status(Status.NOT_FOUND).entity(errorMessage).build();
		if (message == null) {
			throw new WebApplicationException(response);
		}
		Map<Long, Comment> comments = message.getComments();
		Comment comment = comments.get(commentId);
		if (comment == null) {
			throw new NotFoundException(response);
		}
		return comment;
	}

	public Comment addComment(long messageId, Comment comment) {
		Map<Long, Comment> comments = messageMap.get(messageId).getComments();
		comment.setId(comments.size() + 1);
		comment.setCreated(new Date());
		comments.put(comment.getId(), comment);
		return comment;
	}

	public Comment updateComment(long messageId, Comment comment) {
		Map<Long, Comment> comments = messageMap.get(messageId).getComments();
		if (comment.getId() <= 0) {
			return null;
		}
		comments.put(comment.getId(), comment);
		return comment;
	}

	public Comment deleteComment(long messageId, long commentId) {
		Map<Long, Comment> comments = messageMap.get(messageId).getComments();
		return comments.remove(commentId);
	}
}