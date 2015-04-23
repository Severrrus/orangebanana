package com.example.chickentime;

import android.app.Activity;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.app.NotificationCompat;
import android.support.v4.app.TaskStackBuilder;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.example.chickentime.Chicken.ChickenStatus;

public class MainActivity extends Activity
{
  static MainActivity main;
  Chicken             chicken;

  @Override
  protected void onCreate(Bundle savedInstanceState)
  {
    super.onCreate(savedInstanceState);
    main = this;
    chicken = new Chicken();
    setContentView(R.layout.activity_main);

    IntentFilter filter = new IntentFilter(Intent.ACTION_SCREEN_ON);
    filter.addAction(Intent.ACTION_SCREEN_OFF);
    BroadcastReceiver mReceiver = new ScreenReceiver();
    registerReceiver(mReceiver, filter);

    onResume();
  }

  @Override
  public void onResume()
  {
    super.onResume();

    SharedPreferences sharedPref = this.getSharedPreferences("com.example.chickentime", Context.MODE_PRIVATE);
    if (!sharedPref.getBoolean("chickenIsAlive", true) && !ScreenReceiver.toBeOff)
    {
      sharedPref.edit().putBoolean("chickenIsAlive", true).apply();
      restartChicken(null);
      Intent intent = new Intent(this, KillChickenActivity.class);
      startActivity(intent);
    }
    if (ScreenReceiver.toBeOff == true)
    {
      switch (chicken.prev)
      {
        case DEAD:
          chicken.status = ChickenStatus.DEAD;
          break;
        case DOSTHG:
          chicken.status = ChickenStatus.DOSTHG;
          break;
        case START:
          chicken.status = ChickenStatus.START;
          break;
      }
      ScreenReceiver.toBeOff = false;
    }
  }

  @Override
  public void onPause()
  {
    super.onPause();
    if (chicken.status == ChickenStatus.DOSTHG)
      kill();

    ScreenReceiver.toBeOff = false;
    SharedPreferences sharedPref = this.getSharedPreferences("com.example.chickentime", Context.MODE_PRIVATE);
    Boolean b = true;
    if (chicken.status == ChickenStatus.DEAD)
      b = false;
    sharedPref.edit().putBoolean("chickenIsAlive", b).apply();

  }

  public void farm(View view)
  {
    Intent intent = new Intent(this, TabActivity.class);
    startActivity(intent);
  }

  public void kill()
  {
    switch (chicken.status)
    {
      case DEAD:
        chicken.prev = ChickenStatus.DEAD;
        break;
      case DOSTHG:
        chicken.prev = ChickenStatus.DOSTHG;
        break;
      case START:
        chicken.prev = ChickenStatus.START;
        break;
    }

    chicken.status = ChickenStatus.DEAD;
    new Handler().postDelayed(new Runnable()
    {

      @Override
      public void run()
      {
        if (ScreenReceiver.toBeOff == false)
        {
        	LayoutInflater inflater = getLayoutInflater();
        	View layout = inflater.inflate(R.layout.toast_view,
        	                               (ViewGroup) findViewById(R.id.toast_layout_root));

        	TextView text = (TextView) layout.findViewById(R.id.text);
        	text.setText("This is a custom toast");

        	Toast toast = new Toast(getApplicationContext());
        	toast.setGravity(Gravity.CENTER_VERTICAL, 0, 0);
        	toast.setDuration(Toast.LENGTH_LONG);
        	toast.setView(layout);
        	toast.show();
        	
        	Toast aToast = Toast.makeText(MainActivity.main, getString(R.string.ChickenKilled), Toast.LENGTH_SHORT);
        	aToast.show();
        }
      }
    }, 1000);

  }

  public void spawnChicken(View view)
  {
    chicken.status = Chicken.ChickenStatus.DOSTHG;
//    findViewById(R.id.button1).setVisibility(View.INVISIBLE);
//    findViewById(R.id.button2).setVisibility(View.VISIBLE);
  }

  public void increment(View view){
    ProgressWheel pw = (ProgressWheel)findViewById(R.id.progressBarTwo);
    pw.incrementProgress();
  }
  public void restartChicken(View view)
  {
//    chicken.status = Chicken.ChickenStatus.START;
//    findViewById(R.id.button2).setVisibility(View.INVISIBLE);
//    findViewById(R.id.button1).setVisibility(View.VISIBLE);
  }

}
