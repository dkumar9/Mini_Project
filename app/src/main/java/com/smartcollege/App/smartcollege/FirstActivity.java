package com.smartcollege.aradhyakahate.smartcollege;


import android.app.ProgressDialog;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class FirstActivity extends AppCompatActivity {
    private EditText Name;
    private EditText Password;
    private TextView Info;
    private Button Login;
    private int counter = 5;
    private TextView userRegistration;
    private FirebaseAuth firebaseAuth;
    private ProgressDialog progressDialog;
    private TextView forgetPassword;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_first);

        Name = (EditText)findViewById(R.id.etUserName);
        Password = (EditText)findViewById(R.id.etPassword);
        Info = (TextView)findViewById(R.id.tvInfo);
        Login = (Button)findViewById(R.id.btnLogin);
        userRegistration = (TextView)findViewById(R.id.tvRegister);
        forgetPassword = (TextView)findViewById(R.id.tvForgetPassword);

        Info.setText("No of attempts remaining:5");
        firebaseAuth = FirebaseAuth.getInstance();
        progressDialog = new ProgressDialog(this);

        FirebaseUser user = firebaseAuth.getCurrentUser();

        if (user!=null) {
            finish();
            startActivity(new Intent(FirstActivity.this,MainActivity.class));
        }


        userRegistration.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(FirstActivity.this,RegistrationActivity.class));
            }
        });


        Login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                validate(Name.getText().toString(),Password.getText().toString());
            }
        });

        forgetPassword.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(FirstActivity.this,PasswordActivity.class));
            }
        });


    }

    private void validate(String userName, String userPassword) {


        if(validate()) {
            progressDialog.setMessage("Wait");
            progressDialog.show();


            firebaseAuth.signInWithEmailAndPassword(userName, userPassword).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                @Override
                public void onComplete(@NonNull Task<AuthResult> task) {
                    if (task.isSuccessful()) {
                        progressDialog.dismiss();
                        checkEmailVerification();
                        // Toast.makeText(MainActivity.this, "Login successfull", Toast.LENGTH_LONG).show();
                        //startActivity(new Intent(MainActivity.this, SecondActivity.class));
                    } else {
                        Toast.makeText(FirstActivity.this, "Login failed", Toast.LENGTH_LONG).show();
                        counter--;
                        Info.setText("No of attempts remaining:" + counter);
                        progressDialog.dismiss();
                        if (counter == 0) {
                            Login.setEnabled(false);
                        }
                    }

                }
            });
        }



    }
    private  Boolean validate() {
        Boolean result = false;

        String name = Name.getText().toString();
        String password = Password.getText().toString();

        if(   name.isEmpty() || password.isEmpty()) {
            Toast.makeText(FirstActivity.this,"Please enter all details",Toast.LENGTH_LONG).show();
        } else {
            result = true;
        }
        return result;
    }
    private  void checkEmailVerification() {
        FirebaseUser firebaseUser = firebaseAuth.getInstance().getCurrentUser();
        Boolean emailflag = firebaseUser.isEmailVerified();
        startActivity(new Intent(FirstActivity.this,MainActivity.class));

        // if(emailflag) {
        //   finish();
        // startActivity(new Intent(MainActivity.this,SecondActivity.class));
        //  }else {
        //    Toast.makeText(MainActivity.this,"please verify ",Toast.LENGTH_LONG).show();
        //    firebaseAuth.signOut();
    }
}



