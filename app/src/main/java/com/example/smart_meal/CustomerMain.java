package com.example.smart_meal;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.GridLayoutManager;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationBarView;

import java.util.ArrayList;
import java.util.List;

public class CustomerMain extends AppCompatActivity implements ItemAdapter.ItemClickListener {

    BottomNavigationView bottomNavigationView;
    ArrayList<ItemModel> itemList;
    androidx.recyclerview.widget.RecyclerView recyclerView;
    ItemAdapter itemAdapter;
    TextView titleTextView;
    CustomerProfileFragment customerProfile = new CustomerProfileFragment();
    CustomerOrderMFragment customerOrder = new CustomerOrderMFragment();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_customer_main);

        createRecyclerView();

        //TextView for the titles
        titleTextView = findViewById(R.id.topText);
        titleTextView.setText("Welcome User");

        //Constraint layout where is the Fragments
        ConstraintLayout constraintLayout = findViewById(R.id.fragmentLayout);

        //Bottom navigation view
        bottomNavigationView = findViewById(R.id.bottom_navigation);
        bottomNavigationView.setOnItemSelectedListener(new NavigationBarView.OnItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()) {
                    case R.id.home:
                        titleTextView.setText("Welcome User");
                        recyclerView.setVisibility(View.VISIBLE);
                        constraintLayout.setVisibility(View.INVISIBLE);
                        return true;
                    case R.id.search:
                        startActivity(new Intent(CustomerMain.this, CustomerSearch.class));
                        return true;
                    case R.id.order:
                        titleTextView.setText("Order");
                        recyclerView.setVisibility(View.INVISIBLE);
                        getSupportFragmentManager().beginTransaction().replace(R.id.fragmentLayout, customerOrder).commit();
                        return true;
                    case R.id.profile:
                        titleTextView.setText("Account");
                        constraintLayout.setVisibility(View.VISIBLE);
                        recyclerView.setVisibility(View.INVISIBLE);
                        getSupportFragmentManager().beginTransaction().replace(R.id.fragmentLayout, customerProfile).commit();
                        return true;
                }
                return false;
            }
        });
    }


    @Override
    public void onItemClick(View view, int position) {
        //Test to see if the items on recycler view is clickable
        Intent intent = new Intent(CustomerMain.this, CustomerRestaurant.class);
        intent.putExtra("RESTAURANTID",position);
        startActivity(intent);
        //End
    }

    //Method for the RecyclerView
    private void createRecyclerView(){
        //Recycler view
        recyclerView = findViewById(R.id.mRecyclerView);
        int numOfColumns = 1;
        recyclerView.setLayoutManager(new GridLayoutManager(this, numOfColumns));
        itemAdapter = new ItemAdapter(this, initData());
        itemAdapter.setClickListener(this);
        recyclerView.setAdapter(itemAdapter);
    }

    //Created a list to tryout the recyclervier
    //Latter figure out how to import from database :)
    private List<ItemModel> initData() {
        itemList = new ArrayList<>();
        //String itemName, int itemImage, String itemDescription, Double itemPrice
        itemList.add(new ItemModel("Title Item",R.drawable.ic_launcher_background,"Description, description bla bla bla",9.99));
        itemList.add(new ItemModel("Title Item 1",R.drawable.ic_launcher_background,"Description, description bla bla bla",19.99));
        itemList.add(new ItemModel("Title Item 2",R.drawable.ic_launcher_background,"Description, description bla bla bla",92.99));
        itemList.add(new ItemModel("Title Item 3",R.drawable.ic_launcher_background,"Description, description bla bla bla",39.99));
        itemList.add(new ItemModel("Title Item 5",R.drawable.ic_launcher_background,"Description, description bla bla bla",49.99));
        itemList.add(new ItemModel("Title Item 6",R.drawable.ic_launcher_background,"Description, description bla bla bla",59.99));
        itemList.add(new ItemModel("Title Item 7",R.drawable.ic_launcher_background,"Description, description bla bla bla",39.99));
        return itemList;
    }
}
