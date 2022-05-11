package hcmute.edu.vn.foodie_infinity.Activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;
import android.view.animation.TranslateAnimation;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import hcmute.edu.vn.foodie_infinity.Adapter.DetailHistoryAdapter;
import hcmute.edu.vn.foodie_infinity.Adapter.HistoryAdapter;
import hcmute.edu.vn.foodie_infinity.Adapter.LineItemAdapter;
import hcmute.edu.vn.foodie_infinity.Database.Database;
import hcmute.edu.vn.foodie_infinity.Model.Bill;
import hcmute.edu.vn.foodie_infinity.Model.User;
import hcmute.edu.vn.foodie_infinity.R;

public class HistoryActivity extends AppCompatActivity {
    Button myButton;
    public  static View  myView;
    boolean isUp;
    public static RecyclerView rcvUser2;
    public static HistoryAdapter foodAdapter2;

    public static RecyclerView recyclerView_detail;
    public static DetailHistoryAdapter historyAdapter_detail;
    public static List<Bill> listBillResult = new ArrayList<>();

    public static TextView name;
    public static ImageView foodx;
    public static TextView pricex;
    public static EditText et_amountx;
    public static TextView textE;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_history);
        myView = findViewById(R.id.my_view);

        name = findViewById(R.id.namex);
        foodx = findViewById(R.id.foodx);
        pricex = findViewById(R.id.pricex);
        et_amountx = findViewById(R.id.et_amountx);
        textE = findViewById(R.id.textE);

        recyclerView_detail = findViewById(R.id.rcv_detail_history);

        rcvUser2 = findViewById(R.id.rcv_history);

        foodAdapter2= new HistoryAdapter((HistoryActivity.this));
        LinearLayoutManager linearLayoutManager2 = new LinearLayoutManager(HistoryActivity.this, RecyclerView.VERTICAL,false);
        rcvUser2.setLayoutManager(linearLayoutManager2);


        foodAdapter2.setData(GetListCartBill());
        rcvUser2.setAdapter(foodAdapter2);

        // initialize as invisible (could also do in xml)
//        myView.setVisibility(View.INVISIBLE);
//        myButton.setText("Slide up");
//        isUp = false;
//        myButton.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                if (isUp) {
//                    slideDown(myView);
//                    myButton.setText("Slide up");
//                } else {
//                    slideUp(myView);
//                    myButton.setText("Slide down");
//                }
//                isUp = !isUp;
//            }
//        });
    }
    Database database = new Database(this, "Foodie.sqlite", null, 1);

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
    public List<Bill> GetListCartBill(){


        List<Bill> billList = database.getBillByUserId(User.id);

        listBillResult.add(billList.get(0));
        for(int i = 0 ; i < billList.size() ; i++)
        {
            int flag = 0 ;
            for(int j = 0 ; j < listBillResult.size();j++)
            {
                if(billList.get(i).getTime().equals(listBillResult.get(j).getTime()) )
                {
                    flag++;
                }
            }
            if(flag==0)
            {
                listBillResult.add(billList.get(i));
            }
            flag = 0;
        }


       return  listBillResult;
    }
}
