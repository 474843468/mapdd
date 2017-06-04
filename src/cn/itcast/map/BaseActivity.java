package cn.itcast.map;

import android.app.Activity;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.widget.Toast;

import com.baidu.mapapi.SDKInitializer;
import com.baidu.mapapi.map.BaiduMap;
import com.baidu.mapapi.map.MapStatusUpdate;
import com.baidu.mapapi.map.MapStatusUpdateFactory;
import com.baidu.mapapi.map.MapView;
import com.baidu.mapapi.model.LatLng;

public class BaseActivity extends Activity {

	private MyReceiver receiver;
	public MapView mapView;
	public BaiduMap baiduMap;
	public LatLng position;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		initSDK();
		setContentView(R.layout.hello_map);
		mapView = (MapView) findViewById(R.id.mapView);
		baiduMap = mapView.getMap();
		initMap();
	}

	// 更新地图的显示状态
	private void initMap() {
		position = new LatLng(34.196421, 108.868445);
		MapStatusUpdate mapStatusUpdate = MapStatusUpdateFactory
				.newLatLng(position);
		baiduMap.animateMapStatus(mapStatusUpdate);

		// 更改地图的缩放级别	3-19
		MapStatusUpdate mapStatusUpdate2 = MapStatusUpdateFactory.zoomTo(14);
		baiduMap.animateMapStatus(mapStatusUpdate2);
	}

	// 初始化SDK
	private void initSDK() {
		receiver = new MyReceiver();
		IntentFilter filter = new IntentFilter();
		filter.addAction(SDKInitializer.SDK_BROADCAST_ACTION_STRING_NETWORK_ERROR);// 网络异常
		filter.addAction(SDKInitializer.SDK_BROADTCAST_ACTION_STRING_PERMISSION_CHECK_ERROR);// 权限异常
		registerReceiver(receiver, filter);
		// 创建SDK...对象
		SDKInitializer.initialize(getApplicationContext());
	}

	@Override
	protected void onDestroy() {
		unregisterReceiver(receiver);
		super.onDestroy();
	}

	class MyReceiver extends BroadcastReceiver {

		@Override
		public void onReceive(Context context, Intent intent) {
			// 网络异常
			if (SDKInitializer.SDK_BROADCAST_ACTION_STRING_NETWORK_ERROR
					.equals(intent.getAction())) {
				showToast("网络异常");
			}

			// 权限异常
			if (SDKInitializer.SDK_BROADTCAST_ACTION_STRING_PERMISSION_CHECK_ERROR
					.equals(intent.getAction())) {
				showToast("权限异常");
			}
		}

	}

	public void showToast(String content) {
		Toast.makeText(this, content, Toast.LENGTH_SHORT).show();
	}

}
