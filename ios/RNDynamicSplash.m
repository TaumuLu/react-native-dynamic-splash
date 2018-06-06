//
//  RNDynamicSplash.m
//  RNDynamicSplash
//
//  Created by mt on 2018/5/20.
//  Copyright © 2018年 mt. All rights reserved.
//

#import "RNDynamicSplash.h"
#import "Utils.h"
#import "FileUtils.h"


@interface RNDynamicSplash() {
    RCTRootView *_rootView;
    NSString *_fileName;
    NSString *_userDefaultsKey;
    NSString *_docsdir;
    SplashConfig *_config;
}

- (void)autoHide;
- (UIImageView *)getImageView;
- (UIImage *)getImage;

@end

@implementation RNDynamicSplash

- (id)initWithShow:(RCTRootView *)rootView splashConfig:(SplashConfig *)config {
    if(self = [super init]) {
        // init
        _config = config;
        _rootView = rootView;
        _userDefaultsKey = @"dynamicSplashConfig";
        _fileName = @"dynamicSplashImage";
        NSString *docsdir = [NSSearchPathForDirectoriesInDomains(NSDocumentDirectory, NSUserDomainMask, YES) objectAtIndex:0];
        // document目录拼接自定义目录
        _config.splashSavePath = [docsdir stringByAppendingPathComponent:_config.splashSavePath];
        UIImageView *imageView = [self getImageView];

        if(_config.autoDownload) {
          [self downloadSplashImg];
        }

        [[NSNotificationCenter defaultCenter] removeObserver:_rootView  name:RCTContentDidAppearNotification object:_rootView];

        [_rootView setLoadingView:imageView];
        if (_config.autoHide) {
            [self autoHide];
        }
    }
    return self;
}

- (UIImageView *)getImageView {
    UIImageView *imageView = [[UIImageView alloc]initWithFrame:[UIScreen mainScreen].bounds];

    imageView.image = [self getImage];
    imageView.contentMode = UIViewContentModeScaleToFill;
    imageView.backgroundColor = [UIColor whiteColor];
    imageView.userInteractionEnabled = YES;
    //  UIViewContentModeScaleAspectFill;
    return imageView;
}

- (UIImage *)getImage {
    if(_config.dynamicShow) {
        UIImage * localImage = [FileUtils loadImage:_fileName inDirectory:_config.splashSavePath];
        // NSArray *file = [[[NSFileManager alloc] init] subpathsAtPath:documentsDirectoryPath];
        if(localImage != nil) {
            return localImage;
        }
    }

    //  NSString *launchImageName = [[NSBundle mainBundle] pathForResource:@"LaunchImage" ofType:@"png"];
    NSString *launchImageName = [FileUtils getLaunchImageName];
    return [UIImage imageNamed:launchImageName];
}

- (void)autoHide {
    float time = _config.autoHideTime / 1000;
    [NSTimer scheduledTimerWithTimeInterval:time target:self selector:@selector(hideImg) userInfo:nil repeats:NO];
}

- (void)hideImg {
    if (!_rootView) {
        return;
    }

    dispatch_async(dispatch_get_main_queue(), ^{
        [self->_rootView.loadingView removeFromSuperview];
        // [_rootView hideLoadingView];
    });
}

- (void)downloadSplashImg {
    NSString *imageUrl = _config.imageUrl;
    if(![Utils isBlankString: imageUrl]) {
        // 获得NSUserDefaults文件
        NSUserDefaults *userDefaults = [NSUserDefaults standardUserDefaults];
        NSString *splashConfig = [userDefaults objectForKey:_userDefaultsKey];
        // 拼接保存路径和图片名作为标识
        NSString *imageName = [_config.splashSavePath stringByAppendingPathComponent:[FileUtils getImageName:imageUrl]];
        if(![splashConfig isEqualToString:imageName]) {
            UIImage *image = [FileUtils getImageFromURL:imageUrl];
            [FileUtils saveImage:image withFileName:_fileName inDirectory:_config.splashSavePath];
        } else {
            [userDefaults setObject:imageName forKey:_userDefaultsKey];
        }
    }
}

RCT_EXPORT_MODULE();

RCT_EXPORT_METHOD(hide) {
    [self hideImg];
}

RCT_EXPORT_METHOD(downloadSplash) {
    [self downloadSplashImg];
}

@end
