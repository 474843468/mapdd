package cn.itcast.map;

import com.baidu.mapapi.map.CircleOptions;

import android.graphics.Color;
import android.os.Bundle;

public class CircleOverLayActivity extends BaseActivity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		initOverLay();
	}

	private void initOverLay() {
		// 1.创建覆盖物
		CircleOptions options = new CircleOptions();
		// 2.设置覆盖物属性
		// 圆心
		options.center(position);
		// 半径 单位:米
		options.radius(500);
		// 颜色
		options.fillColor(Color.RED);
		// 3.添加覆盖物到地图上
		baiduMap.addOverlay(options);
	}

}
