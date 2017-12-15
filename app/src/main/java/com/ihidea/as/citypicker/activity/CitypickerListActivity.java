package com.ihidea.as.citypicker.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;

import com.ihidea.as.citypicker.R;
import com.lljjcoder.style.citylist.CityListSelectActivity;
import com.lljjcoder.style.citylist.bean.CityInfoBean;

public class CitypickerListActivity extends AppCompatActivity {
    
    TextView mListTv;
    
    TextView mResultTv;
    
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_citypicker_list);
        findView();
    }
    
    private void findView() {
        mListTv = (TextView) findViewById(R.id.list_tv);
        mResultTv = (TextView) findViewById(R.id.result_tv);
        
        mListTv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                list();
            }
        });
    }
    
    public void list() {
        Intent intent = new Intent(CitypickerListActivity.this, CityListSelectActivity.class);
        startActivityForResult(intent, CityListSelectActivity.CITY_SELECT_RESULT_FRAG);
        
    }
    
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == CityListSelectActivity.CITY_SELECT_RESULT_FRAG) {
            if (resultCode == RESULT_OK) {
                if (data == null) {
                    return;
                }
                Bundle bundle = data.getExtras();
                
                CityInfoBean cityInfoBean = (CityInfoBean) bundle.getParcelable("cityinfo");
                
                if (null == cityInfoBean) {
                    return;
                }
                
                //城市名称
                String cityName = cityInfoBean.getName();
                //纬度
                String latitude = cityInfoBean.getLongitude();
                //经度
                String longitude = cityInfoBean.getLatitude();
                
                mResultTv.setText("城市： " + cityName + "\n" + "经度： " + latitude + "\n" + "纬度： " + longitude);
            }
        }
    }
}
