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
		// ����������ʵ��
		RoutePlanSearch search = RoutePlanSearch.newInstance();
		// ������������
		DrivingRoutePlanOption option = new DrivingRoutePlanOption();
		// ���
		option.from(PlanNode.withCityNameAndPlaceName("����", "��¥"));
		// �ص�
		option.to(PlanNode.withCityNameAndPlaceName("����", "��¥"));
		// ָ����������
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
					// ����������
					DrivingRouteOverlay overlay = new DrivingRouteOverlay(
							baiduMap);
					// ��������
					overlay.setData(result.getRouteLines().get(0));
					// ��ӵ���ͼ
					overlay.addToMap();
					// �������ż���
					overlay.zoomToSpan();
				}

			}
		});

	}

}
