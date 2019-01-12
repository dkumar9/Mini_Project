package com.smartcollege.deepakkumar.smartcollege;

import android.app.AlertDialog.Builder;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.database.sqlite.SQLiteDatabase;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;




public class Studentinformation extends AppCompatActivity {
    EditText ename,eroll_no,emarks,ephone,eprnNumber,eaddress,eenrollmentid;
    Button add,view,viewall,show,delete,modify;
    SQLiteDatabase db;

    @Override

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_studentinformation);
        ename=(EditText)findViewById(R.id.name);
        ephone=findViewById(R.id.phone);
        eroll_no=(EditText)findViewById(R.id.roll_no);
        emarks=(EditText)findViewById(R.id.marks);
        eprnNumber=(EditText)findViewById(R.id.prnNumber);
        eaddress=(EditText)findViewById(R.id.address);
        eenrollmentid=(EditText)findViewById(R.id.enrollmentid);
        add=(Button)findViewById(R.id.addbtn);
        view=(Button)findViewById(R.id.viewbtn);
        viewall=(Button)findViewById(R.id.Viewallbtn);
        delete=(Button)findViewById(R.id.deletebtn);
        show=(Button)findViewById(R.id.showbtn);
        modify=(Button)findViewById(R.id.modifybtn);


        db=openOrCreateDatabase("Student database", Context.MODE_PRIVATE, null);
        db.execSQL("CREATE TABLE IF NOT EXISTS student(rollno INTEGER,prnNumber VARCHAR,name VARCHAR,marks INTEGER,phone INTEGER,address VARCHAR,enrollmentid VARCHAR);");


        add.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                if(eroll_no.getText().toString().trim().length()==0||
                        eprnNumber.getText().toString().trim().length()==0||
                        ename.getText().toString().trim().length()==0||
                        emarks.getText().toString().trim().length()==0||
                        ephone.getText().toString().trim().length()==0||
                        eaddress.getText().toString().trim().length()==0||
                        eenrollmentid.getText().toString().trim().length()==0 )
                {
                    showMessage("Error", "Please enter all values");
                    return;
                }
                db.execSQL("INSERT INTO student VALUES('"+eroll_no.getText()+"','"+eprnNumber.getText()+
                        "','"+ename.getText()+"','"+emarks.getText()+"','"+ephone.getText()+"','"+eaddress.getText()+"','"+eenrollmentid.getText()+"');");
                showMessage("Success", "Record added successfully");
                clearText();
            }
        });
        delete.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {

                if(eroll_no.getText().toString().trim().length()==0)
                {
                    showMessage("Error", "Please enter Rollno");
                    return;
                }
                Cursor c=db.rawQuery("SELECT * FROM student WHERE rollno='"+eroll_no.getText()+"'", null);
                if(c.moveToFirst())
                {
                    db.execSQL("DELETE FROM student WHERE rollno='"+eroll_no.getText()+"'");
                    showMessage("Success", "Record Deleted");
                }
                else
                {
                    showMessage("Error", "Invalid Rollno");
                }
                clearText();
            }
        });
        modify.setOnClickListener(new View.OnClickListener() {
            @Override

            public void onClick(View v) {

                if(eroll_no.getText().toString().trim().length()==0)
                {
                    showMessage("Error", "Please enter Rollno");
                    return;
                }
                Cursor c=db.rawQuery("SELECT * FROM student WHERE rollno='"+eroll_no.getText()+"'", null);
                if(c.moveToFirst())
                {
                    db.execSQL("UPDATE student SET prnNumber='"+eprnNumber.getText()+"',name='"+ename.getText()+
                            "',marks='"+emarks.getText()+"',phone='"+ephone.getText()+"',address='"+eaddress.getText()+"',enrollmentid='"+eenrollmentid.getText()+"'WHERE rollno='"+eroll_no.getText()+"'");
                    showMessage("Success", "Record Modified");
                }
                else
                {
                    showMessage("Error", "Invalid Rollno");
                }
                clearText();
            }
        });
        viewall.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {

                Cursor c=db.rawQuery("SELECT * FROM student", null);
                if(c.getCount()==0)
                {
                    showMessage("Error", "No records found");
                    return;
                }
                StringBuffer buffer=new StringBuffer();
                while(c.moveToNext())
                {
                    buffer.append("Rollno: "+c.getString(0)+"\n");
                    buffer.append("PrnNumber: "+c.getString(1)+"\n");
                    buffer.append("Name: "+c.getString(2)+"\n");
                    buffer.append("Marks: "+c.getString(3)+"\n");
                    buffer.append("Phone: "+c.getString(4)+"\n");
                    buffer.append("Address: "+c.getString(5)+"\n");
                    buffer.append("EnrollmentId: "+c.getString(6)+"\n\n\n");
                }
                showMessage("Student Details", buffer.toString());
            }
        });
        view.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {

                if(eroll_no.getText().toString().trim().length()==0)
                {
                    showMessage("Error", "Please enter Rollno");
                    return;
                }
                Cursor c=db.rawQuery("SELECT * FROM student WHERE rollno='"+eroll_no.getText()+"'", null);
                if(c.moveToFirst())
                {
                    eprnNumber.setText(c.getString(1));
                    ename.setText(c.getString(2));
                    emarks.setText(c.getString(3));
                    ephone.setText(c.getString(4));
                    eaddress.setText(c.getString(5));
                    eenrollmentid.setText(c.getString(6));
                }
                else
                {
                    showMessage("Error", "Invalid Rollno");
                    clearText();
                }
            }
        });

        show.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {

                showMessage("Student information", "Thank you For Using Student Information!!!.. ");
            }
        });

    }
    public void showMessage(String title,String message)
    {

        Builder builder=new Builder(this);
        builder.setCancelable(true);
        builder.setTitle(title);
        builder.setMessage(message);
        builder.show();
    }
    public void clearText()
    {
        eroll_no.setText("");
        ename.setText("");
        emarks.setText("");
        ephone.setText("");
        eaddress.setText("");
        eprnNumber.setText("");
        eenrollmentid.setText("");
        eroll_no.requestFocus();
    }


}

