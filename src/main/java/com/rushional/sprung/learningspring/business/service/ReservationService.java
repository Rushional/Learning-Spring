package com.rushional.sprung.learningspring.business.service;

import com.rushional.sprung.learningspring.business.domain.RoomReservation;
import com.rushional.sprung.learningspring.data.entity.Guest;
import com.rushional.sprung.learningspring.data.entity.Reservation;
import com.rushional.sprung.learningspring.data.entity.Room;
import com.rushional.sprung.learningspring.data.repository.GuestRepository;
import com.rushional.sprung.learningspring.data.repository.ReservationRepository;
import com.rushional.sprung.learningspring.data.repository.RoomRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

// why is this util and not sql? I'm just following orders, but are they wrong?
// It is probably done in order to avoid linking sql to my service logic
import java.util.*;

@Service
public class ReservationService {
    private final RoomRepository roomRepository;
    private final GuestRepository guestRepository;
    private final ReservationRepository reservationRepository;

//    Not necessary, but helps if I were to add another constructor, and also IDE can understand me better this way
    @Autowired
    public ReservationService(RoomRepository roomRepository, GuestRepository guestRepository, ReservationRepository reservationRepository) {
        this.roomRepository = roomRepository;
        this.guestRepository = guestRepository;
        this.reservationRepository = reservationRepository;
    }

    public List<RoomReservation> getRoomReservationsForDate(Date date) {
//        This isn't optimal, but we're learning spring and not java core
        Iterable<Room> rooms = roomRepository.findAll();
        Map<Long, RoomReservation> roomReservationMap = new HashMap();
        rooms.forEach(room -> {
            RoomReservation roomReservation = new RoomReservation();
            roomReservation.setRoomId(room.getRoomId());
            roomReservation.setRoomName(room.getRoomName());
            roomReservation.setRoomNumber(room.getRoomNumber());
            roomReservationMap.put(room.getRoomId(), roomReservation);
        });
        Iterable<Reservation> reservations = reservationRepository.findReservationByReservationDate(
                new java.sql.Date(date.getTime())
        );
        reservations.forEach(reservation -> {
            RoomReservation roomReservation = roomReservationMap.get(reservation.getRoomId());
            roomReservation.setDate(date);
            Guest guest = this.guestRepository.findById(reservation.getGuestId()).get();
            roomReservation.setFirstName(guest.getFirstName());
            roomReservation.setLastName(guest.getLastName());
            roomReservation.setGuestId(guest.getGuestId());
        });
        List<RoomReservation> roomReservations = new ArrayList<>();
        for (long id: roomReservationMap.keySet()) {
            roomReservations.add(roomReservationMap.get(id));
        }
        roomReservations.sort(new Comparator<RoomReservation>() {
            @Override
            public int compare(RoomReservation o1, RoomReservation o2) {
                if (o1.getRoomName() == o2.getRoomName()){
                    return o1.getRoomNumber().compareTo(o2.getRoomNumber());
                }
                return o1.getRoomName().compareTo(o2.getRoomName());
            }
        });
        return roomReservations;
    }
}
