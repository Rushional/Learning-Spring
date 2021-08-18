package com.rushional.sprung.learningspring.web;

import com.rushional.sprung.learningspring.business.service.GuestService;
import com.rushional.sprung.learningspring.data.entity.Guest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/guests")
public class GuestWebController {
    private final GuestService guestService;

    @Autowired
    public GuestWebController(GuestService guestService) {
        this.guestService = guestService;
    }

    @GetMapping
    public String getGuests(Model model) {
        List<Guest> guests = guestService.getGuests();
        model.addAttribute("guestsList", guests);
//        Tells thymeleaf to find a template called "guests". Cool!
        return "guests";
    }
}
