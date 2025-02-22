import type { TurboModule } from 'react-native';
import { TurboModuleRegistry } from 'react-native';

export interface Spec extends TurboModule {
  showSplash(): void;
  hideSplash(): void;
}

export default TurboModuleRegistry.getEnforcing<Spec>('SplashView');
