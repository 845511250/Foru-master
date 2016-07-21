package com.pro.foru.activity.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.widget.DrawerLayout;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.nineoldandroids.view.ViewHelper;
import com.pro.foru.foru.R;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by hjy on 16/6/4.
 */
public class MainFragment extends Fragment {
    @BindView(R.id.id_drawerLayout)
    public DrawerLayout mDrawerLayout;
    private FragmentManager fragmentManager;
    private NetFragment mNetFragment;
    private WebFragment mWebFragment;


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_main, null);
        ButterKnife.bind(this, view);
        mDrawerLayout.setDrawerLockMode(DrawerLayout.LOCK_MODE_LOCKED_CLOSED,
                Gravity.RIGHT);
        fragmentManager = getActivity().getSupportFragmentManager();
        if (null != fragmentManager) {
            hideFragments(fragmentManager.beginTransaction());
        }
        setTabSelection(0);
        return view;
    }


    private void initEvents() {
        mDrawerLayout.setDrawerListener(new DrawerLayout.DrawerListener() {
            @Override
            public void onDrawerStateChanged(int newState) {
            }

            @Override
            public void onDrawerSlide(View drawerView, float slideOffset) {
                View mContent = mDrawerLayout.getChildAt(0);
                View mMenu = drawerView;
                float scale = 1 - slideOffset;
                float rightScale = 0.8f + scale * 0.2f;

                if (drawerView.getTag().equals("LEFT")) {

                    float leftScale = 1 - 0.3f * scale;

                    ViewHelper.setScaleX(mMenu, leftScale);
                    ViewHelper.setScaleY(mMenu, leftScale);
                    ViewHelper.setAlpha(mMenu, 0.6f + 0.4f * (1 - scale));
                    ViewHelper.setTranslationX(mContent,
                            mMenu.getMeasuredWidth() * (1 - scale));
                    ViewHelper.setPivotX(mContent, 0);
                    ViewHelper.setPivotY(mContent,
                            mContent.getMeasuredHeight() / 2);
                    mContent.invalidate();
                    ViewHelper.setScaleX(mContent, rightScale);
                    ViewHelper.setScaleY(mContent, rightScale);
                }

            }

            @Override
            public void onDrawerOpened(View drawerView) {

            }

            @Override
            public void onDrawerClosed(View drawerView) {
                mDrawerLayout.setDrawerLockMode(
                        DrawerLayout.LOCK_MODE_LOCKED_CLOSED, Gravity.RIGHT);
            }
        });
    }

    private void hideFragments(FragmentTransaction transaction) {

        if (null != mNetFragment) {
            transaction.hide(mNetFragment);
        }
        if (null != mWebFragment) {
            transaction.hide(mWebFragment);
        }

    }
    public void setTabSelection(int index) {
        FragmentTransaction transaction = fragmentManager.beginTransaction();
        hideFragments(transaction);
        switch (index) {
            case 0:
                if (null == mNetFragment) {
                    mNetFragment = new NetFragment();
                    transaction.add(R.id.content, mNetFragment);
                }
                else {
                    transaction.show(mNetFragment);
                }
                break;
            case 1:
                if (null == mWebFragment) {
                    mWebFragment = new WebFragment();
                    transaction.add(R.id.content, mWebFragment);
                }
                else {
                    transaction.show(mWebFragment);
                }
                break;
            case 2:

                break;
        }
        transaction.commitAllowingStateLoss();
    }

    @OnClick({R.id.main_tab_A, R.id.main_tab_B, R.id.main_tab_C})
    public void actionClick(View view) {
        switch (view.getId())
        {
            case R.id.main_tab_A:
                setTabSelection(0);
                break;
            case R.id.main_tab_B:
                setTabSelection(1);
                break;
            case R.id.main_tab_C:
                setTabSelection(2);
                break;
        }

    }


    /**
     * 开启新的Fragment
     */
    private void open(Fragment fragment) {
        final String tag = fragment.getClass().toString();
        getActivity().getSupportFragmentManager()
                .beginTransaction()
                .addToBackStack(tag)
                .replace(R.id.main_content, fragment, tag)
                .commit();
    }
}
