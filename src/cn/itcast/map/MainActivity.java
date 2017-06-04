package cn.itcast.map;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.TextView;

public class MainActivity extends Activity {

	private String[] names = { "HelloWorld", "ͼ��", "Բ�θ�����", "���ָ�����", "ͼƬ������",
			"ͼƬ������2", "��������", "��������", "��������", "·���滮-�ݳ�", "��λ" };
	private Class[] classes = { HelloActivity.class, LayerActvity.class,
			CircleOverLayActivity.class, TextOverLayActivity.class,
			MarkerOverlayActivity.class, MarkerOverlayActivity2.class,
			SearchInBoundActivity.class, SearchNearByActivity.class,
			SearchInCityAcitvity.class, DrivingRoutePlanActivity.class,
			LocationActivity.class };

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		ListView lv = (ListView) findViewById(R.id.lv);
		MyAdapter adapter = new MyAdapter();
		lv.setAdapter(adapter);

		lv.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> parent, View view,
					int position, long id) {
				Intent intent = new Intent(MainActivity.this, classes[position]);
				startActivity(intent);
			}
		});

	}

	class MyAdapter extends BaseAdapter {

		@Override
		public int getCount() {
			return names.length;
		}

		@Override
		public String getItem(int position) {
			return names[position];
		}

		@Override
		public long getItemId(int position) {
			return position;
		}

		@Override
		public View getView(int position, View convertView, ViewGroup parent) {

			View view = View.inflate(MainActivity.this, R.layout.item, null);
			TextView tv = (TextView) view.findViewById(R.id.tv);
			tv.setText(getItem(position));
			return view;
		}

	}

}
