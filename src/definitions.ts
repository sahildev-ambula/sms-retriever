export interface smsRetrieverPlugin {
  echo(options: { value: string }): Promise<{ value: string }>;
}
