package hcmute.edu.vn.foodie_infinity.Adapter;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.bottomsheet.BottomSheetDialog;
import com.makeramen.roundedimageview.RoundedImageView;

import java.util.List;

import hcmute.edu.vn.foodie_infinity.Activity.MainActivity;
import hcmute.edu.vn.foodie_infinity.Database.Database;
import hcmute.edu.vn.foodie_infinity.Model.Cart;
import hcmute.edu.vn.foodie_infinity.Model.Food;
import hcmute.edu.vn.foodie_infinity.Model.LineItem;
import hcmute.edu.vn.foodie_infinity.Model.User;
import hcmute.edu.vn.foodie_infinity.R;


public class FoodAdapter extends RecyclerView.Adapter<FoodAdapter.FoodViewHolder>{

    private Context mContext;
    private List<Food> foodList;

    public FoodAdapter(Context mContext) {
        this.mContext = mContext;
    }

    public void setData(List<Food> list)
    {
        this.foodList = list;
        notifyDataSetChanged();
    }



    @Override
    public void onBindViewHolder(@NonNull FoodAdapter.FoodViewHolder holder, int position) {
        Food foods = foodList.get(position);
        Database database = new Database(mContext, "Foodie.sqlite", null, 1);

        if(foods == null)
        {

            return;
        }
        else {

            Log.e("FOodNAME","qqq"+ foods.getAvatar());
            int id = mContext.getResources().getIdentifier(foods.getAvatar(),"drawable", mContext.getPackageName());

            holder.food.setImageResource(id);
            holder.name.setText(foods.getName());
            holder.price.setText("$"+Double.toString( foods.getPrice()));

            holder.food_layout.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    LayoutInflater layoutInflater = (LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
                    View view1 = layoutInflater.inflate(R.layout.layout_bottom_sheet, null);

                     BottomSheetDialog bottomSheetDialog = new BottomSheetDialog(mContext, com.google.android.material.R.style.Theme_Design_BottomSheetDialog);
                     View bottomSheetView = LayoutInflater.from(mContext).inflate(R.layout.layout_bottom_sheet, view1.findViewById(R.id.food_layout));

                     RoundedImageView roundedImageView =  view1.findViewById(R.id.roudedImage);
                    TextView sheet_name = view1.findViewById(R.id.sheet_name);
                    TextView sheet_price = view1.findViewById(R.id.sheet_price);
                    Button buttonBuy = view1.findViewById(R.id.buttonBuy);
                    int id = mContext.getResources().getIdentifier(foods.getAvatar(),"drawable", mContext.getPackageName());

                    roundedImageView.setImageResource(id);
                    sheet_name.setText(foods.getName());
                    sheet_price.setText("$" + Double.toString(foods.getPrice()));

                     bottomSheetDialog.setContentView(view1);
                     bottomSheetDialog.show();


                    buttonBuy.setOnClickListener(new View.OnClickListener() {
                         @Override
                         public void onClick(View view) {

                             bottomSheetDialog.dismiss();
                             Toast.makeText(mContext, "Added to cart", Toast.LENGTH_SHORT).show();


                             //
//                            Intent intent = new Intent(mContext, MainActivity.class);
//                             mContext.startActivity(intent);

                             int FIRST_FOOD_AMOUNT = 1;
                             Log.e("foodid", Integer.toString(foods.getId()) );

                             if(database.checkIfFoodExist(foods.getId())==false)
                                 {
                                     LineItem lineItem = new LineItem( User.id ,foods.getId(),FIRST_FOOD_AMOUNT,1);
                                     database.InsertLineItem(lineItem);

                                     Log.e("IDUserWhenAddToCart",Integer.toString(User.id)+"--"+Integer.toString(foods.getId()));
                                 }
                             else
                             {
                                database.UpdateAmountLineItem(foods.getId(), true);
                             }


                         }
                     });
                }

            });


        }
    }



    @Override
    public int getItemCount() {

        if(foodList != null)
        {
            return foodList.size();
        }
        return 0;
    }

    @NonNull
    @Override
    public FoodViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.food_row, parent, false);

        return new FoodViewHolder(view);
    }

    public class FoodViewHolder extends RecyclerView.ViewHolder{
        private RelativeLayout food_layout;
        private ImageView food;
        private TextView name;
        private TextView price;


        public FoodViewHolder(@NonNull View itemView) {
            super(itemView);
            food_layout = itemView.findViewById(R.id.food_layout);
            food = itemView.findViewById(R.id.food);
            name = itemView.findViewById(R.id.name);
            price = itemView.findViewById(R.id.price);
        }
    }

}