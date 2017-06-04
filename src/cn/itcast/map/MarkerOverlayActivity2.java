package cn.itcast.map;

import java.util.ArrayList;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.baidu.mapapi.map.BaiduMap.OnMapClickListener;
import com.baidu.mapapi.map.BaiduMap.OnMarkerClickListener;
import com.baidu.mapapi.map.BitmapDescriptor;
import com.baidu.mapapi.map.BitmapDescriptorFactory;
import com.baidu.mapapi.map.MapPoi;
import com.baidu.mapapi.map.MapViewLayoutParams;
import com.baidu.mapapi.map.MapViewLayoutParams.ELayoutMode;
import com.baidu.mapapi.map.Marker;
import com.baidu.mapapi.map.MarkerOptions;
import com.baidu.mapapi.model.LatLng;

public class MarkerOverlayActivity2 extends BaseActivity {

	private View popView;
	private TextView tv;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		initOverlay();
		initPopView();

		baiduMap.setOnMapClickListener(new OnMapClickListener() {

			@Override
			public boolean onMapPoiClick(MapPoi mapPoi) {
				return false;
			}

			@Override
			public void onMapClick(LatLng ll) {
				// mapView.removeView(popView);
				popView.setVisibility(View.INVISIBLE);
			}
		});

	}

	// ��ʼ��PopView
	private void initPopView() {
		popView = View.inflate(MarkerOverlayActivity2.this, R.layout.popview,
				null);
		tv = (TextView) popView.findViewById(R.id.tv_pop);
		popView.setVisibility(View.INVISIBLE);
	}

	// �ƶ�PopView
	public void moveView(Marker marker) {

		mapView.removeView(popView);

		popView.setVisibility(View.VISIBLE);
		tv.setText(marker.getTitle());
		// ����LayoutParams��������������ʾ��λ��
		MapViewLayoutParams params = new MapViewLayoutParams.Builder()
				.position(marker.getPosition()).layoutMode(ELayoutMode.mapMode).build();
		mapView.addView(popView, params);
	}

	//����������
	private void initOverlay() {
		// ����ͼƬ���������
		MarkerOptions options = new MarkerOptions();
		// ����ͼƬ��Դ
		ArrayList<BitmapDescriptor> arrayList = new ArrayList<BitmapDescriptor>();
		BitmapDescriptor bitMap1 = BitmapDescriptorFactory
				.fromResource(R.drawable.eat_icon);
		BitmapDescriptor bitMap2 = BitmapDescriptorFactory
				.fromResource(R.drawable.icon_start);
		arrayList.add(bitMap1);
		arrayList.add(bitMap2);
		options.icons(arrayList);
		// ����ͼƬλ��
		options.position(position);
		// ����ͼƬ�л�Ƶ��
		options.period(10);
		// ����Marke����
		options.title("���ǲ���");
		// ��ӵ���ͼ��
		baiduMap.addOverlay(options);

		baiduMap.setOnMarkerClickListener(new OnMarkerClickListener() {

			@Override
			public boolean onMarkerClick(Marker marker) {
				moveView(marker);
				return false;
			}
		});

	}
}
