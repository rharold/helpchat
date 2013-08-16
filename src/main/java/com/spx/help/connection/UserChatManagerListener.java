package com.spx.help.connection;

import org.jivesoftware.smack.Chat;
import org.jivesoftware.smack.ChatManagerListener;
import org.jivesoftware.smack.MessageListener;

public class UserChatManagerListener implements ChatManagerListener {

	String user;
	MessageListener cometDMessageListener;
	
	public UserChatManagerListener(String user, CometDMessageListener cometDMessageListener) {
		this.user = user;
		this.cometDMessageListener = cometDMessageListener;
	}
	
	@Override
	public void chatCreated(Chat chat, boolean createdLocally)
    {
    	UserChats.addChat(user, chat);
    	
        if (!createdLocally) {
            chat.addMessageListener(cometDMessageListener);
        }
    }


}
