package com.spx.help.connection;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

import javax.inject.Inject;

import org.cometd.annotation.Service;
import org.cometd.annotation.Session;
import org.cometd.bayeux.server.BayeuxServer;
import org.cometd.bayeux.server.ConfigurableServerChannel;
import org.cometd.bayeux.server.LocalSession;
import org.cometd.bayeux.server.ServerChannel;
import org.jivesoftware.smack.Chat;
import org.jivesoftware.smack.MessageListener;
import org.jivesoftware.smack.RosterListener;
import org.jivesoftware.smack.packet.Message;
import org.jivesoftware.smack.packet.Presence;

@Service
public class CometDMessageListener implements MessageListener, RosterListener {

	@Inject
    private BayeuxServer bayeuxServer;
    @Session
    private LocalSession sender;
	
	@Override
	public void processMessage(Chat chat, Message message) {
		
		String channelName = "/chat/" + message.getTo();

        // Initialize the channel, making it persistent and lazy
        createIfAbsent(channelName);

        // Convert the Update business object to a CometD-friendly format
        Map<String, Object> data = new HashMap<String, Object>(4);
        data.put("message", message.getBody());
        data.put("participent", chat.getParticipant());        

        // Publish to all subscribers
        ServerChannel channel = bayeuxServer.getChannel(channelName);
        channel.publish(sender, data, null);
		
	}

	@Override
	public void entriesAdded(Collection<String> arg0) {
	}

	@Override
	public void entriesDeleted(Collection<String> arg0) {	
	}

	@Override
	public void entriesUpdated(Collection<String> arg0) {
	}

	@Override
	public void presenceChanged(Presence presence) {
		String channelName = "/roster";
		createIfAbsent(channelName);
		
		Map<String, Object> data = new HashMap<String, Object>(4);
        data.put("fromUser", presence.getFrom());
        data.put("status", presence.getStatus());        

        // Publish to all subscribers
        ServerChannel channel = bayeuxServer.getChannel(channelName);
        channel.publish(sender, data, null);
	}

	private void createIfAbsent(String channelName) {
		bayeuxServer.createIfAbsent(channelName, new ConfigurableServerChannel.Initializer()
        {
            public void configureChannel(ConfigurableServerChannel channel)
            {
                channel.setPersistent(true);
                channel.setLazy(true);
            }
        });
	}
	
}
