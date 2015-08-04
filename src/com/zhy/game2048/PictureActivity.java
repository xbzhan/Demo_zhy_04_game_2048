package com.zhy.game2048;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.AdapterView.OnItemClickListener;

public class PictureActivity extends Activity implements OnItemClickListener{
	private GridView mGridView;
	private int[] imgIds;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_picture);
		
		imgIds = new int[]{R.drawable.first,
				R.drawable.one1,R.drawable.one2,
				R.drawable.two1,R.drawable.two2,
				R.drawable.three1, R.drawable.three2,
				R.drawable.four1,R.drawable.four2,
				R.drawable.four3,R.drawable.five1,
				R.drawable.five2
				};
		
		mGridView = (GridView) findViewById(R.id.gridView1);
		mGridView.setAdapter(new GridAdapter());
		mGridView.setOnItemClickListener(this);
		
	}

	
	
	public class GridAdapter extends BaseAdapter{

		public int getCount() {
			return imgIds.length;
		}

		public Object getItem(int position) {
			return imgIds[position];
		}

		public long getItemId(int position) {
			return position;
		}

		public View getView(int position, View convertView, ViewGroup parent) {
			ViewHolder viewHolder;
			if(convertView == null){
				convertView = LayoutInflater.from(getApplication()).inflate(R.layout.item, null);
				viewHolder = new ViewHolder();
				viewHolder.imageView = (ImageView) convertView.findViewById(R.id.imageView1);
				convertView.setTag(viewHolder);
			}else{
				viewHolder = (ViewHolder) convertView.getTag();
			}
		
			viewHolder.imageView.setPadding(10,10, 10, 10);
			viewHolder.imageView.setImageResource(imgIds[position]);
			
			return convertView;
		}
		
		
		class ViewHolder {
			public ImageView imageView;
		}
		
	}



	public void onItemClick(AdapterView<?> parent, View view, int position,
			long id) {
		Intent intent = new Intent();
		intent.putExtra("position", position);
		intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
		intent.setClass(getApplication(), ShowPhotoActivity.class);
		startActivity(intent);
	}

}
