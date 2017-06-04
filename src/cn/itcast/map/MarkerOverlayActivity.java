package cn.itcast.map;

import com.baidu.mapapi.map.BitmapDescriptor;
import com.baidu.mapapi.map.BitmapDescriptorFactory;
import com.baidu.mapapi.map.MarkerOptions;

import android.os.Bundle;

public class MarkerOverlayActivity extends BaseActivity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		initOverlay();
	}

	private void initOverlay() {
		// ����ͼƬ���������
		MarkerOptions options = new MarkerOptions();
		// ��������
		// ͼƬ��Դ
		BitmapDescriptor bitMapDescriptor = BitmapDescriptorFactory
				.fromResource(R.drawable.eat_icon);
		options.icon(bitMapDescriptor);
		// λ����Ϣ
		options.position(position);
		// ��ӵ���ͼ��
		baiduMap.addOverlay(options);
	}

}
