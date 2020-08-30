package com.goldfish.dailyplanner;

import android.content.Context;
import android.graphics.Color;
import android.graphics.Rect;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.goldfish.dailyplanner.dao.Database;
import com.goldfish.dailyplanner.model.Subject;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Locale;

public class MainActivity extends AppCompatActivity {

    private Database database;
    private LinearLayout linearLayout;
    private TextView Year;
    private TextView Month;
    private TextView Week;
    private TextView Date;
    private TextView Day;
    private TextView Percent;

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
        setNow();
    }

    void fillUp(int partition){
        Percent = findViewById(R.id.percent);
        String per = Integer.toString((partition+1)*10) + "%";
        Percent.setText(per);
        int len = linearLayout.getChildCount();
        for(int index = 0 ; index<len-1 ; index++){
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

    void setNow(){
        SimpleDateFormat sdf = new SimpleDateFormat("EEEE", Locale.ENGLISH);
        Date d = new Date();
        String dayOfTheWeek = sdf.format(d);

        linearLayout = findViewById(R.id.layout6);
        int len = linearLayout.getChildCount();
        for(int index=0 ; index<len-1 ; index++){
            ImageView img = (ImageView) linearLayout.getChildAt(index);
            img.setOnClickListener(new View.OnClickListener() {
                public void onClick(View v) {
                    fillUp(linearLayout.indexOfChild(v));
                }
            });
        }

        int year = Calendar.getInstance().get(Calendar.YEAR);
        int month = Calendar.getInstance().get(Calendar.MONTH)+1;
        int date = Calendar.getInstance().get(Calendar.DATE);
        int week = Calendar.getInstance().get(Calendar.WEEK_OF_MONTH);

        Year = findViewById(R.id.year);
        Month = findViewById(R.id.month);
        Date = findViewById(R.id.date);
        Week = findViewById(R.id.week);
        Day = findViewById(R.id.day);

        Year.setText(Integer.toString(year));
        Month.setText(Integer.toString(month));
        Date.setText(Integer.toString(date));
        Week.setText(Integer.toString(week));
        Day.setText(dayOfTheWeek);

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

    @Override
    public boolean dispatchTouchEvent(MotionEvent event) {
        if (event.getAction() == MotionEvent.ACTION_DOWN) {
            View v = getCurrentFocus();
            if ( v instanceof EditText) {
                Rect outRect = new Rect();
                v.getGlobalVisibleRect(outRect);
                if (!outRect.contains((int)event.getRawX(), (int)event.getRawY())) {
                    v.clearFocus();
                    InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
                    imm.hideSoftInputFromWindow(v.getWindowToken(), 0);
                }
            }
        }
        return super.dispatchTouchEvent( event );
    }
}