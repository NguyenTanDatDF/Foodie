package hcmute.edu.vn.foodie_infinity.Model;

import android.app.Activity;
import android.graphics.Color;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import hcmute.edu.vn.foodie_infinity.Activity.CartActivity;
import hcmute.edu.vn.foodie_infinity.Activity.MainActivity;
import hcmute.edu.vn.foodie_infinity.Database.Database;
import hcmute.edu.vn.foodie_infinity.R;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import java.util.List;

public class DialogHandler {

    public static void createSingleItemDialog(Activity activity, List<Voucher> list, String title, OnClick listener){
        View view = LayoutInflater.from(activity).inflate(R.layout.layout_single_item_dialog,null);
        TextView titleTextView = view.findViewById(R.id.title_textview);
       // titleTextView.setText(title);

        RecyclerView recyclerView = view.findViewById(R.id.recyclerview);
        recyclerView.setLayoutManager(new LinearLayoutManager(activity));
        AlertDialog dialog = prepareDialog(activity,view);

        final SingleItemAdapter adapter = new SingleItemAdapter(activity,list,dialog,listener);
        recyclerView.setAdapter(adapter);

        dialog.show();
    }

    private static AlertDialog prepareDialog(Activity activity, View view){
        AlertDialog.Builder builder = new AlertDialog.Builder(activity);
        builder.setView(view);
        return builder.create();
    }

    private static class SingleItemAdapter extends RecyclerView.Adapter<SingleItemAdapter.ViewHolder>{
        List<Voucher> list;
        AlertDialog dialog;
        OnClick listener;
        Activity activity;

        SingleItemAdapter(Activity activity, List<Voucher> list, AlertDialog dialog, OnClick listener){
            this.list = list;
            this.dialog = dialog;
            this.listener = listener;
            this.activity = activity;
        }

        @NonNull
        @Override
        public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
            View view = LayoutInflater.from(activity).inflate(R.layout.layout_single_item,viewGroup,false);
            return new ViewHolder(view);
        }

        @Override
        public void onBindViewHolder(@NonNull ViewHolder holder, int i) {
            Voucher item = list.get(i);
            Database database = new Database(activity, "Foodie.sqlite", null, 1);

            holder.name.setText(item.getName().toString());
            holder.price.setText(Integer.toString(item.getPrice()) );
            if(item.getType().equals("d"))
            {
                holder.Per_voucher.setText("$");
            }
            else
            {
                holder.Per_voucher.setText("%");
            }

            holder.button_use.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                    Double price_sub = Double.parseDouble(CartActivity.tv_Subtotal_m.getText().toString() );
                    Double price_total = Double.parseDouble(CartActivity.tv_Total_m.getText().toString() );

                    Log.e("Increase",price_total.toString()+", "  + Integer.toString(item.getPrice()) );
                    database.deleteVoucherById(item.getId());
                    if(item.getType().equals("d"))
                    {
                        CartActivity.tv_Subtotal_m.setText( Double.toString(price_sub - item.getPrice()) );
                        CartActivity.tv_Subtotal_m.setTextColor(Color.parseColor("#21730B"));
                        CartActivity.tv_Total_m.setText( Double.toString(price_total - item.getPrice()) );
                        CartActivity.tv_Total_m.setTextColor(Color.parseColor("#21730B"));
                        Bill.voucher = "c";
                        dialog.dismiss();
                    }
                    else
                    {
                        CartActivity.tv_Subtotal_m.setText( Double.toString( price_sub -(price_sub * item.getPrice() )/100 ) );
                        CartActivity.tv_Subtotal_m.setTextColor(Color.parseColor("#21730B"));

                        CartActivity.tv_Total_m.setText(Double.toString(price_total -(price_total * item.getPrice() ) /100) );
                        CartActivity.tv_Total_m.setTextColor(Color.parseColor("#21730B"));
                        Bill.voucher = "c";
                        dialog.dismiss();
                    }


                }
            });

        }

        @Override
        public int getItemCount() {
            return list.size();
        }

        public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
            TextView name;
            Button button_use;
            TextView price;
            TextView Per_voucher;
            public ViewHolder(@NonNull View itemView) {
                super(itemView);
                name = itemView.findViewById(R.id.tvName_voucher);
                button_use = itemView.findViewById(R.id.btn_use);
                price = itemView.findViewById(R.id.tvPrice_voucher);
                Per_voucher = itemView.findViewById(R.id.Per_voucher);

                button_use.setOnClickListener(this);
            }

            @Override
            public void onClick(View v) {
                listener.onClick(list.get(getAdapterPosition()));
                dialog.dismiss();
            }
        }
    }

    public interface OnClick {
        public void onClick(Voucher item);
    }

}
