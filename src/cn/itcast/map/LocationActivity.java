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
		// ����λ����
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

		// ���ö�λ����Ĳ���
		LocationClientOption option = new LocationClientOption();
		option.setLocationMode(LocationMode.Hight_Accuracy);// ���ö�λģʽ
		option.setCoorType("bd09ll");// ���صĶ�λ����ǰٶȾ�γ��,Ĭ��ֵgcj02
		option.setScanSpan(5000);// ���÷���λ����ļ��ʱ��Ϊ5000ms
		option.setIsNeedAddress(true);// ���صĶ�λ���������ַ��Ϣ
		option.setNeedDeviceDirect(true);// ���صĶ�λ��������ֻ���ͷ�ķ���
		client.setLocOption(option);

		// ������λ
		baiduMap.setMyLocationEnabled(true);

		//���ܶ�λ���
		client.registerLocationListener(new BDLocationListener() {

			@Override
			public void onReceiveLocation(BDLocation location) {
				baiduMap.setMyLocationData(new MyLocationData.Builder()
						.latitude(location.getLatitude())
						.longitude(location.getLongitude()).build());

				System.out.println("����:" + location.getLatitude());
				System.out.println("γ��:" + location.getLongitude());

			}

		});

	}

}
