package com.example.horseracelamp;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Paint.Style;
import android.util.AttributeSet;
import android.view.View;

public class HorseRaceLamp extends View{

	private int lampNum;
	private int lightLampNum;//亮起了的灯的数目.
	private int lampWidth;
	private int lampHeight;
	private int lampPadding;
	private int canvasWidth;
	private int canvasHeight;
	private int rowPadding;
	private float topY,bottomY;
	private float topY1,bottomY1;
	private int lightColor;
	private int darkColor;
	
	private Paint mPaint;
	
	public HorseRaceLamp(Context context) {
		this(context,null);
		// TODO Auto-generated constructor stub
	}
	
	public HorseRaceLamp(Context context, AttributeSet attrs) {
		this(context,attrs,0);
	}
	
	public HorseRaceLamp(Context context, AttributeSet attrs, int defStyleAttr) {
		super(context, attrs, defStyleAttr);
		parseAttr(context,attrs,defStyleAttr);
		init();
		// TODO Auto-generated constructor stub
	}
	
	private void parseAttr(Context context, AttributeSet attr, int defStyle)
	{
		if (attr != null)
		{
			TypedArray a = context.obtainStyledAttributes(attr,
					R.styleable.horse_race_lamp);
			lampNum = a.getInt(R.styleable.horse_race_lamp_lampNum,10);
			lampWidth = a.getDimensionPixelSize(R.styleable.horse_race_lamp_lampWidth, 5);
			lampHeight = a.getDimensionPixelSize(R.styleable.horse_race_lamp_lampHeight, 10);
			lampPadding = a.getDimensionPixelSize(R.styleable.horse_race_lamp_lampPadding, 5);
			lightLampNum = a.getInt(R.styleable.horse_race_lamp_lightLampNum, 5);
			rowPadding = a.getDimensionPixelSize(R.styleable.horse_race_lamp_rowPadding, 2);
			lightColor = a.getColor(R.styleable.horse_race_lamp_lightColor, Color.parseColor("#FF2D2D"));
			darkColor = a.getColor(R.styleable.horse_race_lamp_darkColor, Color.parseColor("#232323"));
			a.recycle();
		}
	}

	private void init()
	{
		mPaint = new Paint();	
	}
	
	public void setLightNum(float light)
	{
		int lamplight = (int)(lampNum * light + 0.5);
		if(lamplight == lightLampNum)return;
		lightLampNum = lamplight;
		postInvalidate();
	}
	
	@Override
	protected void onDraw(Canvas canvas) {
		// TODO Auto-generated method stub
		if(canvasWidth ==0 ||canvasHeight ==0){
			canvasWidth = canvas.getWidth();
			canvasHeight = canvas.getHeight();
			
			topY = canvasHeight/2f-lampHeight-rowPadding/2f;
			bottomY = canvasHeight/2f-rowPadding/2f;
			
			topY1 = canvasHeight/2f+rowPadding/2f;
			bottomY1 = canvasHeight/2f+lampHeight+rowPadding/2f;
		}
		
		mPaint.setStyle(Style.FILL);
		mPaint.setColor(Color.RED);
		for(int i=0;i<lightLampNum;i++){
			float left = i*lampPadding+i*lampWidth;
			float right = left+lampWidth;
			canvas.drawRect(left, topY, right, bottomY, mPaint);
			canvas.drawRect(left, topY1, right, bottomY1, mPaint);
		}
		
		mPaint.setColor(Color.GRAY);
		for(int i=lightLampNum;i<lampNum;i++){
			float left = i*lampPadding+i*lampWidth;
			float right = left+lampWidth;
			canvas.drawRect(left, topY, right, bottomY, mPaint);
			canvas.drawRect(left, topY1, right, bottomY1, mPaint);
		}
		
		
		super.onDraw(canvas);
	}
	
	@Override
	protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
		// TODO Auto-generated method stub
		int widthMode = MeasureSpec.getMode(widthMeasureSpec);  
        int heightMode = MeasureSpec.getMode(heightMeasureSpec);  
        int widthSize = MeasureSpec.getSize(widthMeasureSpec);  
        int heightSize = MeasureSpec.getSize(heightMeasureSpec);
        
        if(widthMode == MeasureSpec.AT_MOST){
        	widthSize = lampWidth*lampNum+lampPadding*(lampNum-1);
        }
        if(heightMode == MeasureSpec.AT_MOST){
        	heightSize = lampHeight*2+rowPadding;
        }
        
        
		setMeasuredDimension(widthSize, heightSize);
		//super.onMeasure(widthMeasureSpec, heightMeasureSpec);
	}
}
