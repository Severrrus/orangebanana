package com.example.chickentime;

import com.example.chickentime.Chicken.ChickenStatus;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Toast;

public class MainActivity extends Activity
{
  static MainActivity main;
  Chicken chicken;
  
  @Override
  protected void onCreate(Bundle savedInstanceState)
  {
    super.onCreate(savedInstanceState);
    main = this;
    chicken = new Chicken();
    setContentView(R.layout.activity_main);
    onResume();
  }

  @Override
  public void onResume(){
    super.onResume();
    
    SharedPreferences sharedPref= this.getSharedPreferences("com.example.chickentime", Context.MODE_PRIVATE);
    if (sharedPref.contains("chickenIsAlive")){
      if(!sharedPref.getBoolean("chickenIsAlive", true)){
        sharedPref.edit().putBoolean("chickenIsAlive", true).apply();
        Intent intent = new Intent(this, KillChickenActivity.class);
        //startActivity(intent);
      }
    }
  }
  
  @Override
  public void onPause()
  {
    super.onPause(); // Always call the superclass method first
    if (isChickenAlive())
      kill();
    
    SharedPreferences sharedPref= this.getSharedPreferences("com.example.chickentime", Context.MODE_PRIVATE);
    sharedPref.edit().putBoolean("chickenIsAlive", isChickenAlive()).apply();
  
  }
  
  public void farm(){
    Intent intent = new Intent(this, ListViewChickens.class);
    startActivity(intent);
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

  public void spawnChicken(View view){
    chicken.status = Chicken.ChickenStatus.DOSTHG;
    findViewById(R.id.button1).setVisibility(View.INVISIBLE);
    findViewById(R.id.button2).setVisibility(View.VISIBLE);
  }
  
  public void restartChicken(View view){
    chicken.status = Chicken.ChickenStatus.START;
    findViewById(R.id.button2).setVisibility(View.INVISIBLE);
    findViewById(R.id.button1).setVisibility(View.VISIBLE);
  }
  
  private boolean isChickenAlive()
  {
    if (chicken.status == ChickenStatus.START)
      return false;
    return true;
  }
}
