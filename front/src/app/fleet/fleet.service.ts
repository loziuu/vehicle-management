import { Fleet } from '../models/fleet';
import { Injectable } from '@angular/core';
import { Headers, Http } from '@angular/http';

import 'rxjs/add/operator/toPromise';
import { Observable } from "rxjs/Observable";
import { HttpClient } from "@angular/common/http";
import { environment } from '../../environments/environment';

@Injectable()
export class FleetService {

  constructor(private http: HttpClient) {}
 
  private apiLocation = environment.apiUrl + "/fleets/";

  public getFleets(): Observable<any> {
    return this.http.get(this.apiLocation);
  }

  public getFleet(id: number): Observable<any> {
    return this.http.get(this.apiLocation + id);
  }

  public getFutureFleet(id: number, date: any): Observable<any> {
    return this.http.get(this.apiLocation + id + "/" + date);
  }

  public createFleet(fleet: Fleet): Observable<any> {
    return this.http.post(this.apiLocation, fleet);
  }

  public deleteFleet(id: number): Promise<any> {
    return this.http.delete(this.apiLocation + id).toPromise();
  }

  public deleteVehicle(fleetId: number, vehicleId: number): Observable<any> {
    return this.http.delete(this.apiLocation + fleetId + "/vehicles/" + vehicleId);
  }

  handleError(error) {
  }
}
