package hcmute.edu.vn.foodie_infinity.Adapter;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.TranslateAnimation;
import android.widget.EdgeEffect;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import org.w3c.dom.Text;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import hcmute.edu.vn.foodie_infinity.Activity.CartActivity;
import hcmute.edu.vn.foodie_infinity.Activity.HistoryActivity;
import hcmute.edu.vn.foodie_infinity.Activity.MainActivity;
import hcmute.edu.vn.foodie_infinity.Activity.RestaurantDetailActivity;
import hcmute.edu.vn.foodie_infinity.Database.Database;
import hcmute.edu.vn.foodie_infinity.Model.Bill;
import hcmute.edu.vn.foodie_infinity.Model.Food;
import hcmute.edu.vn.foodie_infinity.Model.LineItem;
import hcmute.edu.vn.foodie_infinity.Model.User;
import hcmute.edu.vn.foodie_infinity.R;

import hcmute.edu.vn.foodie_infinity.Model.Restaurant;

public class DetailHistoryAdapter extends RecyclerView.Adapter<DetailHistoryAdapter.DetailHistoryViewHolder>{

    Boolean isUp;
    private Context mContext;
    private List<LineItem> lineItemList;
    private FragmentActivity fragmentActivity;
    public DetailHistoryAdapter(Context mContext) {
        this.mContext = mContext;
    }
    public DetailHistoryAdapter(Context mContext,FragmentActivity fragmentActivity) {
        this.mContext = mContext;
        this.fragmentActivity =fragmentActivity;
    }

    public void setData(List<LineItem> list)
    {
        this.lineItemList = list;

        notifyDataSetChanged();
    }


    @Override
    public void onBindViewHolder(@NonNull DetailHistoryAdapter.DetailHistoryViewHolder holder, int position) {
        LineItem lineItem = lineItemList.get(position);


        if(lineItem == null)
        {
            return;
        }
        else {
            Database database = new Database(mContext, "Foodie.sqlite", null, 1);

            Log.e("foodname",database.FindFoodById(lineItem.getFoodId()).getName());


            int id = mContext.getResources().getIdentifier(database.FindFoodById(lineItem.getFoodId()).getAvatar(),"drawable", mContext.getPackageName());

            holder.foodx.setImageResource(id);
            Log.e("content",database.FindFoodById(lineItem.getFoodId()).getName());
            holder.namex.setText(database.FindFoodById(lineItem.getFoodId()).getName());
            holder.pricex.setText("$"+Double.toString(database.FindFoodById(lineItem.getFoodId()).getPrice()));
            holder.et_amountx.setText(Integer.toString(lineItem.getAmount()) );

        }
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
    public DetailHistoryViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.row_history_detail, parent, false);

        return new DetailHistoryViewHolder(view);
    }

    public class DetailHistoryViewHolder extends RecyclerView.ViewHolder {

        ImageView foodx ;
        EditText et_amountx;
        TextView namex;
        TextView pricex;

        public DetailHistoryViewHolder(@NonNull View itemView) {
            super(itemView);

            foodx = itemView.findViewById(R.id.foodx);
            et_amountx = itemView.findViewById(R.id.et_amountx);
            namex = itemView.findViewById(R.id.namex);
            pricex = itemView.findViewById(R.id.pricex);

        }

    }

}