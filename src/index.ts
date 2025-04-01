import { registerPlugin } from '@capacitor/core';

import type { BillingManagerPlugin } from './definitions';

const BillingManager = registerPlugin<BillingManagerPlugin>('BillingManager', {
  web: () => import('./web').then((m) => new m.BillingManagerWeb()),
});

export * from './definitions';
export { BillingManager };
