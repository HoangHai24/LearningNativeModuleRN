/**
 * Sample React Native App
 * https://github.com/facebook/react-native
 *
 * @format
 * @flow strict-local
 */

import React, {useEffect, useState} from 'react';
import {
  SafeAreaView,
  ScrollView,
  StatusBar,
  StyleSheet,
  Text,
  useColorScheme,
  View,
  TouchableOpacity,
} from 'react-native';
import NativeModuleAndroid from './src/components/NativeModuleAndroid';
import {NativeModules, NativeEventEmitter} from 'react-native';

// console.log(NativeModules.Counter);
// NativeModules.Counter.increment(value => {
//   console.log('the count is ' + value);
// });
// console.log(NativeModules.Counter.getConstants());

const CounterEvents = new NativeEventEmitter(NativeModules.Counter);

const App = () => {
  const [st, setSt] = useState(1);
  const increment = () => {
    NativeModules.Counter.increment(result =>
      console.log('incrementRes', result),
    );
  };
  const decrement = () => {
    // console.log('decrement');
    // return;
    NativeModules.Counter.decrement()
      .then(res => console.log('decrementRes', res))
      .catch(error => console.log('decrementError', error));
  };
  useEffect(() => {
    CounterEvents.addListener('onIncrement', result =>
      console.log('onIncrement received', result),
    );
    CounterEvents.addListener('onDecrement', result =>
      console.log('onDecrement received', result),
    );
    return () => {
      CounterEvents.removeAllListeners();
    };
  }, []);

  // console.log('render');
  return (
    <SafeAreaView style={{flex: 1, backgroundColor: 'red'}}>
      {/* <NativeModuleAndroid /> */}
      <TouchableOpacity style={{marginBottom: 20}} onPress={increment}>
        <Text>Increment Emitter</Text>
      </TouchableOpacity>
      <TouchableOpacity onPress={decrement}>
        <Text>Decrement Emitter</Text>
      </TouchableOpacity>
    </SafeAreaView>
  );
};

export default App;
