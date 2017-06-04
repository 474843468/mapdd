package cn.itcast.map;

import com.baidu.mapapi.map.TextOptions;

import android.graphics.Color;
import android.os.Bundle;

public class TextOverLayActivity extends BaseActivity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		initOverlay();
	}

	private void initOverlay() {
		// 创建文字覆盖物
		TextOptions options = new TextOptions();
		// 设置属性
		// 文字内容
		options.text("西安传智播客*Android四期毕业班");
		// 文字大小
		options.fontSize(36);
		// 文字的位置
		options.position(position);
		// 文字颜色
		options.fontColor(Color.RED);
		// 添加到地图上
		baiduMap.addOverlay(options);
	}

}
