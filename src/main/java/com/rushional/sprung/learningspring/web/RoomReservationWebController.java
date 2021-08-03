package com.rushional.sprung.learningspring.web;

import com.rushional.sprung.learningspring.business.domain.RoomReservation;
import com.rushional.sprung.learningspring.business.service.ReservationService;
import com.rushional.sprung.learningspring.data.repository.RoomRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Date;
import java.util.List;

@Controller
@RequestMapping("/reservations")
public class RoomReservationWebController {
    private final ReservationService reservationService;

    @Autowired
    public RoomReservationWebController(ReservationService reservationService) {
        this.reservationService = reservationService;
    }

    @GetMapping
    public String getReservations(
            @RequestParam(value="date", required = false) String dateString,
            Model model
            ) {
        Date date = DateUtils.createDateFromDateString(dateString);
        List<RoomReservation> roomReservations = reservationService.getRoomReservationsForDate(date);
        model.addAttribute("roomReservations", roomReservations);
//        Tells thymeleaf to find a template called "reservations". Sounds cool.
        return "reservations";
    }
}
