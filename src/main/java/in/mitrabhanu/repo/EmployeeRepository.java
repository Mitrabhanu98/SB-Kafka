package in.mitrabhanu.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import in.mitrabhanu.entity.Employee;

public interface EmployeeRepository extends JpaRepository<Employee, Integer> {

}
