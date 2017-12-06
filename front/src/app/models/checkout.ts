export class Checkout {
  date: Date;
  expirationDate: Date;
  result: CheckoutResult;
}

enum CheckoutResult {
  POSITIVE,
  NEGATIVE
}
