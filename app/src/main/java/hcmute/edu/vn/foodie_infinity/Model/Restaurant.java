package hcmute.edu.vn.foodie_infinity.Model;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

import java.io.Serializable;

import hcmute.edu.vn.foodie_infinity.R;

public class Restaurant implements Serializable {
    private int id;
    private String name;
    private String address;
    private String avatar;
    private String price;
    private String time;

    public Restaurant(int id, String name, String address, String avatar, String price, String time, String type) {
        this.id = id;
        this.name = name;
        this.address = address;
        this.avatar = avatar;
        this.price = price;
        this.type = type;
        this.time = time;
    }


    public void setType(String type) {
        this.type = type;
    }

    private String type;






    public Restaurant(String name, String address, String avatar, String price, String time, String type) {
        this.name = name;
        this.address = address;
        this.avatar = avatar;
        this.price = price;
        this.type = type;
        this.time = time;
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getType() {
        return type;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }


}
