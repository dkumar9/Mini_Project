package com.smartcollege.deepakkumar.smartcollege;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.widget.ListView;
import android.widget.TextView;

import de.hdodenhof.circleimageview.CircleImageView;

public class FacultyDetails extends AppCompatActivity {


    private CircleImageView facultyImage;
    private Toolbar toolbar;
    private TextView facultyName;
    private TextView phoneNumber;
    private TextView email;
    private TextView place;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_faculty_details);


        setupUIViews();
        initToolbar();


        setupDetails();
    }

    private void setupUIViews(){
        toolbar = (Toolbar) findViewById(R.id.ToolbarFacultyDetails);
        facultyImage = (CircleImageView)findViewById(R.id.ivFaculty);
        facultyName = (TextView)findViewById(R.id.tvFacultySelName);
        phoneNumber = (TextView)findViewById(R.id.tvPhoneNumber);
        email = (TextView)findViewById(R.id.tvEmail);
        place = (TextView)findViewById(R.id.tvPlace);

    }
    private void initToolbar(){
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("Faculty Details");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }

    private void setupDetails(){
        int faculty_pos = FacultyActivity.sharedPreferences.getInt(FacultyActivity.SEL_FACULTY,0);
        String[] facultyNames = getResources().getStringArray(R.array.faculty_name);
        int[] facultyImages = new int[]{R.drawable.hod,R.drawable.survana ,R.drawable.ketak,R.drawable.vishal,R.drawable.poojaa, R.drawable.rashmi, R.drawable.nalani};

        int[] facultyArray = new int[]{R.array.Shevatekar,R.array.SurvanaPatil,R.array.KetakiBhoyar,R.array.VishalPatil,R.array.PoojaMishra,R.array.RashmiDeshpande,R.array.NalaniYadav};
        String[] facultyDetails = getResources().getStringArray(facultyArray[faculty_pos]);
        phoneNumber.setText(facultyDetails[0]);
        email.setText(facultyDetails[1]);
        place.setText(facultyDetails[2]);
        facultyImage.setImageResource(facultyImages[faculty_pos]);
        facultyName.setText(facultyNames[faculty_pos]);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch(item.getItemId()){
            case android.R.id.home : {
                onBackPressed();
            }
        }
        return super.onOptionsItemSelected(item);
    }
    }
