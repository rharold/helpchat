package com.spx.help.connection;

import java.util.Collection;
import java.util.List;

import org.jivesoftware.smack.Chat;
import org.jivesoftware.smack.MessageListener;
import org.jivesoftware.smack.RosterEntry;
import org.jivesoftware.smack.XMPPConnection;
import org.jivesoftware.smack.XMPPException;

public interface ChatHelper {

	public Chat initialiseChat(XMPPConnection connection, String userToChatTo, MessageListener messageListener);
	
	public Chat getChat(XMPPConnection connection, String chatThreadId);
	
	public void sendMessage(Chat chat, String message) throws XMPPException ;
	
	public void closeChat(XMPPConnection connection, String chatThreadId);
	
	public Collection<RosterEntry> getFriends(XMPPConnection connection);
	
	public List<RosterEntry> getAvailableFriends(XMPPConnection connection);
	
	public List<RosterEntry> getUnavailableFriends(XMPPConnection connection);
	
	public void setStatus(ChatStatus status, XMPPConnection connection);
	
	public ChatStatus getStatus(XMPPConnection connection);
}
