package com.example.chickentime;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.Date;
import java.util.Random;

import android.app.Activity;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.SharedPreferences;
import android.content.res.AssetManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.graphics.drawable.AnimationDrawable;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.chickentime.Chicken.ChickenStatus;

public class MainActivity extends Activity {
	public static MainActivity main;
	Chicken chicken;
	Date date;
	int total_sec;
	int elapsedTime;
	long startMilisec;
	ProgressWheel pw;
	public Handler timerHandler;

	private Bitmap getBitmapFromAsset(String strName) throws IOException {
		AssetManager assetManager = getAssets();

		InputStream istr = assetManager.open(strName);
		Bitmap bitmap = BitmapFactory.decodeStream(istr);
		istr.close();

		return bitmap;
	}

	Runnable timerRunnable = new Runnable() {

		@Override
		public void run() {
			decSec();
			
			elapsedTime++;
			if(elapsedTime%11==0 && elapsedTime>0 && total_sec-elapsedTime>15){
				Random rand = new Random();

				int  n = rand.nextInt(3) + 1;
				int size = 0;
				if(n == 1){
					size = 20;
				}else if(n==2){
					size = 40;
				}else if(n==3){
					size = 30;
				}
				AnimationDrawable anim1 = new AnimationDrawable();
			    
				try {
					for(int i=0; i<=size; i++){

						Bitmap myBitmap = getBitmapFromAsset(String.format("animations/anim_idle%d/%04d.png",n, i));
						anim1.addFrame(new BitmapDrawable(myBitmap), (1000/24));

					}
		
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				ImageView kurczakView = (ImageView) findViewById(R.id.imageView1);
				kurczakView.setImageDrawable(anim1);
				anim1.start();

			}
			
			if (date.getSeconds() != 0 || date.getMinutes() != 0
					|| date.getSeconds() != 0)
				timerHandler.postDelayed(this, 1000);
			else {
				// Co zrobi� jak si� sko�czy czas?
				AnimationDrawable anim1 = new AnimationDrawable();
			    
				try {
					for(int i=0; i<=55; i++){

						Bitmap myBitmap = getBitmapFromAsset(String.format("animations/anim9/%04d.png", i));
						anim1.addFrame(new BitmapDrawable(myBitmap), (1000/24));

					}
					Bitmap myBitmap = getBitmapFromAsset("animations/anim7/0001.png");
					anim1.addFrame(new BitmapDrawable(myBitmap), (1000/24));

				
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				ImageView kurczakView = (ImageView) findViewById(R.id.imageView1);
				kurczakView.setImageDrawable(anim1);
				anim1.start();

				
				chicken.status = ChickenStatus.START;
				setTrianglesVisibility(View.VISIBLE);

				SharedPreferences sharedPref = MainActivity.main
						.getSharedPreferences("com.example.chickentime",
								Context.MODE_PRIVATE);
				sharedPref.edit()
						.putInt("saved", 1 + sharedPref.getInt("saved", 0))
						.apply();
				Log.e("time", Integer.toString(total_sec));
				sharedPref
						.edit()
						.putInt("savedTime",
								total_sec + sharedPref.getInt("savedTime", 0))
						.apply();
			}
		}
	};

	Runnable refreshWheel = new Runnable() {

		@Override
		public void run() {
			pw.setProgress(360 - 360
					* (System.currentTimeMillis() - startMilisec)
					/ (total_sec * 1000f));
			if (total_sec * 1000f > System.currentTimeMillis() - startMilisec)
				timerHandler.postDelayed(this, 80);
			else
				pw.setProgress(360);
		}
	};

	@Override
	protected void onCreate(Bundle savedInstanceState) {
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

		pw = (ProgressWheel) findViewById(R.id.progressBarTwo);

		pw.setProgress(360); // progess in degrees
	}

	@Override
	public void onResume() {
		super.onResume();
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		MenuInflater inflater = getMenuInflater();
		inflater.inflate(R.layout.mainmenu, menu);

		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// Handle presses on the action bar items
		switch (item.getItemId()) {
		case R.id.jump_farm:
			farm(null);
			return true;
		default:
			return super.onOptionsItemSelected(item);
		}
	}

	@Override
	public void onPause() {
		super.onPause();
		if (chicken.status == ChickenStatus.DOSTHG)
			kill();
		ScreenReceiver.toBeOff = false;
	}

	public void farm(View view) {
		Intent intent = new Intent(this, TabActivity.class);
		startActivity(intent);
	}

	public void kill() {
		new Handler().postDelayed(new Runnable() {
			@Override
			public void run() {
				if (ScreenReceiver.toBeOff == false) {
					try {
						timerHandler.removeCallbacks(timerRunnable);
					} catch (Exception e) {
					}
					try {
						timerHandler.removeCallbacks(refreshWheel);
					} catch (Exception e) {
					}

					chicken.status = ChickenStatus.START;
					SharedPreferences sharedPref = MainActivity.main
							.getSharedPreferences("com.example.chickentime",
									Context.MODE_PRIVATE);
					sharedPref
							.edit()
							.putInt("killed",
									1 + sharedPref.getInt("killed", 0)).apply();
					setTrianglesVisibility(View.VISIBLE);
					date.setHours(0);
					date.setMinutes(0);
					date.setSeconds(0);
					((TextView) findViewById(R.id.seconds)).setText("00");
					((TextView) findViewById(R.id.hmin)).setText("00:00");
					pw.setProgress(360);
					ImageView kurczakView = (ImageView) findViewById(R.id.imageView1);
					Bitmap myBitmap;
					try {
						myBitmap = getBitmapFromAsset("animations/anim7/0000.png");
						kurczakView.setImageBitmap(myBitmap);
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
				if (ScreenReceiver.toBeOff == false) {
					LayoutInflater inflater = getLayoutInflater();
					View layout = inflater.inflate(R.layout.toast_view,
							(ViewGroup) findViewById(R.id.toast_layout_root));

//					TextView text = (TextView) layout.findViewById(R.id.text);
//					text.setText("Your chicken has died a miserable death!!");

					ImageView imageView = (ImageView) layout
							.findViewById(R.id.imageview);
					
					AnimationDrawable anim1 = new AnimationDrawable();
					
					Random rand = new Random();
					int  n = rand.nextInt(3) + 1;
					int size = 40;

					
					
					try {
						for(int i=0; i<=size; i++){

							Bitmap myBitmap = getBitmapFromAsset(String.format("animations/anim_dead%d/%04d.png",n, i));
							anim1.addFrame(new BitmapDrawable(myBitmap), (1000/24));

						}
					
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					imageView.setImageDrawable(anim1);
					anim1.start();

					
					
					Toast toast = new Toast(getApplicationContext());
					toast.setGravity(Gravity.CENTER_VERTICAL, 0, 0);
					toast.setDuration(Toast.LENGTH_LONG);
					toast.setView(layout);
					toast.show();

					Toast aToast = Toast.makeText(MainActivity.main,
							getString(R.string.ChickenKilled),
							Toast.LENGTH_SHORT);
					aToast.show();
				}
			}
		}, 1000);

	}

	public void setTrianglesVisibility(int visibil) {
		((ImageView) findViewById(R.id.incMin)).setVisibility(visibil);
		((ImageView) findViewById(R.id.incH)).setVisibility(visibil);
		((ImageView) findViewById(R.id.decH)).setVisibility(visibil);
		((ImageView) findViewById(R.id.decMin)).setVisibility(visibil);
	}

	public void spawnChicken(View view) {
		if (chicken.status != Chicken.ChickenStatus.START
				|| (date.getMinutes() == 0 && date.getHours() == 0))
			return;
		total_sec = 60 * (date.getMinutes() + date.getHours() * 60)
				+ date.getSeconds();
		startMilisec = System.currentTimeMillis();
		chicken.status = Chicken.ChickenStatus.DOSTHG;

		setTrianglesVisibility(View.INVISIBLE);

		timerHandler = new Handler();

		timerHandler.postDelayed(refreshWheel, 0);
		timerHandler.postDelayed(timerRunnable, 0);

		elapsedTime = 0;
		AnimationDrawable anim1 = new AnimationDrawable();
	    
		
		try {
			for (int i = 0; i <= 51; i++) {

				Bitmap myBitmap = getBitmapFromAsset(String.format(
						"animations/anim7/%04d.png", i));
				anim1.addFrame(new BitmapDrawable(myBitmap), (1000 / 24));

			}

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		ImageView kurczakView = (ImageView) findViewById(R.id.imageView1);
		kurczakView.setImageDrawable(anim1);
		anim1.start();
	}

	private void decSec() {
		date.setSeconds(date.getSeconds() - 1);
		StringBuffer s = new StringBuffer();
		if (date.getSeconds() < 10)
			s.append("0");
		s.append(date.getSeconds());
		TextView tv = (TextView) findViewById(R.id.seconds);
		tv.setText(s.toString());

		StringBuffer h = new StringBuffer();
		StringBuffer m = new StringBuffer();
		if (date.getHours() < 10)
			h.append("0");
		h.append(date.getHours());
		if (date.getMinutes() < 10)
			m.append("0");
		m.append(date.getMinutes());
		tv = (TextView) findViewById(R.id.hmin);
		tv.setText(h.toString() + ":" + m.toString());
	}

	public void increment(View view) {
		ProgressWheel pw = (ProgressWheel) findViewById(R.id.progressBarTwo);
		pw.incrementProgress();
	}

	public void incMin(View view) {
		TextView tv = (TextView) findViewById(R.id.hmin);
		date.setMinutes(date.getMinutes() + 1);
		StringBuffer h = new StringBuffer();
		StringBuffer m = new StringBuffer();
		if (date.getHours() < 10)
			h.append("0");
		h.append(date.getHours());
		if (date.getMinutes() < 10)
			m.append("0");
		m.append(date.getMinutes());
		tv.setText(h.toString() + ":" + m.toString());
	}

	public void incH(View view) {
		TextView tv = (TextView) findViewById(R.id.hmin);
		date.setHours(date.getHours() + 1);
		StringBuffer h = new StringBuffer();
		StringBuffer m = new StringBuffer();
		if (date.getHours() < 10)
			h.append("0");
		h.append(date.getHours());
		if (date.getMinutes() < 10)
			m.append("0");
		m.append(date.getMinutes());
		tv.setText(h.toString() + ":" + m.toString());
	}

	public void decH(View view) {
		TextView tv = (TextView) findViewById(R.id.hmin);
		date.setHours(date.getHours() - 1);
		StringBuffer h = new StringBuffer();
		StringBuffer m = new StringBuffer();
		if (date.getHours() < 10)
			h.append("0");
		h.append(date.getHours());
		if (date.getMinutes() < 10)
			m.append("0");
		m.append(date.getMinutes());
		tv.setText(h.toString() + ":" + m.toString());

	}

	public void decMin(View view) {
		TextView tv = (TextView) findViewById(R.id.hmin);
		date.setMinutes(date.getMinutes() - 5);
		StringBuffer h = new StringBuffer();
		StringBuffer m = new StringBuffer();
		if (date.getHours() < 10)
			h.append("0");
		h.append(date.getHours());
		if (date.getMinutes() < 10)
			m.append("0");
		m.append(date.getMinutes());
		tv.setText(h.toString() + ":" + m.toString());
	}

}
