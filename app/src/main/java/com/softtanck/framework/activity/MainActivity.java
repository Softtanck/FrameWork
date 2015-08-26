package com.softtanck.framework.activity;


import android.support.v4.app.FragmentTransaction;

import com.softtanck.framework.R;
import com.softtanck.framework.fragment.MyTaskFragment;
import com.softtanck.framework.slidingmenu.SlidingMenu;
import com.softtanck.framework.utils.ScreenUtils;


public class MainActivity extends BaseActivity {


    private FragmentTransaction fragmentTransaction;
    public static SlidingMenu sm;


    @Override
    protected int getViewId() {
        return R.layout.activity_main;
    }

    @Override
    protected void onActivityCreate() {
        initMenu();
    }

    private void initMenu() {
//        sm = new SlidingMenu(getApplicationContext());
//        sm.setMode(SlidingMenu.LEFT_RIGHT);
//        sm.setShadowWidth(50);
//        sm.setShadowDrawable(R.drawable.shadow);
//        sm.setBehindOffset(ConValue.ScreenWidth / 3);
//        sm.setFadeDegree(0.35f);
//        sm.attachToActivity(this, SlidingMenu.SLIDING_CONTENT);
//        sm.setMenu(R.layout.frame_menu);
//        sm.setTouchModeAbove(SlidingMenu.TOUCHMODE_MARGIN);
        ScreenUtils.setChenjin(findViewById(R.id.rl), MainActivity.this);

        fragmentTransaction = getSupportFragmentManager().beginTransaction();
//        fragmentTransaction.replace(R.id.fl_home_menu, new MenuFragment());
        fragmentTransaction.replace(R.id.fl_home_content, new MyTaskFragment());
        fragmentTransaction.commit();
    }

}
