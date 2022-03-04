package edu.neu.madcourse.numad21fa_yaqianyang;


import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.constraintlayout.widget.ConstraintSet;
import androidx.recyclerview.widget.RecyclerView;

import android.Manifest;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.Locale;

public class MainActivity extends AppCompatActivity {

    private ArrayList<ItemCard> itemList = new ArrayList<>();

    private RecyclerView recyclerView;
    private RviewAdapter rviewAdapter;
    private RecyclerView.LayoutManager rLayoutManger;
    private FloatingActionButton addButton;

    private static final String KEY_OF_INSTANCE = "KEY_OF_INSTANCE";
    private static final String NUMBER_OF_ITEMS = "NUMBER_OF_ITEMS";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void setToast(View view) {
        Toast toast = Toast.makeText(getApplicationContext(), "Yaqian Yang; yang.yaqi@northeastern.edu", Toast.LENGTH_SHORT);
        toast.setMargin(50, 50);
        toast.show();
    }

    public void Btn(View view) {
        Intent intent = new Intent(this, ClickyClickyActivity.class);
        startActivity(intent);
    }

    public void LinkCollector(View view) {
        Intent intent = new Intent(this, LinkCollectorActivity.class);
        startActivity(intent);
    }

    public void location(View view) {
        Intent intent = new Intent(this, locationActivity.class);
        startActivity(intent);

    }
    public void api(View view){
        Intent intent = new Intent(this, api.class);
        startActivity(intent);
    }


}