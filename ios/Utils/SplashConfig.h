//
//  SplashConfig.h
//  RNDynamicSplash
//
//  Created by mt on 2018/6/6.
//  Copyright © 2018年 mt. All rights reserved.
//

#import <Foundation/Foundation.h>

@interface SplashConfig : NSObject

@property NSString *imageUrl;
@property NSString *splashSavePath;
@property bool autoDownload;
@property bool dynamicShow;
@property bool autoHide;
@property int autoHideTime;

@end
