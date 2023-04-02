package com.example.smart_meal;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

public class BusinessMain extends AppCompatActivity {
    Toolbar toolbar;
    Button btnReport;
    Button btnLogout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_business_main);

        btnReport = findViewById(R.id.btnReports);
        btnLogout = findViewById(R.id.btnLogout);

        //Top menu
        toolbar = findViewById(R.id.toolbarBusiness);
        setSupportActionBar(toolbar);

        //Add food item

        //Confirm orders

        //Send reminders

        //Reports
        btnReport.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(BusinessMain.this,BusinessReport.class));
            }
        });

        //Logout
        btnLogout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
            }
        });
    }

    //When creating the other activities from the Business Part, if you want to put the top menu
    //Insert all the bottom code with the toolbar
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.top_menu_business, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.profile) {
            Intent intent = new Intent(this, BusinessProfile.class);
            startActivity(intent);
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

}