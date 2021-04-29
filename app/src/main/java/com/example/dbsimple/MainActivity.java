package com.example.dbsimple;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    ListView listView;
    TextView textView;
    List<String> listItem= new ArrayList<String>();

    @RequiresApi(api = Build.VERSION_CODES.N)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        DatabaseHandler db = new DatabaseHandler(this);



//        db.addContact(new Contact("Ravi", "9100000000"));
//        db.addContact(new Contact("Srinivas", "9199999999"));
//        db.addContact(new Contact("Tommy", "9522222222"));
        db.addContact(new Contact("Karthik ANDREY"));
        db.addContact(new Contact("Oleg Vasya"));


        listView=(ListView)findViewById(R.id.listView);
       //textView=(TextView)findViewById(R.id.textView);


        db.getAllContacts().forEach(contact -> listItem.add(contact.getName()));
       for (Contact contact: db.getAllContacts()){
           db.deleteContact(contact);
       }



        final ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_list_item_1, android.R.id.text1, listItem);

        listView.setAdapter(adapter);


    }
}