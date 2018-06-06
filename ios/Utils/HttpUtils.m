//
//  HttpUtils.m
//  RNDynamicSplash
//
//  Created by mt on 2018/6/5.
//  Copyright © 2018年 mt. All rights reserved.
//

#import "HttpUtils.h"

@implementation HttpUtils

+ (void) get:(NSString *)urlString callback:(getCallBack)callback {
    NSURL *url = [NSURL URLWithString:urlString];
    NSMutableURLRequest *request = [NSMutableURLRequest requestWithURL:url cachePolicy:NSURLRequestUseProtocolCachePolicy timeoutInterval:60.0];

    [NSURLConnection sendAsynchronousRequest:request queue:[NSOperationQueue mainQueue] completionHandler:^(NSURLResponse *response, NSData *data, NSError *connectionError) {
        callback(data);
    }];
}

// + (void)getExample {
//     NSString *urlString =  [[NSString alloc] initWithFormat:@"%@%@%@", agreement, domain, requestURL];
//     [Utils get:urlString callback:^(NSData *data) {
//         if(data != nil) {
//             NSDictionary *dict = [NSJSONSerialization JSONObjectWithData:data options:NSJSONReadingMutableLeaves error:nil];
//             NSDictionary *info = dict[@"app.startup.img"];
//             NSString *value = info[@"value"];

//             if(![Utils isBlankString: value]) {
//                 NSData *valueData = [value dataUsingEncoding:NSUTF8StringEncoding];
//                 NSDictionary *valueDict = [NSJSONSerialization JSONObjectWithData:valueData options:NSJSONReadingMutableLeaves error:nil];
//                 NSString *imgUrl = [valueDict[@"img"] substringFromIndex:2];
//                 // 获得NSUserDefaults文件
//                 NSUserDefaults *userDefaults = [NSUserDefaults standardUserDefaults];
//                 NSString *splashConfig = [userDefaults objectForKey:userDefaultsKey];
//                 NSString *imgName = [self getImageName:imgUrl];
//                 if(![splashConfig isEqualToString:imgName]) {
//                     NSString *imageUrl =  [[NSString alloc] initWithFormat:@"%@%@", agreement, imgUrl];
//                     [self downloadImage:imageUrl];
//                 }
//                 [userDefaults setObject:imgName forKey:userDefaultsKey];
//             }
//         }
//     }];
// }

@end
