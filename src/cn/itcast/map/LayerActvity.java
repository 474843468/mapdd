package cn.itcast.map;

import com.baidu.mapapi.map.BaiduMap;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

public class LayerActvity extends BaseActivity {

	private boolean flag = false;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		menu.addSubMenu(0, 1, 1, "��ͨͼ��");
		menu.addSubMenu(0, 2, 2, "����ͼ��");
		menu.addSubMenu(0, 3, 3, "��ͨͼ��");
		return super.onCreateOptionsMenu(menu);
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		switch (item.getItemId()) {
		case 1: // ��ͨͼ��
			baiduMap.setMapType(BaiduMap.MAP_TYPE_NORMAL);
			break;
		case 2: // ����ͼ��
			baiduMap.setMapType(BaiduMap.MAP_TYPE_SATELLITE);
			break;
		case 3: // ��ͨͼ
			baiduMap.setTrafficEnabled(!flag);
			flag = !flag;
			break;

		default:
			break;
		}
		return super.onOptionsItemSelected(item);
	}

}
