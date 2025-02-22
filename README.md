

# **📖 react-native-splash-view**  
*A lightweight and customizable splash screen module for React Native.*

![npm version](https://img.shields.io/npm/v/react-native-splash-view)  
![License](https://img.shields.io/github/license/jagnesh/react-native-splash-view)  

---

## **✨ Features**  
✅ Show and hide splash screen programmatically  
✅ Lightweight and fast  
✅ Supports both iOS and Android  

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
2️⃣ Ensure `SplashView` is correctly linked in **Development Pods** in Xcode.  

3️⃣ **Create a Storyboard for Splash Screen**:  
- Open **Xcode** and go to the **LaunchScreen.storyboard** file.  
- Ensure the **Storyboard Name** is set as `LaunchScreen`.  
- This will be used as the splash screen when the app starts.  

4️⃣ **Modify `AppDelegate.swift`** to show the splash screen programmatically:  

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
        DispatchQueue.main.async {
            if let splashClass = NSClassFromString("SplashView") as? NSObject.Type,
               let splashInstance = splashClass.perform(NSSelectorFromString("sharedInstance"))?.takeUnretainedValue() as? NSObject {
                splashInstance.perform(NSSelectorFromString("showSplash"))
            }
        }
    }
}
```

---

### **🤖 Android Setup**  

#### **1️⃣ Create `launch_screen.xml` for Splash Screen**  
Create the file **`android/app/src/main/res/layout/launch_screen.xml`** with the following content:  

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
🔹 Replace `@drawable/splash_logo` with your splash screen logo.  

#### **2️⃣ Optionally, Define a Custom Theme**  
You can specify a theme in `android/app/src/main/res/values/styles.xml`  

```xml
<resources>
    <style name="SplashViewTheme" parent="Theme.AppCompat.Light.NoActionBar">
        <item name="android:windowBackground">@drawable/launch_screen</item>
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


showSplash(); // Show the splash screen

setTimeout(() => {
  hideSplash(); // Hide after some time
}, 3000);
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

---

## **💡 Contributing**  
Feel free to open issues and pull requests! Contributions are welcome.  

---

## **📜 License**  
This project is licensed under the **MIT License**.  

---

