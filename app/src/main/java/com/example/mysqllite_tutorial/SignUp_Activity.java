package com.example.mysqllite_tutorial;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import static com.example.mysqllite_tutorial.R.drawable.ic_launcher_background;

public class SignUp_Activity extends AppCompatActivity {

    EditText name,email,password,repassword;
    Button signup;

    SqlliteDatabase_holder db_holder;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up_);

        name = findViewById(R.id.signup_edittext1);
        email = findViewById(R.id.signup_edittext2);
        password = findViewById(R.id.signup_edittext3);
        repassword = findViewById(R.id.signup_edittext4);
        signup = findViewById(R.id.signup_Button1);

        db_holder = new SqlliteDatabase_holder(this);

        signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String uname = name.getText().toString();
                String uemail = email.getText().toString();
                String upassword = password.getText().toString();
                String urepassword = repassword.getText().toString();
                if (name.length()==0 || uemail.isEmpty() || upassword.isEmpty())
                {
                    name.setError("enter name");

                    Toast.makeText(SignUp_Activity.this, "please enter all fields", Toast.LENGTH_SHORT).show();
                }
                else {
                    if (upassword.equals(urepassword))
                    {
                        Boolean checkuser = db_holder.checkUseremail(uemail);
                        if (checkuser == true)
                        {
                            Toast.makeText(SignUp_Activity.this, "user email already exists", Toast.LENGTH_SHORT).show();
                        }
                        else
                        {
                            Boolean insert = db_holder.insertData(uname, uemail, upassword);
                            if (insert == true)
                            {
                                Toast.makeText(SignUp_Activity.this, "Registered Successfully", Toast.LENGTH_SHORT).show();
                                startActivity(new Intent(SignUp_Activity.this, Login_Activity.class));
                            }
                            else
                                Toast.makeText(SignUp_Activity.this, "some thing went wrong", Toast.LENGTH_SHORT).show();
                        }
                    }
                    else Toast.makeText(SignUp_Activity.this, "password not match", Toast.LENGTH_SHORT).show();
                }

            }
        });
    }
}