package com.softtanck.framework.activity;

import android.support.v4.app.FragmentTransaction;

import com.softtanck.framework.R;
import com.softtanck.framework.fragment.ContentFragment;
import com.softtanck.framework.fragment.MenuFragment;
import com.softtanck.framework.slidingmenu.SlidingMenu;


public class MainActivity extends BaseActivity {


    private FragmentTransaction fragmentTransaction;

    @Override
    protected int getViewId() {
        return R.layout.activity_main;
    }

    @Override
    protected void onActivityCreate() {
        setBehindContentView(R.layout.frame_menu);
        MenuFragment menuFragment = new MenuFragment();
        fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.fl_home_menu, menuFragment);
        fragmentTransaction.replace(R.id.fl_home_content, new ContentFragment());
        fragmentTransaction.commit();

        SlidingMenu sm = getSlidingMenu();
        sm.setShadowWidth(50);
        sm.setShadowDrawable(R.drawable.shadow);
        sm.setBehindOffset(200);
        sm.setFadeDegree(0.35f);
        sm.setTouchModeAbove(SlidingMenu.TOUCHMODE_MARGIN);
    }
}
