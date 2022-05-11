package hcmute.edu.vn.foodie_infinity.Adapter;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EdgeEffect;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import hcmute.edu.vn.foodie_infinity.Activity.CartActivity;
import hcmute.edu.vn.foodie_infinity.Activity.MainActivity;
import hcmute.edu.vn.foodie_infinity.Activity.RestaurantDetailActivity;
import hcmute.edu.vn.foodie_infinity.Database.Database;
import hcmute.edu.vn.foodie_infinity.Model.Food;
import hcmute.edu.vn.foodie_infinity.Model.LineItem;
import hcmute.edu.vn.foodie_infinity.Model.User;
import hcmute.edu.vn.foodie_infinity.R;

import hcmute.edu.vn.foodie_infinity.Model.Restaurant;

public class LineItemAdapter extends RecyclerView.Adapter<LineItemAdapter.LineItemViewHolder>{


    private Context mContext;
    private List<LineItem> lineItemList;
    private FragmentActivity fragmentActivity;
    Database database;
    public LineItemAdapter(Context mContext) {
        this.mContext = mContext;
    }
    public LineItemAdapter(Context mContext,FragmentActivity fragmentActivity) {
        this.mContext = mContext;
        this.fragmentActivity =fragmentActivity;
    }

    public void setData(List<LineItem> list)
    {
        this.lineItemList = list;

        notifyDataSetChanged();
    }


    @Override
    public void onBindViewHolder(@NonNull LineItemAdapter.LineItemViewHolder holder, int position) {
        LineItem lineItem = lineItemList.get(position);


        if(lineItem == null)
        {
            return;
        }
        else {
            Database database = new Database(mContext, "Foodie.sqlite", null, 1);

           Food food =  database.FindFoodById(lineItem.getFoodId());

            int id = mContext.getResources().getIdentifier(food.getAvatar(),"drawable", mContext.getPackageName());


            holder.food.setImageResource(id);
            holder.name.setText(food.getName());
            holder.price.setText("$"+Double.toString(food.getPrice()));
            holder.et_amount.setText(Integer.toString(lineItem.getAmount()));



           // UpdateCheckOutInFormation();


            holder.image_add.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                    Log.e("content","+");

                   holder.et_amount.setText(Integer.toString(Integer.parseInt( holder.et_amount.getText().toString())+1));
                    Database  database = new Database(mContext, "Foodie.sqlite", null, 1);

                    database.UpdateAmountLineItem(food.getId(), true);
                    UpdateCheckOutInFormation();

                }
            });
            holder.image_minus.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if(Integer.parseInt( holder.et_amount.getText().toString())>0){
                        Database  database = new Database(mContext, "Foodie.sqlite", null, 1);

                        Log.e("IdUser",Integer.toString(User.id));
                        User user = database.GetUserFromUserName(User.userName);
                        Log.e("IDUser", Integer.toString(User.id));
                        User.id = user.id;

                        holder.et_amount.setText(Integer.toString(Integer.parseInt( holder.et_amount.getText().toString())-1));
                        database.UpdateAmountLineItem(food.getId(), false);


                        LineItemAdapter foodAdapter = new LineItemAdapter(mContext);
                        foodAdapter = CartActivity.foodAdapter2;
                        foodAdapter.setData(GetListCart());
                        CartActivity.rcvUser2.setAdapter(foodAdapter);

                        UpdateCheckOutInFormation();

                    }
                }
            });




        }
    }

    public List<LineItem> GetListCart(){
        Database  database = new Database(mContext, "Foodie.sqlite", null, 1);

        return database.getLineItemByUserId(User.id);
    }

    public void UpdateCheckOutInFormation() {
        database = new Database(mContext, "Foodie.sqlite", null, 1);

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
        total = subTotal + SHIP_FEE;

        CartActivity.tv_Subtotal_m.setText(Double.toString(subTotal) );
        CartActivity.tv_Total_m.setText(Double.toString(total));
    }


    public void GoToDetail(Restaurant restaurant)
    {
        Intent intent = new Intent(mContext, RestaurantDetailActivity.class);
        Bundle bundle = new Bundle();
        bundle.putSerializable("restaurant", (Serializable) restaurant);
        bundle.putSerializable("food", (Serializable) restaurant);
        intent.putExtras(bundle);
        mContext.startActivity(intent);
    }

    @Override
    public int getItemCount() {

        if(lineItemList != null)
        {
            return lineItemList.size();
        }
        return 0;
    }

    @NonNull
    @Override
    public LineItemViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.activity_food_row, parent, false);
         database = new Database(mContext, "Foodie.sqlite", null, 1);

        return new LineItemViewHolder(view);
    }

    public class LineItemViewHolder extends RecyclerView.ViewHolder {

        private ImageView food;
        private TextView name;
        private TextView price;
        private EditText et_amount;
        private ImageView image_add;
        private ImageView image_minus;


        public LineItemViewHolder(@NonNull View itemView) {
            super(itemView);
            food = itemView.findViewById(R.id.food);
            name = itemView.findViewById(R.id.name);
            price = itemView.findViewById(R.id.price);
            et_amount = itemView.findViewById(R.id.et_amount);


            image_add = (ImageView) itemView.findViewById(R.id.minus_cart);
            image_minus = (ImageView) itemView.findViewById(R.id.add_cart);
            et_amount = (EditText) itemView.findViewById(R.id.et_amount);
        }

    }

}