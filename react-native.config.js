/**
 * @type {import('@react-native-community/cli-types').UserDependencyConfig}
 */
module.exports = {
  dependency: {
    platforms: {
      android: {
        cmakeListsPath: 'generated/jni/CMakeLists.txt',
        packageImportPath: 'com.splashview',
        packageInstance: 'new SplashViewPackage()',
      },
    },
  },
};
