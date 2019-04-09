package aravinda.atlaslabs.hotel_booking.model;

import com.squareup.moshi.Json;

/**
 * Created by aravinda_r on 10/25/2018.
 */

public class Hotel {
    @Json(name = "Hotel")
    private String hotel;
    @Json(name = "Longitude")
    private double longitude;
    @Json(name = "Amount")
    private int amount;
    @Json(name = "Contact")
    private long contact;
    @Json(name = "Location")
    private String location;
    @Json(name = "Latitude")
    private double latitude;
    @Json(name = "Email")
    private String email;

    public String getHotel() {
        return hotel;
    }

    public void setHotel(String hotel) {
        this.hotel = hotel;
    }

    public double getLongitude() {
        return longitude;
    }

    public void setLongitude(double longitude) {
        this.longitude = longitude;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    public long getContact() {
        return contact;
    }

    public void setContact(long contact) {
        this.contact = contact;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public double getLatitude() {
        return latitude;
    }

    public void setLatitude(double latitude) {
        this.latitude = latitude;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

}
