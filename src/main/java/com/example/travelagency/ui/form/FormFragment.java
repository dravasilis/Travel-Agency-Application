package com.example.travelagency.ui.form;

import android.content.Context;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.room.Query;

import com.example.travelagency.AboutDatabase.Agency;
import com.example.travelagency.AboutDatabase.Package;
import com.example.travelagency.AboutDatabase.Trip;
import com.example.travelagency.FirestoreDB.Client;
import com.example.travelagency.MainActivity;
import com.example.travelagency.R;
import com.example.travelagency.databinding.FragmentFormBinding;
import com.example.travelagency.databinding.FragmentPackagesBinding;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import java.util.List;

public class FormFragment extends Fragment {


    private FragmentFormBinding binding;
    Button add;
    Button update;
    Button delete;
    Button delete2;
    EditText name;
    EditText surname;
    EditText email;
    EditText code;
    EditText delsurname;
    String hotel; // String pou apothikeuw tin epilogi tou hotel apo to spinner!
     public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        binding = FragmentFormBinding.inflate(inflater, container, false);
        View view = inflater.inflate(R.layout.fragment_form,container,false);


         /**
          * hotel spinner ||
          *               ||
          *               \/
          */
         Spinner spinner = view.findViewById(R.id.spinner);
         // Create an ArrayAdapter using the string array and a default spinner layout
         ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(getActivity(),
                 R.array.planets_array, android.R.layout.simple_spinner_item);
         // Specify the layout to use when the list of choices appears
         adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
         // Apply the adapter to the spinner
         spinner.setAdapter(adapter);
         /**
          * end of spinner
          */
         /**
          * below is when the user selects 1 hotel from the 8 , and we save this value on hotel variable ,
          * so afterwards we can put this value on the users "profile" on the cloud database
          *    ||
          *    ||
          *    \/
          */
         spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
             @Override
             public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                 switch (i){
                     case 0:
                         hotel ="Moss Hotel";
                         break;
                     case 1:
                         hotel = "Windmill Hotel";
                         break;
                     case 2:
                         hotel ="Fancy Hotel";
                         break;
                     case 3:
                         hotel = "Grand Palms Hotel";
                         break;
                     case 4:
                         hotel = "Zion Hotel";
                         break;
                     case 5:
                         hotel = "Lunar Motel";
                         break;
                     case 6:
                         hotel = "Rose Cloud Hotel";
                         break;
                     case 7:
                         hotel = "Diorama Hotel";
                         break;
                 }
             }
             @Override
             public void onNothingSelected(AdapterView<?> adapterView) {

             }
         });

        Toast.makeText(getActivity(), "Please fill the form.", Toast.LENGTH_SHORT).show();

        add = view.findViewById(R.id.addClient);
        update = view.findViewById(R.id.updateClient);
        delete = view.findViewById(R.id.deleteClient);
        delete2 = view.findViewById(R.id.delete2);

        name = view.findViewById(R.id.name);
        surname = view.findViewById(R.id.surname);
        email = view.findViewById(R.id.email);
        code = view.findViewById(R.id.packagecode);
        delsurname = view.findViewById(R.id.id);



        //ADD CLIENT
        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String var_name = name.getText().toString();
                String var_surname = surname.getText().toString();
                String var_email = email.getText().toString();
                int var_code = 0;
                try {
                    var_code = Integer.parseInt(code.getText().toString());
                }
                catch (NumberFormatException ex) {
                    System.out.println("Count not parse " + ex);
                }
                try {
                    if(TextUtils.isEmpty(name.getText().toString()))
                        Toast.makeText(getActivity(),"Empty fields not allowed",Toast.LENGTH_LONG).show();
                    else {
                        Client client = new Client();
                        client.setName(var_name);
                        client.setSurname(var_surname);
                        client.setEmail(var_email);
                        client.setCode(var_code);
                        client.setHotel(hotel);
                        MainActivity.firestoredb.
                                collection("Users").
                                document("" + var_surname).
                                set(client).
                                addOnCompleteListener(new OnCompleteListener<Void>() {
                                    @Override
                                    public void onComplete(@NonNull Task<Void> task) {
                                        Toast.makeText(getActivity(),"Client Registered",Toast.LENGTH_LONG).show();

                                    }
                                })
                                .addOnFailureListener(new OnFailureListener() {
                                    @Override
                                    public void onFailure(@NonNull Exception e) {
                                        Toast.makeText(getActivity(),"Registration failed",Toast.LENGTH_LONG).show();
                                    }
                                });
                    }
                }
                catch (Exception e){
                    String message = e.getMessage();
                    Toast.makeText(getActivity(), message, Toast.LENGTH_LONG).show();
                }
                name.setText("");
                surname.setText("");
                email.setText("");
                code.setText("");

            }
        });
         //UPDATE CLIENT
         update.setOnClickListener(new View.OnClickListener() {
             @Override
             public void onClick(View view) {
                 String var_name = name.getText().toString();
                 String var_surname = surname.getText().toString();
                 String var_email = email.getText().toString();
                 int var_code = 0;
                 try {
                     var_code = Integer.parseInt(code.getText().toString());
                 }
                 catch (NumberFormatException ex) {
                     System.out.println("Count not parse " + ex);
                 }
                 try {
                     if(TextUtils.isEmpty(name.getText().toString()))
                         Toast.makeText(getActivity(),"Empty fields not allowed",Toast.LENGTH_LONG).show();
                     else {
                         Client client = new Client();
                         client.setName(var_name);
                         client.setSurname(var_surname);
                         client.setEmail(var_email);
                         client.setCode(var_code);
                         client.setHotel(hotel);
                         MainActivity.firestoredb.
                                 collection("Users").
                                 document("" + surname.getText().toString()).
                                 set(client).
                                 addOnCompleteListener(new OnCompleteListener<Void>() {
                                     @Override
                                     public void onComplete(@NonNull Task<Void> task) {
                                         Toast.makeText(getActivity(),"Client Registered",Toast.LENGTH_LONG).show();

                                     }
                                 })
                                 .addOnFailureListener(new OnFailureListener() {
                                     @Override
                                     public void onFailure(@NonNull Exception e) {
                                         Toast.makeText(getActivity(),"Registration failed",Toast.LENGTH_LONG).show();
                                     }
                                 });
                     }
                 }
                 catch (Exception e){
                     String message = e.getMessage();
                     Toast.makeText(getActivity(), message, Toast.LENGTH_LONG).show();
                 }
                 name.setText("");
                 surname.setText("");
                 email.setText("");
                 code.setText("");

             }
         });
        //DELETE CLIENT
        delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //Me kathe click sto button antistrefw thn katastash oratothtas twn stoixeiwn
                if(delete2.getVisibility() == View.VISIBLE){
                    delete2.setVisibility(View.INVISIBLE);
                    delsurname.setVisibility(View.INVISIBLE);
                }
                else {
                    delete2.setVisibility(View.VISIBLE);
                    delsurname.setVisibility(View.VISIBLE);
                }
                delete2.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        if(TextUtils.isEmpty(delsurname.getText().toString()))
                            Toast.makeText(getActivity(),"Empty fields not allowed",Toast.LENGTH_LONG).show();
                        else {
                            MainActivity.firestoredb.collection("Users").document("" + delsurname.getText().toString()).delete();
                            Toast.makeText(getActivity(), "Client deleted", Toast.LENGTH_LONG).show();
                            delete2.setVisibility(View.INVISIBLE);
                            delsurname.setVisibility(View.INVISIBLE);
                        }

                    }
                });
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