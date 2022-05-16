import React, {useEffect} from 'react';
import {Button, NativeModules, DeviceEventEmitter} from 'react-native';
import module from './module';

const NewModuleBtn = () => {
  const onPress = async () => {
    try {
      const resPicker = await module.ImagePickerModule.pickImage();
      console.log('resPicker', resPicker);
      return;
      module.CalendarModule.test();
      console.log('We will invoke the native module here!');
      const responseCb = await module.CalendarModule.createCalendarEvent(
        'testName',
        123,
        true,
        {a: 'b'},
        [1, 2, 3],
        // (err, res) => {
        //     console.log('calendarCallbackData', err, res)
        // }
      );
      console.log('responseCb', responseCb);
      // console.log(module.CalendarModule.getConstants());
    } catch (error) {
      console.log('error', error);
    }
  };

  useEffect(() => {
    DeviceEventEmitter.addListener('count', data => {
      console.log('NewModuleBtnEvent', data);
    });
    return () => {
      DeviceEventEmitter.removeAllListeners();
    };
  }, []);
  return (
    <Button
      title="Click to invoke your native module!"
      color="#841584"
      onPress={onPress}
    />
  );
};
export default NewModuleBtn;
