package com.example.paint3.Views;

import com.example.paint3.Main;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.view.MotionEvent;
import android.view.View;


public class PictureView extends View {
	private Canvas c;
	private Bitmap b;
	private int [] colors;
	private int width, height, originX = 0, originY = 0;
	private static final int SWIPE_DISTANCE_RIGHT = 100, SWIPE_DISTANCE_UP = 50; //pixels
	private boolean inDoubleRight, inDoubleUp;
	private static final double PERCENTAGE_OF_SCREEN_FOR_SWIPE = 20; 

	public PictureView (Context context)
	{
		super(context);
		width = Main.getWidth();
		height = Main.getHeight();

		b = Bitmap.createBitmap(width,height,Bitmap.Config.ARGB_8888);//each pixel stored on 4 bytes
		c = new Canvas(b);
		colors = new int [width*height];

		for (int i = 0;i<(width*height);i++) {
			colors[i] = Color.BLACK;
		}
		c.drawBitmap(colors, 0, width, 0, 0, width, height, false, new Paint());
	}


	@Override
	public boolean onTouchEvent (MotionEvent event) {
		int leftPartOfScreen = (int) (((PERCENTAGE_OF_SCREEN_FOR_SWIPE)/100) * Main.getWidth());
		int bottomPartOfScreen = (int)  ((1.0 - (PERCENTAGE_OF_SCREEN_FOR_SWIPE)/100) * Main.getHeight());

		if(event.getActionMasked() == MotionEvent.ACTION_POINTER_DOWN){

			if(!inDoubleUp && event.getPointerCount() == 2  && event.getX() <= leftPartOfScreen){
				handleSwipeRight(event);
			}

			else if (!inDoubleRight && event.getPointerCount() == 2 && event.getY() >= bottomPartOfScreen){
				System.out.println("inDouble");
				handleSwipeUp(event);
			}	
		}				
		else if (event.getActionMasked() == MotionEvent.ACTION_UP && event.getPointerCount() == 1 && (inDoubleRight == true || inDoubleUp == true)){
			handleUp(event);
		}
		return true;
	}

	private void handleSwipeUp (MotionEvent event) {
		inDoubleUp = true;
		originY = (int) event.getY(1);
	}

	private void handleUp (MotionEvent event) {
		if (inDoubleRight && event.getX() - originX >= SWIPE_DISTANCE_RIGHT) {
			originX = 0;
			Main.goToSettings();
		}				
		else if (inDoubleUp && originY - event.getY() >= SWIPE_DISTANCE_UP) {
			originY = 0;
			Main.showTools();
		}
		inDoubleRight = false;
		inDoubleUp = false;
	}

	private void handleSwipeRight (MotionEvent event){		
		inDoubleRight = true;
		originX = (int) event.getX();								
	}	

	@Override
	protected void onDraw(Canvas canvas){
		System.out.println("onDraw");
		Paint paint = new Paint ();
		paint.setFilterBitmap(true);

		// The image will be scaled so it will fill the width, and the
		// height will preserve the image’s aspect ration	
		Rect dest = new Rect(0, 0, width,height);
		canvas.drawBitmap(b, null, dest, paint);
	}	
}
