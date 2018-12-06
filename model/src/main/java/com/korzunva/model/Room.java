package com.korzunva.model;

import com.korzunva.validator.annotations.NotNull;
import com.korzunva.validator.annotations.Range;

import java.util.Objects;

/**
 * Class Room represents Room
 */
public class Room extends BaseEntity {

    @NotNull(message = "Floor mustn't be null")
    @Range(min = 1, max = Integer.MAX_VALUE, message = "Floor must be in rage 1 - " + Integer.MAX_VALUE)
    private Integer floor;

    @NotNull(message = "Places mustn't be null")
    @Range(min = 1, max = 10, message = "In room must be from 1 to 10 places")
    private Integer places;

    @NotNull(message = "Room cannot exist without Hotel")
    private Hotel hotel;

    private User user;

    public Room() {

    }

    public Room(String id) {
        this.id = id;
    }

    public Room(String id, Integer floor, Integer places, Hotel hotel, User user) {
        this.id = id;
        this.floor = floor;
        this.places = places;
        this.hotel = hotel;
        this.user = user;
    }

    public Integer getFloor() {
        return floor;
    }

    public void setFloor(Integer floor) {
        this.floor = floor;
    }

    public Integer getPlaces() {
        return places;
    }

    public void setPlaces(Integer places) {
        this.places = places;
    }

    public Hotel getHotel() {
        return hotel;
    }

    public void setHotel(Hotel hotel) {
        this.hotel = hotel;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Room room = (Room) o;
        return Objects.equals(id, room.id) &&
                Objects.equals(floor, room.floor) &&
                Objects.equals(places, room.places) &&
                Objects.equals(hotel, room.hotel);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, floor, places, hotel);
    }

}
