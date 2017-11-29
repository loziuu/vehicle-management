import { Vehicle } from './vehicle';
import { Injectable } from '@angular/core';
import { Headers, Http } from '@angular/http';

import 'rxjs/add/operator/toPromise';

@Injectable()
export class VehicleService {

  constructor(private http: Http) {}

  private apiLocation = 'http://localhost:8080/api/v1/fleets/1/vehicles/';

  public getVehicles(): Promise<Vehicle[]> {
    return this.http.get(this.apiLocation)
      .toPromise()
      .then(response => response.json() as Vehicle[])
      .catch(error => this.handleError(error));
  }

  public getVehicle(id: number): Promise<Vehicle> {
    return this.http.get(this.apiLocation + id)
      .toPromise()
      .then(response => response.json() as Vehicle)
      .catch(error => this.handleError(error));
  }

  public createVehicle(vehicle: Vehicle): Promise<any> {
    return this.http.post(this.apiLocation, vehicle)
      .toPromise();
  }

  public deleteVehicle(id: number): Promise<any> {
    return this.http.delete(this.apiLocation + id).toPromise();
  }

  handleError(error) {
  }
}
