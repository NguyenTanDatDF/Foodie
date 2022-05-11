package hcmute.edu.vn.foodie_infinity.Fragment;

import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;

import androidx.appcompat.widget.SearchView;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import java.io.ByteArrayOutputStream;
import java.util.ArrayList;
import java.util.List;

import hcmute.edu.vn.foodie_infinity.Activity.MainActivity;
import hcmute.edu.vn.foodie_infinity.Adapter.RestaurantNavigationAdapter;
import hcmute.edu.vn.foodie_infinity.Database.Database;
import hcmute.edu.vn.foodie_infinity.Model.Food;
import hcmute.edu.vn.foodie_infinity.Model.Restaurant;
import hcmute.edu.vn.foodie_infinity.R;


public class RestaurantFragment extends Fragment {
    RecyclerView rcv_restaurnt;
    ImageView chicken, vegetable, drink, all;
    SearchView searchView;
    MenuItem menuItem;
    Database database;
    private RestaurantNavigationAdapter RestaurantAdapter;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        setHasOptionsMenu(true);

        super.onCreate(savedInstanceState);


    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_restaurant, container, false);
        database = new Database(getActivity(), "Foodie.sqlite", null, 1);

        rcv_restaurnt = view.findViewById(R.id.rcv_navigation_restaurant);
        RestaurantAdapter = new RestaurantNavigationAdapter((getActivity()));
        GridLayoutManager gridLayoutManager = new GridLayoutManager(getActivity(), 2, GridLayoutManager.VERTICAL, false);
        rcv_restaurnt.setLayoutManager(gridLayoutManager);
        RestaurantAdapter.setData(getListRestaurant());
        rcv_restaurnt.setAdapter(RestaurantAdapter);


        chicken = (ImageView) view.findViewById(R.id.chicken);
        vegetable = (ImageView) view.findViewById(R.id.leaf);
        drink = (ImageView) view.findViewById(R.id.drink);
        all = (ImageView) view.findViewById(R.id.allFood);

        EditText search_restaurant = view.findViewById(R.id.search_restaurant);


        List<Restaurant> Listrest =  getListRestaurant();
        search_restaurant.setOnKeyListener(new View.OnKeyListener() {
            @Override
            public boolean onKey(View view, int i, KeyEvent keyEvent) {

                List<Restaurant> list = new ArrayList<>();

                for (int j = 0; j < Listrest.size(); j++) {
                    if (Listrest.get(j).getName().contains(search_restaurant.getText())) {
                        list.add(Listrest.get(j));
                    }
                }

                if(search_restaurant.getText().toString().trim().equals("") )
                {
                    list =Listrest;
                }
                RestaurantAdapter.setData(list);
                rcv_restaurnt.setAdapter(RestaurantAdapter);
                return false;

            }
        });

        chicken.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                List<Restaurant> listRest =  getListRestaurant();

                List<Restaurant> list = new ArrayList<>();
                for (int i = 0; i < listRest.size(); i++) {
                    if (listRest.get(i).getType().equals("m")) {
                        list.add(listRest.get(i));
                    }
                }
                Toast.makeText(getActivity(), Integer.toString(list.size()), Toast.LENGTH_SHORT).show();

                RestaurantAdapter.setData(list);
                rcv_restaurnt.setAdapter(RestaurantAdapter);
            }
        });


        vegetable.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                List<Restaurant> listRest =  getListRestaurant();

                List<Restaurant> list = new ArrayList<>();
                for (int i = 0; i < listRest.size(); i++) {
                    if (listRest.get(i).getType().equals("v")) {
                        list.add(listRest.get(i));
                    }
                }

                RestaurantAdapter.setData(list);
                rcv_restaurnt.setAdapter(RestaurantAdapter);
            }
        });

        drink.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                List<Restaurant> listRest =  getListRestaurant();

                List<Restaurant> list = new ArrayList<>();
                for (int i = 0; i < listRest.size(); i++) {
                    if (listRest.get(i).getType().equals("d")) {
                        list.add(listRest.get(i));
                    }
                }
                RestaurantAdapter.setData(list);
                rcv_restaurnt.setAdapter(RestaurantAdapter);
            }
        });

        all.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                RestaurantAdapter.setData(getListRestaurant());
                rcv_restaurnt.setAdapter(RestaurantAdapter);
            }
        });

        return view;
    }

    private List<Restaurant> getListRestaurant() {
        List<Restaurant> list = new ArrayList<>();
        database = new Database(getActivity(), "Foodie.sqlite", null, 1);

        Cursor cursor = database.GetData("Select * From Restaurant");
        while (cursor.moveToNext())
        {
            int id = cursor.getInt(0);
            String name = cursor.getString(1);
            String address = cursor.getString(2);
            String avatar = cursor.getString(3);
            String price = cursor.getString(4);
            String time = cursor.getString(5);
            String type = cursor.getString(6);

            list.add((new Restaurant(id, name,address,avatar,price,time, type )));
        }



        return list;
    }
    public byte[] toByteArray(int id){
        Bitmap bitmap = BitmapFactory.decodeResource(getResources(), id);
        ByteArrayOutputStream byteArray = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.PNG, 50, byteArray);
        byte[] img = byteArray.toByteArray();
        return img;
    }
}