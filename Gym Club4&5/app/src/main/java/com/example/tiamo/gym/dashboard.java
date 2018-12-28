package com.example.tiamo.gym;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;

public class dashboard extends AppCompatActivity {

    private ImageView student;
    private ImageView teacher;
    private ImageView trainer;
    private ImageView worker;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboard);
        initView();
    }


    private void initView() {
        student = (ImageView) findViewById(R.id.rejstudent);
        teacher = (ImageView) findViewById(R.id.rejteacher);
        trainer = (ImageView) findViewById(R.id.rejtrainer);
        worker = (ImageView) findViewById(R.id.rejworker);

        student.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(dashboard.this, RegisterActivity.class);
                i.putExtra("rmsg","1");
                startActivity(i);
            }
        });
        teacher.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(dashboard.this, RegisterActivity.class);
                i.putExtra("rmsg","2");
                startActivity(i);
            }
        });
        trainer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(dashboard.this, RegisterActivity.class);
                i.putExtra("rmsg","3");
                startActivity(i);
            }
        });
        worker.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(dashboard.this, RegisterActivity.class);
                i.putExtra("rmsg","4");
                startActivity(i);
            }
        });
    }
}
