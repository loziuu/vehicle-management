import { Insurance } from './insurance';
import { Repair } from './repair';

export class Vehicle {
    content: content;
}

class content {
  id: number;
  model: String;
  manufacturer: String;
  productionYear: number;
  repairs: Repair[];
  insurances: Insurance[];
}
