package com.example.examen_projet;



import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.widget.TextView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import org.json.JSONException;
import org.json.JSONObject;
public class viewPatient extends AppCompatActivity {
    List<String> mobileList = new ArrayList<>();
    ArrayAdapter<String> adapter;
    private DatabaseReference databaseReference;
    private TextView column1TextView, column2TextView;
    FirebaseDatabase firebaseatabase = FirebaseDatabase.getInstance();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_patient);
         adapter = new ArrayAdapter<String>(this,
                R.layout.activity_listview, mobileList);

        ListView listView = (ListView) findViewById(R.id.mobile_list);
        listView.setAdapter(adapter);


        firebaseatabase = FirebaseDatabase.getInstance();
        databaseReference = firebaseatabase.getReference("/");


        fetchDataFromFirebase();


    }
    private void fetchDataFromFirebase() {
        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                mobileList.clear();
                for (DataSnapshot childSnapshot : dataSnapshot.getChildren()) {
                    Map<String, Object> dataMap = (Map<String, Object>) childSnapshot.getValue();
                    if (dataMap != null && dataMap.containsKey("name")
                            && dataMap.containsKey("age")  && dataMap.containsKey("desc"))
                    {String name = (String) dataMap.get("name");String age = (String) dataMap.get("age");
                        String desc = (String) dataMap.get("desc");
                        mobileList.add("Name: " + name + ", Age: " + age+"desc : "+desc );
                                                            } else {

                    }}adapter.notifyDataSetChanged();}

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
            }});
    }
}


