package cn.itcast.map;

import android.os.Bundle;

import com.baidu.location.BDLocation;
import com.baidu.location.BDLocationListener;
import com.baidu.location.LocationClient;
import com.baidu.location.LocationClientOption;
import com.baidu.location.LocationClientOption.LocationMode;
import com.baidu.mapapi.map.MyLocationData;

public class LocationActivity extends BaseActivity {

	private LocationClient client;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		initLocation();
	}

	@Override
	protected void onStart() {
		// 发起定位请求
		client.start();
		super.onStart();
	}

	@Override
	protected void onDestroy() {
		client.stop();
		super.onDestroy();
	}

	private void initLocation() {
		client = new LocationClient(getApplicationContext());

		// 设置定位请求的参数
		LocationClientOption option = new LocationClientOption();
		option.setLocationMode(LocationMode.Hight_Accuracy);// 设置定位模式
		option.setCoorType("bd09ll");// 返回的定位结果是百度经纬度,默认值gcj02
		option.setScanSpan(5000);// 设置发起定位请求的间隔时间为5000ms
		option.setIsNeedAddress(true);// 返回的定位结果包含地址信息
		option.setNeedDeviceDirect(true);// 返回的定位结果包含手机机头的方向
		client.setLocOption(option);

		// 开启定位
		baiduMap.setMyLocationEnabled(true);

		//接受定位结果
		client.registerLocationListener(new BDLocationListener() {

			@Override
			public void onReceiveLocation(BDLocation location) {
				baiduMap.setMyLocationData(new MyLocationData.Builder()
						.latitude(location.getLatitude())
						.longitude(location.getLongitude()).build());

				System.out.println("经度:" + location.getLatitude());
				System.out.println("纬度:" + location.getLongitude());

			}

		});

	}

}
