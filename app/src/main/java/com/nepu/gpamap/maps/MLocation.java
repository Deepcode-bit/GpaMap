package com.nepu.gpamap.maps;

import android.content.Context;
import android.location.Location;

import com.baidu.location.BDAbstractLocationListener;
import com.baidu.location.BDLocation;
import com.baidu.location.LocationClient;
import com.baidu.location.LocationClientOption;
import com.baidu.mapapi.map.BaiduMap;
import com.baidu.mapapi.map.BitmapDescriptorFactory;
import com.baidu.mapapi.map.MyLocationConfiguration;
import com.baidu.mapapi.map.MyLocationData;
import com.nepu.gpamap.R;

public class MLocation {
    private BaiduMap map;
    private LocationClient client;

    public MLocation(BaiduMap map){
        this.map=map;
        //设置默认的定位模式
        MyLocationConfiguration configuration=new MyLocationConfiguration(
                MyLocationConfiguration.LocationMode.FOLLOWING,
                true,
                BitmapDescriptorFactory.fromResource(R.drawable.ic_launcher_foreground),
                0xAAFFFF88,
                0xAA00FF00);
        map.setMyLocationConfiguration(configuration);

    }

    public void StartLocation(Context context){
        //定位初始化
        client = new LocationClient(context);
        //通过LocationClientOption设置LocationClient相关参数
        LocationClientOption option = new LocationClientOption();
        option.setOpenGps(true); // 打开gps
        option.setCoorType("bd09ll"); // 设置坐标类型
        option.setScanSpan(1000);
        client.setLocOption(option);
        client.registerLocationListener(new BDAbstractLocationListener(){
            @Override
            public void onReceiveLocation(BDLocation location) {
                if (location == null || map== null){
                    return;
                }
                MyLocationData locData = new MyLocationData.Builder()
                        .accuracy(location.getRadius())
                        // 此处设置开发者获取到的方向信息，顺时针0-360
                        .direction(location.getDirection()).latitude(location.getLatitude())
                        .longitude(location.getLongitude()).build();
                map.setMyLocationData(locData);
            }
        });
        client.start();
    }

    public void StopLocation(){
        if(client!=null)
        client.stop();
    }


}
