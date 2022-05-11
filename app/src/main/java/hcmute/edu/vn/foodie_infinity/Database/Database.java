package hcmute.edu.vn.foodie_infinity.Database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.database.sqlite.SQLiteStatement;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.Log;

import androidx.annotation.Nullable;

import java.io.ByteArrayOutputStream;
import java.util.ArrayList;
import java.util.List;

import hcmute.edu.vn.foodie_infinity.Model.Belong;
import hcmute.edu.vn.foodie_infinity.Model.Bill;
import hcmute.edu.vn.foodie_infinity.Model.Cart;
import hcmute.edu.vn.foodie_infinity.Model.Food;
import hcmute.edu.vn.foodie_infinity.Model.LineItem;
import hcmute.edu.vn.foodie_infinity.Model.Restaurant;
import hcmute.edu.vn.foodie_infinity.Model.User;
import hcmute.edu.vn.foodie_infinity.Model.Voucher;
import hcmute.edu.vn.foodie_infinity.R;

public class Database extends SQLiteOpenHelper {

    public Database(@Nullable Context context, @Nullable String name, @Nullable SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {

    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }

    public void QueryData(String sql){
        SQLiteDatabase database = getWritableDatabase();
        database.execSQL(sql);
    }


    public Cursor GetData(String sql){
        SQLiteDatabase database = getReadableDatabase();
        return database.rawQuery(sql, null) ;
    }

    public void InsertRestaurant(Restaurant restaurant)
    {
        SQLiteDatabase database = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("name", restaurant.getName());
        contentValues.put("address", restaurant.getAddress());
        contentValues.put("avatar", restaurant.getAvatar());
        contentValues.put("price", restaurant.getPrice());
        contentValues.put("time", restaurant.getTime());
        contentValues.put("type", restaurant.getType());
        database.insert("Restaurant",null, contentValues);
    }
    public Cursor getFoods() {
        return GetData("SELECT * FROM Restaurant");
    }
    public Cursor getRestaurant() {
        return GetData("SELECT * FROM Food");
    }


    public Restaurant FindRestaurantById(int id)
    {
        SQLiteDatabase database = this.getReadableDatabase();
        String sql = "Select * FROM Restaurant WHERE id  = '"+id+"' " ;
        Cursor cursor =  GetData(sql);
        Restaurant  restaurant = null;
        while (cursor.moveToNext())
        {
            String name = cursor.getString(1);
            String address = cursor.getString(2);
            String avatar = cursor.getString(3);
            String price = cursor.getString(4);
            String time = cursor.getString(5);
            String type = cursor.getString(6);
            restaurant = new Restaurant(id, name,address, avatar , price, time, type);
        }

        Log.e("NameRESTAURANT", "NameRES"+ restaurant.getName());
        return restaurant;
    }

    public Food FindFoodById(int id)
    {
        String sql = "Select * FROM Food WHERE id = '"+id+"'";
        Cursor cursor =  GetData(sql);
        Food  food = null;
        while (cursor.moveToNext())
        {
            String name = cursor.getString(1);
            Double price = cursor.getDouble(2);
           String avatar = cursor.getString(3);


            food = new Food(id, name, price, avatar);
        }
        return food;
    }

    public void InsertFood(Food food)
    {
        SQLiteDatabase database = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();

        contentValues.put("name", food.getName());
        contentValues.put("price", food.getPrice());
        contentValues.put("avatar", food.getAvatar());
        database.insert("Food",null, contentValues);

    }

    public void InsertBelong(Belong belong)
    {
        SQLiteDatabase database = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();

        contentValues.put("rid", belong.getRid());
        contentValues.put("fid", belong.getFid());
        database.insert("Belong",null, contentValues);
    }

    public void InsertLineItem(LineItem lineItem)
    {
        SQLiteDatabase database = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("idUser", lineItem.getIdUser());
        contentValues.put("foodId", lineItem.getFoodId());
        contentValues.put("amount", lineItem.getAmount());
        database.insert("LineItem",null, contentValues);
    }


    public Boolean checkIfFoodExist(int id)
    {
        SQLiteDatabase database = this.getReadableDatabase();
        String sql = "Select * FROM LineItem WHERE foodId = '"+id+"' AND idUser = '"+User.id+"'";
        Cursor cursor =  GetData(sql);

        Food  food = null;
        boolean exist = false;
        while (cursor.moveToNext())
        {
            exist = true;
        }
        return exist;
    }

//    public List<LineItem> getListLineItemFromCartByUserID(int UserID)
//    {
//        List<LineItem> lineItemList = new ArrayList<>();
//        String sql1 = "Select * FROM Cart WHERE idUser = '"+UserID+"'";
//        Cursor cursor1 =  GetData(sql1);
//        int LineItemId=-1;
//        List<Integer> listInt = new ArrayList<>();
//        while (cursor1.moveToNext())
//        {
//            int id = cursor1.getInt(0);
//            LineItemId = cursor1.getInt(1);
//            listInt.add(LineItemId);
//        }
//
//        for(int i = 0 ; i < listInt.size(); i++)
//        {
//            String sql2 = "Select * FROM LineItem WHERE foodId = '"+listInt.get(i)+"'";
//            Cursor cursor2 =  GetData(sql2);
//            while (cursor2.moveToNext())
//            {
//                int foodId = cursor2.getInt(0);
//                int amount = cursor2.getInt(1);
//                LineItem lineItem = new LineItem( foodId, amount);
//                lineItemList.add(lineItem);
//            }
//        }
//
//
//        return lineItemList;
//    }

    public void UpdateAmountLineItem(int foodId, boolean operation)
    {
        String sql1 = "Select * FROM LineItem WHERE foodId = '"+foodId+"'";
        Cursor cursor2 =  GetData(sql1);
        int currentAmount = 0;
        while (cursor2.moveToNext())
        {
            currentAmount  = cursor2.getInt(2);
            Log.e("currentAmount", Integer.toString(currentAmount));
        }
        int newAmount = 0;
        if(operation==true)
        {
            Log.e("content", "if");

            newAmount = currentAmount +1;

            Log.e("content", Integer.toString(currentAmount));

        }
        else {
            Log.e("content", "else");
            newAmount = currentAmount -1;
            Log.e("content", Integer.toString(currentAmount));

        }



        if(newAmount>0)
        {
            UpdateAmountLineItem(foodId,newAmount);
            //QueryData("UPDATE LineItem SET amount = '"+currentAmount +"' WHERE foodId = '" + foodId + "'");
       }
        else {

            Log.e("IdUserWhenDelete", Integer.toString(User.id));
            QueryData("DELETE FROM LineItem WHERE foodId = '"+foodId+"' AND idUser = '"+User.id+"'");
        }
    }
    public void UpdateAmountLineItem(int idFood, int amount)
    {
//        SQLiteDatabase database = this.getWritableDatabase();
//
//        ContentValues data=new ContentValues();
//        data.put("amount",amount);
//        database.update("LineItem", data, "foodId=" + idFood, null);

       QueryData("UPDATE LineItem SET amount = '"+amount+"' WHERE foodId = '" + idFood + "'");
    }

    public boolean checkIfExist(String userName)
    {
        String sql = "Select * FROM User WHERE userName = '"+userName+"'";
        Cursor cursor =  GetData(sql);
        boolean exist = false;
        while (cursor.moveToNext())
        {
            exist = true;
        }
        return exist;
    }

    public void InsertUser(User user)
    {
        SQLiteDatabase database = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();

        contentValues.put("userName", user.getUserName());
        contentValues.put("password", user.getPassword());
        database.insert("User",null, contentValues);
    }
    public User GetUserFromUserName(String userName)
    {
        String sql = "Select * FROM User WHERE userName = '"+userName+"'";
        Cursor cursor =  GetData(sql);
        User user = null;
        while (cursor.moveToNext())
        {
            int id = cursor.getInt(0);
            String password = cursor.getString(2);


             user = new User(userName, password);
            user.id = id;
        }
        return user;
    }

    public boolean ValidateUser(String userName, String password)
    {
        SQLiteDatabase database = this.getReadableDatabase();
        String sql = "Select * FROM User WHERE userName = '"+userName+"' AND password = '"+password+"'";
        Cursor cursor =  GetData(sql);
        boolean have = false;
        while (cursor.moveToNext())
        {
            have = true;
        }
        return have;
    }

    public List<LineItem> getLineItemByUserId(int Userid)
    {
        SQLiteDatabase database = this.getReadableDatabase();
        String sql = "Select * FROM LineItem WHERE idUser = '"+Userid+"'";
        Cursor cursor =  GetData(sql);
        List<LineItem>  lineItemList = new ArrayList<>();

        while (cursor.moveToNext())
        {
            LineItem lineItem = null;
            int fooId = cursor.getInt(1);
            int amount = cursor.getInt(2);
            lineItem = new LineItem(Userid, fooId, amount, 1);
            lineItemList.add(lineItem);
        }
        return lineItemList;
    }

    public void deleteLineItemByUserId(int idUser)
    {
        QueryData("DELETE FROM LineItem WHERE idUser = '"+idUser+"' ");
    }
    public void deleteVoucherById(int id)
    {
        QueryData("DELETE FROM Voucher WHERE id = '"+id+"' ");
    }
    public List<Bill> getBillByUserId(int Userid)
    {
        SQLiteDatabase database = this.getReadableDatabase();
        String sql = "Select * FROM Bill WHERE idUser = '"+Userid+"'";
        Cursor cursor =  GetData(sql);
        List<Bill>  lineBill = new ArrayList<>();

        while (cursor.moveToNext())
        {
            LineItem lineItem = null;
            int id = cursor.getInt(0);

            String foodName = cursor.getString(2);
            int amount = cursor.getInt(3);
            String voucher = cursor.getString(4);
            String time = cursor.getString(5);

            Bill bill = new Bill(User.id, foodName, amount, voucher, time);
            lineBill.add(bill);
        }
        return lineBill;
    }

    public void insertBill(Bill bill) {
        SQLiteDatabase database = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();

        contentValues.put("idUser", User.id);
        contentValues.put("foodName", bill.getFoodName());
        contentValues.put("amount", bill.getAmount());
        contentValues.put("voucher", bill.getVoucher());
        contentValues.put("time", bill.getTime());

        database.insert("Bill", null, contentValues);
    }

    public List<LineItem> getLineItemByTimeInBill(String time)
    {
        Log.e("Time",time);
        String sql = "Select * FROM Bill WHERE time = '"+time+"'";
        Cursor cursor =  GetData(sql);
        List<LineItem> lineItemList = new ArrayList<>();
        while (cursor.moveToNext())
        {
            // int id = cursor.getInt(0);
            int idUser = cursor.getInt(1);
            String foodName = cursor.getString(2);
            int foodId = FindFoodIDByName(foodName);
            int amount = cursor.getInt(3);
           LineItem lineItem = new LineItem(idUser, foodId, amount, 1);

           //LineItem lineItem = new LineItem(1, 2,1,1);
            lineItemList.add(lineItem);
        }

        return lineItemList;
    }

    public String getVoucherByTimeInBill(String time)
    {
        String sql = "Select * FROM Bill WHERE time = '"+time+"'";
        Cursor cursor =  GetData(sql);
        String voucher="";
        while (cursor.moveToNext()){

            voucher = cursor.getString(4);

        }

        return voucher;
    }



    public void insertVoucher(Voucher voucher)
    {
        SQLiteDatabase database = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();

        contentValues.put("name", voucher.getName());
        contentValues.put("price", voucher.getPrice());
        contentValues.put("type", voucher.getType());


        database.insert("Voucher",null, contentValues);
    }

    public Food FindFoodByName(String name)
    {
        SQLiteDatabase database = this.getReadableDatabase();
        Log.e("Foodname" , name);
        String sql = "Select * FROM Food WHERE name = '"+name+"'";
        Cursor cursor =  GetData(sql);
        Food  food = null;
        while (cursor.moveToNext())
        {

            Double price = cursor.getDouble(2);
            String avatar = cursor.getString(3);


            food = new Food(name, price, avatar);
        }
        return food;
    }
    public int FindFoodIDByName(String name)
    {
        SQLiteDatabase database = this.getReadableDatabase();
        Log.e("Foodname" , name);
        String sql = "Select * FROM Food WHERE name = '"+name+"'";
        Cursor cursor =  GetData(sql);
        int id=9999;
        while (cursor.moveToNext())
        {

            id  = cursor.getInt(0);



        }
        return id;
    }
}
