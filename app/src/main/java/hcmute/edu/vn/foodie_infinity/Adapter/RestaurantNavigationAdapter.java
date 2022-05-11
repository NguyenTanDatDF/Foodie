package hcmute.edu.vn.foodie_infinity.Adapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import hcmute.edu.vn.foodie_infinity.Activity.RestaurantDetailActivity;
import hcmute.edu.vn.foodie_infinity.R;

import hcmute.edu.vn.foodie_infinity.Model.Restaurant;

public class RestaurantNavigationAdapter extends RecyclerView.Adapter<RestaurantNavigationAdapter. RestaurantNavigationViewHolder> implements Filterable {

    private Context mContext;
    private List<Restaurant> restaurantList;
    private List<Restaurant> restaurantListOld;
    public RestaurantNavigationAdapter(Context mContext) {
        this.mContext = mContext;
    }


    public void setData(List<Restaurant> list)
    {
        this.restaurantList = list;
        this.restaurantListOld = list;
        notifyDataSetChanged();
    }



    @Override
    public void onBindViewHolder(@NonNull RestaurantNavigationAdapter.RestaurantNavigationViewHolder holder,int position) {
        Restaurant restaurants = restaurantList.get(position);
        if(restaurants == null)
        {
            return;
        }
        else {
            int id = mContext.getResources().getIdentifier(restaurants.getAvatar(),"drawable", mContext.getPackageName());

           holder.img_restaurant.setImageResource(id);
            holder.name_restaurant.setText(restaurants.getName());
            holder.address_restaurant.setText(restaurants.getAddress());
            holder.price_restaurant.setText(restaurants.getPrice());

            holder.restaurant_layout.setOnClickListener(new View.OnClickListener() {
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
    public  RestaurantNavigationViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.restaurant_detail_row, parent, false);

        return new  RestaurantNavigationViewHolder(view);
    }

    @Override
    public Filter getFilter() {
        return new Filter() {
            @Override
            protected FilterResults performFiltering(CharSequence charSequence) {
                String strSearch = charSequence.toString();
                if (strSearch.isEmpty())
                {
                    restaurantList = restaurantListOld;
                }
                else
                {
                    List<Restaurant> list = new ArrayList<>();
                    for(Restaurant restaurant : restaurantListOld)
                    {
                        if(restaurant.getName().toLowerCase().contains(strSearch.toLowerCase(Locale.ROOT)))
                        {
                            list.add(restaurant);
                        }
                    }
                    restaurantList = list;
                }
                FilterResults filterResults = new FilterResults();
                filterResults.values = restaurantList;
                return filterResults;
            }

            @Override
            protected void publishResults(CharSequence charSequence, FilterResults filterResults) {
                restaurantList = (List<Restaurant>) filterResults.values;
                notifyDataSetChanged();
            }
        };
    }

    public class  RestaurantNavigationViewHolder extends RecyclerView.ViewHolder{
        private RelativeLayout restaurant_layout;
        private ImageView img_restaurant;
        private TextView name_restaurant;
        private TextView address_restaurant;
        private TextView price_restaurant;


        public  RestaurantNavigationViewHolder(@NonNull View itemView) {
            super(itemView);
            restaurant_layout = itemView.findViewById(R.id.restaurant_layoutzx);
            img_restaurant = itemView.findViewById(R.id.img_restaurantz);
            name_restaurant = itemView.findViewById(R.id.name_restaurant);
            address_restaurant = itemView.findViewById(R.id.address_restaurant);
            price_restaurant = itemView.findViewById(R.id.price_restaurant);
        }
    }

}