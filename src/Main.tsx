import React, {useEffect, useState} from 'react';
import {
  Text,
  View,
  StyleSheet,
  Platform,
  Alert,
  ActivityIndicator,
} from 'react-native';
import NMBridge from './NMBridge';

export default () => {
  const [loaded, setLoaded] = useState(false);
  useEffect(() => {
    if (Platform.OS === 'ios') {
      Alert.alert('Not supported', 'We are not currently supporting iOS');
      return;
    }

    NMBridge.initialize();
  }, []);

  if (Platform.OS === 'ios') {
    return null;
  }
  if (!loaded) {
    return (
      <View
        style={{
          flex: 1,
          justifyContent: 'center',
          alignItems: 'center',
        }}>
        <ActivityIndicator size={'large'} color={'#000'} />
        <Text style={{marginTop: 20}}>initializing...</Text>
      </View>
    );
  }
  return (
    <View
      style={{
        flex: 1,
        justifyContent: 'center',
        alignItems: 'center',
        backgroundColor: 'lightgray',
      }}>
      <Text>Main</Text>
    </View>
  );
};
