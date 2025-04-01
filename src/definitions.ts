export interface BillingManagerPlugin {
  echo(options: { value: string }): Promise<{ value: string }>;
}
