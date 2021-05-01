package com.example.dbsimple;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Build;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    ListView tvNames;
    ListView tvTimestamps;
    ListView tvIds;

    List<String> namesList = new ArrayList<String>();
    List<String> stampsList = new ArrayList<String>();
    List<Integer> idsList = new ArrayList<Integer>();

    @RequiresApi(api = Build.VERSION_CODES.N)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        DatabaseHandler db = new DatabaseHandler(this);

        if(db.getAllContacts().size()==0) {
            db.addContact(new Contact("Karthik ANDREY"));
            db.addContact(new Contact("Oleg Vasya"));
        }


        tvNames =(ListView)findViewById(R.id.tvNames);
        tvTimestamps = (ListView) findViewById(R.id.tvTimestamps);
        tvIds = (ListView) findViewById(R.id.tvIds);



        db.getAllContacts().forEach(contact -> namesList.add(contact.getName()));

        for (String ts: db.getTimeStamps()) {
            stampsList.add(ts);
        }

        Iterator<Integer> idsIterator = db.getIds().iterator();
        while(idsIterator.hasNext()) idsList.add(idsIterator.next());




        final ArrayAdapter<String> namesAdapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_list_item_1, android.R.id.text1, namesList);
        tvNames.setAdapter(namesAdapter);

        final ArrayAdapter<String> stampsAdapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_list_item_1, android.R.id.text1, stampsList);
        tvTimestamps.setAdapter(stampsAdapter);

        final ArrayAdapter<Integer> idsAdapter = new ArrayAdapter<Integer>(this,
                android.R.layout.simple_list_item_1, android.R.id.text1, idsList);

        tvIds.setAdapter(idsAdapter);


    }
}