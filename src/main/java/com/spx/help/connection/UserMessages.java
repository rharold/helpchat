package com.spx.help.connection;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;

public class UserMessages {

    private static Map<String, LinkedList<String>> userMessages = new HashMap<String, LinkedList<String>>();
	
    public static void addMessage(String threadId, String message) {
		LinkedList<String> messages = userMessages.get(threadId);
		if(messages == null) {
			messages = new LinkedList<String>();
			userMessages.put(threadId, messages);
		}
		
		messages.add(message);
	}
	
	public static String getMessage(String threadId) {
		LinkedList<String> messages = userMessages.get(threadId);
		if(messages == null) {
			return null;
		}
		
		if(messages.isEmpty()) {
			return null;
		}
		
		return messages.removeFirst();
	}
	
}
