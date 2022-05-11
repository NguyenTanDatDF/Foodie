package hcmute.edu.vn.foodie_infinity.Model;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

import hcmute.edu.vn.foodie_infinity.R;

public class Food {
    private int id;
    private String name;
    private double price;
    private String avatar;
    int idimg;


    public Food(int id, String name, double price, String avatar) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.avatar = avatar;
        this.idimg = idimg;
    }

    public Food(String name, double price, String avatar) {
        this.name = name;
        this.price = price;
        this.avatar = avatar;
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

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    public int getIdimg() {
        return idimg;
    }

    public void setIdimg(int idimg) {
        this.idimg = idimg;
    }
}
