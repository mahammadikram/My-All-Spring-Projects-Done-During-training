package in.karthik.service;

import java.util.List;

import in.karthik.entity.Employee;

public interface IEmployeeService {

	Integer saveEmployee(Employee emp);
	
	List<Employee> getAllEmployees();
	
	Employee getOneEmployee(Integer id);
	
	void deleteEmployee(Integer id);
	
	void updateEmployee(Employee emp);
}
