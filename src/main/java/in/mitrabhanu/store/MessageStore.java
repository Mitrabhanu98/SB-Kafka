package in.mitrabhanu.store;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.fasterxml.jackson.databind.ObjectMapper;

import in.mitrabhanu.entity.Employee;
import in.mitrabhanu.repo.EmployeeRepository;


@Component
public class MessageStore {

	private static final Logger logger = LoggerFactory.getLogger(MessageStore.class);
	
	@Autowired
	private EmployeeRepository repo;
	
	public void createMessage(String message) throws Exception {
		logger.info("Message received from consumer {}", message);
		
		ObjectMapper om = new ObjectMapper();
		Employee employee = om.readValue(message, Employee.class);
		
		repo.save(employee);
		logger.info("Employee is created with Id", employee.getId());
	}
	
	public List<Employee> getAllMessages() {
		return repo.findAll();
	}
	
}
