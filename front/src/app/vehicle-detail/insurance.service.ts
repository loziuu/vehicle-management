import { Injectable } from "@angular/core";
import { Http } from "@angular/http";
import { Insurance } from "../models/insurance";

import 'rxjs/add/operator/toPromise';

@Injectable()
export class InsuranceService {
  constructor(private http: Http) {}

  public postInsurance(fleet_id, vehicle_id, insurance): Promise<any> {
    return this.http.post("http://localhost:8080/api/v1/fleets/" + fleet_id + "/vehicles/ " + vehicle_id + "/insurances", insurance, { withCredentials: true })
      .toPromise();
    }

  public deleteInsurance(fleet_id, vehicle_id, insurances_id): Promise<any> {
    return this.http.delete("http://localhost:8080/api/v1/fleets/" + fleet_id + "/vehicles/ " + vehicle_id + "/insurances", { withCredentials: true })
      .toPromise();
  }
}
