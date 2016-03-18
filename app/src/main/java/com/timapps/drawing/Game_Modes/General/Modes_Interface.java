package com.timapps.drawing.Game_Modes.General;

import android.view.View;
import android.widget.ImageView;

/**
 * Created by Tim Froberg on 3/1/2016.
 * Interface for at least expanded and regular
 */
public interface Modes_Interface {
   void fillHash();
   int prevOnClick(String id);
   int nextOnClick();
   void goOnClick() throws InterruptedException;
   void startOnClick();
   int randColor();
   void changeColor(String temp, ImageView colorBackground);
   String elapsedTime();
   void isGameDone(int winningCount);
   void saveScores(String time);
   void scoreLaunch(View v);
}