package com.example.travelagency.ui.Destinations;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.example.travelagency.AboutDatabase.Trip;
import com.example.travelagency.MainActivity;
import com.example.travelagency.databinding.FragmentDestinationsBinding;

import java.util.List;

public class DestinationsFragment extends Fragment {

    private FragmentDestinationsBinding binding;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
       // DestinationsViewModel galleryViewModel =
                //new ViewModelProvider(this).get(DestinationsViewModel.class);

        binding = FragmentDestinationsBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        Button button2 = binding.bt2;
        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String res = "";
                TextView txt = binding.text10;
                List<Trip> trips = MainActivity.MyDatabase.mydaotemp().getTrip();
                for (Trip i : trips) {

                    int id = i.getId();
                    String city = i.getCity();
                    String country = i.getCountry();
                    res =res+ "\n Id: " + id + "\n City: " + city + "\n Country: " + country + "\n";
                    Toast.makeText(getActivity(), "Στοιχεία Προορισμών", Toast.LENGTH_SHORT).show();

                }
                txt.setText(res);

            }
        });
        return root;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}