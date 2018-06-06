//
//  HttpUtils.h
//  RNDynamicSplash
//
//  Created by mt on 2018/6/5.
//  Copyright © 2018年 mt. All rights reserved.
//

#import <Foundation/Foundation.h>

typedef void (^getCallBack)(NSData *data);

@interface HttpUtils : NSObject

+ (void)get:(NSString *)url callback:(getCallBack)callback;

@end
