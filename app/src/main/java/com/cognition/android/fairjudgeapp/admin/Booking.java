package com.cognition.android.fairjudgeapp.admin;

import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toolbar;

import com.cognition.android.fairjudgeapp.R;

public class Booking extends AppCompatActivity {

    Toolbar toolbar;
    ListView listView;
    String[] listItem;

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_booking);

        /*Initialization of views*/
        toolbar = findViewById(R.id.toolbar);
        listView = findViewById(R.id.list_view);

        listItem = getResources().getStringArray(R.array.array_payment);
        final ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_list_item_1, android.R.id.text1, listItem);
        listView.setAdapter(adapter);

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            toolbar.inflateMenu(R.menu.payment_list);
            toolbar.setTitleTextColor(Color.WHITE);
            toolbar.setTitle("Book & view payment");
            toolbar.setOnMenuItemClickListener(new Toolbar.OnMenuItemClickListener() {
                @Override
                public boolean onMenuItemClick(MenuItem item) {
                    switch (item.getItemId()) {
                        case R.id.paylist:
                            // Toast.makeText(Booking.this, "Payment Here", Toast.LENGTH_SHORT).show();
                            //Payment Shifted below infor dialog
                    }
                    return false;
                }
            });
        }
    }
}
