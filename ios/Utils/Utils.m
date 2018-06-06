//
//  Utils.m
//  RNDynamicSplash
//
//  Created by mt on 2018/5/20.
//  Copyright © 2018年 mt. All rights reserved.
//

#import "Utils.h"

@implementation Utils

+ (BOOL)isBlankString:(NSString *)str {
    NSString *string = str;
    if (string == nil || string == NULL) {
        return YES;
    }
    if ([string isKindOfClass:[NSNull class]]) {
        return YES;
    }
    if ([[string stringByTrimmingCharactersInSet:[NSCharacterSet whitespaceCharacterSet]] length]==0) {
        return YES;
    }

    return NO;
}

@end
