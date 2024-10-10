package com.example.virtualcare.call;

import android.webkit.JavascriptInterface;

import com.example.virtualcare.Activity.VideoCallView;

public class CallClass {
    VideoCallView videoCallView;

   public CallClass(VideoCallView videoCallView){
        this.videoCallView=videoCallView;
   }
   @JavascriptInterface
   public void peerConnected(){
           videoCallView.isPeerConnected();
   }
     }
