package com.spx.help.connection;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.jivesoftware.smack.Chat;

public class UserChats {

	private static Map<String, List<Chat>> userChats = new HashMap<String, List<Chat>>();
	
	public static void addChat(String user, Chat chat) {
		List<Chat> chats = userChats.get(user);
		if(chats == null) {
			chats = new ArrayList<Chat>();
			userChats.put(user, chats);
		}
		
		chats.add(chat);
	}
	
	public static List<Chat> getChats(String user) {
		return userChats.get(user);
	}
	
	public static Chat getChat(String me, String participent) {
		List<Chat> chats = userChats.get(me);
		if(chats == null) {
			return null;
		}
		
		if(chats.isEmpty()) {
			return null;
		}
		
		for (Chat chat : chats) {
			if(chat.getParticipant().equalsIgnoreCase(participent)) {
				return chat;
			}
		}
		
		return null;
	}
	
	public static void removeUser(String user) {
		userChats.remove(user);
	}
}
