package hcmute.edu.vn.foodie_infinity.Adapter;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.io.Serializable;
import java.util.List;

import hcmute.edu.vn.foodie_infinity.Activity.MainActivity;
import hcmute.edu.vn.foodie_infinity.Activity.RestaurantDetailActivity;
import hcmute.edu.vn.foodie_infinity.R;

import hcmute.edu.vn.foodie_infinity.Model.Restaurant;

public class RestaurantAdapter extends RecyclerView.Adapter<RestaurantAdapter.RestaurantViewHolder>{

    private Context mContext;
    private List<Restaurant> restaurantList;
    public RestaurantAdapter(Context mContext) {
        this.mContext = mContext;
    }

    public void setData(List<Restaurant> list)
    {
        this.restaurantList = list;

        notifyDataSetChanged();
    }


    @Override
    public void onBindViewHolder(@NonNull RestaurantAdapter.RestaurantViewHolder holder, int position) {
        Restaurant restaurants = restaurantList.get(position);
        if(restaurants == null)
        {
            return;
        }
        else {
            int id = mContext.getResources().getIdentifier(restaurants.getAvatar(),"drawable", mContext.getPackageName());
            holder.imgUser.setImageResource(id);
            holder.tvName.setText(restaurants.getName());
            holder.tvDescription.setText(restaurants.getAddress());
            holder.restaurant_home.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    GoToDetail(restaurants);
                }
            });
        }
    }


    public void GoToDetail(Restaurant restaurant)
    {
        Intent intent = new Intent(mContext, RestaurantDetailActivity.class);
        Bundle bundle = new Bundle();
//        bundle.putSerializable("restaurant", (Serializable) restaurant);
        bundle.putSerializable("restaurantId", (Serializable) restaurant.getId());
        intent.putExtras(bundle);
        mContext.startActivity(intent);
    }

    @Override
    public int getItemCount() {

        if(restaurantList != null)
        {
            return restaurantList.size();
        }
        return 0;
    }

    @NonNull
    @Override
    public RestaurantViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.restaurant_row, parent, false);

        return new RestaurantViewHolder(view);
    }

    public class RestaurantViewHolder extends RecyclerView.ViewHolder {

        private ImageView imgUser;
        private TextView tvName;
        private TextView tvDescription;
        private RelativeLayout restaurant_home;
        public RestaurantViewHolder(@NonNull View itemView) {
            super(itemView);
            restaurant_home = itemView.findViewById(R.id.restaurant_home);
            imgUser = itemView.findViewById(R.id.img_restaurant);
            tvName = itemView.findViewById(R.id.tvName);
            tvDescription = itemView.findViewById(R.id.tvDescription);

        }

    }

}