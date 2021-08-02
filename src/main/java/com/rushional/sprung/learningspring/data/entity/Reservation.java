package com.rushional.sprung.learningspring.data.entity;

import javax.persistence.*;
import java.sql.Date;

@Entity
@Table(name="RESERVATION")
public class Reservation {
    @Id
    @Column(name="RESERVATION_ID")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long reservationId;

//    Idk why, but the course's author just used plain @Column and long. Have I made a mistake?
    @ManyToOne
    @JoinColumn(name = "ROOM_ID", nullable = false)
    private long roomId;

    @ManyToOne
    @JoinColumn(name = "GUEST_ID", nullable = false)
    private long guestId;

    @Column(name = "RES_DATE", nullable = false)
    private Date reservationDate;

    public long getReservationId() {
        return reservationId;
    }

    public void setReservationId(long reservationId) {
        this.reservationId = reservationId;
    }

    public long getRoomId() {
        return roomId;
    }

    public void setRoomId(long roomId) {
        this.roomId = roomId;
    }

    public long getGuestId() {
        return guestId;
    }

    public void setGuestId(long guestId) {
        this.guestId = guestId;
    }

    public Date getReservationDate() {
        return reservationDate;
    }

    public void setReservationDate(Date resDate) {
        this.reservationDate = resDate;
    }
}
