package com.example.resalat.contentreader;

import android.content.ContentValues;
import android.database.Cursor;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.net.URI;

public class MainActivity extends AppCompatActivity {

    String uri = "content://com.example.resalat.MyContentProvider";
    String table = "student_table";
    Uri CONTENT_URI = Uri.parse(uri);
    Button button;
    Cursor cursor;
    EditText editText;
    Button b2;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
       // savedInstanceState.putByte();

        b2 = (Button)findViewById(R.id.button2);
        editText = (EditText)findViewById(R.id.et1);

        b2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                ContentValues values = new ContentValues();
               // getContentResolver().query(Uri.withAppendedPath(CONTENT_URI,table),null,null,null,null);
                values.put("NAME",editText.getText().toString());
                getContentResolver().insert(Uri.withAppendedPath(CONTENT_URI,table),values);


            }
        });

        button = (Button)findViewById(R.id.click);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                cursor = getContentResolver().query(Uri.withAppendedPath(CONTENT_URI,table),null,null,null,null);
                if(cursor!=null && cursor.moveToFirst())
                {
                    cursor.moveToFirst();
                    do{
                        String name = cursor.getString(cursor.getColumnIndex("NAME"));
                        Toast.makeText(MainActivity.this,"value from db: "+name,Toast.LENGTH_SHORT).show();

                    }while (cursor.moveToNext());

                }
            }
        });
    }
}
