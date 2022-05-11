package hcmute.edu.vn.foodie_infinity.Fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import hcmute.edu.vn.foodie_infinity.Model.Slider;
import hcmute.edu.vn.foodie_infinity.R;

public class SliderFragment extends Fragment {

    private View view;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_slider, container, false);
        Bundle bundle = getArguments();
        Slider slider = (Slider) bundle.get("objectSlider");
        ImageView Img  = view.findViewById(R.id.img_photo);
        Img.setImageResource(slider.getResourceId());
        return view;
    }
}
