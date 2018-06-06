//
//  FileUtils.h
//  RNDynamicSplash
//
//  Created by mt on 2018/6/5.
//  Copyright © 2018年 mt. All rights reserved.
//

#import <UIKit/UIKit.h>
#import <Foundation/Foundation.h>

@interface FileUtils : NSObject

+ (UIImage *) getImageFromURL:(NSString *)imageUrl;
+ (UIImage *) loadImage:(NSString *)fileName inDirectory:(NSString *)directoryPath;
+ (void) saveImage:(UIImage *)image withFileName:(NSString *)imageName inDirectory:(NSString *)directoryPath;
+ (NSString *) getImageName:(NSString *)url;
+ (NSString *) getLaunchImageName;

@end
