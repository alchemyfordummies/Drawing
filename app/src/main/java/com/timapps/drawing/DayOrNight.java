package com.timapps.drawing;

import android.graphics.Color;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.ImageView;
import android.content.Intent;

public class DayOrNight extends AppCompatActivity {

    private String background = "";
    private String image = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_day_or_night);
        //Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        //setSupportActionBar(toolbar);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_day_or_night, menu);
        return true;
    }

    public void nightButtonClick (View v) {
        setBackgroundNight();
        setSkyObject(background);
    }

    public void dayButtonClick (View v) {
        setBackgroundDay();
        setSkyObject(background);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    public void setBackgroundNight() {
        View view = this.getWindow().getDecorView();
        view.setBackgroundColor(Color.BLACK);
        background = "moon";
        image = "cartoonmoon";
    }

    public void setBackgroundDay() {
        View view = this.getWindow().getDecorView();
        view.setBackgroundColor(Color.WHITE);
        background = "sun";
        image = "cartoonsun";
    }

    public void setSkyObject(String s) {
        ImageView img = (ImageView)findViewById(R.id.sunOrMoon);

        if (s.equals("moon")) {
            img.setImageResource(R.drawable.cartoonmoon);
        }

        if (s.equals("sun")) {
            img.setImageResource(R.drawable.cartoonsun);
        }
    }

    //connected by the resource file to the button at the bottom
    public void sendMessage(View v) {
        Intent mainDraw = new Intent(this, GameMode.class);
        mainDraw.putExtra("BACKGROUND_COLOR", background);
        startActivity(mainDraw);
    }
}
