package com.code_with_malaka.ABC_lab_system.services;

import com.code_with_malaka.ABC_lab_system.dao.MessagesManager;
import com.code_with_malaka.ABC_lab_system.models.Message;

public class MessageService {
	private static MessageService messageServiceObj;
	
	private MessageService() {
		
	}
	
	public static synchronized MessageService getMessagerServiceInstance() {
		
		if(messageServiceObj == null) {
			messageServiceObj = new MessageService();
		}
		
		return messageServiceObj;
	}
	
	private MessagesManager getMessagesManager() {
		return new MessagesManager();
	}

    public boolean sendMessage(Message message) throws ClassNotFoundException {
		return getMessagesManager().sendMessage(message);
	}
}
