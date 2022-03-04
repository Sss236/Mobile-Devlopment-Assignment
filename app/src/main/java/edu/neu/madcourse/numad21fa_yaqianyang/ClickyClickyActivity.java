package edu.neu.madcourse.numad21fa_yaqianyang;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class ClickyClickyActivity extends AppCompatActivity implements View.OnTouchListener {

    public TextView textView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_clicky_clicky);

        textView = findViewById(R.id.textView2);

        Button button2 = findViewById(R.id.button2);
        Button button3 = findViewById(R.id.button3);
        Button button4 = findViewById(R.id.button4);
        Button button5 = findViewById(R.id.button5);
        Button button6 = findViewById(R.id.button6);
        Button button7 = findViewById(R.id.button7);

        button2.setOnTouchListener(this);
        button3.setOnTouchListener(this);
        button4.setOnTouchListener(this);
        button5.setOnTouchListener(this);
        button6.setOnTouchListener(this);
        button7.setOnTouchListener(this);
    }

    @Override
    public boolean onTouch(View view, MotionEvent motionEvent) {
        if (motionEvent.getAction() == MotionEvent.ACTION_DOWN) {

            switch (view.getId()) {
                case R.id.button2:
                    textView.setText("Pressed: A");
                    break;
                case R.id.button3:
                    textView.setText("Pressed: B");
                    break;
                case R.id.button4:
                    textView.setText("Pressed: C");
                    break;
                case R.id.button5:
                    textView.setText("Pressed: D");
                    break;
                case R.id.button6:
                    textView.setText("Pressed: E");
                    break;
                case R.id.button7:
                    textView.setText("Pressed: F");
                    break;
            }
        } else if (motionEvent.getAction() == MotionEvent.ACTION_UP) {
            textView.setText("Pressed: - ");
        }

        return true;
    }
}