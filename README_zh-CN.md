# react-native-dynamic-splash
React Native动态启动页(广告页)，支持Android和iOS

## 安装
```
npm install react-native-dynamic-splash --save
```

## Demo
| IOS | Android |
| --- | ------- |
| ![IOS](./demo.ios.gif) | ![Android](./demo.android.gif) |

## RN安装

### Android

#### 自动安装
`react-native link react-native-dynamic-splash`

#### 手动安装
- android/settings.gradle 文件添加代码
```java
include ':react-native-dynamic-splash'
project(':react-native-dynamic-splash').projectDir = new File(rootProject.projectDir, '../node_modules/react-native-dynamic-splash/android')
```

- android/app/build.gradle 文件添加代码
```
...
dependencies {
    ...
    compile project(':react-native-dynamic-splash')
}
...
```

- android/app/src/main/java/com/example/MainApplication.java 文件添加代码
```java
...
import com.taumu.rnDynamicSplash.DynamicSplashReactPackage;  // 引入包

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
                new DynamicSplashReactPackage()  // 在此添加
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

#### 通过CocoaPods安装
1. Podfile文件中添加 `pod 'RNDeviceInfo', :path => '../node_modules/react-native-device-info'`
3. 运行 pod install

#### 手动安装
1. 在XCode中的项目导航器中，右键单击 Libraries ➜ Add Files to [your project's name]
2. 转到 node_modules ➜ react-native-dynamic-splash 并添加 RNDynamicSplash.xcodeproj
3. 在XCode中的项目导航器中，选择targets 添加 libRNDynamicSplash.a 到你的项目，在 Build Phases ➜ Link Binary With Libraries中
4. 修复 'RNDynamicSplash.h' 头文件找不大, 必须选择 project/targets →  Build Settings → Search Paths → Header Search Paths to add:
  - project $(SRCROOT)/../node_modules/react-native-dynamic-splash/ios recursive
  - targets $(inherited) recursive


## 使用

### Android
- MainActivity.java 文件
```java
...
import com.taumu.rnDynamicSplash.DynamicSplash;
import com.taumu.rnDynamicSplash.utils.Config;

public class MainActivity extends ReactActivity {
    ...
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Config splashConfig = new Config();  // splash配置类
        // splashConfig.setImageUrl("http://custom/splash.png");
        splashConfig.setAutoHide(true);
        // splashConfig.setAutoHideTime(2000);
        // splashConfig.setLayoutResID(R.layout.custom_splash_dynamic);
        // splashConfig.setThemeResId(R.style.Custom_Splash_Theme);
        // splashConfig.setAutoDownload(false);
        // splashConfig.setSplashSavePath("/customSplashPath/");
        // splashConfig.setDynamicShow(false);
        new DynamicSplash(this, splashConfig);  // 在此添加展示splash
        super.onCreate(savedInstanceState);
    }
    ...
}
```

### iOS
- AppDelegate.m 文件
```objective-c
...
#import "RNDynamicSplash.h"
#import "SplashConfig.h"

@implementation AppDelegate

- (BOOL)application:(UIApplication *)application didFinishLaunchingWithOptions:(NSDictionary *)launchOptions
{
  ...
  [self.window makeKeyAndVisible];

  SplashConfig *config = [[SplashConfig alloc] init];  // splash配置类
  // config.imageUrl = @"http://custom/splash.png";
  config.autoHide = true;
  // config.autoHideTime = 1000;
  // config.dynamicShow = false;
  // config.autoDownload = false;
  // config.splashSavePath = @"custom";
  [[RNDynamicSplash alloc] initWithShow:rootView splashConfig:config];  // 在此添加展示splash

  return YES;
}

@end
...
```

### 配置项
| type | Field | defaultValue | setter | description |
| ---- | ----- | ------------ | ------ | ----------- |
| String | imageUrl | "" | setImageUrl | 下载图片地址 |
| String | splashSavePath | /splash/ | setSplashSavePath | 保存图片地址 |
| int | themeResId(Android only) | R.style.DynamicSplash_Theme | setThemeResId | 使用主题资源id |
| int | layoutResId(Android only) | R.layout.splash_dynamic | setLayoutResId | 使用布局文件资源id |
| boolean | autoDownload | true | setAutoDownload | 是否自动下载 |
| boolean | dynamicShow | true | setDynamicShow | 是否展示下载的图片 |
| boolean | autoHide | false | setAutoHide | 是否自动隐藏 |
| long | autoHideTime | 3000 | setAutoHideTime | 自动隐藏时间 |

### 其他说明
- 第一次启动展示默认图片，第二次再次启动展示下载好的图片
- 可以用自己写的资源文件和主题，可以和默认的配置同名，否则调用set方法更改配置，参考包中的资源文件
- android未设置imageUrl将展示布局文件中的资源图片
- ios未设置imageUrl将展示LaunchImage的图片


#### 提供gei请求方法
可以调用请求以取获取图片地址
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
| hide() | function | js控制隐藏splash | autoHide不要设置true |
| downloadSplash() | function | js控制下载图片 | autoDownload要设置为false |

## TODO
- [x] android版本splash
- [x] ios版本splash
- [ ] js中传入url作为图片下载链接
- [ ] 配置添加跳过按钮
- [ ] ios使用swift重写

## Changelog
- 1.0.*
- 1.1.*
