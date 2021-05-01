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
    ListView listView2;
    List<String> listItem= new ArrayList<String>();
    List<String> listItem2= new ArrayList<String>();

    @RequiresApi(api = Build.VERSION_CODES.N)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        DatabaseHandler db = new DatabaseHandler(this);



//        db.addContact(new Contact("Ravi", "9100000000"));
//        db.addContact(new Contact("Srinivas", "9199999999"));
//        db.addContact(new Contact("Tommy", "9522222222"));
        if(db.getAllContacts().size()==0) {
            db.addContact(new Contact("Karthik ANDREY"));
            db.addContact(new Contact("Oleg Vasya"));
        }


        listView=(ListView)findViewById(R.id.listView);
        listView2 = (ListView) findViewById(R.id.listView2);
       //textView=(TextView)findViewById(R.id.textView);


        db.getAllContacts().forEach(contact -> listItem.add(contact.getName()));
        for (String ts: db.getTimeStamps()) {
            listItem2.add(ts+"23");
        }




        final ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_list_item_1, android.R.id.text1, listItem);

        listView.setAdapter(adapter);

        final ArrayAdapter<String> adapter2 = new ArrayAdapter<String>(this,
                android.R.layout.simple_list_item_1, android.R.id.text1, listItem2);

        listView2.setAdapter(adapter2);


    }
}