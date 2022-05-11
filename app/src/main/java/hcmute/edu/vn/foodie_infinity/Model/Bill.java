package hcmute.edu.vn.foodie_infinity.Model;

public class Bill {
    int id;
    int idUser;
    String foodName;
    int amount;
    public static String voucher ;
    String time;


    public Bill(int idUser, String foodName, int amount, String voucher, String time) {
        this.idUser = idUser;
        this.foodName = foodName;
        this.amount = amount;
        this.voucher = voucher;
        this.time = time;

    }



    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getIdUser() {
        return idUser;
    }

    public void setIdUser(int idUser) {
        this.idUser = idUser;
    }

    public String getFoodName() {
        return foodName;
    }

    public void setFoodName(String foodName) {
        this.foodName = foodName;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    public String getVoucher() {
        return voucher;
    }

    public void setVoucher(String voucher) {
        this.voucher = voucher;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }
}
