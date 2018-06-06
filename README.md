# react-native-dynamic-splash
[以中文查看](./README_zh-CN.md)  
React Native dynamic launch page (advertisement page), support for Android and iOS

## Installation
```
npm install react-native-dynamic-splash --save
```

## Demo
| IOS | Android |
| --- | ------- |
| ![IOS](./demo.ios.gif) | ![Android](./demo.android.gif) |

## Rn installation

### Android

#### Automatic installation
`react-native link react-native-dynamic-splash`

#### Manual installation
- android/settings.gradle File add code
```java
include ':react-native-dynamic-splash'
project(':react-native-dynamic-splash').projectDir = new File(rootProject.projectDir, '../node_modules/react-native-dynamic-splash/android')
```

- android/app/build.gradle File add code
```
...
dependencies {
    ...
    compile project(':react-native-dynamic-splash')
}
...
```

- android/app/src/main/java/com/example/MainApplication.java File add code
```java
...
import com.taumu.rnDynamicSplash.DynamicSplashReactPackage;  // Import package

public class MainApplication extends Application implements ReactApplication {
    ...
    private final ReactNativeHost mReactNativeHost = new ReactNativeHost(this) {
        @Override
        public boolean getUseDeveloperSupport() {
            return BuildConfig.DEBUG;
        }

        @Override
        protected List<ReactPackage> getPackages() {
            return Arrays.<ReactPackage>asList(
                new MainReactPackage(),
                new DynamicSplashReactPackage()  // Add here
            );
        }

        @Override
        protected String getJSMainModuleName() {
            return "index";
        }
    };
    ...
}
```

### iOS

#### Installing via CocoaPods
1. Added in Podfile file `pod 'RNDeviceInfo', :path => '../node_modules/react-native-device-info'`
3. Then run pod install

#### Manual installation
1. In XCode, in the project navigator, right click Libraries ➜ Add Files to [your project's name]
2. Go to node_modules ➜ react-native-dynamic-splash and add RNDynamicSplash.xcodeproj
3. In XCode, in the project navigator, select your targets. Add libRNDynamicSplash.a to your project's Build Phases ➜ Link Binary With Libraries
4. To fix 'RNDynamicSplash.h' file not found, you have to select your project/targets →  Build Settings → Search Paths → Header Search Paths to add:
  - project $(SRCROOT)/../node_modules/react-native-dynamic-splash/ios recursive
  - targets $(inherited) recursive

## Usage

### Android
- MainActivity.java File
```java
...
import com.taumu.rnDynamicSplash.DynamicSplash;
import com.taumu.rnDynamicSplash.utils.Config;

public class MainActivity extends ReactActivity {
    ...
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Config splashConfig = new Config();  // Splash configuration class
        // splashConfig.setImageUrl("http://custom/splash.png");
        splashConfig.setAutoHide(true);
        // splashConfig.setAutoHideTime(2000);
        // splashConfig.setLayoutResID(R.layout.custom_splash_dynamic);
        // splashConfig.setThemeResId(R.style.Custom_Splash_Theme);
        // splashConfig.setAutoDownload(false);
        // splashConfig.setSplashSavePath("/customSplashPath/");
        // splashConfig.setDynamicShow(false);
        new DynamicSplash(this, splashConfig);  // Add display splash here
        super.onCreate(savedInstanceState);
    }
    ...
}
```

### iOS
- AppDelegate.m File
```objective-c
...
#import "RNDynamicSplash.h"
#import "SplashConfig.h"

@implementation AppDelegate

- (BOOL)application:(UIApplication *)application didFinishLaunchingWithOptions:(NSDictionary *)launchOptions
{
  ...
  [self.window makeKeyAndVisible];

  SplashConfig *config = [[SplashConfig alloc] init];  // Splash configuration class
  // config.imageUrl = @"http://custom/splash.png";
  config.autoHide = true;
  // config.autoHideTime = 1000;
  // config.dynamicShow = false;
  // config.autoDownload = false;
  // config.splashSavePath = @"custom";
  [[RNDynamicSplash alloc] initWithShow:rootView splashConfig:config];  // Add display splash here

  return YES;
}

@end
...
```

### Configuration
| type | Field | defaultValue | setter | description |
| ---- | ----- | ------------ | ------ | ----------- |
| String | imageUrl | "" | setImageUrl | Download picture address |
| String | splashSavePath | /splash/ | setSplashSavePath | Save image address |
| int | themeResId(Android only) | R.style.DynamicSplash_Theme | setThemeResId | Use theme resource id |
| int | layoutResId(Android only) | R.layout.splash_dynamic | setLayoutResId | Use layout file resource id |
| boolean | autoDownload | true | setAutoDownload | Whether to download automatically |
| boolean | dynamicShow | true | setDynamicShow | Whether to display the downloaded picture |
| boolean | autoHide | false | setAutoHide | Whether to automatically hide |
| long | autoHideTime | 3000 | setAutoHideTime | Automatically hide time |

### Other instructions
- The first time to start displaying the default image, the second time to start displaying the downloaded image again
- Can use their own written resource files and topics, and the same name as the default configuration, otherwise call the set method to change the configuration, reference package resource file
- Android doesn't set imageUrl to show resource image in layout file
- ios does not set imageUrl to show picture of LaunchImage

#### Provide gei request method
Can call the request to get the address of the picture
```java
// mock json data
// {
//     splashInfo: {
//         imageUrl: "http://***.png"
//     }
// }
...
import com.taumu.rnDynamicSplash.utils.HttpUtils;

public static void getSplashImageUrl(String getApi) {
    HttpUtils.get(getApi, new HttpUtils.Callback() {
        @Override
        public void onResponse(String jsonString) {
            try {
                JSONObject jsonObject = new JSONObject(jsonString);
                JSONObject valueObject = jsonObject.getJSONObject("splashInfo");
                String imageUrl = valueObject.getString("imageUrl");
                if (!TextUtils.isEmpty(imageUrl)) {
                    Config splashConfig = new Config();
                    splashConfig.setImageUrl(imageUrl);
                    new DynamicSplash(activity, splashConfig);
                }
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
    });
}
...
```

## API
| name | type | description | conflict |
| ---- | ---- | ----------- | -------- |
| hide() | function | Js control hidden splash | autoHide not set true |
| downloadSplash() | function | Js control download pictures | autoDownload is set to false |

## TODO
- [x] Android version splash
- [x] Ios version splash
- [ ] js incoming url as image download link
- [ ] Configuration add skip button
- [ ] Ios rewrite using swift

## Changelog
- 1.0.*
