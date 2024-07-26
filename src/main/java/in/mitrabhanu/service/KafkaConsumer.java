package in.mitrabhanu.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

import in.mitrabhanu.store.MessageStore;

@Service
public class KafkaConsumer {

	@Autowired
	private MessageStore store; 
	
	@KafkaListener(topics = "${my.kafka-tpc-name}", groupId = "group-id")
	public void receiveMessage(String message) throws Exception {
		
		store.createMessage(message);
	}
}
