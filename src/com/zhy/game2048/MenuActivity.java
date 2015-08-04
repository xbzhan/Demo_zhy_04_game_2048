package com.zhy.game2048;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class MenuActivity extends Activity {
	protected void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);	
   }

	public void OnMainActivity(View v) {
		// TODO Auto-generated method stub
		 Intent intent=new Intent(MenuActivity.this,MainActivity.class);
		 startActivity(intent);
	}
	public void OnPlayWay(View v){
		 Intent intent=new Intent(MenuActivity.this,PictureActivity.class);
		 startActivity(intent);
	}
	public void OnExit(){
		finish();
	}

}
