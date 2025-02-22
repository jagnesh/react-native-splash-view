#import "SplashView.h"
#import <UIKit/UIKit.h>

@implementation SplashView

RCT_EXPORT_MODULE()

static SplashView *sharedInstance = nil;
UIWindow *splashWindow = nil;  // Dedicated window for splash screen
UIViewController *loadedViewController = nil;  // Keep track of the loaded splash view

+ (instancetype)sharedInstance {
    static dispatch_once_t onceToken;
    dispatch_once(&onceToken, ^{
        sharedInstance = [[self alloc] init];
    });
    return sharedInstance;
}

- (void)showSplash {
    dispatch_async(dispatch_get_main_queue(), ^{
        if (splashWindow) {
            NSLog(@"⚠️ Splash already visible");
            return;
        }

        // Load LaunchScreen.storyboard
        UIStoryboard *storyboard = [UIStoryboard storyboardWithName:@"LaunchScreen" bundle:nil];
        UIViewController *viewController = [storyboard instantiateInitialViewController];

        if (!viewController) {
            NSLog(@"❌ Failed to load LaunchScreen");
            return;
        }

        loadedViewController = viewController;

        // Get active window scene (for iOS 13+)
        UIWindowScene *windowScene = nil;
        for (UIScene *scene in UIApplication.sharedApplication.connectedScenes) {
            if (scene.activationState == UISceneActivationStateForegroundActive) {
                windowScene = (UIWindowScene *)scene;
                break;
            }
        }

        if (!windowScene) {
            NSLog(@"❌ No active window scene found");
            return;
        }

        // Create a new overlay window to ensure splash screen stays on top
        splashWindow = [[UIWindow alloc] initWithFrame:windowScene.coordinateSpace.bounds];
        splashWindow.windowScene = windowScene;
        splashWindow.windowLevel = UIWindowLevelAlert + 1; // Always on top
        splashWindow.rootViewController = viewController;
        splashWindow.hidden = NO;

    });
}

- (void)hideSplash {

    dispatch_async(dispatch_get_main_queue(), ^{
        if (splashWindow) {
            splashWindow.hidden = YES;
            splashWindow = nil; // Remove reference
            loadedViewController = nil;
            NSLog(@"✅ Splash Screen Hidden");
        } else {
            NSLog(@"⚠️ No active splash window found");
        }
    });
}

// React Native TurboModule integration
- (std::shared_ptr<facebook::react::TurboModule>)getTurboModule:(const facebook::react::ObjCTurboModule::InitParams &)params {
    return std::make_shared<facebook::react::NativeSplashViewSpecJSI>(params);
}

@end
