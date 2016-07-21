package com.pro.foru.activity.fragment;

import android.os.Bundle;
import android.os.Message;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import com.pro.foru.foru.R;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by hjy on 16/6/22.
 */
public class WebFragment extends Fragment {
    @BindView(R.id.webView1)
    public WebView webView;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view =inflater.inflate(R.layout.fragment_webview,null);
        ButterKnife.bind(this,view);
        initViews();
        return view;
    }

    public void initViews()
    {
        WebSettings setting = webView.getSettings();


        setting.setPluginState(null);
        setting.setJavaScriptCanOpenWindowsAutomatically(true);
        webView.setScrollBarStyle(WebView.SCROLLBARS_OUTSIDE_OVERLAY);
        setting.setLoadWithOverviewMode(true);
        setting.setUseWideViewPort(true);
        setting.setSupportZoom(true);
        setting.setAppCacheEnabled(true);
        webView.clearCache(false);
        setting.setBuiltInZoomControls(true);
        setting.setUseWideViewPort(true);
        setting.setJavaScriptEnabled(true); // Support JavaScript
        setting.setPluginState(WebSettings.PluginState.ON);// Support Plugins, for example
        // setting.setAppCacheEnabled(false);
        // setting.setCacheMode(WebSettings.LOAD_NO_CACHE);
        webView.setWebChromeClient(new WebChromeClient() {
            @Override
            public boolean onCreateWindow(WebView view, boolean isDialog, boolean isUserGesture, Message resultMsg) {
                return super.onCreateWindow(view, isDialog, isUserGesture, resultMsg);
            }

            @Override
            public void onReceivedTitle(WebView view, String title) {
            }

        });
        webView.setWebViewClient(new WebViewClient() {
            String pendingUrl;

            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                WebView.HitTestResult hit = view.getHitTestResult();
                view.loadUrl(url);
                return true;
            }
        });
        webView.loadUrl("https://m.forudesigns.com/");
    }

}
