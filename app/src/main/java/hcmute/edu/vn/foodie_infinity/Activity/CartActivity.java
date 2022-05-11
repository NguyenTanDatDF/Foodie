package hcmute.edu.vn.foodie_infinity.Activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;


import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

import hcmute.edu.vn.foodie_infinity.Adapter.LineItemAdapter;
import hcmute.edu.vn.foodie_infinity.Database.Database;
import hcmute.edu.vn.foodie_infinity.Model.Bill;
import hcmute.edu.vn.foodie_infinity.Model.DialogHandler;
import hcmute.edu.vn.foodie_infinity.Model.Food;
import hcmute.edu.vn.foodie_infinity.Model.LineItem;
import hcmute.edu.vn.foodie_infinity.Model.User;
import hcmute.edu.vn.foodie_infinity.Model.Voucher;
import hcmute.edu.vn.foodie_infinity.R;
public class CartActivity extends AppCompatActivity {
    public static RecyclerView rcvUser2;
    public static LineItemAdapter foodAdapter2;
    public static TextView tv_Subtotal_m;
    public static TextView tv_Total_m;
    public static List<LineItem> deletedLineItem;
    Database database;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cart);
        Database database = new Database(CartActivity.this, "Foodie.sqlite", null, 1);

        ImageView image_back =(ImageView) findViewById(R.id.back_img);
        ImageView img_add = (ImageView) findViewById(R.id.minus_cart) ;
        ImageView img_minus = (ImageView) findViewById(R.id.add_cart) ;
        Button btn_Checkout = (Button) findViewById(R.id.btn_Checkout);

        Bill.voucher= "k";

        tv_Subtotal_m = (TextView) findViewById(R.id.tv_Subtotal_m) ;
        tv_Total_m = (TextView) findViewById(R.id.tv_Total_m) ;
        ImageView voucher = (ImageView) findViewById(R.id.voucher);

        rcvUser2 = findViewById(R.id.rcv_cart);
        foodAdapter2= new LineItemAdapter((CartActivity.this));
        LinearLayoutManager linearLayoutManager2 = new LinearLayoutManager(CartActivity.this, RecyclerView.VERTICAL,false);
        rcvUser2.setLayoutManager(linearLayoutManager2);


        foodAdapter2.setData(GetListCart());
        rcvUser2.setAdapter(foodAdapter2);
        database = new Database(this, "Foodie.sqlite", null, 1);

        List<LineItem> lineItemList = database.getLineItemByUserId(User.id);
        List<Food> foodList = new ArrayList<>();
        for(int i = 0; i < lineItemList.size(); i++)
        {
            foodList.add(database.FindFoodById(lineItemList.get(i).getFoodId()));
        }
//        Double subTotal = Double.parseDouble( CartActivity.tv_Subtotal_m.getText().toString()) ;
//        Double total = Double.parseDouble( CartActivity.tv_Total_m.getText().toString()) ;
        int subTotal =0;
        int total =0;

        for (int i = 0; i < lineItemList.size(); i++)
        {

            subTotal += (int) (foodList.get(i).getPrice()* lineItemList.get(i).getAmount());

        }
        int SHIP_FEE = 0;


        tv_Subtotal_m.setText(Double.toString(subTotal) );
        tv_Total_m.setText(Double.toString(subTotal));


        image_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });


        btn_Checkout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Database database = new Database(CartActivity.this, "Foodie.sqlite", null, 1);

                List<Bill> listBill = new ArrayList<>();
               List<LineItem> lineItems = new ArrayList<>();
               lineItems = database.getLineItemByUserId(User.id);
                SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
                Date date = new Date();
                System.out.println(formatter.format(date));
                for(int i = 0 ; i < lineItems.size(); i++)
               {
                  database.insertBill(new Bill(User.id, database.FindFoodById(lineItems.get(i).getFoodId()).getName(), lineItems.get(i).getAmount(), Bill.voucher, formatter.format(date)));
               }
                deletedLineItem = database.getLineItemByUserId(User.id);

                database.deleteLineItemByUserId(User.id);
                foodAdapter2.setData(GetListCart());
                rcvUser2.setAdapter(foodAdapter2);
                Toast.makeText(CartActivity.this, "You have ordered this foods", Toast.LENGTH_SHORT).show();

                Intent intent = new Intent(CartActivity.this, checkout_activity.class);
                CartActivity.this.startActivity(intent);
            }
        });

        voucher.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                List<Voucher> vouchers = new ArrayList<>();
                Voucher voucher1 = new Voucher("Ăn nhiều giảm nhiều", 30, "p");
                Voucher voucher2 = new Voucher("Khao Giá 30k", 30, "d");
                Voucher voucher3 = new Voucher("Ăn đông giảm nhiều", 50, "p");
                Voucher voucher4= new Voucher("Khuyến mãi ngày mới", 20, "d");


                vouchers.add(voucher1);
                vouchers.add(voucher2);
                vouchers.add(voucher3);
                vouchers.add(voucher4);



                DialogHandler.createSingleItemDialog(CartActivity.this, vouchers,
                        "RecyclerView Spinner", s -> {

                    Toast.makeText(CartActivity.this, "You used a voucher", Toast.LENGTH_SHORT).show();



                        });


            }
        });



    }
    public List<LineItem> GetListCart(){
        database = new Database(this, "Foodie.sqlite", null, 1);

        return database.getLineItemByUserId(User.id);
    }
    private List<Food> getListFood() {
        List<Food> list = new ArrayList<>();
        Database database = new Database(this, "Foodie.sqlite", null, 1);
        Cursor cursor = database.GetData("Select * From Food");
        while (cursor.moveToNext())
        {
            int id = cursor.getInt(0);
            String name = cursor.getString(1);
            Double price = cursor.getDouble(2);
            String avatar = cursor.getString(3);
            list.add(new Food(id,name,price,avatar));
        }
        return list;
    }

}