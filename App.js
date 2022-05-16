/**
 * Sample React Native App
 * https://github.com/facebook/react-native
 *
 * @format
 * @flow strict-local
 */

import React from 'react';
import {
  SafeAreaView,
  ScrollView,
  StatusBar,
  StyleSheet,
  Text,
  useColorScheme,
  View,
} from 'react-native';
import NativeModuleAndroid from './src/components/NativeModuleAndroid';

const App = () => {
  return (
    <SafeAreaView style={{flex: 1, backgroundColor: 'red'}}>
       <NativeModuleAndroid />
    </SafeAreaView>
  );
};


export default App;
