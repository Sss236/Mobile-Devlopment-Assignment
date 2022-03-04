package edu.neu.madcourse.numad21fa_yaqianyang;

import android.app.ProgressDialog;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CalendarView;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.squareup.picasso.Picasso;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import okhttp3.OkHttpClient;
import okhttp3.Response;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class api extends AppCompatActivity {

    ImageView imageView;
    ProgressDialog pd;
    ProgressBar progressBar;
    CalendarView calendar;


    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);

        setContentView(R.layout.api);
        imageView = findViewById(R.id.imageView);
        progressBar = findViewById(R.id.progressBar);
        progressBar.setVisibility(View.INVISIBLE);

        calendar = findViewById(R.id.calendarView);
        SimpleDateFormat sdf = new SimpleDateFormat("YYYY-MM-dd");
        try{
            Date minDate = sdf.parse("2014-1-1");
            calendar.setMinDate(minDate.getTime());
        } catch (ParseException e){
            e.printStackTrace();
        }
        calendar.setMaxDate(System.currentTimeMillis());
        calendar.setOnDateChangeListener(new CalendarView.OnDateChangeListener() {

            @Override
            public void onSelectedDayChange(@NonNull CalendarView calendarView, int year, int month, int dayOfMonth) {
                String date = year + "-" + month + "-" + dayOfMonth;
                System.out.println(date);

                RequestTask request =new RequestTask();
                request.execute("https://api.nasa.gov/mars-photos/api/v1/rovers/curiosity/photos?earth_date="+ date +"&api_key=" + "1BWNLhJtlO3xt2ryM4QOn1MEsMAXN0ZLCh2Mw5Ts");

            }


        });
    }
    class RequestTask extends AsyncTask<String, Integer, String>{

        protected void onPreExecute() {
            super.onPreExecute();
            progressBar.setVisibility(View.VISIBLE);
        }
        @Override
        protected String doInBackground(String...params) {
            OkHttpClient client = new OkHttpClient();
            StringBuilder sb = new StringBuilder(params[0]);
//            Log.e("doInBackground: ", sb.toString());
//            sb.append("?earth_date=").append(date).append("&api_key=").append(key);
            Request request = new Request.Builder()
                    .url(sb.toString())
                    .build();

            try (Response response = client.newCall(request).execute()) {
                return response.body().string();
            } catch (IOException e) {
                e.printStackTrace();
                return "empty";
            }

        }
        @Override
        protected void onPostExecute(String result) {
            super.onPostExecute(result);
            if (progressBar.getVisibility()==View.VISIBLE){
                progressBar.setVisibility(View.INVISIBLE);
            }

            try {
                JSONObject json = new JSONObject(result);
                JSONArray photos = json.getJSONArray("photos");
                JSONObject selectedPhoto = photos.getJSONObject(0);
                String url = selectedPhoto.get("img_src").toString();
                Picasso.get().load(url).into(imageView);

            } catch (JSONException e){
                System.out.println(e.getMessage());
                return;
            }

        }
    }
}
