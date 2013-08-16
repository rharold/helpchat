package com.spx.help.connection;

import org.jivesoftware.smack.Chat;
import org.jivesoftware.smack.MessageListener;
import org.jivesoftware.smack.packet.Message;

public class SystemOutMessageListener implements MessageListener {
	
	@Override
	public void processMessage(Chat chat, Message message) {
		UserMessages.addMessage(chat.getThreadID(), message.getBody());
	}

}
