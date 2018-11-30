package com.example.tiamo.gym;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.amap.api.location.AMapLocation;
import com.amap.api.location.AMapLocationClient;
import com.amap.api.location.AMapLocationClientOption;
import com.amap.api.location.AMapLocationListener;
import com.amap.api.maps.AMap;
import com.amap.api.maps.AMap.InfoWindowAdapter;
import com.amap.api.maps.AMap.OnInfoWindowClickListener;
import com.amap.api.maps.AMap.OnMapClickListener;
import com.amap.api.maps.AMap.OnMarkerClickListener;
import com.amap.api.maps.LocationSource;
import com.amap.api.maps.MapView;
import com.amap.api.maps.model.LatLng;
import com.amap.api.maps.model.Marker;
import com.amap.api.maps.model.MyLocationStyle;
import com.amap.api.services.core.AMapException;
import com.amap.api.services.core.LatLonPoint;
import com.amap.api.services.route.BusRouteResult;
import com.amap.api.services.route.DriveRouteResult;
import com.amap.api.services.route.RideRouteResult;
import com.amap.api.services.route.RouteSearch;
import com.amap.api.services.route.RouteSearch.OnRouteSearchListener;
import com.amap.api.services.route.RouteSearch.WalkRouteQuery;
import com.amap.api.services.route.WalkPath;
import com.amap.api.services.route.WalkRouteResult;
import com.example.tiamo.gym.overlay.WalkRouteOverlay;
import com.example.tiamo.gym.util.AMapUtil;
import com.example.tiamo.gym.util.ToastUtil;

import java.text.SimpleDateFormat;
import java.util.Date;


public class WalkRouteActivity extends Activity implements OnMapClickListener,
        OnMarkerClickListener, OnInfoWindowClickListener, InfoWindowAdapter, OnRouteSearchListener, AMap.OnMapLoadedListener {
	private AMap aMap;
	private MapView mapView;
	private Context mContext;
	private RouteSearch mRouteSearch;
	private WalkRouteResult mWalkRouteResult;
	private LatLonPoint mStartPoint ;//起点，39.996678,116.479271
	private LatLonPoint mEndPoint = new LatLonPoint(39.997796,116.468939);//终点，39.997796,116.468939
	private final int ROUTE_TYPE_WALK = 3;

	private boolean flag = false;

    private LocationSource.OnLocationChangedListener mListener;
    private AMapLocationClient mLocationClient;
    private AMapLocationClientOption mLocationOption;
	
	private RelativeLayout mBottomLayout, mHeadLayout;
	private TextView mRotueTimeDes, mRouteDetailDes;
	private ProgressDialog progDialog = null;// 搜索时进度条
	private WalkRouteOverlay walkRouteOverlay;
	@Override
	protected void onCreate(Bundle bundle) {
		super.onCreate(bundle);
		setContentView(R.layout.route_activity);
		
		mContext = this.getApplicationContext();
		mapView = (MapView) findViewById(R.id.route_map);
		mapView.onCreate(bundle);// 此方法必须重写
		init();
	}

	/**
	 * 初始化AMap对象
	 */
	private void init() {
		if (aMap == null) {
			aMap = mapView.getMap();	
		}
		registerListener();

		mRouteSearch = new RouteSearch(this);
		mRouteSearch.setRouteSearchListener(this);
		mBottomLayout = (RelativeLayout) findViewById(R.id.bottom_layout);
		mHeadLayout = (RelativeLayout) findViewById(R.id.routemap_header);
		mHeadLayout.setVisibility(View.GONE);
		mRotueTimeDes = (TextView) findViewById(R.id.firstline);
		mRouteDetailDes = (TextView) findViewById(R.id.secondline);
	}

	/**
	 * 注册监听
	 */
	private void registerListener() {
		aMap.setOnMapClickListener(WalkRouteActivity.this);
		aMap.setOnMarkerClickListener(WalkRouteActivity.this);
		aMap.setOnInfoWindowClickListener(WalkRouteActivity.this);
		aMap.setInfoWindowAdapter(WalkRouteActivity.this);
		aMap.setOnMapLoadedListener(this);
        aMap.setLocationSource(mLocationSource);
        MyLocationStyle myLocationStyle = new MyLocationStyle();
        myLocationStyle.strokeColor(Color.BLACK);// 設置圓形的邊框顏色
        myLocationStyle.radiusFillColor(Color.argb(100, 0, 191, 255));// 設置圓形的填充顏色
        myLocationStyle.strokeWidth(1.0f);// 設置圓形的邊框粗細
        aMap.setMyLocationStyle(myLocationStyle);
        aMap.getUiSettings().setMyLocationButtonEnabled(true);// 設置默認定位按鈕是否顯示
        aMap.setMyLocationEnabled(true);//
	}

    public LocationSource mLocationSource = new LocationSource(){
        @Override
        public void activate(OnLocationChangedListener onLocationChangedListener) {
            mListener = onLocationChangedListener;
            //初始化定位
            initAmapLocation();
        }
        @Override
        public void deactivate() {
            mListener = null;
            if (mLocationClient != null) {
                mLocationClient.stopLocation();
                mLocationClient.onDestroy();
            }
            mLocationClient = null;
        }
    };

    /**
     * 初始化定位
     */
    private void initAmapLocation() {
        //初始化定位
        mLocationClient = new AMapLocationClient(this);
        //設置定位回調監聽
        mLocationClient.setLocationListener(mAMapLocationListener);
        //初始化AMapLocationClientOption對象
        mLocationOption = new AMapLocationClientOption();
        // 設置定位場景，目前支持三種場景（簽到、出行、運動，默認無場景）
        mLocationOption.setLocationPurpose(AMapLocationClientOption.AMapLocationPurpose.Transport);
        //設置定位模式爲AMapLocationMode.Hight_Accuracy，高精度模式。
        mLocationOption.setLocationMode(AMapLocationClientOption.AMapLocationMode.Hight_Accuracy);
        //設置定位間隔,單位毫秒,默認爲2000ms，最低1000ms。
        mLocationOption.setInterval(5000);
        //設置是否返回地址信息（默認返回地址信息）
        mLocationOption.setNeedAddress(true);
        //單位是毫秒，默認30000毫秒，建議超時時間不要低於8000毫秒。
        mLocationOption.setHttpTimeOut(20000);
        if (null != mLocationClient) {
            mLocationClient.setLocationOption(mLocationOption);
            //設置場景模式後最好調用一次stop，再調用start以保證場景模式生效
            //  mLocationClient.stopLocation();
            mLocationClient.startLocation();
        }
    }

    /**
     * 定位
     */
    public AMapLocationListener mAMapLocationListener = new AMapLocationListener() {
        @Override
        public void onLocationChanged(AMapLocation amapLocation) {
            if (amapLocation != null) {
                if (amapLocation.getErrorCode() == 0) {
                    //可在其中解析amapLocation獲取相應內容。
                    mListener.onLocationChanged(amapLocation);// 顯示系統小藍點,不寫這一句無法顯示到當前位置
                    mStartPoint =  new LatLonPoint(amapLocation.getLongitude(), amapLocation.getAltitude());
                    flag = true;
                    /*
                    amapLocation.getLocationType();//獲取當前定位結果來源，如網絡定位結果，詳見定位類型表
                    amapLocation.getLatitude();//獲取緯度
                    amapLocation.getLongitude();//獲取經度
                    amapLocation.getAccuracy();//獲取精度信息
                    amapLocation.getAddress();//地址，如果option中設置isNeedAddress爲false，則沒有此結果，網絡定位結果中會有地址信息，GPS定位不返回地址信息。
                    amapLocation.getCountry();//國家信息
                    amapLocation.getProvince();//省信息
                    amapLocation.getCity();//城市信息
                    amapLocation.getDistrict();//城區信息
                    amapLocation.getStreet();//街道信息
                    amapLocation.getStreetNum();//街道門牌號信息
                    amapLocation.getCityCode();//城市編碼
                    amapLocation.getAdCode();//地區編碼
                    amapLocation.getAoiName();//獲取當前定位點的AOI信息
                    amapLocation.getBuildingId();//獲取當前室內定位的建築物Id
                    amapLocation.getFloor();//獲取當前室內定位的樓層
                    amapLocation.getGpsAccuracyStatus();//獲取GPS的當前狀態
                    amapLocation.getLocationDetail();//定位信息描述
                    amapLocation.getBearing();//獲取方向角信息
                    amapLocation.getSpeed();//獲取速度信息  單位：米/秒
                    amapLocation.getPoiName();//獲取當前位置的POI名稱
                    */
                    //獲取定位時間
                    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                    Date date = new Date(amapLocation.getTime());
                    sdf.format(date);
                } else {
                    //定位失敗時，可通過ErrCode（錯誤碼）信息來確定失敗的原因，errInfo是錯誤信息，詳見錯誤碼錶。
                    ;
                }
            }
        }
    };

	@Override
	public View getInfoContents(Marker arg0) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public View getInfoWindow(Marker arg0) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void onInfoWindowClick(Marker arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public boolean onMarkerClick(Marker arg0) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public void onMapClick(LatLng arg0) {
		// TODO Auto-generated method stub
		
	}

	
	/**
	 * 开始搜索路径规划方案
	 */
	public void searchRouteResult(int routeType) {
		if (mStartPoint == null) {
			ToastUtil.show(mContext, "定位中，稍后再试...");
			return;
		}
		if (mEndPoint == null) {
			ToastUtil.show(mContext, "终点未设置");
		}
		showProgressDialog();
		final RouteSearch.FromAndTo fromAndTo = new RouteSearch.FromAndTo(
				mStartPoint, mEndPoint);
		if (routeType == ROUTE_TYPE_WALK) {// 步行路径规划
			WalkRouteQuery query = new WalkRouteQuery(fromAndTo);
			mRouteSearch.calculateWalkRouteAsyn(query);// 异步路径规划步行模式查询
		}
	}

	@Override
	public void onBusRouteSearched(BusRouteResult result, int errorCode) {
		
	}

	@Override
	public void onDriveRouteSearched(DriveRouteResult result, int errorCode) {
		
	}

	@Override
	public void onWalkRouteSearched(WalkRouteResult result, int errorCode) {
		dissmissProgressDialog();
		aMap.clear();// 清理地图上的所有覆盖物
		if (errorCode == AMapException.CODE_AMAP_SUCCESS) {
			if (result != null && result.getPaths() != null) {
				if (result.getPaths().size() > 0) {
					mWalkRouteResult = result;
					final WalkPath walkPath = mWalkRouteResult.getPaths()
							.get(0);
					if (walkRouteOverlay != null){
						walkRouteOverlay.removeFromMap();
					}
					walkRouteOverlay = new WalkRouteOverlay(
							this, aMap, walkPath,
							mWalkRouteResult.getStartPos(),
							mWalkRouteResult.getTargetPos());
					walkRouteOverlay.addToMap();
					walkRouteOverlay.zoomToSpan();
					mBottomLayout.setVisibility(View.VISIBLE);
					int dis = (int) walkPath.getDistance();
					int dur = (int) walkPath.getDuration();
					String des = AMapUtil.getFriendlyTime(dur)+"("+AMapUtil.getFriendlyLength(dis)+")";
					mRotueTimeDes.setText(des);
					mRouteDetailDes.setVisibility(View.GONE);
					mBottomLayout.setOnClickListener(new OnClickListener() {
						@Override
						public void onClick(View v) {
							Intent intent = new Intent(mContext,
									WalkRouteDetailActivity.class);
							intent.putExtra("walk_path", walkPath);
							intent.putExtra("walk_result",
									mWalkRouteResult);
							startActivity(intent);
						}
					});
				} else if (result != null && result.getPaths() == null) {
					ToastUtil.show(mContext, R.string.no_result);
				}
			} else {
				ToastUtil.show(mContext, R.string.no_result);
			}
		} else {
			ToastUtil.showerror(this.getApplicationContext(), errorCode);
		}
	}
	

	/**
	 * 显示进度框
	 */
	private void showProgressDialog() {
		if (progDialog == null)
			progDialog = new ProgressDialog(this);
		    progDialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
		    progDialog.setIndeterminate(false);
		    progDialog.setCancelable(true);
		    progDialog.setMessage("正在搜索");
		    progDialog.show();
	    }

	/**
	 * 隐藏进度框
	 */
	private void dissmissProgressDialog() {
		if (progDialog != null) {
			progDialog.dismiss();
		}
	}

	/**
	 * 方法必须重写
	 */
	@Override
	protected void onResume() {
		super.onResume();
		mapView.onResume();
	}

	/**
	 * 方法必须重写
	 */
	@Override
	protected void onPause() {
		super.onPause();
		mapView.onPause();
	}

	/**
	 * 方法必须重写
	 */
	@Override
	protected void onSaveInstanceState(Bundle outState) {
		super.onSaveInstanceState(outState);
		mapView.onSaveInstanceState(outState);
	}

	/**
	 * 方法必须重写
	 */
	@Override
	protected void onDestroy() {
		super.onDestroy();
		mapView.onDestroy();
	}

	@Override
	public void onRideRouteSearched(RideRouteResult arg0, int arg1) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onMapLoaded() {
		searchRouteResult(ROUTE_TYPE_WALK);
	}
}

