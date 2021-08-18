package com.rushional.sprung.learningspring.business.service;

import com.rushional.sprung.learningspring.data.entity.Guest;
import com.rushional.sprung.learningspring.data.repository.GuestRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class GuestService {
    private final GuestRepository guestRepository;

    //    Annotation isn't necessary, but helps if I were to add another constructor,
    //    and also IDE can understand me better this way
    @Autowired
    public GuestService(GuestRepository guestRepository) {
        this.guestRepository = guestRepository;
    }

    public List<Guest> getGuests() {
        Iterable<Guest> guests = guestRepository.findAll();
        List<Guest> guestsList = new ArrayList<>();
        guests.forEach(guestsList::add);
//        why does Idea put 2 tabs in this case? Can't google it. Idk if it's how it should be. Meh.
        return guestsList
                .stream()
                .sorted((guest1, guest2) ->
                        guest1.getLastName().compareToIgnoreCase(guest2.getLastName()))
                .collect(Collectors.toList());
    }
}
