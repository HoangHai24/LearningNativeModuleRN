//
//  Counter.m
//  LearningNativeModuleRN
//
//  Created by admin on 7/11/22.
//

#import <Foundation/Foundation.h>

#import "React/RCTBridgeModule.h"
#import "React/RCTEventEmitter.h"


@interface RCT_EXTERN_MODULE(Counter, RCTEventEmitter)

RCT_EXTERN_METHOD(increment: (RCTResponseSenderBlock)callback)

RCT_EXTERN_METHOD(decrement: (RCTPromiseResolveBlock)callback
                  reject: (RCTPromiseRejectBlock)reject)

 
@end

