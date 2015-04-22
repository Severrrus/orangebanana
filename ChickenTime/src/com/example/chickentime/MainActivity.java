package com.example.chickentime;

import android.app.Activity;
import android.content.Context;
import android.content.DialogInterface.OnShowListener;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.widget.Toast;

public class MainActivity extends Activity
{
  static MainActivity main;

  @Override
  protected void onCreate(Bundle savedInstanceState)
  {
    super.onCreate(savedInstanceState);
    main = this;
    setContentView(R.layout.activity_main);
    onResume();
  }

  @Override
  public void onResume(){
    super.onResume();
    
    SharedPreferences sharedPref= this.getSharedPreferences("com.example.chickentime", Context.MODE_PRIVATE);
    if (sharedPref.contains("chickenIsAlive")){
      if(!sharedPref.getBoolean("chickenIsAlive", true)){
        
      }
    }
  }
  
  @Override
  public void onPause()
  {
    super.onPause(); // Always call the superclass method first
    kill();
    
    SharedPreferences sharedPref= this.getSharedPreferences("com.example.chickentime", Context.MODE_PRIVATE);
    sharedPref.edit().putBoolean("chickenIsAlive", isChickenAlive()).apply();
  
  }

  public void kill()
  {
    new Handler().postDelayed(new Runnable()
    {

      @Override
      public void run()
      {
        Toast aToast = Toast.makeText(MainActivity.main,getString(R.string.ChickenKilled) , Toast.LENGTH_SHORT);
        aToast.show();
      }
    }, 2000);

  }

  private boolean isChickenAlive()
  {
    return false;
  }
}
