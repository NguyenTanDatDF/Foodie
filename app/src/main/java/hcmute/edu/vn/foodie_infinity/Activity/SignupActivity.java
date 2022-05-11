package hcmute.edu.vn.foodie_infinity.Activity;

import androidx.appcompat.app.AppCompatActivity;

import hcmute.edu.vn.foodie_infinity.Database.Database;
import hcmute.edu.vn.foodie_infinity.Model.User;
import hcmute.edu.vn.foodie_infinity.R;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class SignupActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);

        TextView noAccount = (TextView) findViewById(R.id.signin_tv);
        EditText username_et = (EditText) findViewById(R.id.username_ets);
        EditText password_et= (EditText) findViewById(R.id.password_ets);
        EditText confirm_et= (EditText) findViewById(R.id.confirm_ets);
        Button button_login = (Button) findViewById(R.id.signin_btn) ;
        Database database = new Database(this, "Foodie.sqlite", null, 1);

        button_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if((password_et.getText().toString().equals(confirm_et.getText().toString())) || !username_et.getText().toString().isEmpty() || !password_et.getText().toString().isEmpty() || !confirm_et.getText().toString().isEmpty())
                {
                    Log.e("content",username_et.getText().toString());
                    if(database.checkIfExist(username_et.getText().toString())==false)
                    {
                        User user = new User(username_et.getText().toString(),password_et.getText().toString());
                        database.InsertUser(user);
                        Toast.makeText(SignupActivity.this, "Sign Up successfully", Toast.LENGTH_SHORT).show();
                    }
                   else {
                        Toast.makeText(SignupActivity.this, "Username was taken by someone", Toast.LENGTH_SHORT).show();

                    }

                }
                else
                {
                    Toast.makeText(SignupActivity.this, "Please confirm your password again", Toast.LENGTH_SHORT).show();

                }

            }
        });

        noAccount.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent( SignupActivity.this, SigninActivity.class);
                SignupActivity.this.startActivity(intent);
            }
        });


    }
}