package hcmute.edu.vn.foodie_infinity.Activity;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import hcmute.edu.vn.foodie_infinity.R;

public class checkout_activity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_checkout);

      Button btn_zzz = (Button) findViewById(R.id.btn_zzz);
      btn_zzz.setOnClickListener(new View.OnClickListener() {
          @Override
          public void onClick(View view) {
              finish();
          }
      });
    }
}