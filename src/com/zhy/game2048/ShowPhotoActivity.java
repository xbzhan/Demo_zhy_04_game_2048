package com.zhy.game2048;

import android.app.Activity;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.View.OnTouchListener;
import android.view.animation.AnimationUtils;
import android.widget.ImageSwitcher;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Toast;
import android.widget.RelativeLayout.LayoutParams;
import android.widget.ViewSwitcher.ViewFactory;

public class ShowPhotoActivity extends Activity implements ViewFactory, OnTouchListener{
	/**
	 * ImagaSwitcher ������
	 */
	private ImageSwitcher mImageSwitcher;
	/**
	 * ͼƬid����
	 */
	private int[] imgIds;
	/**
	 * ��ǰѡ�е�ͼƬid���
	 */
	private int currentPosition;
	/**
	 * ���µ��X����
	 */
	private float downX;
	/**
	 * װ�ص�������
	 */
	private LinearLayout linearLayout;
	/**
	 * �������
	 */
	private ImageView[] tips;
 
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.show_photo);
		
		imgIds = new int[]{R.drawable.first,
				R.drawable.one1,R.drawable.one2,
				R.drawable.two1,R.drawable.two2,
				R.drawable.three1, R.drawable.three2,
				R.drawable.four1,R.drawable.four2,
				R.drawable.four3,R.drawable.five1,
				R.drawable.five2
				};
		//ʵ����ImageSwitcher
		mImageSwitcher  = (ImageSwitcher) findViewById(R.id.imageSwitcher1);
		//����Factory
		mImageSwitcher.setFactory(this);
		//����OnTouchListener������ͨ��Touch�¼����л�ͼƬ
		mImageSwitcher.setOnTouchListener(this);
		
		linearLayout = (LinearLayout) findViewById(R.id.viewGroup);
		
		tips = new ImageView[imgIds.length];
		for(int i=0; i<imgIds.length; i++){
			ImageView mImageView = new ImageView(this);
			tips[i] = mImageView;
			LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(new ViewGroup.LayoutParams(LayoutParams.WRAP_CONTENT,    
                    LayoutParams.WRAP_CONTENT));  
			layoutParams.rightMargin = 3;
			layoutParams.leftMargin = 3;
			
			mImageView.setBackgroundResource(R.drawable.page_indicator_unfocused);
			linearLayout.addView(mImageView, layoutParams);
		}
		
		//������Ǵ���һ�����洫�����ģ���һ��������һ��GridView
		currentPosition = getIntent().getIntExtra("position", 0);
		mImageSwitcher.setImageResource(imgIds[currentPosition]);
		
		setImageBackground(currentPosition);
		
	}
	
	 /** 
     * ����ѡ�е�tip�ı��� 
     * @param selectItems 
     */  
    private void setImageBackground(int selectItems){  
        for(int i=0; i<tips.length; i++){  
            if(i == selectItems){  
            	tips[i].setBackgroundResource(R.drawable.page_indicator_focused);  
            }else{  
            	tips[i].setBackgroundResource(R.drawable.page_indicator_unfocused);  
            }  
        }  
    } 

	public View makeView() {
		final ImageView i = new ImageView(this);
		i.setBackgroundColor(0xff000000);
		i.setScaleType(ImageView.ScaleType.CENTER_CROP);
		i.setLayoutParams(new ImageSwitcher.LayoutParams(LayoutParams.FILL_PARENT, LayoutParams.FILL_PARENT));
		return i ;
	}

	public boolean onTouch(View v, MotionEvent event) {
		switch (event.getAction()) {
		case MotionEvent.ACTION_DOWN:{
			//��ָ���µ�X����
			downX = event.getX();
			break;
		}
		case MotionEvent.ACTION_UP:{
			float lastX = event.getX();
			//̧���ʱ���X������ڰ��µ�ʱ�����ʾ��һ��ͼƬ
			if(lastX > downX){
				if(currentPosition > 0){
					//���ö���������Ķ����Ƚϼ򵥣������׵�ȥ���Ͽ����������
					mImageSwitcher.setInAnimation(AnimationUtils.loadAnimation(getApplication(), R.anim.left_in));
					mImageSwitcher.setOutAnimation(AnimationUtils.loadAnimation(getApplication(), R.anim.right_out));
					currentPosition --;
					mImageSwitcher.setImageResource(imgIds[currentPosition % imgIds.length]);
					setImageBackground(currentPosition);
				}else{
					Toast.makeText(getApplication(), "�Ѿ��ǵ�һ��", Toast.LENGTH_SHORT).show();
				}
			} 
			
			if(lastX < downX){
				if(currentPosition < imgIds.length - 1){
					mImageSwitcher.setInAnimation(AnimationUtils.loadAnimation(getApplication(), R.anim.right_in));
					mImageSwitcher.setOutAnimation(AnimationUtils.loadAnimation(getApplication(), R.anim.left_out));
					currentPosition ++ ;
					mImageSwitcher.setImageResource(imgIds[currentPosition]);
					setImageBackground(currentPosition);
				}else{
					Toast.makeText(getApplication(), "�������һ��", Toast.LENGTH_SHORT).show();
				}
			}
			}
			
			break;
		}

		return true;
	}

}