package com.rushional.sprung.learningspring.web;

import com.rushional.sprung.learningspring.business.domain.GuestDomain;
import com.rushional.sprung.learningspring.business.service.GuestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

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
    public String getGuests(
//            I have no idea if I need this
            Model model
    ) {
//        okay, once again - guestDomain is a stupid name, but idk what's the correct one.
//        And I don't care enough to find out.
        List<GuestDomain> guestDomains = guestService.getGuestDomains();
        model.addAttribute("guestDomains", guestDomains);
//        Tells thymeleaf to find a template called "guests". Cool!
        return "guests";
    }
}
