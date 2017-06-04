package cn.itcast.map;

import com.baidu.mapapi.overlayutil.PoiOverlay;
import com.baidu.mapapi.search.poi.OnGetPoiSearchResultListener;
import com.baidu.mapapi.search.poi.PoiDetailResult;
import com.baidu.mapapi.search.poi.PoiNearbySearchOption;
import com.baidu.mapapi.search.poi.PoiResult;
import com.baidu.mapapi.search.poi.PoiSearch;

import android.os.Bundle;

public class SearchNearByActivity extends BaseActivity{

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		initSearch();
	}

	private void initSearch() {
		//创建PoiSearch对象
		PoiSearch poiSearch = PoiSearch.newInstance();
		//设置搜索条件
		PoiNearbySearchOption option = new PoiNearbySearchOption();
		//指定圆心坐标
		option.location(position);
		//指定半径 	单位:米
		option.radius(1000);
		//指定搜索关键字
		option.keyword("酒店");
		//发起搜索
		poiSearch.searchNearby(option);
		
		//解析搜索结果
		poiSearch.setOnGetPoiSearchResultListener(new OnGetPoiSearchResultListener() {
			
			@Override
			public void onGetPoiResult(PoiResult result) {
				//创建PoiOverLay
				PoiOverlay overlay = new PoiOverlay(baiduMap);
				//设置数据
				overlay.setData(result);
				//添加到地图上
				overlay.addToMap();
				//缩放地图级别,更加有好的显示
				overlay.zoomToSpan();
			}
			
			@Override
			public void onGetPoiDetailResult(PoiDetailResult arg0) {
				
			}
		});
	}
	
}
