package com.example.smart_meal;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class CustomerRestaurant extends AppCompatActivity implements Communicator, CustomerOrderFragment.OnButtonClickListener {
    private List<ItemModel> itemList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_customer_restaurant);

        //Test to see if the Recycler View is clickable
        TextView restaurantDesc = findViewById(R.id.txtRestDesc);
        Intent intent = getIntent();
        int restaurantId = getIntent().getIntExtra("RESTAURANTID",-1);
        restaurantDesc.setText("Restaurant selected is " + restaurantId);
        //End test
    }

    @Override
    public void respond(HashMap<String,Double[]> data) {
        DecimalFormat decimalFormat = new DecimalFormat("#");
        DecimalFormat currency = new DecimalFormat("#.##");
        double finalTotal = 0;

        FragmentManager fragmentManager = getSupportFragmentManager();
        CustomerOrderFragment customerOrderFragment = (CustomerOrderFragment)
                fragmentManager.findFragmentById(R.id.selectedOrder);

        StringBuilder str = new StringBuilder();

        for (String key : data.keySet()) {
            Double[] values = data.get(key);
            str.append(decimalFormat.format(values[1]) + " x " + key + " $ " + currency.format(values[0]) + "\n");
            finalTotal += values[0] * values[1];
        }

        final double FEE = 0.6 * finalTotal;
        str.append("\nSubtotal  $" + currency.format(finalTotal)+ "\n");
        str.append("Fees  $" + currency.format(FEE)+ "\n");
        str.append("\nTotal  $" + currency.format(finalTotal + FEE)+ "\n");
        customerOrderFragment.makeOrder(str, data);
    }

    //Handle order submit click, input data and pass to previous activity
    @Override
    public void onButtonClick(String delivery) {
        Intent intent = new Intent();
        intent.putExtra("DELIVERY",delivery);
        setResult(RESULT_OK, intent);
        finish();
    }

    //Get the items from this Restaurant on DB
    //Put them here
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