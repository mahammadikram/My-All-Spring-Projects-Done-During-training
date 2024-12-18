package in.karthik.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import in.karthik.entity.Employee;
import in.karthik.service.IEmployeeService;

@Controller
@RequestMapping("/employee")
public class EmployeeController {

	@Autowired
	private IEmployeeService service;
	
	//1. show Register page
	@GetMapping("/register")
	public String showReg() {
		return "EmployeeRegister";
	}
	
	//2. read form data
	@PostMapping("/save")
	public String save(
			@ModelAttribute Employee employee,
			Model model
			) 
	{
		Integer id = service.saveEmployee(employee);
		String message = "Employee '"+id+"' created";
		model.addAttribute("message", message);
		return "EmployeeRegister";
	}
	
	
	//3. display all rows
	@GetMapping("/all")
	public String getAll(
			@RequestParam(required = false) String message,
			Model model
			) 
	{
		List<Employee> list = service.getAllEmployees();
		model.addAttribute("list", list);
		model.addAttribute("message", message);
		return "EmployeeData";
	}
	
	
	//4. delete row by id and redirect to all
	@GetMapping("/delete")
	public String delete(
			@RequestParam Integer id,
			RedirectAttributes attributes
			) 
	{
		service.deleteEmployee(id);
		attributes.addAttribute("message", "Employee '"+id+"' deleted");
		return "redirect:all";
	}
	
	
	//5. show data in edit page
	@GetMapping("/edit")
	public String showEdit(
			@RequestParam Integer id,
			Model model
			) 
	{
		Employee emp = service.getOneEmployee(id);
		model.addAttribute("employee", emp);
		return "EmployeeEdit";
	}

	//6. on click update set to DB and redirect to all
	@PostMapping("/update")
	public String update(
			@ModelAttribute Employee employee,
			RedirectAttributes attributes
			) 
	{
		service.updateEmployee(employee);
		String message = "Employee '"+employee.getEmpId()+"' Updated";
		attributes.addAttribute("message", message);
		return "redirect:all";
	}
}
