package com.nepu.gpamap.activity;

import android.app.Activity;
import android.os.Bundle;

import com.baidu.mapapi.map.BaiduMap;
import com.baidu.mapapi.map.BaiduMapOptions;
import com.baidu.mapapi.map.MapView;
import com.nepu.gpamap.maps.MLocation;

public class MainActivity extends Activity {
    private MapView mapView = null;
    private BaiduMap mBaiduMap=null;
    private MLocation location=null;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        BaiduMapOptions options = new BaiduMapOptions();
        options.mapType(BaiduMap.MAP_TYPE_NORMAL);
        mapView = new MapView(this, options);
        mBaiduMap=mapView.getMap();
        mBaiduMap.setMyLocationEnabled(true);
        location=new MLocation(mBaiduMap);
        location.StartLocation(this);
        setContentView(mapView);
    }
    @Override
    protected void onResume() {
        super.onResume();
        //在activity执行onResume时执行mMapView. onResume ()，实现地图生命周期管理
        mapView.onResume();
    }
    @Override
    protected void onPause() {
        super.onPause();
        //在activity执行onPause时执行mMapView. onPause ()，实现地图生命周期管理
        mapView.onPause();
    }
    @Override
    protected void onDestroy() {
        super.onDestroy();
        //在activity执行onDestroy时执行mMapView.onDestroy()，实现地图生命周期管理
        mapView.onDestroy();
        mBaiduMap.setMyLocationEnabled(false);
        location.StopLocation();
    }
}
