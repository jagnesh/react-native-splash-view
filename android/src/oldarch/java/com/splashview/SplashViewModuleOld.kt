package com.splashview

import com.facebook.react.bridge.ReactApplicationContext
import com.facebook.react.bridge.ReactContextBaseJavaModule
import com.facebook.react.bridge.ReactMethod

class SplashViewModuleOld(reactContext: ReactApplicationContext) :
  ReactContextBaseJavaModule(reactContext) {

  override fun getName() = NAME

  @ReactMethod
  fun showSplash() {
    currentActivity?.let { SplashView.showSplashView(it) }
  }

  @ReactMethod
  fun hideSplash() {
    SplashView.hideSplashView()
  }

  companion object {
    const val NAME = "SplashView"
  }
}
