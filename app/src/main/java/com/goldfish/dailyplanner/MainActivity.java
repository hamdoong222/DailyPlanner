package com.goldfish.dailyplanner;

import androidx.appcompat.app.AppCompatActivity;
import androidx.room.Room;

import android.os.Bundle;
import android.widget.Toast;

import com.goldfish.dailyplanner.dao.AppDatabase;
import com.goldfish.dailyplanner.dao.Database;
import com.goldfish.dailyplanner.model.Subject;

import java.util.Date;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private Database database;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        database = Database.getInstance(this);

        database.insertSubject(new Subject(1, "test", "test", true));

        database.getSubjectList(new Date(2019, 8, 29), new Date(2021, 8, 30), new Database.ResultCallBack<List<Subject>>() {
            @Override
            public void run(List<Subject> result) {
                Toast.makeText(MainActivity.this, result.toString(), Toast.LENGTH_LONG).show();
            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();
    }

    @Override
    protected void onPause() {
        super.onPause();
        //TODO : 이곳에서 작성한 정보를 저장
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }
}