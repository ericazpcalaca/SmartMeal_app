package com.example.smart_meal;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationBarView;

import java.util.ArrayList;
import java.util.List;

public class CustomerMain extends AppCompatActivity implements ItemAdapter.ItemClickListener{

    BottomNavigationView bottomNavigationView;
    ArrayList<ItemModel> itemList;
    RecyclerView recyclerView;
    ImageView imageView;
    ItemAdapter itemAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_customer_main);

        //Recycler view
        androidx.recyclerview.widget.RecyclerView recyclerView = findViewById(R.id.mRecyclerView);
        int numOfColumns = 1;
        recyclerView.setLayoutManager(new GridLayoutManager(this,numOfColumns));
        itemAdapter = new ItemAdapter(this,initData());
        itemAdapter.setClickListener(this);
        recyclerView.setAdapter(itemAdapter);

        bottomNavigationView = findViewById(R.id.bottom_navigation);
        bottomNavigationView.setOnItemSelectedListener(new NavigationBarView.OnItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()){
                    case R.id.home:
                        recyclerView.setVisibility(View.VISIBLE);
                        return true;
                    case R.id.search:
                        startActivity(new Intent(CustomerMain.this,CustomerSearch.class));
                        return true;
                    case R.id.order:
                        recyclerView.setVisibility(View.INVISIBLE);

                        return true;
                    case R.id.profile:
                        recyclerView.setVisibility(View.INVISIBLE);
                        return true;
                }

                return false;
            }
        });
    }

    //Created a list to tryout the recyclervier
    //Latter figure out how to import from database :)
    private List<ItemModel> initData(){
        itemList = new ArrayList<>();
        itemList.add(new ItemModel(R.drawable.ic_launcher_background,"Title Item","$9.99","Description, description bla bla bla"));
        itemList.add(new ItemModel(R.drawable.ic_launcher_background,"Title Item","$9.99","Description, description bla bla bla"));
        itemList.add(new ItemModel(R.drawable.ic_launcher_background,"Title Item","$9.99","Description, description bla bla bla"));
        itemList.add(new ItemModel(R.drawable.ic_launcher_background,"Title Item","$9.99","Description, description bla bla bla"));
        itemList.add(new ItemModel(R.drawable.ic_launcher_background,"Title Item","$9.99","Description, description bla bla bla"));
        itemList.add(new ItemModel(R.drawable.ic_launcher_background,"Title Item","$9.99","Description, description bla bla bla"));
        itemList.add(new ItemModel(R.drawable.ic_launcher_background,"Title Item","$9.99","Description, description bla bla bla"));
        itemList.add(new ItemModel(R.drawable.ic_launcher_background,"Title Item","$9.99","Description, description bla bla bla"));
        itemList.add(new ItemModel(R.drawable.ic_launcher_background,"Title Item","$9.99","Description, description bla bla bla"));
        itemList.add(new ItemModel(R.drawable.ic_launcher_background,"Title Item","$9.99","Description, description bla bla bla"));

        return itemList;
    }

    @Override
    public void onItemClick(View view, int position) {
        Toast.makeText(this,"Selected space",Toast.LENGTH_LONG).show();
    }
}