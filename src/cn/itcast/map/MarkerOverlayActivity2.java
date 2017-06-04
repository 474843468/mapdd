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

	// 初始化PopView
	private void initPopView() {
		popView = View.inflate(MarkerOverlayActivity2.this, R.layout.popview,
				null);
		tv = (TextView) popView.findViewById(R.id.tv_pop);
		popView.setVisibility(View.INVISIBLE);
	}

	// 移动PopView
	public void moveView(Marker marker) {

		mapView.removeView(popView);

		popView.setVisibility(View.VISIBLE);
		tv.setText(marker.getTitle());
		// 创建LayoutParams参数描述对象显示的位置
		MapViewLayoutParams params = new MapViewLayoutParams.Builder()
				.position(marker.getPosition()).layoutMode(ELayoutMode.mapMode).build();
		mapView.addView(popView, params);
	}

	//创建覆盖物
	private void initOverlay() {
		// 创建图片覆盖物对象
		MarkerOptions options = new MarkerOptions();
		// 设置图片资源
		ArrayList<BitmapDescriptor> arrayList = new ArrayList<BitmapDescriptor>();
		BitmapDescriptor bitMap1 = BitmapDescriptorFactory
				.fromResource(R.drawable.eat_icon);
		BitmapDescriptor bitMap2 = BitmapDescriptorFactory
				.fromResource(R.drawable.icon_start);
		arrayList.add(bitMap1);
		arrayList.add(bitMap2);
		options.icons(arrayList);
		// 设置图片位置
		options.position(position);
		// 设置图片切换频率
		options.period(10);
		// 设置Marke标题
		options.title("传智播客");
		// 添加到地图中
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
