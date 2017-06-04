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
		// 创建POISeach
		PoiSearch poiSearch = PoiSearch.newInstance();
		// 设置搜索条件
		PoiBoundSearchOption options = new PoiBoundSearchOption();
		// 指定搜索范围
		LatLngBounds bounds = new LatLngBounds.Builder()
				.include(new LatLng(34.196421 + 1, 108.868445 + 1))
				.include(new LatLng(34.196421 - 1, 108.868445 - 1)).build();
		options.bound(bounds);
		// 指定搜索关键字    
		options.keyword("酒店");
		
		options.pageCapacity(20);
		
		// 发起搜索
		poiSearch.searchInBound(options);

		//解析搜索结果
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
