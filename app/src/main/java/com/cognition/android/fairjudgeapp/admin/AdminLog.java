package com.cognition.android.fairjudgeapp.admin;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutCompat;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;

import com.cognition.android.fairjudgeapp.R;
import com.cognition.android.fairjudgeapp.activities.NewEstActivity;
import com.cognition.android.fairjudgeapp.models.DatabaseHelper;
import com.cognition.android.fairjudgeapp.models.Establishment;
import com.cognition.android.fairjudgeapp.utils.AdminEstablishmentAdapter;
import com.cognition.android.fairjudgeapp.utils.EstablishmentAdapter;

import java.util.ArrayList;
import java.util.List;

public class AdminLog extends AppCompatActivity {
    LinearLayoutCompat lytParent;
    Toolbar toolbar;
    SwipeRefreshLayout refreshList;
    RecyclerView estList;
    FloatingActionButton fabAddEstablishment;

    DatabaseHelper databaseHelper;
    EstablishmentAdapter establishmentAdapter;
    AdminEstablishmentAdapter adminEstablishmentAdapter;
    List<Establishment> establishmentList;


    FloatingActionButton floatingActionButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_log);
        /*Initialize*/
        setTheme(R.style.AppTheme);
        /*Call methods to init views and setup the list*/
        setSupportActionBar(toolbar);
        initViews();
        setUpList();
        floatingActionButton = findViewById(R.id.fabAddEstablishment);
        floatingActionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(AdminLog.this, NewEstActivity.class));
            }
        });
    }

    private void initViews() {
        lytParent = findViewById(R.id.lytParent);
        toolbar = findViewById(R.id.toolbar);
        refreshList = findViewById(R.id.refreshList);
        estList = findViewById(R.id.estList);
        fabAddEstablishment = findViewById(R.id.fabAddEstablishment);
        refreshList.setColorSchemeResources(R.color.colorPrimary);
        refreshList.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                setUpList();
            }
        });

        databaseHelper = new DatabaseHelper(AdminLog.this);
        establishmentList = new ArrayList<>();
        //establishmentAdapter = new EstablishmentAdapter(AdminLog.this, establishmentList);
        adminEstablishmentAdapter = new AdminEstablishmentAdapter(AdminLog.this, establishmentList);
        estList.setAdapter(adminEstablishmentAdapter);
        //  estList.setAdapter(establishmentAdapter);
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(AdminLog.this);
        estList.setLayoutManager(mLayoutManager);
        estList.setItemAnimator(new DefaultItemAnimator());
    }

    private void setUpList() {
        refreshList.setRefreshing(true);
        establishmentList.clear();
        establishmentList.addAll(databaseHelper.getAllEstablishments());
        //establishmentAdapter.notifyDataSetChanged();
        adminEstablishmentAdapter.notifyDataSetChanged();
        refreshList.setRefreshing(false);
    }
}
