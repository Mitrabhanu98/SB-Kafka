package in.mitrabhanu.rest;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.databind.ObjectMapper;

import in.mitrabhanu.entity.Employee;
import in.mitrabhanu.service.ProducerService;
import in.mitrabhanu.store.MessageStore;

@RestController
@RequestMapping("/employee")
public class EmployeeRestController {

	@Autowired
	private ProducerService producerService;
	
	@Autowired
	private MessageStore store; 
	
	@GetMapping("/send")
	public String createEmployee(
			@RequestParam String name,
			@RequestParam String dept,
			@RequestParam(
					value = "sal", 
			        required = false, 
			        defaultValue = "25000.0") Double sal) throws Exception 
	{
	    Employee employee = new Employee();
	    employee.setName(name);
	    employee.setDept(dept);
	    employee.setSal(sal);
	    
	    ObjectMapper om = new ObjectMapper();
	    String message = om.writeValueAsString(employee);
	    
	    producerService.sendMessage(message);
		return "SUCCESS";
	}
	
	@GetMapping("/all")
	public List<Employee> fetchAllMessage() {
		
		List<Employee> messages = store.getAllMessages();
		
		return messages;
	}
}
