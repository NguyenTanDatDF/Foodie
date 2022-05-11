package hcmute.edu.vn.foodie_infinity.Model;

public class Cart {
    int LineItemId;
    int UserId;

    public Cart(int userId, int lineItemId) {
        LineItemId = lineItemId;
        UserId = userId;
    }

    public int getLineItemId() {
        return LineItemId;
    }

    public void setLineItemId(int lineItemId) {
        LineItemId = lineItemId;
    }

    public int getUserId() {
        return UserId;
    }

    public void setUserId(int userId) {
        UserId = userId;
    }
}
