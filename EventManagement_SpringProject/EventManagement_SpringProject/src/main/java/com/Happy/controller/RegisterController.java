package com.Happy.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.Happy.entity.Form;
import com.Happy.entity.RegisterEntity;
import com.Happy.serviceInterface.RegisterService;

import jakarta.servlet.http.HttpSession;

@Controller
@RequestMapping("/EventManagement")
public class RegisterController {

    @Autowired
    private RegisterService service;

    @GetMapping("app")
    public String loadIndex() {
        return "index";
    }

    @GetMapping("signuppage")
    public String openSignupPage() {
        return "Signup";
    }

    @GetMapping("loginpage")
    public String openLoginPage() {
        return "login";
    }

    @GetMapping("forgot")
    public String openForgotPage() {
        return "forgot";
    }

    @GetMapping("Home")
    public String openHome(HttpSession session, Model model) {
        model.addAttribute("uname", session.getAttribute("uname"));
        model.addAttribute("umail", session.getAttribute("umail"));
        model.addAttribute("uphone", session.getAttribute("uphone"));

        return "Home";
    }

    // Registration Controller
    @PostMapping("userRegister")
    public String userRegister(@ModelAttribute RegisterEntity user, Model model) {
        boolean exist = service.checkUser(user.getUserEmail());
        String page = "";
        if (exist == false) {
            Integer uid = service.saveUser(user);
            String uname = user.getUserName();

            if (uid > 0) {
                model.addAttribute("message", uname + " Registered Succesfully with id :" + uid);
                page = "login";
            } else {
                model.addAttribute("message", "Register UnSuccesfull");
                page = "Signup";
            }
        } else {

            model.addAttribute("message", "Registration UnSuccesfull");
            page = "Signup";

        }
        return page;
    }

    // Login Controller
    @PostMapping("userLogin")
    public String userLogin(RegisterEntity entity, HttpSession session, Model model) {
        String page = "";
        String status = service.loginUser(entity.getUserEmail(), entity.getUserPassword(), session);

        if (status.equals("success")) {

            model.addAttribute("uname", session.getAttribute("uname"));
            model.addAttribute("umail", session.getAttribute("umail"));
            model.addAttribute("uphone", session.getAttribute("uphone"));

            if (entity.getUserEmail().equals("admin@gmail.com")
                    && (entity.getUserPassword().equals("88888888"))) {
                System.out.println("Admin login Succesfull..");
                page = "Admin";
            } else {

                System.out.println("User login sucessful..");
                page = "Home";
            }

        }

        else {
            model.addAttribute("message", "Login failed");
            System.out.println("Login failed..");
            page = "login";
        }
        return page;
    }

    // Logout Controller
    @GetMapping("logout")
    public String logout(HttpSession session) {
        session.invalidate();
        System.out.println("Logged out...");
        return "login";
    }

    // forgot controller
    @PostMapping("forgotPass")
    public String forgotPass(RegisterEntity entity, Model model) {
        String page = "";
        System.out.println(entity.getUserEmail());
        String result = service.forgotPassword(entity.getUserEmail(), entity.getUserPassword());
        if (result.equals("success")) {
            model.addAttribute("message", "Password changed succesfully");
            page = "login";
        } else {
            model.addAttribute(("message"), "No such user email");
            page = "forgot";
        }
        return page;
    }
    
    // for Email check with ajax
    @GetMapping("/checkEmail")
    public ResponseEntity<Boolean> checkCode(@RequestParam String useremail) {
        boolean isCodeAvailable = service.isCodeAvailable(useremail);
        return ResponseEntity.ok(isCodeAvailable);
    }

    // admin

    @GetMapping("/users")
    public String data(Model model, @RequestParam(required = false) String message) {
        List<RegisterEntity> list = service.data();
        if (message != null) {
            model.addAttribute("message", message);
        }
        model.addAttribute("list", list);
        return "User";

    }

    //admin
    @GetMapping("deleteUserBooking")
    public String cancelEvent(HttpSession session, Model model, @RequestParam Integer id) {
        service.deleteUserBookingByAdmin(id);
        return "redirect:/EventManagement/allBookings?message= Event of id: " + id + " Cancelled successfully";

    }

    // admin
    @GetMapping("allBookings")
    public String bookingsForAdminPage(Model model , @RequestParam(required = false) String message) {
        
        String page = "";
        if (message != null) {
            model.addAttribute("message", message);
        }
        List<Form> list = service.getAllBookings();
        model.addAttribute("list", list);
        page = "Booking";
        return page;
    }

    // admin
    @GetMapping("/delete")
    public String delete(@RequestParam Integer id) {
        service.deleteUser(id);
        return "redirect:/EventManagement/users?message=User " + id + " Deleted successfully";
    }

    
    

}