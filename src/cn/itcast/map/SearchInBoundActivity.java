package cn.itcast.map;

import com.baidu.mapapi.model.LatLng;
import com.baidu.mapapi.model.LatLngBounds;
import com.baidu.mapapi.overlayutil.PoiOverlay;
import com.baidu.mapapi.search.poi.OnGetPoiSearchResultListener;
import com.baidu.mapapi.search.poi.PoiBoundSearchOption;
import com.baidu.mapapi.search.poi.PoiDetailResult;
import com.baidu.mapapi.search.poi.PoiResult;
import com.baidu.mapapi.search.poi.PoiSearch;

import android.os.Bundle;

public class SearchInBoundActivity extends BaseActivity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		initSearch();
	}

	private void initSearch() {
		// ����POISeach
		PoiSearch poiSearch = PoiSearch.newInstance();
		// ������������
		PoiBoundSearchOption options = new PoiBoundSearchOption();
		// ָ��������Χ
		LatLngBounds bounds = new LatLngBounds.Builder()
				.include(new LatLng(34.196421 + 1, 108.868445 + 1))
				.include(new LatLng(34.196421 - 1, 108.868445 - 1)).build();
		options.bound(bounds);
		// ָ�������ؼ���    
		options.keyword("�Ƶ�");
		
		options.pageCapacity(20);
		
		// ��������
		poiSearch.searchInBound(options);

		//�����������
		poiSearch.setOnGetPoiSearchResultListener(new OnGetPoiSearchResultListener() {
			
			@Override
			public void onGetPoiResult(PoiResult result) {
				PoiOverlay overlay = new PoiOverlay(baiduMap);
				overlay.setData(result);
				overlay.addToMap();
				overlay.zoomToSpan();
			}
			
			@Override
			public void onGetPoiDetailResult(PoiDetailResult result) {
				
			}
		});
		
	}

}
