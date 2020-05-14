package com.cognition.android.fairjudgeapp.activities;

import android.app.SearchManager;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutCompat;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SearchView;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.widget.Toast;

import com.cognition.android.fairjudgeapp.R;
import com.cognition.android.fairjudgeapp.admin.AdminLogin;
import com.cognition.android.fairjudgeapp.admin.Booking;
import com.cognition.android.fairjudgeapp.models.DatabaseHelper;
import com.cognition.android.fairjudgeapp.models.Establishment;
import com.cognition.android.fairjudgeapp.utils.EstablishmentAdapter;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    LinearLayoutCompat lytParent;
    Toolbar toolbar;
    SwipeRefreshLayout refreshList;
    RecyclerView estList;
    FloatingActionButton fabAddEstablishment;

    DatabaseHelper databaseHelper;
    EstablishmentAdapter establishmentAdapter;
    List<Establishment> establishmentList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        setTheme(R.style.AppTheme);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        /*Call methods to init views and setup the list*/
        setSupportActionBar(toolbar);
        initViews();
        setUpList();
    }

    @Override
    protected void onResume() {
        super.onResume();
        setUpList();
    }

    private void initViews() {
        lytParent = findViewById(R.id.lytParent);
        toolbar = findViewById(R.id.toolbar);
        refreshList = findViewById(R.id.refreshList);
        estList = findViewById(R.id.estList);
        //   fabAddEstablishment = findViewById(R.id.fabAddEstablishment);
        toolbar.inflateMenu(R.menu.menu_main);

        /*Add action to items on the toolbar*/
        toolbar.setOnMenuItemClickListener(new Toolbar.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {
                int item_id = item.getItemId();
                switch (item_id) {
                    case R.id.admin_area:
                        /*Start Admin page*/
                        /*Add Login Functionality, Therefore launch admin login UI*/
                        startActivity(new Intent(MainActivity.this, AdminLogin.class));
                        Toast.makeText(MainActivity.this, "Some action on the admin area", Toast.LENGTH_SHORT).show();
                        break;
                    case R.id.book:
                        Toast.makeText(MainActivity.this, "Booking", Toast.LENGTH_SHORT).show();
                        startActivity(new Intent(MainActivity.this, Booking.class));
                }
                return false;
            }
        });

        SearchManager searchManager = (SearchManager) getSystemService(Context.SEARCH_SERVICE);
        SearchView searchView = (SearchView) toolbar.getMenu().findItem(R.id.action_search).getActionView();

        searchView.setQueryHint("Search");
        searchView.setSearchableInfo(searchManager != null ? searchManager.getSearchableInfo(getComponentName()) : null);
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                establishmentAdapter.getFilter().filter(query);
                return true;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                establishmentAdapter.getFilter().filter(newText);
                return true;
            }
        });

        refreshList.setColorSchemeResources(R.color.colorPrimary);
        refreshList.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                setUpList();
            }
        });

        databaseHelper = new DatabaseHelper(MainActivity.this);
        establishmentList = new ArrayList<>();
        establishmentAdapter = new EstablishmentAdapter(MainActivity.this, establishmentList);
        estList.setAdapter(establishmentAdapter);
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(MainActivity.this);
        estList.setLayoutManager(mLayoutManager);
        estList.setItemAnimator(new DefaultItemAnimator());
    }

    private void setUpList() {
        refreshList.setRefreshing(true);
        establishmentList.clear();
        establishmentList.addAll(databaseHelper.getAllEstablishments());
        establishmentAdapter.notifyDataSetChanged();
        refreshList.setRefreshing(false);
    }
}
