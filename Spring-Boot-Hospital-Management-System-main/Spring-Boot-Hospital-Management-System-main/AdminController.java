package com.HospitalManagement.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.HospitalManagement.Entity.Contact;
import com.HospitalManagement.Entity.DoctorAdd;
import com.HospitalManagement.Entity.PathologyAdd;
import com.HospitalManagement.Entity.PatientAppointment;
import com.HospitalManagement.Entity.TestAppointment;
import com.HospitalManagement.Repository.AdminRepository;
import com.HospitalManagement.Repository.TestAppointmentRepository;
import com.HospitalManagement.Service.DoctorAddService;
import com.HospitalManagement.Service.PathologyAddService;
import com.HospitalManagement.Service.PatientAppointmentService;
import com.HospitalManagement.Service.TestAppointmentService;

@Controller
public class AdminController {

	@Autowired
	private AdminRepository adminRe;
	
	@Autowired
	private DoctorAddService doctSer;
	
	@Autowired
	private PathologyAddService pathSer;
	
	@GetMapping("/adminlogin")
	public String adminLogin(){
		return "adminlogin";
	}
	
	@GetMapping("/adminhome")
	public String adminLoginHome(){
		return "adminhome";
	}
	
	//AddDoctorMapping
	@GetMapping("/adddoctor")
	public String addDoctor(Model model){
		model.addAttribute("doctoradd", new DoctorAdd());
		return "adddoctor";
	}
	
	@PostMapping("/viewdoctor")
	public String addDoctor(DoctorAdd doctoradd){
		doctSer.save(doctoradd);
		return "redirect:/viewdoctor";
	}
	
	@RequestMapping("/viewdoctor")
	public String viewDoctorPage(Model model) {
	    List<DoctorAdd> listdoc = doctSer.listAll();
	    model.addAttribute("listdoc", listdoc);     
	    return "viewdoctor";
	}
	
	@RequestMapping("/editdoctor/{id}")
	public ModelAndView showEditDoctorAdd(@PathVariable(name = "id") int id) {
	    ModelAndView mav = new ModelAndView("editdoctor");
	    DoctorAdd doctoradd = doctSer.get(id);
	    mav.addObject("DoctorAdd", doctoradd);    
	    return mav;
	}
	
	@RequestMapping("/deletedoctor/{id}")
	public String deleteProduct(@PathVariable(name = "id") int id) {
		doctSer.delete(id);
	    return "redirect:/viewdoctor";       
	}
	
	@RequestMapping("/doctorlogin")
	public String doctorLogin(){
		return "doctorlogin";
	}
	
	@RequestMapping("/doctorhome")
	public String doctorLoginHome(Model model){
		model.addAttribute("DoctorAdd", new DoctorAdd());
		return "doctorhome";
	}
	
	@Autowired
	private PatientAppointmentService patientSe;
	
	@RequestMapping("/appointmentList")
	public String viewAppointmentPage(Model model) {
	    List<PatientAppointment> listappointment = patientSe.listAll();
	    model.addAttribute("listappointment", listappointment);     
	    return "appointmentList";
	}
	
	//AddPathologyMapping
	@RequestMapping("/addpathology")
	public String addPathology(Model model){
		model.addAttribute("pathologyadd", new PathologyAdd());
		return "addpathology";
	}
	
	@PostMapping("/viewpathology")
	public String addPathology(PathologyAdd pathologyadd){
		pathSer.save(pathologyadd);
		return "redirect:/viewpathology";
	}
	
	@RequestMapping("/viewpathology")
	public String viewPathologyPage(Model model) {
	    List<PathologyAdd> listpath = pathSer.listAll();
	    model.addAttribute("listpath", listpath);     
	    return "viewpathology";
	}
	
	@RequestMapping("/editpathology/{id}")
	public ModelAndView showEditPathologyAdd(@PathVariable(name = "id") int id) {
	    ModelAndView mav = new ModelAndView("editpathology");
	    PathologyAdd pathologyadd = pathSer.get(id);
	    mav.addObject("PathologyAdd", pathologyadd);    
	    return mav;
	}
	
	@RequestMapping("/deletepathology/{id}")
	public String deletePathology(@PathVariable(name = "id") int id) {
		pathSer.delete(id);
	    return "redirect:/viewpathology";       
	}
	
	@RequestMapping("/pathologylogin")
	public String pathologyLogin(){
		return "pathologylogin";
	}
	
	@RequestMapping("/pathologyhome")
	public String pathologyLoginHome(Model model){
		model.addAttribute("PathologyAdd", new PathologyAdd());
		return "pathologyhome";
	}
	
	@Autowired
	private TestAppointmentService testSe;
	
	@RequestMapping("/testList")
	public String viewTestPage(Model model) {
	    List<TestAppointment> testapp = testSe.listSave();
	    model.addAttribute("testapp", testapp);     
	    return "testList";
	}
	
	//About
	@GetMapping("/about")
	public String patientAbout() {
		return "about";
	}
	
	//ContactMapping
	
	@RequestMapping("/contact")
	public String addContact(Model model){
		model.addAttribute("contact", new Contact());
		return "contact";
	}
	
	@RequestMapping("/home")
	public String addContactSave(Contact contact){
		adminRe.save(contact);
		return "redirect:/home";
	}
	
	
}
