import { Repair } from '../models/repair';
import { Injectable, Input } from '@angular/core';
import { Headers, Http } from '@angular/http';

import 'rxjs/add/operator/toPromise';
import { HttpClient } from '@angular/common/http';

@Injectable()
export class RepairService {

  constructor(private http: HttpClient) {}

  public postRepair(fleetId, vehicleId, repair: Repair): Promise<any> {
    return this.http.post('http://localhost:8080/api/v1/fleets/' + fleetId + '/vehicles/' + vehicleId + '/repairs', repair, { withCredentials: true })
      .toPromise();
  }

  public removeRepair(vehicleId, repairId): Promise<any> {
    return this.http.delete('http://localhost:8080/v1/vehicles/' + vehicleId + '/repairs/' + repairId, { withCredentials: true })
    .toPromise();
  }

    handleError(error) {
    return Promise.reject(error.message || error);
  }
}
