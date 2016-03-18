package com.timapps.drawing;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import java.math.RoundingMode;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Random;

public class SandcwichScreen extends AppCompatActivity {
   /* GENERAL CLASS VARIABLES */

   //DATA STRUCTURES
   private HashMap<Integer, String> roygbivPlus = new HashMap<Integer, String>();
   private ArrayList timeList = new ArrayList();

   //INTEGERS
   private int key          = 0; //the correct number
   private int endOfGame    = 0; //number of correct to finish the game
   private int counter      = 0; //User's total correct
   private int wrongCounter = 0;

   //LONGS (for time)
   private long startTime, endTime;

   //BOOLEANS
   private boolean isStarted = false; //Checks the number of times start was pressed.
   //true means to show reset, false is to show
   //start.

   //STRINGS
   private String totalTime    = ""; //will take the difference between startTime and
   //endTime
   private String currentColor = ""; //the current background
   private String prevColor    = ""; //stores previous color
   private String background   = ""; //color from previous activities
    /*             END               */

   @Override
   protected void onCreate(Bundle savedInstanceState) {
      String background;
      super.onCreate(savedInstanceState);
      setContentView(R.layout.activity_sandcwich_screen);

      Bundle extras = getIntent().getExtras();
      background = extras.getString("BACKGROUND_COLOR");
        /*                           */

      View view = this.getWindow().getDecorView();
      ImageView img = (ImageView) findViewById(R.id.imageView4);

      //Carries over information from previous activity, sets sky object
      if (background.equals("moon")) {
         view.setBackgroundColor(Color.BLACK);
         img.setImageResource(R.drawable.cartoonmoon);
      } else {
         view.setBackgroundColor(Color.WHITE);
         img.setImageResource(R.drawable.cartoonsun);
      }

      fillHash();

      Button prevLeft = (Button) findViewById(R.id.prevButtLeft);
      prevLeft.setOnClickListener(new View.OnClickListener() {
         public void onClick(View v) {
            prevLeftOnClick();
         }
      });

      Button prevRight = (Button) findViewById(R.id.prevButtRight);
      prevRight.setOnClickListener(new View.OnClickListener() {
         public void onClick(View v) {
            prevRightOnClick();
         }
      });

      Button nextLeft = (Button) findViewById(R.id.nextButtLeft);
      nextLeft.setOnClickListener(new View.OnClickListener() {
         public void onClick(View v) {
            nextLeftOnClick();
         }
      });

      Button nextRight = (Button) findViewById(R.id.nextButtRight);
      nextRight.setOnClickListener(new View.OnClickListener() {
         public void onClick(View v) {
            nextRightOnClick();
         }
      });

      Button start = (Button) findViewById(R.id.startButt);
      start.setOnClickListener(new View.OnClickListener() {
         public void onClick(View v) {
            startOnClick();
         }
      });

      Button go = (Button) findViewById(R.id.goButt);
      start.setOnClickListener(new View.OnClickListener() {
         public void onClick(View v) {
            goOnClick();
         }
      });
   }

   //ROYGBIV, simple order and integer keys
   public void fillHash() {
      roygbivPlus.put(1, "red");
      roygbivPlus.put(2, "orange");
      roygbivPlus.put(3, "light orange");
      roygbivPlus.put(4, "yellow");
      roygbivPlus.put(5, "lime");
      roygbivPlus.put(6, "green");
      roygbivPlus.put(7, "cyan");
      roygbivPlus.put(8, "blue");
      roygbivPlus.put(9, "indigo");
      roygbivPlus.put(10, "violet");
      roygbivPlus.put(11, "pink");
   }

   //Button listener for prevButt
   //Checks if start has been pressed, then moves the current key of
   //the hashmap, moving the background color to the left.
   //If it goes all the way to the left (1) it wraps around to 7
   //(all the way at the end).
   public int prevLeftOnClick() {
      ImageView matchColor = (ImageView) findViewById(R.id.leftColor);
      if (isStarted) {
         key -= 1;
         if (key < 1) {
            key = 11;
         }

         //Gets the string associated with the current key value,
         //then runs the changeColor function, then says the current
         //color is that string value.
         String temp = String.valueOf(roygbivPlus.get(key));
         changeColor(temp, matchColor);
         currentColor = temp;
      }

      return key;
   }

   //Same functionality but changes the other button
   public int prevRightOnClick() {
      ImageView matchColor = (ImageView) findViewById(R.id.rightColor);
      if (isStarted) {
         key -= 1;
         if (key < 1) {
            key = 11;
         }

         String temp = String.valueOf(roygbivPlus.get(key));
         changeColor(temp, matchColor);
         currentColor = temp;
      }

      return key;
   }

   //Does the same as prevOnClick but moves in the opposite direction
   public int nextLeftOnClick() {
      ImageView matchColor = (ImageView) findViewById(R.id.leftColor);
      if (isStarted) {
         key += 1;
         if (key > 11) {
            key = 1;
         }

         String temp = String.valueOf(roygbivPlus.get(key));
         changeColor(temp, matchColor);
         currentColor = temp;
      }

      return key;
   }

   //nextLeft for the right button
   public int nextRightOnClick() {
      ImageView matchColor = (ImageView) findViewById(R.id.rightColor);
      if (isStarted) {
         key += 1;
         if (key > 11) {
            key = 1;
         }

         String temp = String.valueOf(roygbivPlus.get(key));
         changeColor(temp, matchColor);
         currentColor = temp;
      }

      return key;
   }

   //Linked to the startButt
   //If it hasn't been clicked yet, it generates a random color,
   //changes isStarted, takes the startTime, then changes the
   //button into the reset button
   //If it has already been started, it performs the resetClick()
   //operation, taking it back to original state
   public void startOnClick() {
      TextView text = (TextView) findViewById(R.id.sandwichCounter);
      text.setText("69!");
      Button startButton = (Button) findViewById(R.id.startButt);
      if (!isStarted) {
         startTime = System.nanoTime();
         randColor();
         isStarted = true;
         startButton.setText("Reset");
         startButton.setBackgroundColor(Color.RED);
      } else {
         //If startButt is in its reset state, and it is clicked
         //1. Says it's no longer started and takes a new time
         //2. It changes the button back to its default state
         TextView countText = (TextView) findViewById(R.id.test);
         TextView colorText = (TextView) findViewById(R.id.colorNow);
         startTime = System.nanoTime();
         isStarted = false;
         counter = 0;
         countText.setText("0");
         colorText.setText("");
         startButton.setText("Start");
         startButton.setBackgroundColor(Color.GREEN);
      }

   }

   //Assigned to the goButt
   //1. it checks if the program has been started
   //2. it checks the current color versus the text of the
   //randomly generated color
   //If they match, it will generate a new color, increment the
   //number of correct matches
   //3. it will change the textfield to reflect the new count,
   //and set the text color to grey
   //4. If the colors don't match, it sets the counter textfield
   //to say "wrong" in the red color, and increments the wrongCounter
   public void goOnClick() {
      TextView colorText = (TextView) findViewById(R.id.colorNow);
      TextView countText = (TextView) findViewById(R.id.test);
      if (isStarted) {
         if (currentColor.equals(colorText.getText())) {
            randColor();
            counter++;
            countText.setText(String.valueOf(counter));
            countText.setTextColor(Color.rgb(142, 142, 142));

            //This is the endgame condition
            //1. Checks to see if the user has gotten the correct number
            //right (5 is a test, the counter will switch depending on the
            //constant and the rest of this text will be relegated to a
            //function.
            //2. If it matches, it takes the endtime (which will be a tiny
            //bit off, but close enough)
            //3. Calls the elapsedTime() function, setting it equal to totalTime,
            //says it's started, adds the time to the list, and resets the color
            //to blank
            isGameDone(endOfGame);
         } else {
            countText.setText("WRONG");
            countText.setTextColor(Color.RED);
            wrongCounter++;
         }
      }
   }

   //Changes the color based on the text received. Might replace with a
   //for loop, would probably be shorter.
   public void changeColor(String temp, ImageView colorBackground) {
      switch (temp) {
         case "red":
            colorBackground.setBackgroundColor(Color.RED);
            break;
         case "orange":
            colorBackground.setBackgroundColor(Color.rgb(255, 132, 0));
            break;
         case "light orange":
            colorBackground.setBackgroundColor(Color.rgb(255, 178, 102));
            break;
         case "yellow":
            colorBackground.setBackgroundColor(Color.YELLOW);
            break;
         case "lime":
            colorBackground.setBackgroundColor(Color.rgb(127, 255, 0));
            break;
         case "green":
            colorBackground.setBackgroundColor(Color.GREEN);
            break;
         case "cyan":
            colorBackground.setBackgroundColor(Color.CYAN);
            break;
         case "blue":
            colorBackground.setBackgroundColor(Color.BLUE);
            break;
         case "indigo":
            colorBackground.setBackgroundColor(Color.rgb(51, 0, 102));
            break;
         case "violet":
            colorBackground.setBackgroundColor(Color.rgb(102, 0, 102));
            break;
         case "pink":
            colorBackground.setBackgroundColor(Color.rgb(227, 39, 221));
            break;
      }
   }

   //Checks the game mode constant to see if the user has enough correct
   //to win the game yet
   //Takes the end time, then calculates the total time run using the
   //elapsedTime() function, then changes the text, sets some defaults, and
   //adds the time to the bank of times.
   public void isGameDone(int winningCount) {
      TextView countText = (TextView) findViewById(R.id.test);
      TextView colorText = (TextView) findViewById(R.id.colorNow);
      if (counter == winningCount) {
         endTime = System.nanoTime();
         totalTime = elapsedTime();
         countText.setText(totalTime);
         saveScores(totalTime);
         isStarted = false;
         colorText.setText("");
      }
   }

   //calculates the elapsed time, assuming that a startTime was already initiated
   //and endTime was just taken. It properly formats the time to three decimal
   //places.
   public String elapsedTime() {
      double temp = (endTime - startTime) * 0.000000001;
      DecimalFormat form = new DecimalFormat("#.###");
      form.setRoundingMode(RoundingMode.CEILING);
      return form.format(temp);
   }

   public void saveScores(String time) {
      //save the items in the ArrayList
      timeList.add(time);
      //write to the saves.txt file
   }

   //Starts the program off with a new Text color which the
   //user must match
   public int randColor() {
      ImageView currentColor = (ImageView) findViewById(R.id.colorToMatch);
      Random goRand = new Random();
      int num = goRand.nextInt(11) + 1;
      if (roygbivPlus.get(num).equals(prevColor)) {
         randColor();
      } else {
         changeColor(roygbivPlus.get(num), currentColor);
      }

      return num;
   }
}
