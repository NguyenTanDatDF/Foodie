package hcmute.edu.vn.foodie_infinity.Fragment;

import android.content.Intent;
import android.database.Cursor;
import android.database.CursorWindow;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager2.widget.ViewPager2;

import android.os.Handler;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;


import java.io.ByteArrayOutputStream;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;
import java.util.Timer;

import hcmute.edu.vn.foodie_infinity.Activity.CartActivity;
import hcmute.edu.vn.foodie_infinity.Activity.MainActivity;
import hcmute.edu.vn.foodie_infinity.Adapter.FoodAdapter;
import hcmute.edu.vn.foodie_infinity.Adapter.LineItemAdapter;
import hcmute.edu.vn.foodie_infinity.Adapter.RestaurantAdapter;
import hcmute.edu.vn.foodie_infinity.Adapter.SliderAdapter;
import hcmute.edu.vn.foodie_infinity.Database.Database;
import hcmute.edu.vn.foodie_infinity.Model.Food;
import hcmute.edu.vn.foodie_infinity.Model.Restaurant;
import hcmute.edu.vn.foodie_infinity.Model.Slider;
import hcmute.edu.vn.foodie_infinity.R;
import me.relex.circleindicator.CircleIndicator3;

public class HomeFragment extends Fragment {
    private int currentPosition = 0;
    Timer timer;

    RecyclerView rcvUser1;
    private RestaurantAdapter userAdapter1;

    RecyclerView rcvUser2;
    private FoodAdapter foodAdapter2;

    public static ViewPager2 viewPager2;
    public static CircleIndicator3 circleIndicator3;
    private Handler sliderHandler = new Handler();
    private Runnable runnable = null;
    Database database;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);



    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        database = new Database(getActivity(), "Foodie.sqlite", null, 1);

        try {
            Field field = CursorWindow.class.getDeclaredField("sCursorWindowSize");
            field.setAccessible(true);
            field.set(null, 100 * 1024 * 1024); //the 100MB is the new size
        } catch (Exception e) {
            e.printStackTrace();
        }
        View view = inflater.inflate(R.layout.fragment_home, container, false);
        rcvUser1 = view.findViewById(R.id.rcv_restaurant);
        userAdapter1 = new RestaurantAdapter((getActivity()));
        LinearLayoutManager linearLayoutManager1 = new LinearLayoutManager(getActivity(), RecyclerView.HORIZONTAL,false);
        rcvUser1.setLayoutManager(linearLayoutManager1);
        userAdapter1.setData(getListRestaurant());
        rcvUser1.setAdapter(userAdapter1);



        rcvUser2 = view.findViewById(R.id.rcv_food);
        foodAdapter2= new FoodAdapter((getActivity()));
        LinearLayoutManager linearLayoutManager2 = new LinearLayoutManager(getActivity(), RecyclerView.VERTICAL,false);
        rcvUser2.setLayoutManager(linearLayoutManager2);
        foodAdapter2.setData(getListFood());
        rcvUser2.setAdapter(foodAdapter2);



        viewPager2 = view.findViewById(R.id.view_page_2);
        circleIndicator3 = view.findViewById(R.id.circle_indicator_3);
        SliderAdapter sliderAdapter = new SliderAdapter( getActivity(), getListSlider());
        viewPager2.setAdapter(sliderAdapter);
        circleIndicator3.setViewPager(viewPager2);

        startAutoSlider(sliderAdapter.getItemCount());

        ImageView imageView = view.findViewById(R.id.ImgCart);

        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity(), CartActivity.class);
                getActivity().startActivity(intent);
            }
        });



        return view;
    }

    private void startAutoSlider(final int count) {

        runnable = new Runnable() {
            @Override
            public void run() {
                int pos = viewPager2.getCurrentItem();
                pos = pos + 1;
                if (pos >= count) pos = 0;
                viewPager2.setCurrentItem(pos);
                sliderHandler.postDelayed(runnable, 2000);
            }
        };
        sliderHandler.postDelayed(runnable, 3000);
    }

    @Override
    public void onDestroy() {
        if (runnable != null) sliderHandler.removeCallbacks(runnable);
        super.onDestroy();
    }

    private List<Restaurant> getListRestaurant() {
        List<Restaurant> list = new ArrayList<>();
//        Database database = new Database(getActivity(), "Foodie.sqlite", null, 1);
//        Cursor cursor = database.GetData("Select * From Restaurant");
      Database  database = new Database(getActivity(), "Foodie.sqlite", null, 1);

        Cursor cursor = database.getFoods();
        while (cursor.moveToNext())
        {

            int id = cursor.getInt(0);
            String name = cursor.getString(1);
            String address = cursor.getString(2);
            Log.e("nameRestaurant", name);
            String avatar = cursor.getString(3);
            String price = cursor.getString(4);
            String time = cursor.getString(5);
            String type = cursor.getString(6);
            list.add(new Restaurant(id,name,address,avatar,price,time,type));
        }
        return list;
    }


    private List<Food> getListFood() {
        List<Food> list = new ArrayList<>();
        Cursor cursor  = database.getRestaurant();
        while (cursor.moveToNext())
        {
            int id = cursor.getInt(0);
            String name = cursor.getString(1);
            Double price = cursor.getDouble(2);
            Log.e("NameFOod", name);

            String avatar = cursor.getString(3);
            list.add(new Food( id,name,price,avatar));
        }

        return list;
    }

    private List<Slider> getListSlider()
    {
        List<Slider> sliders = new ArrayList<>();
        sliders.add(new Slider(R.drawable.banner1 ));
        sliders.add(new Slider(R.drawable.banner2 ));
        sliders.add(new Slider(R.drawable.banner3 ));
        sliders.add(new Slider(R.drawable.banner4 ));
        sliders.add(new Slider(R.drawable.banner5 ));
        return sliders;
    }
    public byte[] toByteArray(int id){
        Bitmap bitmap = BitmapFactory.decodeResource(getResources(), id);
        ByteArrayOutputStream byteArray = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.PNG, 50, byteArray);
        byte[] img = byteArray.toByteArray();
        return img;
    }
}