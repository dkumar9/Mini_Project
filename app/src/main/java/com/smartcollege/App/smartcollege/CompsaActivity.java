package com.smartcollege.aradhyakahate.smartcollege;

import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class CompsaActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_compsa);
    }

    public void open(View view) {
        Intent browserIntent=new Intent(Intent.ACTION_VIEW, Uri.parse("http://compsa.co.in /"));
        startActivity(browserIntent);

    }
}
