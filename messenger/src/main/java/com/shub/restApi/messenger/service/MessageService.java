package com.shub.restApi.messenger.service;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Map;

import com.shub.restApi.messenger.database.DatabaseClass;
import com.shub.restApi.messenger.exception.DataNotFoundException;
import com.shub.restApi.messenger.model.Message;

public class MessageService {

	private Map<Long, Message> messageMap = DatabaseClass.getMessages();

	public MessageService() {
		messageMap.put(1L, new Message(1, "Hello World!", "Shub"));
		messageMap.put(2L, new Message(2, "Hello Jersey!", "Shub"));
	}

	public List<Message> getAllMessagesByYear(int year) {
		List<Message> messageForYear = new ArrayList<Message>();
		Calendar cal = Calendar.getInstance();
		for (Message message : messageMap.values()) {
			cal.setTime(message.getCreated());
			if (cal.get(Calendar.YEAR) == year) {
				messageForYear.add(message);
			}
		}
		return messageForYear;
	}

	public List<Message> getAllMessagesPaginated(int start, int size) {
		ArrayList<Message> list = new ArrayList<Message>(messageMap.values());
		if (start + size > list.size()) {
			return new ArrayList<Message>();
		}
		return list.subList(start, start + size);
	}

	public List<Message> getAllMessages() {
		return new ArrayList<Message>(messageMap.values());
	}

	public Message getMessage(long id) {
		Message message = messageMap.get(id);
		if(message == null)
		{
			throw new DataNotFoundException("Message with id "+id +" not found");
		}
		return message;
	}

	public Message addMessage(Message message) {
		message.setId(messageMap.size() + 1);
		message.setCreated(new Date());
		messageMap.put(message.getId(), message);
		return message;
	}

	public Message updateMessage(Message message) {
		if (message.getId() <= 0) {
			return null;
		}
		messageMap.put(message.getId(), message);
		return message;
	}

	public Message removeMessage(long id) {
		return messageMap.remove(id);
	}
}
