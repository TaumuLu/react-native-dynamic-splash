//
//  RNDynamicSplash.h
//  RNDynamicSplash
//
//  Created by mt on 2018/5/20.
//  Copyright © 2018年 mt. All rights reserved.
//

#import <Foundation/Foundation.h>
#import <React/RCTBridgeModule.h>
#import <React/RCTRootView.h>
#import "SplashConfig.h"

@interface RNDynamicSplash : NSObject <RCTBridgeModule>

- (id)initWithShow:(RCTRootView *)rootView splashConfig:(SplashConfig *)config;
- (void)hide;
- (void)downloadSplash;

@end
