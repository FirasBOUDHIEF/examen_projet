package com.example.examen_projet;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class deleteAccount extends AppCompatActivity {
    List<String> mobileList = new ArrayList<>();
    CustomAdapter adapter;
    private static DatabaseReference databaseReference;
    FirebaseDatabase firebaseDatabase = FirebaseDatabase.getInstance();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_delete_account);

        adapter = new CustomAdapter(this, R.layout.deletepartie, mobileList);

        ListView listView = findViewById(R.id.mobile_list);
        listView.setAdapter(adapter);

        firebaseDatabase = FirebaseDatabase.getInstance();
        databaseReference = firebaseDatabase.getReference("/");

        fetchDataFromFirebase();
    }

    private void fetchDataFromFirebase() {
        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                // This method is called once with the initial value and whenever data at this location is updated

                // Clear the existing list
                mobileList.clear();

                // Iterate through the children nodes
                for (DataSnapshot childSnapshot : dataSnapshot.getChildren()) {
                    // Get the name and age values from each child
                    Map<String, Object> dataMap = (Map<String, Object>) childSnapshot.getValue();

                    // Check if dataMap is not null and contains the expected fields
                    if (dataMap != null && dataMap.containsKey("name") && dataMap.containsKey("age")) {
                        String name = (String) dataMap.get("name");
                        String age = (String) dataMap.get("age");

                        // Add the data to the list
                        mobileList.add("Name: " + name + ", Age: " + age);
                    } else {
                        // Handle the case where the data format is unexpected
                        // Print a log, show a message, etc.
                    }
                }

                // Update the adapter
                adapter.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                // Handle onCancelled event if needed
            }
        });
    }

    // CustomAdapter class
    private static class CustomAdapter extends ArrayAdapter<String> {
        private final int layoutResource;

        public CustomAdapter(deleteAccount context, int resource, List<String> objects) {
            super(context, resource, objects);
            this.layoutResource = resource;
        }

        @NonNull
        @Override
        public View getView(int position, View convertView, @NonNull ViewGroup parent) {
            if (convertView == null) {
                LayoutInflater inflater = LayoutInflater.from(getContext());
                convertView = inflater.inflate(layoutResource, parent, false);
            }

            // Get the TextView and Button from the custom layout
            TextView textView = convertView.findViewById(R.id.label);
            Button button = convertView.findViewById(R.id.your_button_id);

            // Set the text for the TextView based on the data at the current position
            textView.setText(getItem(position));
            button.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                    String selectedItem = getItem(position);
                    deleteFromDatabase(selectedItem);
                    // Add your action here
                }
            });
            // Handle button click actions if needed

            return convertView;
        }

        private void deleteFromDatabase(String userName) {

            DatabaseReference usersRef = databaseReference.child("/");
            usersRef.addListenerForSingleValueEvent(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                    for (DataSnapshot userSnapshot : dataSnapshot.getChildren()) {
                        // Remove the user from the database

                        for (DataSnapshot childSnapshot : dataSnapshot.getChildren()) {
                            // Get the name and age values from each child
                            Map<String, Object> dataMap = (Map<String, Object>) childSnapshot.getValue();
                            // Check if dataMap is not null and contains the expected fields
                            if (dataMap != null && dataMap.containsKey("name") && dataMap.containsKey("age")) {
                                String name = (String) dataMap.get("name");
                                String age = (String) dataMap.get("age");
                                String res  = (String)  "Name: " + name + ", Age: " + age ;
                                if (res.equals(userName)) {
                                    // Remove the user from the database
                                    userSnapshot.getRef().removeValue();
                                }

                            } else {
                                // Handle the case where the data format is unexpected
                                // Print a log, show a message, etc.
                            }
                        }

                    }
                }

                @Override
                public void onCancelled(@NonNull DatabaseError error) {
                    // Handle onCancelled event if needed
                }
            });
        }
    }
}
