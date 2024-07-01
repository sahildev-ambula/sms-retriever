import { WebPlugin } from '@capacitor/core';

import type { smsRetrieverPlugin } from './definitions';

export class smsRetrieverWeb extends WebPlugin implements smsRetrieverPlugin {
  async echo(options: { value: string }): Promise<{ value: string }> {
    console.log('ECHO', options);
    return options;
  }
}
