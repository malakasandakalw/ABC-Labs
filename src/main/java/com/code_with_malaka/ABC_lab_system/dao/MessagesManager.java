package com.code_with_malaka.ABC_lab_system.dao;

import com.code_with_malaka.ABC_lab_system.models.Message;

public class MessagesManager {
	 public boolean sendMessage(Message message) {
	        System.out.println(message.getMessage());
	        return true;
	 }
}
