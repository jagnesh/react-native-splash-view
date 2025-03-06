package com.splashview

import android.app.Activity
import android.app.Dialog
import android.graphics.Color
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.WindowManager

object SplashView {
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
      val themeResId = if (themeId != 0) themeId else R.style.SplashScreen_SplashTheme

      splashDialog = Dialog(activity, themeResId).apply {
        setContentView(dialogView)
        setCancelable(false)
        window?.apply {
          setLayout(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT)
          setBackgroundDrawableResource(android.R.color.transparent)
          addFlags(WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS) // Extend behind status bar
        }
        show()
      }
    }
  }

  fun hideSplashView() {
    splashDialog?.takeIf { it.isShowing }?.dismiss()
    splashDialog = null
  }
}
