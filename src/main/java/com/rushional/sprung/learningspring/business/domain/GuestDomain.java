package com.rushional.sprung.learningspring.business.domain;

// I have exactly zero understanding of what I'm doing right now.
// This is probably really dumb, but I'm just repeating the RoomReservation way
// TODO: fix this class, what the heck is this and why am I doing it?
//  I probably need to have a DTO instead.
public class GuestDomain {
    private long guestId;
    private String firstName;
    private String lastName;
    private String email;
    private String phoneNumber;

    public long getGuestId() {
        return guestId;
    }

    public void setGuestId(long guestId) {
        this.guestId = guestId;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

}
