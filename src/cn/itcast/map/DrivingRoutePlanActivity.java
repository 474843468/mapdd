package cn.itcast.map;

import com.baidu.mapapi.overlayutil.DrivingRouteOverlay;
import com.baidu.mapapi.search.route.DrivingRoutePlanOption;
import com.baidu.mapapi.search.route.DrivingRouteResult;
import com.baidu.mapapi.search.route.OnGetRoutePlanResultListener;
import com.baidu.mapapi.search.route.PlanNode;
import com.baidu.mapapi.search.route.RoutePlanSearch;
import com.baidu.mapapi.search.route.TransitRouteResult;
import com.baidu.mapapi.search.route.WalkingRouteResult;
import com.baidu.mapapi.search.route.DrivingRoutePlanOption.DrivingPolicy;

import android.os.Bundle;

public class DrivingRoutePlanActivity extends BaseActivity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		initSearch();
	}

	private void initSearch() {
		// 创建出搜索实例
		RoutePlanSearch search = RoutePlanSearch.newInstance();
		// 设置搜索条件
		DrivingRoutePlanOption option = new DrivingRoutePlanOption();
		// 起点
		option.from(PlanNode.withCityNameAndPlaceName("北京", "钟楼"));
		// 重点
		option.to(PlanNode.withCityNameAndPlaceName("西安", "钟楼"));
		// 指定搜索策略
		option.policy(DrivingPolicy.ECAR_DIS_FIRST);
		search.drivingSearch(option);

		search.setOnGetRoutePlanResultListener(new OnGetRoutePlanResultListener() {

			@Override
			public void onGetWalkingRouteResult(WalkingRouteResult arg0) {

			}

			@Override
			public void onGetTransitRouteResult(TransitRouteResult arg0) {

			}

			@Override
			public void onGetDrivingRouteResult(DrivingRouteResult result) {
				if (result.getRouteLines() != null) {
					// 创建覆盖物
					DrivingRouteOverlay overlay = new DrivingRouteOverlay(
							baiduMap);
					// 设置数据
					overlay.setData(result.getRouteLines().get(0));
					// 添加到地图
					overlay.addToMap();
					// 控制缩放级别
					overlay.zoomToSpan();
				}

			}
		});

	}

}
