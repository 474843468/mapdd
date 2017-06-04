package cn.itcast.map;

import com.baidu.mapapi.overlayutil.PoiOverlay;
import com.baidu.mapapi.search.core.PoiInfo;
import com.baidu.mapapi.search.poi.OnGetPoiSearchResultListener;
import com.baidu.mapapi.search.poi.PoiCitySearchOption;
import com.baidu.mapapi.search.poi.PoiDetailResult;
import com.baidu.mapapi.search.poi.PoiDetailSearchOption;
import com.baidu.mapapi.search.poi.PoiResult;
import com.baidu.mapapi.search.poi.PoiSearch;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

public class SearchInCityAcitvity extends BaseActivity {

	private int pageNum = 0;
	private PoiSearch search;
	private PoiCitySearchOption option;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		initSearch();
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		menu.addSubMenu(0, 1, 1, "上一页");
		menu.addSubMenu(0, 2, 2, "下一页");
		return super.onCreateOptionsMenu(menu);
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		switch (item.getItemId()) {
		case 1: // 上一页
			if (pageNum > 0) {
				option.pageNum(pageNum--);
				search.searchInCity(option);
			}
			break;
		case 2: // 下一页
			option.pageNum(pageNum++);
			search.searchInCity(option);
			break;

		default:
			break;
		}
		return super.onOptionsItemSelected(item);
	}

	private void initSearch() {
		search = PoiSearch.newInstance();
		option = new PoiCitySearchOption();
		option.city("西安");
		// 指定搜索关键字
		option.keyword("酒店");
		search.searchInCity(option);

		// 解析搜索结果
		search.setOnGetPoiSearchResultListener(new OnGetPoiSearchResultListener() {

			@Override
			public void onGetPoiResult(final PoiResult result) {
				// 加载数据前清空上次数据
				baiduMap.clear();

				PoiOverlay overlay = new PoiOverlay(baiduMap) {
					@Override
					public boolean onPoiClick(int index) {
						detailSearch(result.getAllPoi().get(index));
						return super.onPoiClick(index);
					}
				};
				overlay.setData(result);
				overlay.addToMap();
				overlay.zoomToSpan();

				baiduMap.setOnMarkerClickListener(overlay);

			}

			@Override
			public void onGetPoiDetailResult(PoiDetailResult result) {

			}
		});

	}

	// 发起详情搜索
	public void detailSearch(PoiInfo info) {
		PoiSearch detailSearch = PoiSearch.newInstance();
		PoiDetailSearchOption detailOption = new PoiDetailSearchOption();
		detailOption.poiUid(info.uid);
		detailSearch.searchPoiDetail(detailOption);

		detailSearch
				.setOnGetPoiSearchResultListener(new OnGetPoiSearchResultListener() {

					@Override
					public void onGetPoiResult(PoiResult result) {

					}

					@Override
					public void onGetPoiDetailResult(PoiDetailResult result) {
						showToast(result.getName() + "--" + result.getAddress());
					}
				});

	}

}
