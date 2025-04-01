import { WebPlugin } from '@capacitor/core';

import type { BillingManagerPlugin } from './definitions';

export class BillingManagerWeb extends WebPlugin implements BillingManagerPlugin {
  async echo(options: { value: string }): Promise<{ value: string }> {
    console.log('ECHO', options);
    return options;
  }
}
