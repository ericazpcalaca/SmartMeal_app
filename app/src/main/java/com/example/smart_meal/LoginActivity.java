package com.example.smart_meal;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.util.Log;
import android.view.View;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class LoginActivity extends AppCompatActivity {
    private Button btnLogin;
    private Button btnNewAccount;
    private DBHelper DB;
    private EditText email;
    private EditText pass;
    private String accountType;
    private TextView btnUpdatePassword;
    private SharedPreferences sharedPreferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        btnLogin = findViewById(R.id.txtLogin);
        btnNewAccount = findViewById(R.id.txtNewAccount);
        email = findViewById(R.id.txtEmail);
        pass = findViewById(R.id.txtPassword);
        btnUpdatePassword = findViewById(R.id.upPass);

        DB = new DBHelper(this);

        ImageView imageView = findViewById(R.id.imgRotate2);
        imageView.startAnimation(AnimationUtils.loadAnimation(this, R.anim.rotation));
        sharedPreferences = PreferenceManager.getDefaultSharedPreferences(this);

        //Update password
        btnUpdatePassword.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(LoginActivity.this,PasswordUpdate.class));
            }
        });

        //Login
        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String user = email.getText().toString();
                String password = pass.getText().toString();

                //Checking if user exists and validating with db
                // Simple validation to prevent empty fields
                if (user.equals("") || password.equals(""))
                    Toast.makeText(LoginActivity.this, "Please enter all the fields", Toast.LENGTH_SHORT).show();
                else {
                    try {
                        Cursor cursorAccountType = DB.checkAccountType(user);
                        accountType = cursorAccountType.getString(0);
                        boolean c = DB.checkUserAccount(user, password);

                        String[] data = DB.getUserData(user);
                        SharedPreferences.Editor editor = getSharedPreferences("data", MODE_PRIVATE).edit();
                        String[] columns = {"CustomerID", "AccountType", "EmailCust", "PasswordCust", "Name", "Phone", "Address", "City", "Province"};

                        for (int i = 0; i < data.length; i++) {
                            editor.putString(columns[i], data[i]);
                            editor.apply();
                            Log.d(columns[i], data[i]);
                        }

                        if (accountType.equals("Business")) {
                            if (c == true) {
                                startActivity(new Intent(LoginActivity.this, BusinessMain.class));
                            } else {
                                Toast.makeText(LoginActivity.this, "Login or Password doesn't exist, try again.", Toast.LENGTH_SHORT).show();
                            }

                        } else if (accountType.equals("Customer")) {
                            if (c == true) {
                                startActivity(new Intent(LoginActivity.this, CustomerMain.class));
                            } else {
                                Toast.makeText(LoginActivity.this, "Login or Password doesn't exist, try again.", Toast.LENGTH_SHORT).show();
                            }
                        }

                    } catch (Exception e) {
                        Toast.makeText(LoginActivity.this, "Account not found.", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });

        //Create new account
        btnNewAccount.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(LoginActivity.this, NewAccount.class));
            }
        });
    }
}