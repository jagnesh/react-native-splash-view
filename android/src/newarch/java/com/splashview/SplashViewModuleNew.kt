package com.splashview

import com.facebook.react.bridge.ReactApplicationContext
import com.facebook.react.module.annotations.ReactModule

@ReactModule(name = SplashViewModuleNew.NAME)
class SplashViewModuleNew(reactContext: ReactApplicationContext) :
  NativeSplashViewSpec(reactContext) {

  override fun getName() = NAME

  override fun showSplash() {
    currentActivity?.let { SplashView.showSplashView(it) }
  }

  override fun hideSplash() {
    SplashView.hideSplashView()
  }

  companion object {
    const val NAME = "SplashView"
  }
}
