import { Fleet } from '../models/fleet';
import { Injectable } from '@angular/core';
import { Headers, Http } from '@angular/http';

import 'rxjs/add/operator/toPromise';
import { Observable } from "rxjs/Observable";
import { HttpClient } from "@angular/common/http";

@Injectable()
export class FleetService {

  constructor(private http: HttpClient) {}
 
  private apiLocation = 'http://localhost:8080/api/v1/fleets/';

  public getFleets(): Observable<any> {
    return this.http.get(this.apiLocation);
  }

  public getFleet(id: number): Observable<any> {
    return this.http.get(this.apiLocation + id);
  }

  public createFleet(fleet: Fleet): Promise<any> {
    return this.http.post(this.apiLocation, fleet)
      .toPromise();
  }

  public deleteFleet(id: number): Promise<any> {
    return this.http.delete(this.apiLocation + id).toPromise();
  }

  handleError(error) {
  }
}
