package hcmute.edu.vn.foodie_infinity.Model;

public class LineItem {
    int idUser;
    int foodId;
    int amount;

    public LineItem(int idUser , int foodId, int amount, int i) {
        this.idUser = idUser;
        this.foodId = foodId;
        this.amount = amount;
    }


    public int getIdUser() {
        return idUser;
    }

    public void setIdUser(int idUser) {
        this.idUser = idUser;
    }

    public int getFoodId() {
        return foodId;
    }

    public void setFoodId(int foodId) {
        this.foodId = foodId;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }
}
