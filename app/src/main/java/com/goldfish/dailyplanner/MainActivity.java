package com.goldfish.dailyplanner;

import androidx.appcompat.app.AppCompatActivity;
import androidx.room.Room;

import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.goldfish.dailyplanner.dao.AppDatabase;
import com.goldfish.dailyplanner.dao.Database;
import com.goldfish.dailyplanner.model.Subject;
import com.goldfish.dailyplanner.util.TimeUtil;

import java.util.ArrayList;
import java.time.LocalDate;
import java.util.Date;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private Database database;
    private LinearLayout linearLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        database = Database.getInstance(this);

        database.insertSubject(new Subject(1, "test", "test", true));

        database.getSubjectList(new Database.ResultCallBack<List<Subject>>() {
            @Override
            public void run(List<Subject> result) {
                Toast.makeText(MainActivity.this, result.toString(), Toast.LENGTH_LONG).show();
            }
        });

        linearLayout = findViewById(R.id.layout6);

        int len = linearLayout.getChildCount();

        for(int index=0 ; index<len ; index++){
            ImageView img = (ImageView) linearLayout.getChildAt(index);
            img.setOnClickListener(new View.OnClickListener() {
                public void onClick(View v) {
                    fillUp(linearLayout.indexOfChild(v));
                }
            });
        }
    }

    void fillUp(int partition){
        int len = linearLayout.getChildCount();
        for(int index = 0 ; index<len ; index++){
            if(index <= partition){
                ImageView fill = (ImageView) linearLayout.getChildAt(index);
                switch (index){
                    case 0:
                        fill.setBackgroundColor(Color.parseColor("#c1e7e8"));
                        break;
                    case 1:
                        fill.setBackgroundColor(Color.parseColor("#8dd5e1"));
                        break;
                    case 2:
                        fill.setBackgroundColor(Color.parseColor("#61c0d4"));
                        break;
                    case 3:
                        fill.setBackgroundColor(Color.parseColor("#4bafc9"));
                        break;
                    case 4:
                        fill.setBackgroundColor(Color.parseColor("#42abc9"));
                        break;
                    case 5:
                        fill.setBackgroundColor(Color.parseColor("#2e9abe"));
                        break;
                    case 6:
                        fill.setBackgroundColor(Color.parseColor("#218bb3"));
                        break;
                    case 7:
                        fill.setBackgroundColor(Color.parseColor("#0a6498"));
                        break;
                    case 8:
                        fill.setBackgroundColor(Color.parseColor("#05467c"));
                        break;
                    case 9:
                        fill.setBackgroundColor(Color.parseColor("#042d63"));
                        break;
                }
            }
            else{
                ImageView fill = (ImageView) linearLayout.getChildAt(index);
                fill.setBackgroundColor(Color.parseColor("#00000000"));
            }
        }
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