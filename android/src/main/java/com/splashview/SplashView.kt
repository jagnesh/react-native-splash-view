package com.splashview

import android.app.Activity
import android.app.Dialog
import android.view.LayoutInflater
import android.view.View

public  class SplashView {

  companion object {
    private var splashDialog: Dialog? = null
    fun showSplashView(activity: Activity) {

      if (activity.isFinishing || activity.isDestroyed) {
        println("Skipping showSplash: Activity is not ready.")
        return
      }

      activity.runOnUiThread {
        if (splashDialog?.isShowing == true) return@runOnUiThread
        val inflater = LayoutInflater.from(activity)
        val dialogView: View = inflater.inflate(R.layout.launch_screen, null)
        val themeId = activity.resources.getIdentifier("SplashViewTheme", "style", activity.packageName)
        splashDialog =if(themeId !=0)  Dialog(activity,R.style.SplashViewTheme) else Dialog(activity,R.style.SplashScreen_SplashTheme)
        splashDialog?.setContentView(dialogView)
        splashDialog?.setCancelable(false)
        splashDialog?.show()
      }
    }

    fun hideSplashView() {
      splashDialog?.dismiss()
      splashDialog = null
    }

  }

}
