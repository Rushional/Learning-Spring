package com.rushional.sprung.learningspring.business.service;

import com.rushional.sprung.learningspring.business.domain.GuestDomain;
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

//    I have no idea what I'm doing. I made a domain-level object, then
//    simply called it GuestDomain as I had no clue what to call it otherwise.
//    So I'm using some weird GuestDomain class here. It should have been a DTO or something, I think.
//    But I'm learning how to make a web page right now, and all this Thymeleaf+Spring stuff is more important,
//    So I'm letting this one slide - I'll learn faster this way. One new thing at a time.
//    So don't look at me like this! I had my reasons!
//    TODO: someday I might even fix this mess! I'm planning to! Maybe even very soon!
    @Autowired
    public GuestService(GuestRepository guestRepository) {
        this.guestRepository = guestRepository;
    }

    public List<GuestDomain> getGuestDomains() {
//        This isn't optimal, but we're learning spring and not java core
//        Again, these are not domains, these are domain level objects
//        But the thing is, after I watch the solution to the challenge I will probably fix it
//        So I don't bother with naming at all.
        Iterable<Guest> guestDomains = guestRepository.findAll();
        List<GuestDomain> guestDomainsArray = new ArrayList<>();
        guestDomains.forEach(guest -> {
            GuestDomain guestDomain = new GuestDomain();
            guestDomain.setGuestId(guest.getGuestId());
            guestDomain.setFirstName(guest.getFirstName());
            guestDomain.setLastName(guest.getLastName());
            guestDomain.setEmail(guest.getEmailAddress());
            guestDomain.setPhoneNumber(guest.getPhoneNumber());
            guestDomainsArray.add(guestDomain);
        });
//        why does Idea put 2 tabs in this case? Can't google it. Idk if it's how it should be. Meh.
        return guestDomainsArray
                .stream()
                .sorted(((guestDomain1, guestDomain2) -> guestDomain1.getLastName().compareToIgnoreCase(guestDomain2.getLastName())))
                .collect(Collectors.toList());
//        guestDomainsArray.sort(new Comparator<GuestDomain>() {
//            @Override
//            public int compare(GuestDomain o1, GuestDomain o2) {
//                if (o1.getLastName() == o2.getLastName()){
//                    return o1.getRoomNumber().compareTo(o2.getRoomNumber());
//                }
//                return o1.getLastName().compareTo(o2.getLastName());
//            }
//        });
    }
}
