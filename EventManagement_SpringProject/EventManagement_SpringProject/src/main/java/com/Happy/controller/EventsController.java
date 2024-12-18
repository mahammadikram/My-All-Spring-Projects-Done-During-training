package com.Happy.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.Happy.entity.EventsEntity;
import com.Happy.entity.Form;
import com.Happy.serviceInterface.EventServiceInterface;

import jakarta.servlet.http.HttpSession;

@Controller
@RequestMapping("/Events")
public class EventsController {

    @Autowired
    private EventServiceInterface service;

    @GetMapping("BookingPage")
    public String shakthi(@RequestParam String eventCategory, Model model, HttpSession session) {
        String page = "";

        EventsEntity enity2 = service.loadEventData(eventCategory);
        model.addAttribute("EventData", enity2);
        if (enity2 != null) {
            model.addAttribute("uname", session.getAttribute("uname"));
            model.addAttribute("umail", session.getAttribute("umail"));
            model.addAttribute("uphone", session.getAttribute("uphone"));
            page = "EventData";
        } else {
            model.addAttribute("message", eventCategory + " Under Development");
            page = "Home";
        }
        return page;
    }

    // User booking page
    @GetMapping("/booking")
    public String booking(HttpSession session, Model model, @RequestParam(required = false) String message) {
        if (message != null) {
            model.addAttribute("message", message);
        }
        model.addAttribute("uname", session.getAttribute("uname"));
        model.addAttribute("umail", session.getAttribute("umail"));
        model.addAttribute("uphone", session.getAttribute("uphone"));
        List<Form> list = service.getUserEvents((String) session.getAttribute("umail"));

        model.addAttribute("list", list);
        return "UserBookings";
    }

    // User Booking Canceling
    @GetMapping("deleteUserBooking")
    public String cancelEvent(HttpSession session, Model model, @RequestParam Integer id) {
        service.deleteUserBooking(id);
        return "redirect:/Events/booking?message= Event of id: " + id + " Cancelled successfully";

    }
    

}
