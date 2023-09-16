package com.example.travelagency;

import android.app.Activity;
import android.os.Bundle;
import android.text.SpannableString;
import android.text.style.UnderlineSpan;
import android.view.View;
import android.view.Menu;
import android.widget.TextView;

import com.example.travelagency.AboutDatabase.Agency;
import com.example.travelagency.AboutDatabase.MyDatabase;
import com.example.travelagency.AboutDatabase.Package;
import com.example.travelagency.AboutDatabase.Trip;
import com.google.android.material.snackbar.Snackbar;
import com.google.android.material.navigation.NavigationView;

import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.appcompat.app.AppCompatActivity;
import androidx.room.Room;

import com.example.travelagency.databinding.ActivityMainBinding;
import com.google.firebase.firestore.FirebaseFirestore;
import java.util.List;

public class MainActivity extends AppCompatActivity    {

    private AppBarConfiguration mAppBarConfiguration;
    private ActivityMainBinding binding;
    public static MyDatabase MyDatabase;
    public static FirebaseFirestore firestoredb;

    //Ta built-in stoixeia pou theloume na exei h vash

    Agency agency1 = new Agency(1,"Sky Express","Κατσιμίδη 74");
    Agency agency2 = new Agency(2,"Ryanair","Μαρκ. Μπότσαρη 63");
    Agency agency3 = new Agency(3,"Aegean","Λυκάονος 13");
    Agency agency4 = new Agency(4,"Olympic Air","Βασιλίσσης Όλγας 198");

    //Ta trip einai ta destination nohmatika
    Trip trip1 = new Trip(1,"London","United Kingdom");
    Trip trip2 = new Trip(2,"Paris","France");
    Trip trip3 = new Trip(3,"Bahamas","Caribbean Islands");
    Trip trip4 = new Trip(4,"Los Angeles","USA");
    Trip trip5 = new Trip(5,"Barcelona","Spain");

    Package package1 = new Package(1,3,4,5,"12/8/2022",550,"Road Trip");
    Package package2 = new Package(2,1,5,8,"5/8/2023",340,"Road Trip");
    Package package3 = new Package(3,2,1,7,"15/10/2023",350,"Cruise");
    Package package4 = new Package(4,4,2,8,"23/12/2023",330,"Road Trip");
    Package package5 = new Package(5,3,1,4,"18/11/2023",150,"Cruise");
    Package package6 = new Package(6,2,2,6,"12/6/2023",280,"Cruise");
    Package package7 = new Package(7,4,3,2,"7/7/2023",250,"Cruise");
    Package package8 = new Package(8,1,4,3,"28/6/2023",450,"Cruise");
    Package package9 = new Package(9,3,5,5,"15/5/2023",220,"Cruise");

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        MyDatabase= Room.databaseBuilder(getApplicationContext(),MyDatabase.class,"user").allowMainThreadQueries().build();
        firestoredb = FirebaseFirestore.getInstance();


        if(MainActivity.MyDatabase.mydaotemp().getAgency().isEmpty()) {
            MainActivity.MyDatabase.mydaotemp().addAgency(agency1);
            MainActivity.MyDatabase.mydaotemp().addAgency(agency2);
            MainActivity.MyDatabase.mydaotemp().addAgency(agency3);
            MainActivity.MyDatabase.mydaotemp().addAgency(agency4);
        }
        if(MainActivity.MyDatabase.mydaotemp().getTrip().isEmpty()) {
            MainActivity.MyDatabase.mydaotemp().addTrip(trip1);
            MainActivity.MyDatabase.mydaotemp().addTrip(trip2);
            MainActivity.MyDatabase.mydaotemp().addTrip(trip3);
            MainActivity.MyDatabase.mydaotemp().addTrip(trip4);
            MainActivity.MyDatabase.mydaotemp().addTrip(trip5);
        }
        if(MainActivity.MyDatabase.mydaotemp().getPackage().isEmpty()) {
            MainActivity.MyDatabase.mydaotemp().addPackage(package1);
            MainActivity.MyDatabase.mydaotemp().addPackage(package2);
            MainActivity.MyDatabase.mydaotemp().addPackage(package3);
            MainActivity.MyDatabase.mydaotemp().addPackage(package4);
            MainActivity.MyDatabase.mydaotemp().addPackage(package5);
            MainActivity.MyDatabase.mydaotemp().addPackage(package6);
            MainActivity.MyDatabase.mydaotemp().addPackage(package7);
            MainActivity.MyDatabase.mydaotemp().addPackage(package8);
            MainActivity.MyDatabase.mydaotemp().addPackage(package9);
        }





        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        setSupportActionBar(binding.appBarMain.toolbar);

        DrawerLayout drawer = binding.drawerLayout;
        NavigationView navigationView = binding.navView;
        // Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations.
        mAppBarConfiguration = new AppBarConfiguration.Builder(
                R.id.nav_Destinations, R.id.nav_Agencies, R.id.nav_Packages,R.id.nav_Home,R.id.nav_Settings,R.id.nav_Query)
                .setOpenableLayout(drawer)
                .build();
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment_content_main);
        NavigationUI.setupActionBarWithNavController(this, navController, mAppBarConfiguration);
        NavigationUI.setupWithNavController(navigationView, navController);


    }



    @Override
    public boolean onSupportNavigateUp() {
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment_content_main);
        return NavigationUI.navigateUp(navController, mAppBarConfiguration)
                || super.onSupportNavigateUp();
    }
}