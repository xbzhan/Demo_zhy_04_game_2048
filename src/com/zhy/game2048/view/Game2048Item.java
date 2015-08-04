package com.zhy.game2048.view;
import com.zhy.game2048.R;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Paint.Style;
import android.graphics.Rect;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;

/**
 * 2048鐨勬瘡涓狪tem
 * 
 * @author zhy
 * 
 */
public class Game2048Item extends View
{
	/**
	 * 璇iew涓婄殑鏁板瓧
	 */
	private int mNumber;
	private String mNumberVal;
	private Paint mPaint;
	/**
	 * 缁樺埗鏂囧瓧鐨勫尯鍩�
	 */
	private Rect mBound;
	private static int[] mImgs=new int[]{
		R.drawable.button_1,R.drawable.button_2,R.drawable.button_3,R.drawable.button_4,
		R.drawable.button_5,R.drawable.button_6,R.drawable.button_7,
		R.drawable.button_8
	};
	private static Bitmap[] mBitmaps=null;
	{
		if (mBitmaps == null)
		{
			mBitmaps = new Bitmap[mImgs.length];
			for (int i = 0; i < mImgs.length; i++)
			{
				mBitmaps[i] = BitmapFactory.decodeResource(getResources(),
						mImgs[i]);
			}

		}

	}
	public Game2048Item(Context context, AttributeSet attrs, int defStyle)
	{
		super(context, attrs, defStyle);
		mPaint = new Paint();

	}

	public Game2048Item(Context context)
	{
		this(context, null);
	}

	public Game2048Item(Context context, AttributeSet attrs)
	{
		this(context, attrs, 0);
	}

	public void setNumber(int number)
	{
		mNumber = number;
		mNumberVal = mNumber + "";
		mPaint.setTextSize(30.0f);
		mBound = new Rect();
		mPaint.getTextBounds(mNumberVal, 0, mNumberVal.length(), mBound);
		invalidate();
	}
	
	

	public int getNumber()
	{
		return mNumber;
	}

	@Override
	protected void onDraw(Canvas canvas)
	{
		
		super.onDraw(canvas);
		int index = -1;
		switch (mNumber)
		{
		case 2:
			index = 0;
			break;
		case 4:
			index = 1;
			break;
		case 8:
			index = 2;
			break;
		case 16:
			index = 3;
			break;
		case 32:
			index = 4;
			break;
		case 64:
			index = 5;
			break;
		case 128:
			index = 6;
			break;
		case 256:
			index = 7;
			break;
		case 512:
			index = 8;
			break;
		case 1024:
			index = 9;
			break;
		case 2048:
			index = 10;
			break;
		}
		if (mNumber == 0)
		{
			mPaint.setColor(Color.parseColor("#EEE4DA"));
			mPaint.setStyle(Style.FILL);
			canvas.drawRect(0, 0, getWidth(), getHeight(), mPaint);
		}
		//
		if (mNumber != 0){
			canvas.drawBitmap(mBitmaps[index], null, new Rect(0, 0, getWidth(),
					getHeight()), null);
		            drawText(canvas);}
		
		           

	} 
	private void drawText(Canvas canvas1)
	{
		
		mPaint.setColor(Color.BLACK);
		float x = (getWidth() - mBound.width()) / 2;
		float y = getHeight() / 2 + mBound.height() / 2;
		canvas1.drawText(mNumberVal, x, y, mPaint);
	}
	
		

}
