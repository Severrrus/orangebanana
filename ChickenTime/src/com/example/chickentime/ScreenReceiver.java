package com.example.chickentime;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;

public class ScreenReceiver extends BroadcastReceiver
{

  public static boolean toBeOff = false;

  @Override
  public void onReceive(Context context, Intent intent)
  {
    if (intent.getAction().equals(Intent.ACTION_SCREEN_OFF))
    {
      Log.e("jest", "jest");
      toBeOff = true;
    }
  }

}
