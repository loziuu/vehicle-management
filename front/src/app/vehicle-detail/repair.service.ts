import { Repair } from '../repair';
import { Injectable, Input } from '@angular/core';
import { Headers, Http } from '@angular/http';

import 'rxjs/add/operator/toPromise';

@Injectable()
export class RepairService {

  constructor(private http: Http) {}

  public addRepair(id, repair: Repair): Promise<any> {
    return this.http.post('http://localhost:8080/v1/vehicles/' + id + '/repairs', repair)
      .toPromise();
  }

  public deleteRepair(vehicleId, repairId): Promise<any> {
    return this.http.delete('http://localhost:8080/v1/vehicles/' + vehicleId + '/repairs/' + repairId)
    .toPromise();
  }

    handleError(error) {
    return Promise.reject(error.message || error);
  }
}
