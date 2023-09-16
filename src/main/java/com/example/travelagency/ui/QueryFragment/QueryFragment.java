package com.example.travelagency.ui.QueryFragment;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.example.travelagency.MainActivity;
import com.example.travelagency.R;
import com.example.travelagency.databinding.FragmentQueryBinding;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.Query;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.List;

public class QueryFragment extends Fragment {

    private FragmentQueryBinding binding;

    Button bt1;
    Button hidebt1;
    TextView result1;

    Button bt2;
    Button hidebt2;
    TextView result2;

    Button bt3;
    Button hidebt3;
    TextView result3;


    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {


        binding = FragmentQueryBinding.inflate(inflater, container, false);
        View view = inflater.inflate(R.layout.fragment_query,container,false);

        //Q1
        bt1 = view.findViewById(R.id.q1);
        hidebt1 = view.findViewById(R.id.Hide);
        result1 = view.findViewById(R.id.result1);
        bt1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                hidebt1.setVisibility(View.VISIBLE);
                String result = "";
                List<Integer> packages = MainActivity.MyDatabase.mydaotemp().getQuery1();
                for(Integer i: packages){
                    int id = i;
                    result = result + "Id: "+id  +"\n";
                }
                result1.setVisibility(View.VISIBLE);
                result1.setText(result);

                hidebt1.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    hidebt1.setVisibility(View.INVISIBLE);
                    result1.setVisibility(View.INVISIBLE);
                    result1.setText("");
                }
            });
            }
        });

        //Q2
        bt2 = view.findViewById(R.id.q2);
        hidebt2 = view.findViewById(R.id.Hide2);
        result2 = view.findViewById(R.id.result2);
        bt2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                hidebt2.setVisibility(View.VISIBLE);
                String result = "";
                List<Integer> packages = MainActivity.MyDatabase.mydaotemp().getQuery2();
                for(Integer i: packages){
                    int id = i;
                    result = result + "Id: "+id  +"\n";
                }
                result2.setVisibility(View.VISIBLE);
                result2.setText(result);
                hidebt2.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        hidebt2.setVisibility(View.INVISIBLE);
                        result2.setVisibility(View.INVISIBLE);
                        result2.setText("");
                    }
                });
            }
        });


        //Q3
        bt3 = view.findViewById(R.id.q3);
        hidebt3 = view.findViewById(R.id.Hide3);
        result3 = view.findViewById(R.id.result3);
        bt3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                hidebt3.setVisibility(View.VISIBLE);
                String result = "";
                List<Integer> packages = MainActivity.MyDatabase.mydaotemp().getQuery3();
                for(Integer i: packages){
                    int id = i;
                    result = result + "Id: "+id  +"\n";
                }
                result3.setVisibility(View.VISIBLE);
                result3.setText(result);
                hidebt3.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        hidebt3.setVisibility(View.INVISIBLE);
                        result3.setVisibility(View.INVISIBLE);
                        result3.setText("");
                    }
                });
            }
        });

        /**
         * firebase first querie
         */
        Button showusers = view.findViewById(R.id.q4);
        TextView result4 = view.findViewById(R.id.result4);
        Button hidebt4 = view.findViewById(R.id.hide4);
        showusers.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                hidebt4.setVisibility(View.VISIBLE);
                CollectionReference re = MainActivity.firestoredb.collection("Users");
                Query query = re.whereEqualTo("hotel","Rose Cloud Hotel");
                query.get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        if (task.isSuccessful()) {
                            String res="";
                            for (QueryDocumentSnapshot document :
                                    task.getResult()) {
                                String name = document.getString("name");
                                String surname = document.getString("surname");
                                res = res + "\n" + " NAME : " + name + "\nSURNAME : " + surname + "\n\n";
                            }
                            result4.setVisibility(View.VISIBLE);
                            result4.setText(res);
                            hidebt4.setOnClickListener(new View.OnClickListener() {
                                @Override
                                public void onClick(View view) {
                                    hidebt4.setVisibility(View.INVISIBLE);
                                    result4.setVisibility(View.INVISIBLE);
                                    result4.setText("");
                                }
                            });
                        }
                    }
                });
            }
        });


        /**
         * second firebase querie
         */
        TextView result5 = view.findViewById(R.id.result5);
        Button showhotpack = view.findViewById(R.id.q5);
        Button hidebt5 = view.findViewById(R.id.hide5);
        showhotpack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                hidebt5.setVisibility(View.VISIBLE);
                CollectionReference re = MainActivity.firestoredb.collection("Users");
                Query query = re.orderBy("hotel").startAt("L").endAt("L"+"\uf8ff");
                query.get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        if (task.isSuccessful()) {
                            String res="";
                            for (QueryDocumentSnapshot document :
                                    task.getResult()) {
                                if(document.getDouble("code")==2) {
                                    String sname = document.getString("name");
                                    String ssurname = document.getString("surname");
                                    String shotel = document.getString("hotel");
                                    double scode = document.getDouble("code");
                                    res = res + "\n" + " NAME : " + sname + "\nSURNAME : " + ssurname + "\nHOTEL : " + shotel + "\nCODE : " + scode + "\n\n";
                                }
                            }
                            result5.setVisibility(View.VISIBLE);
                            result5.setText(res);
                            hidebt5.setOnClickListener(new View.OnClickListener() {
                                @Override
                                public void onClick(View view) {
                                    hidebt5.setVisibility(View.INVISIBLE);
                                    result5.setVisibility(View.INVISIBLE);
                                    result5.setText("");
                                }
                            });
                        }
                    }
                });
            }
        });

        /**
         * third querie firebase
         */
        TextView result6 = view.findViewById(R.id.result6);
        Button showsurpack = view.findViewById(R.id.q6);
        Button hidebt6 = view.findViewById(R.id.hide6);
        showsurpack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                hidebt6.setVisibility(View.VISIBLE);
                CollectionReference re = MainActivity.firestoredb.collection("Users");
                Query q = re.orderBy("surname").startAt("K").endAt("K"+"\uf8ff");
                q.get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        if(task.isSuccessful()) {
                            String res="";
                            for (QueryDocumentSnapshot document :
                                    task.getResult()) {
                                if (document.getDouble("code") == 1.0) {
                                    String sname = document.getString("name");
                                    String ssurname = document.getString("surname");
                                    String shotel = document.getString("hotel");
                                    double scode = document.getDouble("code");
                                    res = res + "\n" + " NAME : " + sname + "\nSURNAME : " + ssurname + "\nHOTEL : " + shotel + "\nCODE : " + scode + "\n\n";
                                }
                            }
                            result6.setVisibility(View.VISIBLE);
                            result6.setText(res);
                            hidebt6.setOnClickListener(new View.OnClickListener() {
                                @Override
                                public void onClick(View view) {
                                    hidebt6.setVisibility(View.INVISIBLE);
                                    result6.setVisibility(View.INVISIBLE);
                                    result6.setText("");
                                }
                            });
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