import { useEffect } from 'react';
import { View, StyleSheet, Button } from 'react-native';
import { hideSplash, showSplash } from 'react-native-splash-view';

// const result = multiply(3, 7);

export default function App() {
  const start = () => {
    showSplash();
    setTimeout(() => {
      hideSplash();
    }, 3000);
  };
  useEffect(() => {
    setTimeout(() => {
      hideSplash();
    }, 3000);
  }, []);
  return (
    <View style={styles.container}>
      <Button onPress={() => start()} title="Show Splash" />
    </View>
  );
}

const styles = StyleSheet.create({
  container: {
    flex: 1,
    alignItems: 'center',
    justifyContent: 'center',
  },
});
