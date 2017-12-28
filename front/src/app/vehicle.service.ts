import { Vehicle } from './models/vehicle';
import { Injectable } from '@angular/core';
import { Headers, Http } from '@angular/http';

import 'rxjs/add/operator/toPromise';
import { HttpClient } from "@angular/common/http";
import { Observable } from "rxjs/Observable";

@Injectable()
export class VehicleService {

  constructor(private http: HttpClient) {}

  private apiLocation = 'http://localhost:8080/api/v1/fleets/';

  public getVehicles(id: number): Observable<any> {
    var api = this.apiLocation + id + "/vehicles/";
    return this.http.get(api);
  }

  public getVehicle(fleetId: number, vehicleId: number): Observable<any> {
    var api = this.apiLocation + fleetId + "/vehicles/";
    return this.http.get(api + vehicleId);
  }

  public createVehicle(fleetId: number, vehicle: Vehicle): Observable<any> {
    var api = this.apiLocation + fleetId + '/vehicles/';
    return this.http.post(api, vehicle);
  }

  public deleteVehicle(id: number): Promise<any> {
    return this.http.delete(this.apiLocation + id).toPromise();
  }

  handleError(error) {
  }
}
