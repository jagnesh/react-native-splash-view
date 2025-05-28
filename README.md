

# **📖 react-native-splash-view**  
A lightweight and customizable splash screen module for React Native.

[![npm version](https://img.shields.io/npm/v/react-native-splash-view)](https://www.npmjs.com/package/react-native-splash-view)
[![License](https://img.shields.io/github/license/jagnesh/react-native-splash-view)](https://github.com/jagnesh/react-native-splash-view?tab=MIT-1-ov-file#readme) 

---

## **✨ Features**  
✅ Show and hide splash screen programmatically  
✅ Lightweight and fast  
✅ Supports both iOS and Android  


---
### Demo Video

[![IMAGE ALT TEXT HERE](https://img.youtube.com/vi/7OAoN9VlYCg/0.jpg)](https://www.youtube.com/watch?v=7OAoN9VlYCg)

---


## **📦 Installation**  

### **Using npm**  
```sh
npm install react-native-splash-view
```

### **Using yarn**  
```sh
yarn add react-native-splash-view
```

---

## **🛠️ Setup Instructions**  

### **📱 iOS Setup**  
1️⃣ Install CocoaPods dependencies:  
```sh
cd ios && pod install --repo-update && cd ..
```
2️⃣ Ensure `SplashView` is correctly linked.  

3️⃣ **Create a Storyboard for Splash Screen**:  
- Open **Xcode** and go to the **LaunchScreen.storyboard** file.  
- Ensure the **Storyboard Name** is set as `LaunchScreen`.  
- This will be used as the splash screen when the app starts.  

4️⃣ **Modify `AppDelegate`** to show the splash screen programmatically:  
### If you are using swift update AppDelegate.swift 
```swift
import UIKit

@UIApplicationMain
class AppDelegate: UIResponder, UIApplicationDelegate {

    var window: UIWindow?

    func application(
        _ application: UIApplication,
        didFinishLaunchingWithOptions launchOptions: [UIApplication.LaunchOptionsKey: Any]? = nil
    ) -> Bool {
        
        showSplashScreen() // Call the method to display the splash screen
        
        return true
    }

    //Add below method in AppDelegate.swift
    private func showSplashScreen() {
      if let splashClass = NSClassFromString("SplashView") as? NSObject.Type,
          let splashInstance = splashClass.perform(NSSelectorFromString("sharedInstance"))?.takeUnretainedValue() as? NSObject {
          splashInstance.perform(NSSelectorFromString("showSplash"))
          print("✅ Splash Screen Shown Successfully")
      } else {
          print("⚠️ SplashView module not found")
      }
    }

}
```
### If you are using Obj C update AppDelegate.m or AppDelegate.mm 
```objc

@implementation AppDelegate

- (BOOL)application:(UIApplication *)application didFinishLaunchingWithOptions:(NSDictionary *)launchOptions
{
  self.moduleName = @"yourapp";
  self.initialProps = @{};

  [self showSplashScreen]; // Call the method to display the splash screen
 
  return [super application:application didFinishLaunchingWithOptions:launchOptions];
}

// Add this method to AppDelegate.m
- (void)showSplashScreen {
    Class splashClass = NSClassFromString(@"SplashView");
    if (splashClass) {
        id splashInstance = [splashClass performSelector:NSSelectorFromString(@"sharedInstance")];
        if (splashInstance && [splashInstance respondsToSelector:NSSelectorFromString(@"showSplash")]) {
            [splashInstance performSelector:NSSelectorFromString(@"showSplash")];
        }
    }
}
```
---

### **🤖 Android Setup**  

#### **1️⃣ Create `launch_screen.xml` for Splash Screen**  
Create the file **`android/app/src/main/res/layout/launch_screen.xml`** as per requirement:  

```xml
<?xml version="1.0" encoding="utf-8"?>
<FrameLayout xmlns:android="http://schemas.android.com/apk/res/android"
    android:layout_width="match_parent"
    android:layout_height="match_parent"
    android:background="@color/white">

    <ImageView
        android:layout_width="wrap_content"
        android:layout_height="wrap_content"
        android:layout_gravity="center"
        android:src="@drawable/splash_logo" />

</FrameLayout>
```

#### **2️⃣ Optionally, Define a Custom Theme**  
You can specify a theme in `android/app/src/main/res/values/styles.xml` and style name should be `SplashViewTheme`. 

```xml
<resources>
  <style name="SplashViewTheme" parent="Theme.AppCompat.NoActionBar">
    <item name="android:windowExitAnimation">@android:anim/fade_out</item>
    <item name="android:windowLightStatusBar">true</item>
  </style>
</resources>
```

#### **3️⃣ Modify `MainActivity.kt` to Show the Splash Screen**  
Update **`MainActivity.kt`** to display the splash screen on launch:  

```kotlin
package com.example

import android.os.Bundle
import com.facebook.react.ReactActivity
import com.splashview.SplashView

class MainActivity : ReactActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        SplashView.showSplashView(this) // Show the splash screen
        super.onCreate(savedInstanceState)
    }
}
```

---

## **🚀 Usage**  

### **Basic Example**  
```tsx
import { hideSplash, showSplash } from 'react-native-splash-view';


showSplash(); // Show the splash screen (If you don't want to start it from native side)

useEffect(() => {
    setTimeout(() => {
      hideSplash(); // Hide after some time
    }, 5000);
}, []);
```

---

## **⚙️ API**  

| Method          | Description                        |
|----------------|----------------------------------|
| `showSplash()`  | Shows the splash screen   |
| `hideSplash()`  | Hides the splash screen   |

---

## **🐞 Troubleshooting**  

### **1️⃣ Cannot find `SplashView` in Pods folder (iOS)**  

Then run:  
```sh
cd ios && pod install --repo-update && cd ..
```

### **3️⃣ `SplashView` not found in `MainActivity.kt` (Android)**  
Ensure your package is correctly linked. Run the following:  
```sh
cd android && ./gradlew clean && cd ..
npx react-native run-android
```

## **🛠️ Patch for React Native 0.75.x and Below**

If you're using **React Native 0.75.x or below**, you may face issues with `EventEmitterCallback` in the `react-native-splash-view` package due to TurboModule compatibility changes. You can apply a patch to make it work.

### **Steps to Patch**

1. **Install `patch-package`**:

   ```sh
   npm install patch-package --save-dev
   ```

   Or with Yarn:

   ```sh
   yarn add patch-package --dev
   ```

2. **Update `package.json`**
   Add this to the `scripts` section:

   ```json
   "scripts": {
     "postinstall": "patch-package"
   }
   ```

3. **Download and Place the Patch File**
   Download the patch file from the following link:
   [react-native-splash-view+0.0.15.patch](https://github.com/jagnesh/react-native-splash-view/blob/main/react-native-splash-view+0.0.15.patch)

   Once downloaded, place it in the root of your project under the `patches/` folder. You may need to create the `patches/` folder if it doesn't already exist. The file structure should look like this:

   ```
   /your-project-root
   ├── patches/
   │   └── react-native-splash-view+0.0.15.patch
   └── package.json
   ```

4. **Apply the Patch**
   After placing the patch file in the `patches/` folder, run:

   ```sh
   npx patch-package react-native-splash-view
   ```

   This will apply the patch and fix issues related to **React Native 0.75.x and below**.

---
## **💡 Contributing**  
Feel free to open issues and pull requests! Contributions are welcome.  

---

## **📜 License**  
This project is licensed under the **MIT License**.  

---

