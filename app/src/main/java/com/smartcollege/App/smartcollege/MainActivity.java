package com.smartcollege.aradhyakahate.smartcollege;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.smartcollege.aradhyakahate.smartcollege.R;

public class MainActivity extends AppCompatActivity {


    private FirebaseAuth firebaseAuth;
    private Toolbar toolbar;
    private ListView listView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        firebaseAuth = FirebaseAuth.getInstance();


        setupUIViews();
        initToolbar();
        setupListView();

    }


    private void Logout() {
        firebaseAuth.signOut();
        finish();
        startActivity(new Intent(MainActivity.this, FirstActivity.class));
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.id_developer) {

            Intent intentdeveloper = new Intent(MainActivity.this, DeveloperActivity.class);
            startActivity(intentdeveloper);


        } else {
            switch (item.getItemId()) {
                case R.id.logout: {
                    Logout();
                }

            }
        }

        return super.onOptionsItemSelected(item);
    }

    private void setupUIViews() {
        toolbar = (Toolbar) findViewById(R.id.ToolbarMain);
        listView = (ListView) findViewById(R.id.lvMain);
    }

    private void initToolbar() {
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("Smart College");
    }

    private void setupListView() {

        String[] title = getResources().getStringArray(R.array.Main);
        String[] description = getResources().getStringArray(R.array.Description);

        SimpleAdapter simpleAdapter = new SimpleAdapter(this, title, description);
        listView.setAdapter(simpleAdapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                switch (position) {
                    case 0: {
                        Intent intent = new Intent(MainActivity.this, WeekActivity.class);
                        startActivity(intent);
                        break;
                    }
                    case 1: {
                        Intent intent = new Intent(MainActivity.this, SubjectActivity.class);
                        startActivity(intent);

                        break;
                    }
                    case 2: {
                        Intent intent = new Intent(MainActivity.this, FacultyActivity.class);
                        startActivity(intent);
                        break;
                    }
                    case 3: {
                        Intent intent = new Intent(MainActivity.this, CompsaActivity.class);
                        startActivity(intent);

                        break;
                    }
                    case 4: {
                        Intent intent = new Intent(MainActivity.this, CollegeActivity.class);
                        startActivity(intent);

                        break;
                    }
                    case 5: {
                        Intent intent = new Intent(MainActivity.this, Studentinformation.class);
                        startActivity(intent);

                        break;
                    }

                    case 6: {
                        Intent intent = new Intent(MainActivity.this, PlacementActivity.class);
                        startActivity(intent);
                    }
                    default:
                        break;
                }
            }
        });
    }

    public class SimpleAdapter extends BaseAdapter {

        private Context mContext;
        private LayoutInflater layoutInflater;
        private TextView title, description;
        private String[] titleArray;
        private String[] descriptionArray;
        private ImageView imageView;

        private SimpleAdapter(Context context, String[] title, String[] description) {
            mContext = context;
            titleArray = title;
            descriptionArray = description;
            layoutInflater = LayoutInflater.from(context);
        }


        @Override
        public int getCount() {
            return titleArray.length;
        }

        @Override
        public Object getItem(int position) {
            return titleArray[position];
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            if (convertView == null) {
                convertView = layoutInflater.inflate(R.layout.main_activity_single_item, null);
            }

            title = (TextView) convertView.findViewById(R.id.tvMain);
            description = (TextView) convertView.findViewById(R.id.tvDescription);
            imageView = (ImageView) convertView.findViewById(R.id.ivMain);

            title.setText(titleArray[position]);
            description.setText(descriptionArray[position]);

            if (titleArray[position].equalsIgnoreCase("Timetable")) {
                imageView.setImageResource(R.drawable.timetable);
            } else if (titleArray[position].equalsIgnoreCase("Subjects")) {
                imageView.setImageResource(R.drawable.ani);
            } else if (titleArray[position].equalsIgnoreCase("Faculty")) {
                imageView.setImageResource(R.drawable.facultyyyy);
            } else if (titleArray[position].equalsIgnoreCase("CompSa")) {
                imageView.setImageResource(R.drawable.babu);
            } else if (titleArray[position].equalsIgnoreCase("College Curriculum")) {
                imageView.setImageResource(R.drawable.activities);
            } else if (titleArray[position].equalsIgnoreCase("Student Information")) {
                imageView.setImageResource(R.drawable.bcbc);

            } else{
                    imageView.setImageResource(R.drawable.pully);

                }
                return convertView;

            }
        }

    }
