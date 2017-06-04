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
		//����PoiSearch����
		PoiSearch poiSearch = PoiSearch.newInstance();
		//������������
		PoiNearbySearchOption option = new PoiNearbySearchOption();
		//ָ��Բ������
		option.location(position);
		//ָ���뾶 	��λ:��
		option.radius(1000);
		//ָ�������ؼ���
		option.keyword("�Ƶ�");
		//��������
		poiSearch.searchNearby(option);
		
		//�����������
		poiSearch.setOnGetPoiSearchResultListener(new OnGetPoiSearchResultListener() {
			
			@Override
			public void onGetPoiResult(PoiResult result) {
				//����PoiOverLay
				PoiOverlay overlay = new PoiOverlay(baiduMap);
				//��������
				overlay.setData(result);
				//��ӵ���ͼ��
				overlay.addToMap();
				//���ŵ�ͼ����,�����кõ���ʾ
				overlay.zoomToSpan();
			}
			
			@Override
			public void onGetPoiDetailResult(PoiDetailResult arg0) {
				
			}
		});
	}
	
}
