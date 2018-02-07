import { Injectable } from '@angular/core';
import { Repair } from '../models/repair';
import { Headers, Http } from '@angular/http';

import 'rxjs/add/operator/toPromise';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';

@Injectable()
export class CheckoutService {

  constructor(private http: HttpClient) { }

  public postCheckout(fleetId, vehicleId, checkout): Promise<any> {
    return this.http.post("http://localhost:8080/api/v1/fleets/" + fleetId + "/vehicles/" + vehicleId + "/checkouts", checkout, { withCredentials: true })
      .toPromise();
  }

  public deleteCheckout(fleetId, vehicleId, checkoutId): Observable<any> {
    return this.http.delete('http://localhost:8080/api/v1/fleets/' + fleetId + '/vehicles/' + vehicleId + '/checkouts/' + checkoutId, { withCredentials: true });
  }
}
