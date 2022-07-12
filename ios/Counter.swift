//
//  Counter.swift
//  LearningNativeModuleRN
//
//  Created by admin on 7/11/22.
//

import Foundation
import React

@objc(Counter)
class Counter: RCTEventEmitter{
  
  private var count = 0;
  
  @objc
  func increment(_ callback: RCTResponseSenderBlock){
    count += 1;
//    print(count);
    callback([count]);
    sendEvent(withName: "onIncrement", body: ["count increment", count])
  }
  
  @objc
  override static func requiresMainQueueSetup() -> Bool{
    return true;
  }
  
  override func supportedEvents() -> [String]! {
    return ["onIncrement", "onDecrement"]
  }
  
  @objc
  override func constantsToExport() -> [AnyHashable: Any]!{
    return ["initialCount": 0];
  }
  
  @objc
  func decrement(_ resolve: RCTPromiseResolveBlock, reject: RCTPromiseRejectBlock)
  {
    if(count == 0){
      let error = NSError(domain: "", code: 200, userInfo: nil);
      reject("ERROR_COUNT", "count cannot be negative", error);
    }
    else{
      count -= 1;
      resolve("count is \(count)");
      sendEvent(withName: "onDecrement", body: ["count decrement", count])
    }
  }

}
