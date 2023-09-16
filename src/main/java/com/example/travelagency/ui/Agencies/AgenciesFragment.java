package com.example.travelagency.ui.Agencies;

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

import com.example.travelagency.AboutDatabase.Agency;
import com.example.travelagency.MainActivity;
import com.example.travelagency.databinding.FragmentAgenciesBinding;

import java.util.List;

public class AgenciesFragment extends Fragment {

    private FragmentAgenciesBinding binding;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
       // AgenciesViewModel homeViewModel =
             // new ViewModelProvider(this).get(AgenciesViewModel.class);

        binding = FragmentAgenciesBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        Button button = binding.bt;
        TextView txt = binding.text3;
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String res = "";
                List<Agency> agencies = MainActivity.MyDatabase.mydaotemp().getAgency();
                for (Agency i : agencies) {
                    int id = i.getId();
                    String name = i.getName();
                    String address = i.getAddress();
                    res = res+ "\n Id: " + id + "\n Name: " + name + "\n Address: " + address + "\n";
                    Toast.makeText(getActivity(), "Στοιχεία πρακτορείων", Toast.LENGTH_SHORT).show();

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