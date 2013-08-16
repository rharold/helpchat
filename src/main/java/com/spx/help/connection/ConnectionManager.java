package com.spx.help.connection;

import java.util.HashMap;
import java.util.Map;

import org.jivesoftware.smack.ConnectionConfiguration;
import org.jivesoftware.smack.SASLAuthentication;
import org.jivesoftware.smack.XMPPConnection;
import org.jivesoftware.smack.XMPPException;

public class ConnectionManager {

	private static ConnectionManager theInstance = new ConnectionManager();
	
	private Map<String, XMPPConnection> managedConnections = 
			new HashMap<String, XMPPConnection>();
	
	
	private ConnectionManager() {
		
	}
	
	public static ConnectionManager getInstance() {
		return theInstance;
	}
	
	public XMPPConnection login(String username, String password, CometDMessageListener cometDMessageListener) throws XMPPException  {
		XMPPConnection connection = managedConnections.get(username);
		if(connection == null) {
			ConnectionConfiguration config = new ConnectionConfiguration("127.0.0.1", 5222, "dskso-rharold");
			
			SASLAuthentication.supportSASLMechanism("PLAIN", 0);
			
			connection = new XMPPConnection(config);
			connection.getRoster().addRosterListener(cometDMessageListener);
			connection.connect();
			connection.login(username, password);
			managedConnections.put(connection.getUser(), connection);
		}
		
		connection.getChatManager().addChatListener(
				new UserChatManagerListener(connection.getUser(), cometDMessageListener));
		
		
		
		return connection;
	}
	
	public XMPPConnection getLoggedInConnection(String username) throws Exception {
		XMPPConnection connection = managedConnections.get(username);
		if(connection == null) {
			throw new NotLoggedInException();
		}
		
		return connection;
	}
	
	public void logout(String user) throws NotLoggedInException {
		XMPPConnection connection = managedConnections.get(user);
		if(connection == null) {
			throw new NotLoggedInException();
		}
		
		connection.disconnect();
		managedConnections.remove(user);
	}
	
}
