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

import java.io.Serializable;
import java.sql.Time;
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

public class HistoryAdapter extends RecyclerView.Adapter<HistoryAdapter.HistoryViewHolder>{

    Boolean isUp;
    private Context mContext;
    private List<Bill> lineItemList;
    private FragmentActivity fragmentActivity;

    public HistoryAdapter(Context mContext) {
        this.mContext = mContext;
    }
    public HistoryAdapter(Context mContext,FragmentActivity fragmentActivity) {
        this.mContext = mContext;
        this.fragmentActivity =fragmentActivity;
    }

    public void setData(List<Bill> list)
    {
        this.lineItemList = list;

        notifyDataSetChanged();
    }


    @Override
    public void onBindViewHolder(@NonNull HistoryAdapter.HistoryViewHolder holder, int position) {
        Bill bill = lineItemList.get(position);



        if(bill == null)
        {
            return;
        }
        else {



            Log.e("bill-time", bill.getTime());


            holder.name.setText(bill.getTime());

            // initialize as invisible (could also do in xml)

            isUp = false;
            HistoryActivity.myView.setVisibility(View.INVISIBLE);

            holder.foodlayout.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if (isUp) {
                        slideDown( HistoryActivity.myView);


                    } else {
                        LayoutInflater layoutInflater = (LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
                        View view1 = layoutInflater.inflate(R.layout.layout_bottom_sheet, null);

//                        HistoryActivity.name.setText(MainActivity.database.FindFoodById(lineItem.getFoodId()).getName());
//                        HistoryActivity.foodx.setImageBitmap(MainActivity.database.FindFoodById(lineItem.getFoodId()).getImageBitmap());
//                        HistoryActivity.pricex.setText( Double.toString(MainActivity.database.FindFoodById(lineItem.getFoodId()).getPrice()));
//                        HistoryActivity.et_amountx.setText( Double.toString(lineItem.getAmount()));


                          TextView historyString = view1.findViewById(R.id.namezzz);
                         // Log.e("historyString", historyString.getText().toString());
                        Database database = new Database(mContext, "Foodie.sqlite", null, 1);

                        DetailHistoryAdapter detailHistoryAdapter = new DetailHistoryAdapter(mContext);
                        detailHistoryAdapter= new DetailHistoryAdapter(mContext);
                        LinearLayoutManager linearLayoutManager2 = new LinearLayoutManager(mContext, RecyclerView.VERTICAL,false);
                        RecyclerView recyclerView_detail_new = HistoryActivity.recyclerView_detail;
                        recyclerView_detail_new.setLayoutManager(linearLayoutManager2);
                        String time =  bill.getTime();
                       List<LineItem> listHistory =  database.getLineItemByTimeInBill(time);
                       String vouch = database.getVoucherByTimeInBill(bill.getTime()).trim();

                        if(vouch.equals("c"))
                        {
                            HistoryActivity.textE.setText("Used voucher");
                        }
                        else
                        {
                            HistoryActivity.textE.setText("No voucher");
                        }
                        detailHistoryAdapter.setData(listHistory);
                        recyclerView_detail_new.setAdapter(detailHistoryAdapter);


                        slideUp( HistoryActivity.myView);

                    }
                    isUp = !isUp;
                }
            });

            // UpdateCheckOutInFormation();




        }
    }


    public List<LineItem> GetListCartBill()
    {
        Database database = new Database(mContext, "Foodie.sqlite", null, 1);

        return database.getLineItemByUserId(User.id);


    }

    // slide the view from below itself to the current position
    public void slideUp(View view){
        view.setVisibility(View.VISIBLE);
        TranslateAnimation animate = new TranslateAnimation(
                0,                 // fromXDelta
                0,                 // toXDelta
                view.getHeight(),  // fromYDelta
                0);                // toYDelta
        animate.setDuration(500);
        animate.setFillAfter(true);
        view.startAnimation(animate);
    }

    // slide the view from its current position to below itself
    public void slideDown(View view){
        TranslateAnimation animate = new TranslateAnimation(
                0,                 // fromXDelta
                0,                 // toXDelta
                0,                 // fromYDelta
                view.getHeight()); // toYDelta
        animate.setDuration(500);
        animate.setFillAfter(true);
        view.startAnimation(animate);
    }

    public void UpdateCheckOutInFormation() {
        Database database = new Database(mContext, "Foodie.sqlite", null, 1);

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
    public HistoryViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.history_row, parent, false);

        return new HistoryViewHolder(view);
    }

    public class HistoryViewHolder extends RecyclerView.ViewHolder {


        private TextView name;
        private RelativeLayout foodlayout;


        public HistoryViewHolder(@NonNull View itemView) {
            super(itemView);

            foodlayout= itemView.findViewById(R.id.food_layout_zz);
            name = itemView.findViewById(R.id.namezzz);

        }

    }

}