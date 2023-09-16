package com.example.travelagency.ui.Settings;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.example.travelagency.AboutDatabase.Agency;
import com.example.travelagency.AboutDatabase.Package;
import com.example.travelagency.AboutDatabase.Trip;
import com.example.travelagency.MainActivity;
import com.example.travelagency.R;
import com.example.travelagency.databinding.FragmentSettingsBinding;
import com.example.travelagency.databinding.HomeFragmentBinding;
import com.google.firebase.firestore.FirebaseFirestore;

public class SettingsFragment extends Fragment {

    private FragmentSettingsBinding binding;
    EditText agencyId;
    EditText agencyName;
    EditText agencyAddress;

    EditText tripId;
    EditText tripCity;
    EditText tripCountry;

    EditText packageId;
    EditText packageAgencyId;
    EditText packageTripId;
    EditText packageDuration;
    EditText packageDate;
    EditText packagePrice;
    EditText packageTripType;

    Button bt1;
    Button bt2;
    Button bt3;
    Button bt4;
    Button bt5;
    Button bt6;
    Button bt7;
    Button bt8;
    Button bt9;


    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {


        binding = FragmentSettingsBinding.inflate(inflater, container, false);


        View view = inflater.inflate(R.layout.fragment_settings,container,false);

        agencyId =view.findViewById(R.id.agencyid);
        agencyName = view.findViewById(R.id.agencyname);
        agencyAddress = view.findViewById(R.id.agencyaddress);

        tripId = view.findViewById(R.id.tripid);
        tripCity = view.findViewById(R.id.tripcity);
        tripCountry = view.findViewById(R.id.tripcountry);

        packageId = view.findViewById(R.id.packageid);
        packageAgencyId = view.findViewById(R.id.packageagencyid);
        packageTripId = view.findViewById(R.id.packagetripid);
        packageDuration = view.findViewById(R.id.packageduration);
        packageDate = view.findViewById(R.id.packagedate);
        packagePrice = view.findViewById(R.id.packageprice);
        packageTripType = view.findViewById(R.id.packagetriptype);

        bt1= view.findViewById(R.id.addAgency);
        bt2 = view.findViewById(R.id.updateAgency);
        bt3 = view.findViewById(R.id.deleteAgency);

        bt4 = view.findViewById(R.id.addDestination);
        bt5 = view.findViewById(R.id.updateDestination);
        bt6 = view.findViewById(R.id.deleteDestination);

        bt7 = view.findViewById(R.id.addPackage);
        bt8 = view.findViewById(R.id.updatePackage);
        bt9 = view.findViewById(R.id.deletePackage);

        //GIA TO AGENCY
        //ADD AGENCY
        bt1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int var_agencyid=0;
                try{
                    var_agencyid = Integer.parseInt(agencyId.getText().toString());
                }
                catch (NumberFormatException ex){
                    System.out.println("Could not parse "+ex);
                }
                String var_agencyname= agencyName.getText().toString();
                String var_agencyaddress= agencyAddress.getText().toString();

                try {
                    if(TextUtils.isEmpty(agencyId.getText().toString()) && TextUtils.isEmpty(agencyName.getText().toString()) && TextUtils.isEmpty(agencyAddress.getText().toString()))
                        Toast.makeText(getActivity(),"Empty fields not allowed",Toast.LENGTH_LONG).show();
                    else {
                        Agency agency = new Agency();
                        agency.setId(var_agencyid);
                        agency.setName(var_agencyname);
                        agency.setAddress(var_agencyaddress);
                        MainActivity.MyDatabase.mydaotemp().addAgency(agency);
                        Toast.makeText(getActivity(), "Agency added successfully", Toast.LENGTH_LONG).show();
                    }
                }
                catch (Exception e){
                    String message = e.getMessage();
                    Toast.makeText(getActivity(),message,Toast.LENGTH_LONG).show();
                }

                agencyId.setText("");
                agencyName.setText("");
                agencyAddress.setText("");
            }
        });
        //UPDATE AGENCY
        bt2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int var_agencyid=0;
                try{
                    var_agencyid = Integer.parseInt(agencyId.getText().toString());
                }
                catch (NumberFormatException ex){
                    System.out.println("Could not parse "+ex);
                }
                String var_agencyname= agencyName.getText().toString();
                String var_agencyaddress= agencyAddress.getText().toString();
                try {
                    if(TextUtils.isEmpty(agencyId.getText().toString()) && TextUtils.isEmpty(agencyName.getText().toString()) && TextUtils.isEmpty(agencyAddress.getText().toString()))
                        Toast.makeText(getActivity(),"Empty fields not allowed",Toast.LENGTH_LONG).show();
                    else {
                        Agency agency = new Agency();
                        agency.setId(var_agencyid);
                        agency.setName(var_agencyname);
                        agency.setAddress(var_agencyaddress);
                        MainActivity.MyDatabase.mydaotemp().updateAgency(agency);
                        Toast.makeText(getActivity(), "Agency updated successfully", Toast.LENGTH_LONG).show();
                    }
                }
                catch (Exception e){
                    String message = e.getMessage();
                    Toast.makeText(getActivity(),message,Toast.LENGTH_LONG).show();
                }

                agencyId.setText("");
                agencyName.setText("");
                agencyAddress.setText("");
            }
        });
        //DELETE AGENCY
        bt3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int var_id=0;
                try {
                    var_id=Integer.parseInt(agencyId.getText().toString());
                }catch (NumberFormatException ex){
                    System.out.println("Could not parse "+ex);
                }
                if(TextUtils.isEmpty(agencyId.getText().toString()))
                    Toast.makeText(getActivity(),"Empty fields not allowed",Toast.LENGTH_LONG).show();
                else {
                    Agency agency = new Agency();
                    agency.setId(var_id);
                    MainActivity.MyDatabase.mydaotemp().deleteAgency(agency);
                    Toast.makeText(getActivity(), "Agency deleted successfully", Toast.LENGTH_LONG).show();
                    agencyId.setText("");
                }
            }
        });
        //GIA TA DESTINATION
        //ADD DESTINATION
        bt4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int var_destinationid=0;
                try{
                        var_destinationid = Integer.parseInt(tripId.getText().toString());
                }
                catch (NumberFormatException ex){
                    System.out.println("Could not parse "+ex);
                }
                String var_destinationCity= tripCity.getText().toString();
                String var_destinationCountry= tripCountry.getText().toString();
                try {
                    if(TextUtils.isEmpty(tripId.getText().toString()) && TextUtils.isEmpty(tripCity.getText().toString()) && TextUtils.isEmpty(tripCountry.getText().toString()))
                        Toast.makeText(getActivity(),"Empty fields not allowed",Toast.LENGTH_LONG).show();
                    else {
                        Trip trip = new Trip();
                        trip.setId(var_destinationid);
                        trip.setCity(var_destinationCity);
                        trip.setCountry(var_destinationCountry);
                        MainActivity.MyDatabase.mydaotemp().addTrip(trip);
                        Toast.makeText(getActivity(), "Destination added successfully", Toast.LENGTH_LONG).show();
                    }
                }
                catch (Exception e){
                    String message = e.getMessage();
                    Toast.makeText(getActivity(),message,Toast.LENGTH_LONG).show();
                }

                tripId.setText("");
                tripCity.setText("");
                tripCountry.setText("");
            }
        });
        //UPDATE DESTINATION
        bt5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int var_destinationid=0;
                try{
                    var_destinationid = Integer.parseInt(tripId.getText().toString());
                }
                catch (NumberFormatException ex){
                    System.out.println("Could not parse "+ex);
                }
                String var_destinationCity= tripCity.getText().toString();
                String var_destinationCountry= tripCountry.getText().toString();
                try {
                    if(TextUtils.isEmpty(tripId.getText().toString()) && TextUtils.isEmpty(tripCity.getText().toString()) && TextUtils.isEmpty(tripCountry.getText().toString()))
                        Toast.makeText(getActivity(),"Empty fields not allowed",Toast.LENGTH_LONG).show();
                    else {
                        Trip trip = new Trip();
                        trip.setId(var_destinationid);
                        trip.setCity(var_destinationCity);
                        trip.setCountry(var_destinationCountry);
                        MainActivity.MyDatabase.mydaotemp().updateTrip(trip);
                        Toast.makeText(getActivity(), "Destination updated successfully", Toast.LENGTH_LONG).show();
                    }
                }
                catch (Exception e){
                    String message = e.getMessage();
                    Toast.makeText(getActivity(),message,Toast.LENGTH_LONG).show();
                }

                tripId.setText("");
                tripCity.setText("");
                tripCountry.setText("");
            }
        });
        //DELETE DESTINATION
        bt6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int var_id=0;
                try {
                    var_id=Integer.parseInt(tripId.getText().toString());
                }catch (NumberFormatException ex){
                    System.out.println("Could not parse "+ex);
                }
                if(TextUtils.isEmpty(tripId.getText().toString()))
                    Toast.makeText(getActivity(),"Empty fields not allowed",Toast.LENGTH_LONG).show();
                else {
                    Trip trip = new Trip();
                    trip.setId(var_id);
                    MainActivity.MyDatabase.mydaotemp().deleteTrip(trip);
                    Toast.makeText(getActivity(), "Destination deleted successfully", Toast.LENGTH_LONG).show();
                    agencyId.setText("");
                }
            }
        });
        //ADD PACKAGE
        bt7.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int var_packid=0;
                try{
                    var_packid = Integer.parseInt(packageId.getText().toString());
                }
                catch (NumberFormatException ex){
                    System.out.println("Could not parse "+ex);
                }
                int var_packAgencyId=0;
                try{
                    var_packAgencyId = Integer.parseInt(packageAgencyId.getText().toString());
                }
                catch (NumberFormatException ex){
                    System.out.println("Could not parse "+ex);
                }
                int var_packTripId=0;
                try{
                    var_packTripId = Integer.parseInt(packageTripId.getText().toString());
                }
                catch (NumberFormatException ex){
                    System.out.println("Could not parse "+ex);
                }
                int var_duration=0;
                try{
                    var_duration = Integer.parseInt(packageDuration.getText().toString());
                }
                catch (NumberFormatException ex){
                    System.out.println("Could not parse "+ex);
                }
                String var_packDate= packageDate.getText().toString();
                int var_price=0;
                try{
                    var_price = Integer.parseInt(packagePrice.getText().toString());
                }
                catch (NumberFormatException ex){
                    System.out.println("Could not parse "+ex);
                }
                String var_packTripType= packageTripType.getText().toString();

                try {
                    if(TextUtils.isEmpty(packageId.getText().toString())
                            && TextUtils.isEmpty(packageAgencyId.getText().toString())
                            && TextUtils.isEmpty(packageTripId.getText().toString())
                            && TextUtils.isEmpty(packageDate.getText().toString())
                            && TextUtils.isEmpty(packagePrice.getText().toString())
                            && TextUtils.isEmpty(packageDuration.getText().toString())
                            && TextUtils.isEmpty(packageTripType.getText().toString()))
                        Toast.makeText(getActivity(),"Empty fields not allowed",Toast.LENGTH_LONG).show();
                    else {
                        Package pack = new Package();
                        pack.setId(var_packid);
                        pack.setAid(var_packAgencyId);
                        pack.setTid(var_packTripId);
                        pack.setDuration(var_duration);
                        pack.setDate(var_packDate);
                        pack.setPrice(var_price);
                        pack.setTriptype(var_packTripType);
                        MainActivity.MyDatabase.mydaotemp().addPackage(pack);
                        Toast.makeText(getActivity(), "Package added successfully", Toast.LENGTH_LONG).show();
                    }
                }
                catch (Exception e){
                    String message = e.getMessage();
                    Toast.makeText(getActivity(),message,Toast.LENGTH_LONG).show();
                }

                packageId.setText("");
                packageAgencyId.setText("");
                packageTripId.setText("");
                packageDuration.setText("");
                packageDate.setText("");
                packagePrice.setText("");
                packageTripType.setText("");
            }
        });
        //UPDATE PACKAGE
        bt8.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int var_packid=0;
                try{
                    var_packid = Integer.parseInt(packageId.getText().toString());
                }
                catch (NumberFormatException ex){
                    System.out.println("Could not parse "+ex);
                }
                int var_packAgencyId=0;
                try{
                    var_packAgencyId = Integer.parseInt(packageAgencyId.getText().toString());
                }
                catch (NumberFormatException ex){
                    System.out.println("Could not parse "+ex);
                }
                int var_packTripId=0;
                try{
                    var_packTripId = Integer.parseInt(packageTripId.getText().toString());
                }
                catch (NumberFormatException ex){
                    System.out.println("Could not parse "+ex);
                }
                int var_duration=0;
                try{
                    var_duration = Integer.parseInt(packageDuration.getText().toString());
                }
                catch (NumberFormatException ex){
                    System.out.println("Could not parse "+ex);
                }
                String var_packDate= packageDate.getText().toString();
                int var_price=0;
                try{
                    var_price = Integer.parseInt(packagePrice.getText().toString());
                }
                catch (NumberFormatException ex){
                    System.out.println("Could not parse "+ex);
                }
                String var_packTripType= packageTripType.getText().toString();

                try {
                    if(TextUtils.isEmpty(packageId.getText().toString())
                            && TextUtils.isEmpty(packageAgencyId.getText().toString())
                            && TextUtils.isEmpty(packageTripId.getText().toString())
                            && TextUtils.isEmpty(packageDate.getText().toString())
                            && TextUtils.isEmpty(packagePrice.getText().toString())
                            && TextUtils.isEmpty(packageDuration.getText().toString())
                            && TextUtils.isEmpty(packageTripType.getText().toString()))
                        Toast.makeText(getActivity(),"Empty fields not allowed",Toast.LENGTH_LONG).show();
                    else {
                        Package pack = new Package();
                        pack.setId(var_packid);
                        pack.setAid(var_packAgencyId);
                        pack.setTid(var_packTripId);
                        pack.setDuration(var_duration);
                        pack.setDate(var_packDate);
                        pack.setPrice(var_price);
                        pack.setTriptype(var_packTripType);
                        MainActivity.MyDatabase.mydaotemp().updatePackage(pack);
                        Toast.makeText(getActivity(), "Package updated successfully", Toast.LENGTH_LONG).show();
                    }
                }
                catch (Exception e){
                    String message = e.getMessage();
                    Toast.makeText(getActivity(),message,Toast.LENGTH_LONG).show();
                }

                packageId.setText("");
                packageAgencyId.setText("");
                packageTripId.setText("");
                packageDuration.setText("");
                packageDate.setText("");
                packagePrice.setText("");
                packageTripType.setText("");
            }
        });
        //DELETE PACKAGE
        bt9.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int var_id=0;
                try {
                    var_id=Integer.parseInt(packageId.getText().toString());
                }catch (NumberFormatException ex){
                    System.out.println("Could not parse "+ex);
                }
                if(TextUtils.isEmpty(packageId.getText().toString()))
                    Toast.makeText(getActivity(),"Empty fields not allowed",Toast.LENGTH_LONG).show();
                else {
                    Package pack = new Package();
                    pack.setId(var_id);
                    MainActivity.MyDatabase.mydaotemp().deletePackage(pack);
                    Toast.makeText(getActivity(), "Package deleted successfully", Toast.LENGTH_LONG).show();
                    packageId.setText("");
                }
            }
        });
        return view;
    }
  @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}