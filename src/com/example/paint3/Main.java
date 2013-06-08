package com.example.paint3;

import com.example.paint3.Fragments.CanvasFragment;
import com.example.paint3.Fragments.SettingsFragment;
import com.example.paint3.Fragments.ToolsFragment;

import android.annotation.TargetApi;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.graphics.Point;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.util.Log;
import android.view.Display;
import android.view.Window;
import android.view.WindowManager;

public class Main extends FragmentActivity {
	private static int height, width;
	private static FragmentTransaction fragmentTransaction;
	private static FragmentManager fragmentManager;
	private static CanvasFragment canvas;
	private static ToolsFragment tools;
	private static SettingsFragment settings;

	@TargetApi(Build.VERSION_CODES.HONEYCOMB_MR2)
	@Override
	public void onCreate(Bundle b) {
		super.onCreate(b);

		//Remove title bar
		this.requestWindowFeature(Window.FEATURE_NO_TITLE);
		//Remove notification bar
		this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);

		Display display = getWindowManager().getDefaultDisplay();
		Point size = new Point();
		display.getSize(size);
		width = size.x;
		height = size.y;

		setContentView(R.layout.activity_main); 

		fragmentManager = getFragmentManager();
		fragmentTransaction = fragmentManager.beginTransaction();

		canvas = new CanvasFragment();
		settings = new SettingsFragment();
		tools = new ToolsFragment();
		
		fragmentTransaction.add(R.id.frame, canvas);
		fragmentTransaction.commit();
		
		//TODO: remove this
		fragmentTransaction = fragmentManager.beginTransaction();
		fragmentTransaction.add(R.id.frame, tools);
		fragmentTransaction.setCustomAnimations(R.anim.slide_in_left, R.anim.slide_out_right);		 
		fragmentTransaction.addToBackStack(null);
		fragmentTransaction.commit();
		//END
	}
	
	@TargetApi(Build.VERSION_CODES.HONEYCOMB)
	public static void goToSettings () {
		Log.i("Main_paint3", "go to settings");
		fragmentTransaction = fragmentManager.beginTransaction();
		fragmentTransaction.setCustomAnimations(R.anim.slide_in_left, R.anim.slide_out_right);		 
		fragmentTransaction.replace(R.id.frame, settings);
		fragmentTransaction.addToBackStack(null);
		fragmentTransaction.commit();
	}

	@TargetApi(Build.VERSION_CODES.HONEYCOMB)
	public static void backToCanvas() {
		Log.i("Main_paint3", "back to canvas");
		fragmentTransaction = fragmentManager.beginTransaction();
		fragmentTransaction.setCustomAnimations(R.anim.slide_in_right, R.anim.slide_out_left);	
		fragmentTransaction.replace(R.id.frame, canvas);
		fragmentTransaction.addToBackStack(null);
		fragmentTransaction.commit();
	}

	@TargetApi(Build.VERSION_CODES.HONEYCOMB)
	public static void showTools() {
		Log.i("Main_paint3", "show tools");
		fragmentTransaction = fragmentManager.beginTransaction();
		fragmentTransaction.setCustomAnimations(R.anim.slide_down_bottom, R.anim.slide_up_bottom);
		fragmentTransaction.replace(R.id.frame, tools);
		fragmentTransaction.addToBackStack(null);
		fragmentTransaction.commit();
	}

	@TargetApi(Build.VERSION_CODES.HONEYCOMB)
	public static void hideTools() {
		Log.i("Main_paint3", "hide tools");
		fragmentTransaction = fragmentManager.beginTransaction();
		fragmentTransaction.setCustomAnimations(R.anim.slide_down_bottom, R.anim.slide_up_bottom);
		fragmentTransaction.remove(tools);
		fragmentTransaction.addToBackStack(null);
		fragmentTransaction.commit();
	}

	public static int getHeight() {
		return height;
	}

	public static int getWidth () {
		return width;
	}
}
