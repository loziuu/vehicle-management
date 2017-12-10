import { Injectable } from '@angular/core';
import { Repair } from '../models/repair';
import { Headers, Http } from '@angular/http';

import 'rxjs/add/operator/toPromise';

@Injectable()
export class CheckoutService {

  constructor(private http: Http) { }

  public postCheckout(fleetId, vehicleId, checkout): Promise<any> {
    return this.http.post("http://localhost:8080/api/v1/fleets/" + fleetId + "/vehicles/" + vehicleId + "/checkouts", checkout)
      .toPromise();
  }
}