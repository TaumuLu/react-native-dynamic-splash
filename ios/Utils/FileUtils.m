//
//  FileUtils.m
//  RNDynamicSplash
//
//  Created by mt on 2018/6/5.
//  Copyright © 2018年 mt. All rights reserved.
//

#import "FileUtils.h"

@implementation FileUtils

// 从网络下载图片
+ (UIImage *) getImageFromURL:(NSString *)imageUrl {
    UIImage *result;
    NSData *data = [NSData dataWithContentsOfURL:[NSURL URLWithString:imageUrl]];
    result = [UIImage imageWithData:data];

    return result;
}

//读取本地保存的图片
+ (UIImage *) loadImage:(NSString *)fileName inDirectory:(NSString *)directoryPath {
    NSString *filePath = [NSString stringWithFormat:@"%@/%@.png", directoryPath, fileName];
    UIImage *result = [UIImage imageWithContentsOfFile:filePath];

    return result;
}

// 将所下载的图片保存到本地
+ (void) saveImage:(UIImage *)image withFileName:(NSString *)imageName inDirectory:(NSString *)directoryPath {
    NSString *fileName = [NSString stringWithFormat:@"%@.%@", imageName, @"png"];

    // 建立文件夹
    NSFileManager *fileManager = [NSFileManager defaultManager];
    if(![fileManager fileExistsAtPath:directoryPath]){
       [fileManager createDirectoryAtPath:directoryPath withIntermediateDirectories:YES attributes:nil error:nil];
    }
    [UIImagePNGRepresentation(image) writeToFile:[directoryPath stringByAppendingPathComponent:fileName] options:NSAtomicWrite error:nil];
}

+ (NSString *) getImageName:(NSString *)url {
    NSRange range = [url rangeOfString:@"/" options:NSBackwardsSearch];
    return [url substringFromIndex:range.location + 1];
}

+ (NSString *) getLaunchImageName {
    CGSize viewSize = [[UIScreen mainScreen] bounds].size;
    //    CGSize viewSize = rootView.bounds.size;
    // 竖屏
    NSString *viewOrientation = @"Portrait";
    NSString *launchImageName = nil;
    NSArray* imagesDict = [[[NSBundle mainBundle] infoDictionary] valueForKey:@"UILaunchImages"];
    for (NSDictionary* dict in imagesDict) {
        CGSize imageSize = CGSizeFromString(dict[@"UILaunchImageSize"]);
        if (CGSizeEqualToSize(imageSize, viewSize) && [viewOrientation isEqualToString:dict[@"UILaunchImageOrientation"]])
        {
            launchImageName = dict[@"UILaunchImageName"];
        }
    }

    return launchImageName;
}

@end
