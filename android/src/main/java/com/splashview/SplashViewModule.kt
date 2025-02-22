package com.splashview

import com.facebook.react.bridge.ReactApplicationContext
import com.facebook.react.module.annotations.ReactModule

@ReactModule(name = SplashViewModule.NAME)
class SplashViewModule(reactContext: ReactApplicationContext) :
  NativeSplashViewSpec(reactContext) {

  override fun getName(): String {
    return NAME
  }

  override fun showSplash() {
    val activity = currentActivity ?: return // Get current activity safely
    SplashView.showSplashView(activity)
  }

  override fun hideSplash() {
   SplashView.hideSplashView()
  }

  companion object {
    const val NAME = "SplashView"
  }
}
