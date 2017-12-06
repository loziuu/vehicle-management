import { Insurance } from './insurance';
import { Journal } from "./journal";

export class Vehicle {
  id: number;
  model: String;
  manufacturer: String;
  productionYear: number;
  hasValidCheckout: boolean;
  hasActualInsurance: boolean;
  journal: Journal[]
}
