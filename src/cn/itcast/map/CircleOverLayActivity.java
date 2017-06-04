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
		// 1.����������
		CircleOptions options = new CircleOptions();
		// 2.���ø���������
		// Բ��
		options.center(position);
		// �뾶 ��λ:��
		options.radius(500);
		// ��ɫ
		options.fillColor(Color.RED);
		// 3.��Ӹ����ﵽ��ͼ��
		baiduMap.addOverlay(options);
	}

}
