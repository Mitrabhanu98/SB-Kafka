package in.mitrabhanu.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
public class ProducerService {

	@Autowired
	private KafkaTemplate<String, String> template;
	
	@Value("${my.kafka-tpc-name}")
	private String topic;
	
	public void sendMessage(String message) {
		
		template.send(topic, message);
	}
}
