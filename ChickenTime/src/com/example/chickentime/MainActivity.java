package com.example.chickentime;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

public class MainActivity extends Activity
{

  @Override
  protected void onCreate(Bundle savedInstanceState)
  {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_main);
   
  }
  
  public void kill(View view){
    Toast aToast = Toast.makeText(this, "Hello World", Toast.LENGTH_SHORT);
  }
}
