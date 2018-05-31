package com.hhj.mywork;

import android.content.Intent;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.hhj.appbase.base.RequiresPresenter;
import com.hhj.appbase.utils.RxBus;
import com.hhj.mywork.activity.BaseActivity;
import com.hhj.mywork.activity.ImgListActivity;
import com.hhj.mywork.activity.TestFragmentActivity;
import com.hhj.mywork.event.Event1;
import com.hhj.mywork.presenter.MainPresenter;

import butterknife.BindView;
import io.reactivex.annotations.NonNull;
import io.reactivex.functions.Consumer;
import skin.support.SkinCompatManager;
import skin.support.widget.SkinCompatSupportable;

@RequiresPresenter(MainPresenter.class)
public class MainActivity extends BaseActivity<MainPresenter> {
    @BindView(R.id.tv)
    TextView tv;
    boolean isChange;
    @Override
    public int getLayoutId() {
        return R.layout.activity_main;
    }

    @Override
    public void initView() {
        tv.setText("hahahahaha");
       new  RxBus.EventObserverBuilder<Event1>().create(this.bindToLifecycle()).setConsumer(new Consumer<Event1>() {
           @Override
           public void accept(@NonNull Event1 event1) throws Exception {
               Log.d("MainActivity","收到Event1");
           }
       }).build(Event1.class);
        tv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(isChange){
                    SkinCompatManager.getInstance().restoreDefaultTheme();
                }else {
                    SkinCompatManager.getInstance().loadSkin("night", SkinCompatManager.SKIN_LOADER_STRATEGY_BUILD_IN);
                }
                isChange=!isChange;

            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();
    }
    public void setText(String s){
        tv.setText(s);
    }
}
