package com.example.pratik.shauryatoday.ads;

import android.content.Context;
import android.widget.Toast;

import com.google.android.gms.ads.AdListener;
import com.google.android.gms.ads.AdRequest;

/**
 * Created by prati on 18-Oct-16.
 */

public class ToastListner extends AdListener {

    private Context context;
    private String mErrorReason;

    public ToastListner(Context context) {
        this.context = context;
    }

    @Override
    public void onAdClosed() {
        showToast("onAdCloaed");
    }

    @Override
    public void onAdFailedToLoad(int i) {
        mErrorReason = "";
        switch (i){
            case AdRequest.ERROR_CODE_INTERNAL_ERROR:
                mErrorReason = "Internal Error";
                break;
            case AdRequest.ERROR_CODE_INVALID_REQUEST:
                mErrorReason = "Invalid Request";
                break;
            case AdRequest.ERROR_CODE_NETWORK_ERROR:
                mErrorReason = "Network Error";
                break;
            case AdRequest.ERROR_CODE_NO_FILL:
                mErrorReason = "No Fill";

        }
        showToast("onAdFailedToLoad("+mErrorReason+")");
    }

    @Override
    public void onAdLeftApplication() {
        showToast("onAdLeftApplication");
    }

    @Override
    public void onAdOpened() {
        showToast("onAdOpened");
    }

    @Override
    public void onAdLoaded() {
        showToast("onAdLoaded");
    }

    public void showToast(String message){
        Toast.makeText(context, message, Toast.LENGTH_SHORT).show();
    }

    public String getErrorReason(){
        return mErrorReason == null ? "" : mErrorReason;
    }
}
