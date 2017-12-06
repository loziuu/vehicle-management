import { Repair } from "./repair";
import { Insurance } from "./insurance";
import { Checkout } from "./checkout";

export class Journal {  
  repairs: Repair[];
  insurances: Insurance[];
  checkouts: Checkout[];
}
