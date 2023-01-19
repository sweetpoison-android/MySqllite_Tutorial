package com.example.mysqllite_tutorial;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class Login_Activity extends AppCompatActivity {

    EditText email,password;
    Button login,signup;

    SqlliteDatabase_holder db_holder;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_);

        email = findViewById(R.id.login_edittext1);
        password = findViewById(R.id.login_edittext2);
        login = findViewById(R.id.login_Button1);
        signup = findViewById(R.id.login_Button2);

        db_holder = new SqlliteDatabase_holder(this);

        signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(Login_Activity.this, SignUp_Activity.class));
            }
        });

        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String uemail = email.getText().toString();
                String upassword = password.getText().toString();

                if (uemail.isEmpty() || upassword.isEmpty())
                {
                    Toast.makeText(Login_Activity.this, "please enter all fields", Toast.LENGTH_SHORT).show();
                }
                else {
                    Boolean checkemilandpassword = db_holder.checkUseremailandpassword(uemail,upassword);
                    if (checkemilandpassword == true)
                    {
                        Toast.makeText(Login_Activity.this, "Login successfully", Toast.LENGTH_SHORT).show();
                        startActivity(new Intent(Login_Activity.this, Home_Activity.class));
                    }
                    else
                    {
                        Toast.makeText(Login_Activity.this, "Invalid Credential", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });
    }
}