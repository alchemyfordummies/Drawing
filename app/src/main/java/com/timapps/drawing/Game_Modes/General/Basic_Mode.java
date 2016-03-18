package com.timapps.drawing.Game_Modes.General;

import android.app.Activity;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;

import com.timapps.drawing.R;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * Implements the basic form of the game modes
 * Created by Tim Froberg on 3/1/2016.
 */
public class Basic_Mode extends AppCompatActivity implements Modes_Interface {
   private HashMap<Integer, String> roygbiv = new HashMap<Integer, String>();
   private ArrayList timeList = new ArrayList();

   private int key          = 0;
   private int endOfGame    = 0;
   private int counter      = 0;
   private int wrongCounter = 0;

   private long startTime = 0;
   private long endTime   = 0;

   private boolean isStarted = false;

   private String totalTime    = "";
   private String currentColor = "";
   private String prevColor    = "";
   private String background   = "";

   @Override
   public void fillHash() {
      roygbiv.put(1, "red");
      roygbiv.put(2, "orange");
      roygbiv.put(3, "light orange");
      roygbiv.put(4, "yellow");
      roygbiv.put(5, "lime");
      roygbiv.put(6, "green");
      roygbiv.put(7, "cyan");
      roygbiv.put(8, "blue");
      roygbiv.put(9, "indigo");
      roygbiv.put(10, "violet");
      roygbiv.put(11, "pink");
   }

   @Override
   public int prevOnClick(String id) {
      ImageView matchColor = (ImageView) findViewById(R.id.randColor);
      if (isStarted) {
         key -= 1;
         if (key < 1) {
            key = 11;
         }

         //Gets the string associated with the current key value,
         //then runs the changeColor function, then says the current
         //color is that string value.
         String temp = String.valueOf(roygbiv.get(key));
         changeColor(temp, matchColor);
         currentColor = temp;
      }

      return key;
   }

   @Override
   public int nextOnClick() {
      ImageView matchColor = (ImageView) findViewById(R.id.randColor);
      if (isStarted) {
         key += 1;
         if (key > 7) {
            key = 1;
         }

         String temp = String.valueOf(roygbiv.get(key));
         changeColor(temp, matchColor);
         currentColor = temp;
      }

      return key;
   }

   @Override
   public void goOnClick() throws InterruptedException {

   }

   @Override
   public void startOnClick() {

   }

   @Override
   public int randColor() {
      return 0;
   }

   @Override
   public void changeColor(String temp, ImageView colorBackground) {

   }

   @Override
   public String elapsedTime() {
      return null;
   }

   @Override
   public void isGameDone(int winningCount) {

   }

   @Override
   public void saveScores(String time) {

   }

   @Override
   public void scoreLaunch(View v) {

   }
}
