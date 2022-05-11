package hcmute.edu.vn.foodie_infinity.Adapter;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.viewpager2.adapter.FragmentStateAdapter;



import java.io.Serializable;
import java.util.List;

import hcmute.edu.vn.foodie_infinity.Fragment.HomeFragment;
import hcmute.edu.vn.foodie_infinity.Fragment.SliderFragment;
import hcmute.edu.vn.foodie_infinity.Model.Slider;

public class SliderAdapter extends FragmentStateAdapter {

    private List<Slider> sliders;

    public SliderAdapter(@NonNull FragmentActivity fragmentActivity, List<Slider> list) {
        super(fragmentActivity);

        this.sliders = list;
    }

    @NonNull
    @Override
    public Fragment createFragment(int position) {
        Slider slider = sliders.get(position);

        Bundle bundle = new Bundle();
        bundle.putSerializable("objectSlider", (Serializable) slider);
        SliderFragment silderFragment = new SliderFragment();
        silderFragment.setArguments(bundle);

        return silderFragment;
    }

    @Override
    public int getItemCount() {
        if(sliders != null)
        {
            return sliders.size();
        }
        return 0;
    }

}
