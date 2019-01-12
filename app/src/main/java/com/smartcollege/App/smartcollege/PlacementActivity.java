package com.smartcollege.aradhyakahate.smartcollege;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class PlacementActivity extends AppCompatActivity {

    Button btn_book1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_placement);

        btn_book1=(Button)findViewById(R.id.book1);



        btn_book1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i=new Intent(PlacementActivity.this,Book1.class);
                startActivity(i);
            }
        });
    }
}
