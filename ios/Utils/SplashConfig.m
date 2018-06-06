//
//  SplashConfig.m
//  RNDynamicSplash
//
//  Created by mt on 2018/6/6.
//  Copyright © 2018年 mt. All rights reserved.
//

#import "SplashConfig.h"

@implementation SplashConfig

- (instancetype)init {
    if (self = [super init]) {
        self.imageUrl = @"";
        self.splashSavePath = @"/splash/";
        self.autoDownload = true;
        self.dynamicShow = true;
        self.autoHide = false;
        self.autoHideTime = 3000;
    }
    return self;
}

@end
