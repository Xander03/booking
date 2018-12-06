package com.korzunva.model;

import com.korzunva.validator.annotations.Length;
import com.korzunva.validator.annotations.NotNull;

import java.util.List;
import java.util.Objects;

/**
 * Class Hotel represents Hotel
 */
public class Hotel extends BaseEntity {

    @Length(length = 2, message = "Name must contain at least 2 symbols")
    private String name;

    @Length(length = 2, message = "Address must contain at least 2 symbols")
    private String address;

    private List<Room> rooms;

    public Hotel() {

    }

    public Hotel(String id) {
        this.id = id;
    }

    public Hotel(String id, String name, String address) {
        this.id = id;
        this.name = name;
        this.address = address;
    }

    public Hotel(String id, String name, String address, List<Room> rooms) {
        this.id = id;
        this.name = name;
        this.address = address;
        this.rooms = rooms;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public List<Room> getRooms() {
        return rooms;
    }

    public void setRooms(List<Room> rooms) {
        this.rooms = rooms;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Hotel hotel = (Hotel) o;
        return Objects.equals(id, hotel.id) &&
                Objects.equals(name, hotel.name) &&
                Objects.equals(address, hotel.address) &&
                Objects.equals(rooms, hotel.rooms);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, address, rooms);
    }

}
