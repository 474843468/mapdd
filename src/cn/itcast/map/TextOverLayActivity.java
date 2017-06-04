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
		// �������ָ�����
		TextOptions options = new TextOptions();
		// ��������
		// ��������
		options.text("�������ǲ���*Android���ڱ�ҵ��");
		// ���ִ�С
		options.fontSize(36);
		// ���ֵ�λ��
		options.position(position);
		// ������ɫ
		options.fontColor(Color.RED);
		// ��ӵ���ͼ��
		baiduMap.addOverlay(options);
	}

}
