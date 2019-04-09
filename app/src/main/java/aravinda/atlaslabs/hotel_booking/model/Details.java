package aravinda.atlaslabs.hotel_booking.model;

import com.squareup.moshi.Json;

/**
 * Created by aravinda_r on 10/26/2018.
 */

public class Details {


    private String name;
    private String longitude;
    private String latitude;
    private String contact;
    private String email;
    private String location;
    private String amount;

    public Details() {
    }

    public Details(String name, String longitude, String latitude, String contact, String email, String location, String amount) {
        this.name = name;
        this.longitude = longitude;
        this.latitude = latitude;
        this.contact = contact;
        this.email = email;
        this.location = location;
        this.amount = amount;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLongitude() {
        return longitude;
    }

    public void setLongitude(String longitude) {
        this.longitude = longitude;
    }

    public String getLatitude() {
        return latitude;
    }

    public void setLatitude(String latitude) {
        this.latitude = latitude;
    }

    public String getContact() {
        return contact;
    }

    public void setContact(String contact) {
        this.contact = contact;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getAmount() {
        return amount;
    }

    public void setAmount(String amount) {
        this.amount = amount;
    }
}
