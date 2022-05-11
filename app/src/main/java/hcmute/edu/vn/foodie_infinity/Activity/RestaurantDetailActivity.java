package hcmute.edu.vn.foodie_infinity.Activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.database.Cursor;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;
import hcmute.edu.vn.foodie_infinity.Adapter.FoodAdapter;
import hcmute.edu.vn.foodie_infinity.Database.Database;
import hcmute.edu.vn.foodie_infinity.Model.Belong;
import hcmute.edu.vn.foodie_infinity.Model.Food;
import hcmute.edu.vn.foodie_infinity.Model.Restaurant;
import hcmute.edu.vn.foodie_infinity.R;

public class RestaurantDetailActivity extends AppCompatActivity {
    RecyclerView rcvUser2;
    FoodAdapter foodAdapter2;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_restaurant_detail);

        Bundle bundle = getIntent().getExtras();
        if(bundle==null)
        {
            return;
        }
        Database database = new Database(this, "Foodie.sqlite", null, 1);
        Integer restaurantId =  bundle.getInt("restaurantId");

        Log.e("RestaurantID",Integer.toString(restaurantId) );
        Restaurant restaurant =  database.FindRestaurantById(restaurantId);


        CircleImageView img = findViewById(R.id.imageView2) ;
        TextView name = findViewById(R.id.name_ac);
        TextView address = findViewById(R.id.address_ac);
        TextView price = findViewById(R.id.price_ac);
        TextView time = findViewById(R.id.time_ac);

        int id = RestaurantDetailActivity.this.getResources().getIdentifier(restaurant.getAvatar(),"drawable", RestaurantDetailActivity.this.getPackageName());

        img.setImageResource(id);
        name.setText(restaurant.getName());
        address.setText(restaurant.getAddress());
        price.setText(restaurant.getPrice());
        time.setText(restaurant.getTime());

        List<Belong> belongList = getListBelong();
        List<Food> newFoodList = new ArrayList<>();
        List<Food> allFoodlist = getListFood();


        for(int i = 0 ; i< belongList.size(); i++)
        {
            if(belongList.get(i).getRid()==restaurant.getId())
            {
                for(int j = 0 ; j < allFoodlist.size(); j++)
                {
                    if(allFoodlist.get(j).getId()==belongList.get(i).getFid())
                    {
                        newFoodList.add(allFoodlist.get(j));
                    }
                }
            }
        }

        rcvUser2 = findViewById(R.id.rcv_foodnrestaurant);
        foodAdapter2= new FoodAdapter((this));
        LinearLayoutManager linearLayoutManager2 = new LinearLayoutManager(this, RecyclerView.VERTICAL,false);
        rcvUser2.setLayoutManager(linearLayoutManager2);
        foodAdapter2.setData(newFoodList);
        rcvUser2.setAdapter(foodAdapter2);

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

    private List<Belong> getListBelong() {
        List<Belong> list = new ArrayList<>();
//        Database database = new Database(this, "Foodie.sqlite", null, 1);
//        Cursor cursor = database.GetData("Select * From Belong");
//
//
//
//        while (cursor.moveToNext())
//        {
//            int rid = cursor.getInt(0);
//            int fid = cursor.getInt(1);
//            list.add(new Belong(rid,fid));
//        }

        list.add(new Belong(1,1)
        );
        list.add(new Belong(1,2)
        );
        list.add(new Belong(1,3)
        );
        list.add(new Belong(1,4)
        );
        list.add(new Belong(1,5)
        );
        list.add(new Belong(1,6)
        );
        list.add(new Belong(1,7)
        );
        list.add(new Belong(1,8)
        );
        list.add(new Belong(1,9)
        );
        list.add(new Belong(1,10)
        );

        list.add(new Belong(2,11)
        );
        list.add(new Belong(2,12)
        );
        list.add(new Belong(2,1)
        );
        list.add(new Belong(2,3)
        );
        list.add(new Belong(2,4)
        );
        list.add(new Belong(2,13)
        );
        list.add(new Belong(2,14)
        );
        list.add(new Belong(2,15)
        );
        list.add(new Belong(2,16)
        );
        list.add(new Belong(2,17)
        );

        list.add(new Belong(3,18)
        );
        list.add(new Belong(3,19)
        );
        list.add(new Belong(3,20)
        );
        list.add(new Belong(3,3)
        );
        list.add(new Belong(3,4)
        );
        list.add(new Belong(3,6)
        );
        list.add(new Belong(3,7)
        );
        list.add(new Belong(3,8)
        );
        list.add(new Belong(3,12)
        );
        list.add(new Belong(3,9)
        );

        list.add(new Belong(4,5)
        );
        list.add(new Belong(4,6)
        );
        list.add(new Belong(4,7)
        );
        list.add(new Belong(4,8)
        );
        list.add(new Belong(4,9)
        );
        list.add(new Belong(4,10)
        );
        list.add(new Belong(4,16)
        );
        list.add(new Belong(4,15)
        );
        list.add(new Belong(4,14)
        );
        list.add(new Belong(4,13)
        );

        list.add(new Belong(5,4)
        );
        list.add(new Belong(5,15)
        );
        list.add(new Belong(5,16)
        );
        list.add(new Belong(5,20)
        );
        list.add(new Belong(5,19)
        );
        list.add(new Belong(5,18)
        );
        list.add(new Belong(5,17)
        );
        list.add(new Belong(5,16)
        );
        list.add(new Belong(5,15)
        );
        list.add(new Belong(5,14)
        );

        list.add(new Belong(6,13)
        );
        list.add(new Belong(6,12)
        );
        list.add(new Belong(6,11)
        );
        list.add(new Belong(6,10)
        );
        list.add(new Belong(6,1)
        );
        list.add(new Belong(6,2)
        );
        list.add(new Belong(6,3)
        );
        list.add(new Belong(6,6)
        );
        list.add(new Belong(6,9)
        );
        list.add(new Belong(6,8)
        );

        list.add(new Belong(7,31)
        );
        list.add(new Belong(7,32)
        );
        list.add(new Belong(7,33)
        );
        list.add(new Belong(7,34)
        );
        list.add(new Belong(7,35)
        );
        list.add(new Belong(7,36)
        );
        list.add(new Belong(7,37)
        );
        list.add(new Belong(7,38)
        );
        list.add(new Belong(7,39)
        );
        list.add(new Belong(7,40)
        );

        list.add(new Belong(8,35)
        );
        list.add(new Belong(8,41)
        );
        list.add(new Belong(8,42)
        );
        list.add(new Belong(8,43)
        );
        list.add(new Belong(8,44)
        );
        list.add(new Belong(8,45)
        );
        list.add(new Belong(8,36)
        );
        list.add(new Belong(8,38)
        );
        list.add(new Belong(8,39)
        );
        list.add(new Belong(8,40)
        );

        list.add(new Belong(9,32)
        );
        list.add(new Belong(9,40)
        );
        list.add(new Belong(9,39)
        );
        list.add(new Belong(9,38)
        );
        list.add(new Belong(9,37)
        );
        list.add(new Belong(9,36)
        );
        list.add(new Belong(9,35)
        );
        list.add(new Belong(9,34)
        );
        list.add(new Belong(9,32)
        );
        list.add(new Belong(9,31)
        );

        list.add(new Belong(10,34)
        );
        list.add(new Belong(10,35)
        );
        list.add(new Belong(10,36)
        );
        list.add(new Belong(10,37)
        );
        list.add(new Belong(10,38)
        );
        list.add(new Belong(10,39)
        );
        list.add(new Belong(10,40)
        );
        list.add(new Belong(10,42)
        );
        list.add(new Belong(10,41)
        );
        list.add(new Belong(10,43)
        );

        list.add(new Belong(11,41)
        );
        list.add(new Belong(11,43)
        );
        list.add(new Belong(11,31)
        );
        list.add(new Belong(11,32)
        );
        list.add(new Belong(11,33)
        );
        list.add(new Belong(11,34)
        );
        list.add(new Belong(11,35)
        );
        list.add(new Belong(11,36)
        );
        list.add(new Belong(11,37)
        );
        list.add(new Belong(11,38)
        );

        list.add(new Belong(12,21)
        );
        list.add(new Belong(12,22)
        );
        list.add(new Belong(12,23)
        );
        list.add(new Belong(12,24)
        );
        list.add(new Belong(12,25)
        );
        list.add(new Belong(12,26)
        );
        list.add(new Belong(12,27)
        );
        list.add(new Belong(12,28)
        );
        list.add(new Belong(12,29)
        );
        list.add(new Belong(12,30)
        );

        list.add(new Belong(13,30)
        );
        list.add(new Belong(13,30)
        );
        list.add(new Belong(13,28)
        );
        list.add(new Belong(13,27)
        );
        list.add(new Belong(13,26)
        );
        list.add(new Belong(13,25)
        );
        list.add(new Belong(13,24)
        );
        list.add(new Belong(13,23)
        );
        list.add(new Belong(13,22));
        list.add(new Belong(13,21)
        );

        list.add(new Belong(14,26)
        );
        list.add(new Belong(14,27)
        );
        list.add(new Belong(14,28)
        );
        list.add(new Belong(14,29));
        list.add(new Belong(14,30)
        );
        list.add(new Belong(14,21)
        );
        list.add(new Belong(14,22)
        );
        list.add(new Belong(14,23)
        );
        list.add(new Belong(14,24)
        );
        list.add(new Belong(14,25)
        );

        list.add(new Belong(15,30)
        );
        list.add(new Belong(15,29)
        );
        list.add(new Belong(15,28)
        );
        list.add(new Belong(15,27)
        );
        list.add(new Belong(15,26)
        );
        list.add(new Belong(15,25)
        );
        list.add(new Belong(15,21)
        );
        list.add(new Belong(15,22)
        );
        list.add(new Belong(15,23)
        );
        list.add(new Belong(15,24)
        );



        return list;
    }
}