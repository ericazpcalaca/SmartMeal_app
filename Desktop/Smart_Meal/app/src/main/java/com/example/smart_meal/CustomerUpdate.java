package com.example.smart_meal;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class CustomerUpdate extends AppCompatActivity {
    Spinner spnState;
    Button btnUpdate;
    EditText username, txtPhone, txtAddress, txtCity, txtEmail, txtPassword, txtConfirmPassword;
    TextView txtErrorP, txtErrorE;
    DBHelper DB = new DBHelper(this);

    private SharedPreferences sharedPreferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_customer_update);
        username = findViewById(R.id.nameUpdate);
        txtPhone = findViewById(R.id.phoneUpdate);
        txtAddress = findViewById(R.id.addressUpdate);
        txtCity = findViewById(R.id.cityUpdate);
        spnState = findViewById(R.id.spnStatesUpdate);
        txtEmail = findViewById(R.id.emailUpdate);
        txtPassword = findViewById(R.id.passUpdate);
        txtConfirmPassword = findViewById(R.id.confirmPassUpdate);
        btnUpdate = findViewById(R.id.btnUpdate);
        txtErrorE = findViewById(R.id.txtErrorEmail);
        txtErrorP = findViewById(R.id.txtErrorPassword);
        sharedPreferences = getSharedPreferences("data", MODE_PRIVATE);

        String prevEmail = sharedPreferences.getString("EmailCust", "");

        SharedPreferences userData = getSharedPreferences("data", Context.MODE_PRIVATE);
        String currentEmail = userData.getString("EmailCust","");
        String currentAddress = userData.getString("Address", "");
        String currentPhone = userData.getString("Phone", "");
        String currentCity = userData.getString("City", "");
        String currentProvince = userData.getString("Province", "");
        String currentName = userData.getString("Name", "");
        String currentPassword = userData.getString("PasswordCust", "");

        txtEmail.setText(currentEmail);
        txtAddress.setText(currentAddress);
        txtPhone.setText(currentPhone);
        txtCity.setText(currentCity);
//        spnState.setSelection(currentProvince);
        username.setText(currentName);
        txtPassword.setText(currentPassword);
        txtConfirmPassword.setText(currentPassword);


        //When the user click on "Create", it will create a new account
        btnUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Get the information on the form
                //Get the name depending of the type of account
                String name = username.getText().toString();
                String phone = txtPhone.getText().toString();
                String address = txtAddress.getText().toString();
                String city = txtCity.getText().toString();
                String state = spnState.getSelectedItem().toString();
                String email = txtEmail.getText().toString();
                String password = txtPassword.getText().toString();
                String confirmPassword = txtConfirmPassword.getText().toString();

                try {
                    Boolean emailVerified = DB.checkIfEmailExists(email);
                    if (!emailVerified) {
                        DB.customerUpdate(prevEmail, email, password, name, phone, address, city, state);
                        String[] data = DB.getUserData(email);
                        SharedPreferences.Editor editor = getSharedPreferences("data", MODE_PRIVATE).edit();
                        String[] columns = {"CustomerID", "AccountType", "EmailCust", "PasswordCust", "Name", "Phone", "Address", "City", "Province"};

                        for (int i = 0; i < data.length; i++) {
                            editor.putString(columns[i], data[i]);
                            editor.apply();
                            Log.d(columns[i], data[i]);
                        }
                    } else {
                        Toast.makeText(CustomerUpdate.this, "This email is already in use.", Toast.LENGTH_LONG).show();
                    }
                } catch (Exception e) {
                    if (email.isEmpty() || password.isEmpty() || name.isEmpty() || phone.isEmpty() || address.isEmpty() || city.isEmpty() || state.isEmpty()) {
                        //Check if the information is empty
                        Toast.makeText(CustomerUpdate.this, "Please make sure to fill the form", Toast.LENGTH_LONG).show();
                    } else if (!isEmailValid(email)) {
                        //Check if email is valid
                        txtErrorE.setText("Please make sure to put a valid email");
                    } else if (!password.equals(confirmPassword)) {
                        //Check if the passwords match
                        txtErrorP.setText("Please make sure your passwords match");
                    } else if (!isPasswordValid(password)) {
                        //Check if the password is valid
                        txtErrorP.setText("The password must contain at least:" + "\n - One lowercase letter" + "\n - One uppercase letter " + "\n - One digit " + "\n -One special character" + "\n - At least 8 characters long\n");
                    } else {
                        //Case everything is ok, create a new account
                        txtErrorP.setText("");
                        txtErrorE.setText("");
                        Toast.makeText(CustomerUpdate.this, "Account updated!!", Toast.LENGTH_LONG).show();
                        startActivity(new Intent(CustomerUpdate.this, LoginActivity.class));
                    }

                }
            }
        });
    }

    private boolean isEmailValid(String email) {
        return email.contains("@");
    }

    private boolean isPasswordValid(String password) {
        String pattern = "^(?=.*\\d)(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%]).{8,20}$";
        // The pattern above uses a regular expression to match the password
        // The password must contain at least one lowercase letter, one uppercase letter, one digit, and one special character
        // The password must be at least 8 characters long

        Pattern p = Pattern.compile(pattern);
        Matcher m = p.matcher(password);
        //return m.matches();
        return true;
    }
}