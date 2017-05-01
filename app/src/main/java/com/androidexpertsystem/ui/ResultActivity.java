package com.androidexpertsystem.ui;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.androidexpertsystem.R;
import com.androidexpertsystem.models.G5;
import com.androidexpertsystem.models.G6;
import com.androidexpertsystem.models.GalaxyS6;
import com.androidexpertsystem.models.GalaxyS7Dual;
import com.androidexpertsystem.models.OneM8;
import com.androidexpertsystem.models.OneM8Dual;
import com.androidexpertsystem.models.Pro5;
import com.androidexpertsystem.models.ResultModel;
import com.androidexpertsystem.models.XperiaM2;
import com.androidexpertsystem.models.XperiaZ3;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class ResultActivity extends AppCompatActivity {

    @BindView(R.id.list)
    RecyclerView mRecyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result);

        ButterKnife.bind(this);

        ArrayList<String> list = getIntent().getStringArrayListExtra("list");
        ArrayList<ResultModel> data = new ArrayList<>();
        if (list != null) {
            for (String key : list) {
                if (key.equals("Xperia M2 Dual")) {
                    data.add(new XperiaM2());
                } else if (key.equals("One M8")) {
                    data.add(new OneM8());
                } else if (key.equals("One M8 Dual Sim")) {
                    data.add(new OneM8Dual());
                } else if (key.equals("Galaxy S6")) {
                    data.add(new GalaxyS6());
                } else if (key.equals("Xperia Z3+")) {
                    data.add(new XperiaZ3());
                } else if (key.equals("Galaxy S7 Duos")) {
                    data.add(new GalaxyS7Dual());
                } else if (key.equals("PRO 5")) {
                    data.add(new Pro5());
                } else if (key.equals("G6")) {
                    data.add(new G6());
                } else if (key.equals("G5")) {
                    data.add(new G5());
                }
            }
        }

        ResultAdapter adapter = new ResultAdapter(this, data);
        mRecyclerView.setAdapter(adapter);
        LinearLayoutManager manager = new LinearLayoutManager(this);
        mRecyclerView.setLayoutManager(manager);

    }

    @OnClick(R.id.replay)
    void onReplay() {
        startActivity(new Intent(this, MainActivity.class));
        finish();
    }
}
