package com.spx.help.connection;

import java.util.ArrayList;
import java.util.List;

public enum ChatStatus {
	ONLINE("online"), 
	OFFLINE("offline");
	
	String status;
	
	ChatStatus(String status) {
		this.status = status;
	}
	
	public String getStatus() {
		return status;
	}
	
	public static List<String> getStatuss() {
		List<String> statuss = new ArrayList<String>();
		
		for (ChatStatus status : ChatStatus.values()) {
			statuss.add(status.getStatus());
		}
		
		return statuss;
	}
	
	public static ChatStatus fromString(String status) {
		if(ONLINE.status.equals(status)) {
			return ONLINE;
		}
		
		return OFFLINE;
	}
}
