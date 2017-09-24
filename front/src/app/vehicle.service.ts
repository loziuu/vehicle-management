import { Vehicle } from './vehicle';
import { Injectable } from '@angular/core';
import { Headers, Http } from '@angular/http';

import 'rxjs/add/operator/toPromise';

@Injectable()
export class VehicleService {
  
  constructor(private http: Http) {}
  
  public getVehicles(): Promise<Vehicle[]> {
    return this.http.get('http://localhost:8080/vehicles/')
      .toPromise()
      .then(response => response.json() as Vehicle[])
      .catch(error => this.handleError(error));
  }
  
  public getVehicle(id: number): Promise<Vehicle> {
    return this.http.get('http://localhost:8080/vehicles/' + id)
      .toPromise()
      .then(response => response.json() as Vehicle)
      .catch(error => this.handleError(error));
  }
  
  public addVehicle(vehicle: Vehicle): Promise<Vehicle> {
    return this.http.post('http://localhost:8080/vehicles', vehicle)
      .toPromise()
      .then(response => response.json() as Vehicle)
      .catch(error => this.handleError(error));
  }
  
  public deleteVehicle(id: number): Promise<any> {
    return this.http.delete('http://localhost:8080/vehicles/' + id).toPromise();
  }
   
  handleError(error) {
    return Promise.reject(error.message || error);
  }
}
