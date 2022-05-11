package hcmute.edu.vn.foodie_infinity.Fragment;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import hcmute.edu.vn.foodie_infinity.Activity.CartActivity;
import hcmute.edu.vn.foodie_infinity.Activity.HistoryActivity;
import hcmute.edu.vn.foodie_infinity.Activity.SigninActivity;
import hcmute.edu.vn.foodie_infinity.Model.User;
import hcmute.edu.vn.foodie_infinity.R;


public class ProfileFragment extends Fragment {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_profile, container, false);
        Button history_button = (Button) view.findViewById(R.id.history_button);
        ImageView btn_logOut = (ImageView) view.findViewById(R.id.btn_logOut);
        TextView username_tv = (TextView) view.findViewById(R.id.username_tv);
        TextView id_tv = (TextView) view.findViewById(R.id.id_tv);
        ImageView imageView2 = (ImageView) view.findViewById(R.id.imageView2);



        username_tv.setText(User.userName);
        id_tv.setText( "ID: " + Integer.toString(User.id));

        history_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity(), HistoryActivity.class);
                getActivity().startActivity(intent);
            }
        });
        btn_logOut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity(), SigninActivity.class);
                getActivity().startActivity(intent);
            }
        });

        imageView2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity(), CartActivity.class);
                getActivity().startActivity(intent);
            }
        });


        return view;
    }
}