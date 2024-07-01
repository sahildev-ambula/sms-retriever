import { registerPlugin } from '@capacitor/core';

import type { smsRetrieverPlugin } from './definitions';

const smsRetriever = registerPlugin<smsRetrieverPlugin>('smsRetriever', {
  web: () => import('./web').then(m => new m.smsRetrieverWeb()),
});

export * from './definitions';
export { smsRetriever };
