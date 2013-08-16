package com.spx.help.connection;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.jivesoftware.smack.Chat;
import org.jivesoftware.smack.ChatManager;
import org.jivesoftware.smack.MessageListener;
import org.jivesoftware.smack.RosterEntry;
import org.jivesoftware.smack.XMPPConnection;
import org.jivesoftware.smack.XMPPException;
import org.jivesoftware.smack.packet.Presence;
import org.springframework.stereotype.Service;

@Service
public class ChatHelperImpl implements ChatHelper {

	
	@Override
	public Chat initialiseChat(XMPPConnection connection, String userToChatTo, MessageListener messageListener) {
		ChatManager chatManager = connection.getChatManager();
		Chat chat = chatManager.createChat(userToChatTo, messageListener);
		UserChats.addChat(connection.getUser(), chat);
		
		return chat;
	}

	@Override
	public Chat getChat(XMPPConnection connection, String chatThreadId) {
		ChatManager chatManager = connection.getChatManager();
		Chat chat = chatManager.getThreadChat(chatThreadId);
		
		return chat;
	}

	@Override
	public void closeChat(XMPPConnection connection, String chatThreadId) {
		// TODO Auto-generated method stub		
	}

	@Override
	public void sendMessage(Chat chat, String message) throws XMPPException {
		chat.sendMessage(message);		
	}

	@Override
	public Collection<RosterEntry> getFriends(XMPPConnection connection) {
		return connection.getRoster().getEntries();
	}

		@Override
	public List<RosterEntry> getAvailableFriends(XMPPConnection connection) {
		List<RosterEntry> availableFriends = new ArrayList<RosterEntry>();
			
		for(RosterEntry friend : connection.getRoster().getEntries()) {
			if(this.getPresence(connection, friend).getType() == Presence.Type.available) {
				availableFriends.add(friend);	
			}			
		}
		return availableFriends;
	}

	@Override
	public List<RosterEntry> getUnavailableFriends(XMPPConnection connection) {
		List<RosterEntry> unavailableFriends = new ArrayList<RosterEntry>();
		
		for(RosterEntry friend : connection.getRoster().getEntries()) {
			System.out.println(friend.getStatus());
			if(this.getPresence(connection, friend).getType() == Presence.Type.unavailable) {
				unavailableFriends.add(friend);	
				
			}			
		}
		return unavailableFriends;
	}

	@Override
	public void setStatus(ChatStatus status, XMPPConnection connection) {
		Presence presence;
		
		if(status == ChatStatus.ONLINE) {
			presence = new Presence(Presence.Type.available);
		} else {
			presence = new Presence(Presence.Type.unavailable);
		}
		
		connection.sendPacket(presence);		
	}

	@Override
	public ChatStatus getStatus(XMPPConnection connection) {
		Presence presence = this.getPresence(connection, connection.getUser());
		if(presence.getType() == Presence.Type.available) {
			return ChatStatus.ONLINE;
		} else {
			return ChatStatus.OFFLINE;
		}
	}

	private Presence getPresence(XMPPConnection connection, RosterEntry rosterEntry) {
		return connection.getRoster().getPresence(rosterEntry.getUser());		
	}
	
	private Presence getPresence(XMPPConnection connection, String user) {
		return connection.getRoster().getPresence(user);		
	}
	
}
