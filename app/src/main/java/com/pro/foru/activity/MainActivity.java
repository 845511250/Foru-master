package com.pro.foru.activity;

import android.os.Bundle;

import com.pro.foru.activity.fragment.MainFragment;
import com.pro.foru.foru.R;
import com.trello.rxlifecycle.components.support.RxFragmentActivity;

public class MainActivity extends RxFragmentActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initFragment();
    }
    private void initFragment() {
        getSupportFragmentManager().beginTransaction()
                .replace(R.id.main_content, new MainFragment(), MainFragment.class.getName())
                .commit();
    }

}
