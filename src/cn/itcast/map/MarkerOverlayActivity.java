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
		// 创建图片覆盖物对象
		MarkerOptions options = new MarkerOptions();
		// 设置属性
		// 图片资源
		BitmapDescriptor bitMapDescriptor = BitmapDescriptorFactory
				.fromResource(R.drawable.eat_icon);
		options.icon(bitMapDescriptor);
		// 位置信息
		options.position(position);
		// 添加到地图上
		baiduMap.addOverlay(options);
	}

}
