package com.example.dbsimple;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class MainActivity extends AppCompatActivity  {
    ListView tvNames;
    ListView tvTimestamps;
    ListView tvIds;
    Button deleteAllBtn;
    Button addStudentBtn;
    Button changeLastBtn;
    EditText etInputName;
    //-----------------------------------------------------------------------
    List<String> namesList = new ArrayList<String>();
    List<String> stampsList = new ArrayList<String>();
    List<Integer> idsList = new ArrayList<Integer>();

    public void restartActivity(){
        Intent intent= new Intent(MainActivity.this, MainActivity.class);
        startActivity(intent);
    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        tvNames =(ListView)findViewById(R.id.tvNames);
        tvTimestamps = (ListView) findViewById(R.id.tvTimestamps);
        tvIds = (ListView) findViewById(R.id.tvIds);
        deleteAllBtn = (Button) findViewById(R.id.deleteAllBtn);
        addStudentBtn = (Button) findViewById(R.id.addStudentBtn);
        etInputName = (EditText) findViewById(R.id.etInputName);
        changeLastBtn = (Button) findViewById(R.id.changeLastBtn);

        DatabaseHandler db = new DatabaseHandler(this);

//-----------------------------------------------------------------------
        deleteAllBtn.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                db.getAllStudents().forEach(student -> db.deleteStudent(student));
                db.getFirstNStudents(5).forEach(student -> db.addStudent(student));
                restartActivity();

            }
        });
//-----------------------------------------------------------------------
        addStudentBtn.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                //Toast.makeText(getApplicationContext(),etInputName.getText() ,Toast.LENGTH_SHORT).show();
                db.addStudent(new Student(String.valueOf(etInputName.getText())));
                restartActivity();
            }
        });
        int count = db.getAllStudents().size();
        if(count == 0) {
            db.getFirstNStudents(2).forEach(student -> db.addStudent(student));
        }
        //-----------------------------------------------------------------------
        changeLastBtn.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {



                db.updateStudent(db.getStudent(count), "Ivanov Ivan Ivanovich");
                restartActivity();
            }
        });
//-----------------------------------------------------------------------

//-----------------------------------------------------------------------
        db.getAllStudents().forEach(student -> namesList.add(student.getName()));
//-----------------------------------------------------------------------
        for (String ts: db.getTimeStamps()) {
            stampsList.add(ts);
        }
//-----------------------------------------------------------------------
        Iterator<Integer> idsIterator = db.getIds().iterator();
        while(idsIterator.hasNext()) idsList.add(idsIterator.next());
//-----------------------------------------------------------------------

        final ArrayAdapter<String> namesAdapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_list_item_1, android.R.id.text1, namesList);
        tvNames.setAdapter(namesAdapter);
//-----------------------------------------------------------------------
        final ArrayAdapter<String> stampsAdapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_list_item_1, android.R.id.text1, stampsList);
        tvTimestamps.setAdapter(stampsAdapter);
//-----------------------------------------------------------------------
        final ArrayAdapter<Integer> idsAdapter = new ArrayAdapter<Integer>(this,
                android.R.layout.simple_list_item_1, android.R.id.text1, idsList);

        tvIds.setAdapter(idsAdapter);
//-----------------------------------------------------------------------

    }
}