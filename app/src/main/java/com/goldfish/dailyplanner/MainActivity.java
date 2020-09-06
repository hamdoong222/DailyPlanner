package com.goldfish.dailyplanner;

import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.MobileAds;
import com.google.android.gms.ads.initialization.InitializationStatus;
import com.google.android.gms.ads.initialization.OnInitializationCompleteListener;


import android.content.Context;
import android.graphics.Color;
import android.graphics.Rect;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.goldfish.dailyplanner.db.dao.Database;
import com.goldfish.dailyplanner.model.Subject;
import com.goldfish.dailyplanner.model.Todo;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
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
    private AdView mAdView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        MobileAds.initialize(this, new OnInitializationCompleteListener() {
            @Override
            public void onInitializationComplete(InitializationStatus initializationStatus) {
            }
        });

        //addMob
        mAdView = findViewById(R.id.adView);
        AdRequest adRequest = new AdRequest.Builder().build();
        mAdView.loadAd(adRequest);

        //Database
        database = Database.getInstance(this);

        linearLayout = findViewById(R.id.layout6);
        initButton();
        loadSubject();
        loadTodo();
        setNow();
    }

    private void initButton() {
        View todoLayout = findViewById(R.id.layout_todo_button);
        View subjectLayout = findViewById(R.id.layout_subject_button);

        Button todoRemoveBtn = todoLayout.findViewById(R.id.remove);
        Button todoAddBtn = todoLayout.findViewById(R.id.add);

        Button subjectRemoveBtn = subjectLayout.findViewById(R.id.remove);
        Button subjectAddBtn = subjectLayout.findViewById(R.id.add);

        todoRemoveBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                database.insertTodoList(createTodoFromView());
                database.deleteTodo(((LinearLayout)findViewById(R.id.todo_layout)).getChildCount());
                loadTodo();
            }
        });

        todoAddBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                database.insertTodoList(createTodoFromView());
                database.insertTodoList(new Todo(((LinearLayout)findViewById(R.id.todo_layout)).getChildCount() + 1, false, ""));
                loadTodo();
            }
        });

        subjectAddBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                database.insertSubjectList(createSubjectFromView());
                database.insertSubjectList(new Subject(((LinearLayout)findViewById(R.id.subject_layout)).getChildCount() + 1, "", "", false));
                loadSubject();
            }
        });

        subjectRemoveBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                database.insertSubjectList(createSubjectFromView());
                database.deleteSubject(((LinearLayout)findViewById(R.id.subject_layout)).getChildCount());
                loadSubject();
            }
        });
    }

    void fillUp(int partition){
        Percent = findViewById(R.id.percent);
        String per = (partition + 1) * 10 + "%";
        Percent.setText(per);
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
        database.insertTodoList(createTodoFromView());
        database.insertSubjectList(createSubjectFromView());
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

    private void loadSubject() {
        database.getSubjectList(new Database.ResultCallBack<List<Subject>>() {
            @Override
            public void run(List<Subject> result) {
                LinearLayout linearLayout = (LinearLayout)findViewById(R.id.subject_layout);
                linearLayout.removeAllViews();

                for (Subject subject : result) {
                    RelativeLayout subjectLayout = (RelativeLayout) LayoutInflater.from(getApplicationContext()).inflate(R.layout.layout_subject_item, null);
                    linearLayout.addView(subjectLayout);

                    TextView subjectTextView = subjectLayout.findViewById(R.id.subject);
                    TextView goalTextView = subjectLayout.findViewById(R.id.goal);
                    CheckBox checkBox = subjectLayout.findViewById(R.id.checkbox);

                    subjectTextView.setText(subject.getSubject());
                    goalTextView.setText(subject.getContent());
                    checkBox.setChecked(subject.isChecked());
                }
            }
        });
    }

    private void loadTodo() {
        database.getTodoList(new Database.ResultCallBack<List<Todo>>() {
            @Override
            public void run(List<Todo> result) {
                LinearLayout linearLayout = (LinearLayout)findViewById(R.id.todo_layout);
                linearLayout.removeAllViews();

                for (Todo todo : result) {
                    RelativeLayout todoLayout = (RelativeLayout) LayoutInflater.from(getApplicationContext()).inflate(R.layout.layout_todo_item, null);
                    linearLayout.addView(todoLayout);

                    TextView contentTextView = todoLayout.findViewById(R.id.content);
                    CheckBox checkBox = todoLayout.findViewById(R.id.checkbox);

                    contentTextView.setText(todo.getContent());
                    checkBox.setChecked(todo.isChecked());
                }
            }
        });
    }

    private List<Todo> createTodoFromView() {
        LinearLayout linearLayout = (LinearLayout)findViewById(R.id.todo_layout);
        List<Todo> todoList = new ArrayList<>();
        for (int index = 0; index < linearLayout.getChildCount(); index++) {
            RelativeLayout todoLayout = (RelativeLayout) linearLayout.getChildAt(index);

            TextView contentTextView = todoLayout.findViewById(R.id.content);
            CheckBox checkBox = todoLayout.findViewById(R.id.checkbox);

            todoList.add(new Todo(index + 1, checkBox.isChecked(), contentTextView.getText().toString()));
        }

        return todoList;
    }

    private List<Subject> createSubjectFromView() {
        LinearLayout linearLayout = (LinearLayout)findViewById(R.id.subject_layout);
        List<Subject> subjectList = new ArrayList<>();
        for (int index = 0; index < linearLayout.getChildCount(); index++) {
            RelativeLayout todoLayout = (RelativeLayout) linearLayout.getChildAt(index);

            TextView subjectTextView = todoLayout.findViewById(R.id.subject);
            TextView goalTextView = todoLayout.findViewById(R.id.goal);
            CheckBox checkBox = todoLayout.findViewById(R.id.checkbox);

            subjectList.add(new Subject(index + 1, subjectTextView.getText().toString(), goalTextView.getText().toString(), checkBox.isChecked()));
        }

        return subjectList;
    }

}