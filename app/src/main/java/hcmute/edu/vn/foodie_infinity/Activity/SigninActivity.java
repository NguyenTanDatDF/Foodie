package hcmute.edu.vn.foodie_infinity.Activity;
import hcmute.edu.vn.foodie_infinity.Database.Database;
import hcmute.edu.vn.foodie_infinity.Model.Belong;
import hcmute.edu.vn.foodie_infinity.Model.Food;
import hcmute.edu.vn.foodie_infinity.Model.Restaurant;
import hcmute.edu.vn.foodie_infinity.Model.User;
import hcmute.edu.vn.foodie_infinity.R;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.CursorWindow;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.io.ByteArrayOutputStream;
import java.lang.reflect.Field;

public class SigninActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signin);

        try {
            Field field = CursorWindow.class.getDeclaredField("sCursorWindowSize");
            field.setAccessible(true);
            field.set(null, 100 * 1024 * 1024); //the 100MB is the new size
        } catch (Exception e) {
            e.printStackTrace();
        }

       Database database = new Database(this, "Foodie.sqlite", null, 1);

     //  database.QueryData("drop Table if exists User");
     InsertDatabase();


        TextView noAccount = (TextView) findViewById(R.id.signup_tv);
        EditText username_et = (EditText) findViewById(R.id.username_etx);
        EditText password_et= (EditText) findViewById(R.id.password_etx);

        Button button_login = (Button) findViewById(R.id.signin_btnz) ;

        button_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.e("content", username_et.getText().toString()+ " - "+password_et.getText().toString());

                if( !username_et.getText().toString().isEmpty() || !password_et.getText().toString().isEmpty() )
                {
                    Log.e("content", "1");

                    if(database.ValidateUser(username_et.getText().toString().trim(), password_et.getText().toString().trim()))
                    {
                        Log.e("content", "2");

                        Log.e("content", username_et.getText().toString()+ " - "+password_et.getText().toString());
                        User user = database.GetUserFromUserName(username_et.getText().toString());
                        Log.e("IDUser", Integer.toString(User.id));
                        User.id = user.id;
                        User.userName = user.getUserName();
                        Intent intent = new Intent(SigninActivity.this, MainActivity.class);
                        SigninActivity.this.startActivity(intent);
                        Toast.makeText(SigninActivity.this, "Login sucessfuly", Toast.LENGTH_SHORT);

                    }
                    else {
                        Toast.makeText(SigninActivity.this, "Login fail", Toast.LENGTH_SHORT);

                    }
                }
                else
                {
                    Toast.makeText(SigninActivity.this, "Please enter username and password", Toast.LENGTH_SHORT);
                }
            }
        });


        noAccount.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(SigninActivity.this, SignupActivity.class);
                SigninActivity.this.startActivity(intent);
            }
        });
    }


    public void InsertDatabase()
    {
       Database database = new Database(this, "Foodie.sqlite", null, 1);
      database.QueryData("drop Table if exists Restaurant");
      database.QueryData("CREATE TABLE IF NOT EXISTS Restaurant(id INTEGER PRIMARY KEY AUTOINCREMENT, name VARCHAR(150), address VARCHAR(250), avatar INTEGER, price VARCHAR(250), time VARCHAR(250), type VARCHAR(250) )");

        database.InsertRestaurant(new Restaurant("El Gaucho", "74 Hai B?? Tr??ng, B???n Ngh??", "restaurant1", "$2-$150", "7h-22h", "m"));
        database.InsertRestaurant(new Restaurant("Boomerang Bistro", "107 T??n D???t Ti??n, T??n Phong", "restaurant2", "$1-$200", "6h30-21h", "m"));
        database.InsertRestaurant(new Restaurant("Trung Nam", "1/1 Ho??ng Di???u 2", "restaurant3", "$1-$10", "5h30-20h", "m"));
        database.InsertRestaurant(new Restaurant("Holy Mole", "175 B??nh Th???i, Qu???n 9", "restaurant4", "$1-$15", "8h-22h", "m"));
        database.InsertRestaurant(new Restaurant("Party Fowl", "25 Nguy???n Hu???, Qu???n 1", "restaurant5", "$2-$35", "7h-23h", "m"));
        database.InsertRestaurant(new Restaurant("The Deck Saigon", "38 Nguyen U Di, District 2","restaurant6", "$5-$305", "8h-23h", "m"));
        database.InsertRestaurant(new Restaurant("Wecha", "Ph?????ng 4, T??n B??nh", "restaurant7", "$1-$10", "6h30-21h", "d"));
        database.InsertRestaurant(new Restaurant("MayCha", "38 Tr???nh ????nh Tr???ng, Ph?? Trung", "restaurant8", "$1-$50", "5h30-20h", "d"));
        database.InsertRestaurant(new Restaurant("R&B Tea", "B??i ?????u xe s??n bay Nh???t", "restaurant9", "$1-$15", "8h-22h", "d"));
        database.InsertRestaurant(new Restaurant("Xing Fu Tang", "495 S?? V???n H???nh, Ph?????ng 12","restaurant10", "$2-$35", "7h-23h", "d"));
       database.InsertRestaurant(new Restaurant("Cheese Coffee SVH", "782 S?? V???n H???nh, Ph?????ng 12", "restaurant11", "$5-$25", "8h-23h", "d"));
        database.InsertRestaurant(new Restaurant("Viet Kitchen", "38 Ph???m V??n S??u, Ph?? Trung", "restaurant12", "$1-$10", "5h30-20h", "v"));
        database.InsertRestaurant(new Restaurant("Kebaby", "22 L?? Th??? M??m, Qu???n 6", "restaurant13", "$1-$15", "8h-22h", "v"));
        database.InsertRestaurant(new Restaurant("Shanti Indian Cuisine", "495 Ch??? B??n C???", "restaurant14", "$2-$35", "7h-23h", "v"));
       database.InsertRestaurant(new Restaurant("Benaras Indian Bistro", "782 S?? V???n H???nh, Ph?????ng 12", "restaurant15", "$5-$305", "8h-23h", "v"));



      database.QueryData("drop Table if exists Food");
        database.QueryData("CREATE TABLE IF NOT EXISTS Food(id INTEGER PRIMARY KEY AUTOINCREMENT, name VARCHAR(150), price VARCHAR(250), avatar INTEGER)");

        database.InsertFood(new Food( "B??nh m?? b?? tr???ng", 2, "food1" ));
        database.InsertFood(new Food( "Sallad g?? chi??n m???m", 3, "food2"));
       database.InsertFood(new Food( "m?? ??", 4, "food3"));
        database.InsertFood(new Food("Pizza t??m", 3, "food4"));
      database.InsertFood(new Food("BBQ b?? s???t", 200, "food5"));
        database.InsertFood(new Food("C?? t???m chi??n v??ng", 250,"food6"));

       database.InsertFood(new Food("C??m tr???ng Nh???t", 100, "food7"));
        database.InsertFood(new Food("Beef Steak Ph??p", 220, "food8"));
      database.InsertFood(new Food("Hamburger b??", 50, "food9"));

      database.InsertFood(new Food("Beef Steak Anh", 245,"food10"));




        database.InsertFood(new Food("Beef Steak c??? ??i???n", 220, "food11"));
        database.InsertFood(new Food("Salad t??m mu???i", 100, "food12"));
        database.InsertFood(new Food("B?? s???t c???i", 200, "food13"));
        database.InsertFood(new Food("????i c???u s???t", 225, "food14"));
        database.InsertFood(new Food("B??nh m?? k???p tr???ng", 80, "food15"));
        database.InsertFood(new Food( "Cari c??", 210, "food16"));
        database.InsertFood(new Food("C??m g??", 4,"food17"));
        database.InsertFood(new Food("B??n Th??i", 10, "food18"));
        database.InsertFood(new Food("B??nh m?? k???p tr???ng b??", 10,"food19"));
        database.InsertFood(new Food("C?? Basa chi??n", 12, "food20"));
        database.InsertFood(new Food("D??u ta kem", 5, "food21"));
        database.InsertFood(new Food("B??nh m?? kem d??u", 10, "food22"));
        database.InsertFood(new Food("D??u ng???t", 12,"food23"));
        database.InsertFood(new Food("Chu???i s???a ng???t", 12, "food24"));
      database.InsertFood(new Food("Sinh t??? d??u", 10, "food25"));
        database.InsertFood(new Food("X?? mu???i m???n t????i", 30, "food26"));
        database.InsertFood(new Food("Khoai t??y b??", 10, "food27"));
        database.InsertFood(new Food("Chocola chua", 25, "food28"));



        database.InsertFood(new Food("Khoai t??y ch???m chocola", 12,"food29"));
        database.InsertFood(new Food("B??nh m?? c???i t???i", 20, "food30"));
        database.InsertFood(new Food("N?????c cam t????i", 12,"food31"));
        database.InsertFood(new Food("B???c x???u trang tr??", 15, "food32"));
        database.InsertFood(new Food("Chanh t????i c?? c???n nh???", 20, "food33"));
       database.InsertFood(new Food("Matcha ???? xay", 30,"food34"));
        database.InsertFood(new Food("Tr?? s???a tr??n ch??u", 22,"food35"));
        database.InsertFood(new Food("Kem d??u ????", 24, "food36"));
      database.InsertFood(new Food("Kem chocola ???? xay", 30, "food37"));
        database.InsertFood(new Food( "D??a d???u ???? xay", 45,"food38"));


        database.InsertFood(new Food("Tr?? s???a truy???n th???ng", 10, "food39"));
        database.InsertFood(new Food("Kem c???m s???a", 13, "food40"));
        database.InsertFood(new Food("Tr?? cam n??ng", 10, "food41"));
        database.InsertFood(new Food("R?????i chanh", 20, "food42"));
       database.InsertFood(new Food("M???t ong chanh", 23, "food43"));
        database.InsertFood(new Food("Petunia", 99, "food44"));
        database.InsertFood(new Food("D??u xay ???? h???ng", 10, "food45"));
        database.QueryData("drop Table if exists Belong");
        database.QueryData("CREATE TABLE IF NOT EXISTS Belong(rid INTEGER, fid INTEGER)");

//        database.InsertBelong(new Belong(1,1)
//        );
//        database.InsertBelong(new Belong(1,2)
//        );
//        database.InsertBelong(new Belong(1,3)
//        );
//        database.InsertBelong(new Belong(1,4)
//        );
//        database.InsertBelong(new Belong(1,5)
//        );
//        database.InsertBelong(new Belong(1,6)
//        );
//        database.InsertBelong(new Belong(1,7)
//        );
//        database.InsertBelong(new Belong(1,8)
//        );
//        database.InsertBelong(new Belong(1,9)
//        );
//        database.InsertBelong(new Belong(1,10)
//        );
//
//        database.InsertBelong(new Belong(2,11)
//        );
//        database.InsertBelong(new Belong(2,12)
//        );
//        database.InsertBelong(new Belong(2,1)
//        );
//        database.InsertBelong(new Belong(2,3)
//        );
//        database.InsertBelong(new Belong(2,4)
//        );
//        database.InsertBelong(new Belong(2,13)
//        );
//        database.InsertBelong(new Belong(2,14)
//        );
//        database.InsertBelong(new Belong(2,15)
//        );
//        database.InsertBelong(new Belong(2,16)
//        );
//        database.InsertBelong(new Belong(2,17)
//        );
//
//        database.InsertBelong(new Belong(3,18)
//        );
//        database.InsertBelong(new Belong(3,19)
//        );
//        database.InsertBelong(new Belong(3,20)
//        );
//        database.InsertBelong(new Belong(3,3)
//        );
//        database.InsertBelong(new Belong(3,4)
//        );
//        database.InsertBelong(new Belong(3,6)
//        );
//        database.InsertBelong(new Belong(3,7)
//        );
//        database.InsertBelong(new Belong(3,8)
//        );
//        database.InsertBelong(new Belong(3,12)
//        );
//        database.InsertBelong(new Belong(3,9)
//        );
//
//        database.InsertBelong(new Belong(4,5)
//        );
//        database.InsertBelong(new Belong(4,6)
//        );
//        database.InsertBelong(new Belong(4,7)
//        );
//        database.InsertBelong(new Belong(4,8)
//        );
//        database.InsertBelong(new Belong(4,9)
//        );
//        database.InsertBelong(new Belong(4,10)
//        );
//        database.InsertBelong(new Belong(4,16)
//        );
//        database.InsertBelong(new Belong(4,15)
//        );
//        database.InsertBelong(new Belong(4,14)
//        );
//        database.InsertBelong(new Belong(4,13)
//        );
//
//        database.InsertBelong(new Belong(5,4)
//        );
//        database.InsertBelong(new Belong(5,15)
//        );
//        database.InsertBelong(new Belong(5,16)
//        );
//        database.InsertBelong(new Belong(5,20)
//        );
//        database.InsertBelong(new Belong(5,19)
//        );
//        database.InsertBelong(new Belong(5,18)
//        );
//        database.InsertBelong(new Belong(5,17)
//        );
//        database.InsertBelong(new Belong(5,16)
//        );
//        database.InsertBelong(new Belong(5,15)
//        );
//        database.InsertBelong(new Belong(5,14)
//        );
//
//        database.InsertBelong(new Belong(6,13)
//        );
//        database.InsertBelong(new Belong(6,12)
//        );
//        database.InsertBelong(new Belong(6,11)
//        );
//        database.InsertBelong(new Belong(6,10)
//        );
//        database.InsertBelong(new Belong(6,1)
//        );
//        database.InsertBelong(new Belong(6,2)
//        );
//        database.InsertBelong(new Belong(6,3)
//        );
//        database.InsertBelong(new Belong(6,6)
//        );
//        database.InsertBelong(new Belong(6,9)
//        );
//        database.InsertBelong(new Belong(6,8)
//        );
//
//        database.InsertBelong(new Belong(7,31)
//        );
//        database.InsertBelong(new Belong(7,32)
//        );
//        database.InsertBelong(new Belong(7,33)
//        );
//        database.InsertBelong(new Belong(7,34)
//        );
//        database.InsertBelong(new Belong(7,35)
//        );
//        database.InsertBelong(new Belong(7,36)
//        );
//        database.InsertBelong(new Belong(7,37)
//        );
//        database.InsertBelong(new Belong(7,38)
//        );
//        database.InsertBelong(new Belong(7,39)
//        );
//        database.InsertBelong(new Belong(7,40)
//        );
//
//        database.InsertBelong(new Belong(8,35)
//        );
//        database.InsertBelong(new Belong(8,41)
//        );
//        database.InsertBelong(new Belong(8,42)
//        );
//        database.InsertBelong(new Belong(8,43)
//        );
//        database.InsertBelong(new Belong(8,44)
//        );
//        database.InsertBelong(new Belong(8,45)
//        );
//        database.InsertBelong(new Belong(8,36)
//        );
//        database.InsertBelong(new Belong(8,38)
//        );
//        database.InsertBelong(new Belong(8,39)
//        );
//        database.InsertBelong(new Belong(8,40)
//        );
//
//        database.InsertBelong(new Belong(9,32)
//        );
//        database.InsertBelong(new Belong(9,40)
//        );
//        database.InsertBelong(new Belong(9,39)
//        );
//        database.InsertBelong(new Belong(9,38)
//        );
//        database.InsertBelong(new Belong(9,37)
//        );
//        database.InsertBelong(new Belong(9,36)
//        );
//        database.InsertBelong(new Belong(9,35)
//        );
//        database.InsertBelong(new Belong(9,34)
//        );
//        database.InsertBelong(new Belong(9,32)
//        );
//        database.InsertBelong(new Belong(9,31)
//        );
//
//        database.InsertBelong(new Belong(10,34)
//        );
//        database.InsertBelong(new Belong(10,35)
//        );
//        database.InsertBelong(new Belong(10,36)
//        );
//        database.InsertBelong(new Belong(10,37)
//        );
//        database.InsertBelong(new Belong(10,38)
//        );
//        database.InsertBelong(new Belong(10,39)
//        );
//        database.InsertBelong(new Belong(10,40)
//        );
//        database.InsertBelong(new Belong(10,42)
//        );
//        database.InsertBelong(new Belong(10,41)
//        );
//        database.InsertBelong(new Belong(10,43)
//        );
//
//        database.InsertBelong(new Belong(11,41)
//        );
//        database.InsertBelong(new Belong(11,43)
//        );
//        database.InsertBelong(new Belong(11,31)
//        );
//        database.InsertBelong(new Belong(11,32)
//        );
//        database.InsertBelong(new Belong(11,33)
//        );
//        database.InsertBelong(new Belong(11,34)
//        );
//        database.InsertBelong(new Belong(11,35)
//        );
//        database.InsertBelong(new Belong(11,36)
//        );
//        database.InsertBelong(new Belong(11,37)
//        );
//        database.InsertBelong(new Belong(11,38)
//        );
//
//        database.InsertBelong(new Belong(12,21)
//        );
//        database.InsertBelong(new Belong(12,22)
//        );
//        database.InsertBelong(new Belong(12,23)
//        );
//        database.InsertBelong(new Belong(12,24)
//        );
//        database.InsertBelong(new Belong(12,25)
//        );
//        database.InsertBelong(new Belong(12,26)
//        );
//        database.InsertBelong(new Belong(12,27)
//        );
//        database.InsertBelong(new Belong(12,28)
//        );
//        database.InsertBelong(new Belong(12,29)
//        );
//        database.InsertBelong(new Belong(12,30)
//        );
//
//        database.InsertBelong(new Belong(13,30)
//        );
//        database.InsertBelong(new Belong(13,30)
//        );
//        database.InsertBelong(new Belong(13,28)
//        );
//        database.InsertBelong(new Belong(13,27)
//        );
//        database.InsertBelong(new Belong(13,26)
//        );
//        database.InsertBelong(new Belong(13,25)
//        );
//        database.InsertBelong(new Belong(13,24)
//        );
//        database.InsertBelong(new Belong(13,23)
//        );
//        database.InsertBelong(new Belong(13,22));
//        database.InsertBelong(new Belong(13,21)
//        );
//
//        database.InsertBelong(new Belong(14,26)
//        );
//        database.InsertBelong(new Belong(14,27)
//        );
//        database.InsertBelong(new Belong(14,28)
//        );
//        database.InsertBelong(new Belong(14,29));
//        database.InsertBelong(new Belong(14,30)
//        );
//        database.InsertBelong(new Belong(14,21)
//        );
//        database.InsertBelong(new Belong(14,22)
//        );
//        database.InsertBelong(new Belong(14,23)
//        );
//        database.InsertBelong(new Belong(14,24)
//        );
//        database.InsertBelong(new Belong(14,25)
//        );
//
//        database.InsertBelong(new Belong(15,30)
//        );
//        database.InsertBelong(new Belong(15,29)
//        );
//        database.InsertBelong(new Belong(15,28)
//        );
//        database.InsertBelong(new Belong(15,27)
//        );
//        database.InsertBelong(new Belong(15,26)
//        );
//        database.InsertBelong(new Belong(15,25)
//        );
//        database.InsertBelong(new Belong(15,21)
//        );
//        database.InsertBelong(new Belong(15,22)
//        );
//        database.InsertBelong(new Belong(15,23)
//        );
//        database.InsertBelong(new Belong(15,24)
//        );

        database.QueryData("drop Table if exists Cart");
        database.QueryData("CREATE TABLE IF NOT EXISTS Cart(idUser INTEGER,LineItemId INTEGER)");

        database.QueryData("drop Table if exists LineItem");
        database.QueryData("CREATE TABLE IF NOT EXISTS LineItem(idUser INTEGER, foodId INTEGER, amount INTEGER)");
          database.QueryData("CREATE TABLE IF NOT EXISTS Voucher(id INTEGER PRIMARY KEY AUTOINCREMENT, name INTEGER, price INTEGER)");
          database.QueryData("CREATE TABLE IF NOT EXISTS Bill(id INTEGER PRIMARY KEY AUTOINCREMENT, idUser INTEGER, foodName VARCHAR(150), amount INTEGER, voucher VARCHAR(150), time VARCHAR(150))");
        database.QueryData("CREATE TABLE IF NOT EXISTS User( id INTEGER PRIMARY KEY AUTOINCREMENT, userName VARCHAR(150), password VARCHAR(150))");

    }
    public int toByteArray(int id){
//        Bitmap bitmap = BitmapFactory.decodeResource(getResources(), id);
//        ByteArrayOutputStream byteArray = new ByteArrayOutputStream();
//        bitmap.compress(Bitmap.CompressFormat.PNG, 100, byteArray);
//        byte[] img = byteArray.toByteArray();
        return id;
    }
}