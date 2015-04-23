package com.example.chickentime;

import java.util.Date;
import java.io.IOException;

import android.app.Activity;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.chickentime.Chicken.ChickenStatus;

public class MainActivity extends Activity
{
  static MainActivity main;
  Chicken             chicken;
  Date                date;

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

    date = new Date();
    date.setHours(0);
    date.setMinutes(0);
    date.setSeconds(0);
    
    ProgressWheel pw = (ProgressWheel) findViewById(R.id.progressBarTwo);
    
    pw.setProgress(270); //progess in degrees
  }

  @Override
  public void onResume()
  {
    super.onResume();
    //
    // SharedPreferences sharedPref = this.getSharedPreferences("com.example.chickentime", Context.MODE_PRIVATE);
    // if (!sharedPref.getBoolean("chickenIsAlive", true) && !ScreenReceiver.toBeOff)
    // {
    // sharedPref.edit().putBoolean("chickenIsAlive", true).apply();
    // restartChicken(null);
    // Intent intent = new Intent(this, KillChickenActivity.class);
    // startActivity(intent);
    // }
    // if (ScreenReceiver.toBeOff == true)
    // {
    // switch (chicken.prev)
    // {
    // case DEAD:
    // chicken.status = ChickenStatus.DEAD;
    // break;
    // case DOSTHG:
    // chicken.status = ChickenStatus.DOSTHG;
    // break;
    // case START:
    // chicken.status = ChickenStatus.START;
    // break;
    // }
    // ScreenReceiver.toBeOff = false;
    // }
  }
  @Override
  public boolean onCreateOptionsMenu(Menu menu) {
      MenuInflater inflater = getMenuInflater();
      inflater.inflate(R.layout.mainmenu, menu);
      return true;
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
        	text.setText("Your chicken has died a miserable death!!");
        	
        	WebView webview = (WebView) layout.findViewById(R.id.webview);
        	webview.loadUrl("file:///android_asset/anim4.gif");
        	webview.setBackgroundColor(Color.TRANSPARENT);
        	
            
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
    Log.e("CO", "O");
    chicken.status = Chicken.ChickenStatus.DOSTHG;
  }

  public void increment(View view)
  {
    ProgressWheel pw = (ProgressWheel) findViewById(R.id.progressBarTwo);
    pw.incrementProgress();
  }

  public void incMin(View view)
  {
    TextView tv = (TextView)findViewById(R.id.hmin);
    date.setMinutes(date.getMinutes() + 5);
    StringBuffer h= new StringBuffer();
    StringBuffer m= new StringBuffer();
    if (date.getHours()< 10)
      h.append("0");
    h.append(date.getHours());
    if (date.getMinutes()<10)
      m.append("0");
    m.append(date.getMinutes());
    tv.setText(h.toString() + ":" + m.toString());
  }

  public void incH(View view)
  {
    TextView tv = (TextView)findViewById(R.id.hmin);
    date.setHours(date.getHours() + 1);
    StringBuffer h= new StringBuffer();
    StringBuffer m= new StringBuffer();
    if (date.getHours()< 10)
      h.append("0");
    h.append(date.getHours());
    if (date.getMinutes()<10)
      m.append("0");
    m.append(date.getMinutes());
    tv.setText(h.toString() + ":" + m.toString());
  }

  public void decH(View view)
  {
    TextView tv = (TextView)findViewById(R.id.hmin);
    date.setHours(date.getHours() - 1);
    StringBuffer h= new StringBuffer();
    StringBuffer m= new StringBuffer();
    if (date.getHours()< 10)
      h.append("0");
    h.append(date.getHours());
    if (date.getMinutes()<10)
      m.append("0");
    m.append(date.getMinutes());
    tv.setText(h.toString() + ":" + m.toString());

  }

  public void decMin(View view)
  {
    TextView tv = (TextView)findViewById(R.id.hmin);
    date.setMinutes(date.getMinutes() - 5);
    StringBuffer h= new StringBuffer();
    StringBuffer m= new StringBuffer();
    if (date.getHours()< 10)
      h.append("0");
    h.append(date.getHours());
    if (date.getMinutes()<10)
      m.append("0");
    m.append(date.getMinutes());
    tv.setText(h.toString() + ":" + m.toString());
  }

}
