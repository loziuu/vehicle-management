import { Injectable } from "@angular/core";
import { Insurance } from "../models/insurance";
import { HttpClient } from "@angular/common/http";
import { Observable } from "rxjs/Observable";

@Injectable()
export class InsuranceService {
  constructor(private http: HttpClient) {}

  public postInsurance(fleet_id, vehicle_id, insurance): Promise<any> {
    console.log("http://localhost:8080/api/v1/fleets/" + fleet_id + "/vehicles/ " + vehicle_id + "/insurances");
    return this.http.post("http://localhost:8080/api/v1/fleets/" + fleet_id + "/vehicles/ " + vehicle_id + "/insurances", insurance, { withCredentials: true })
      .toPromise();
    }

  public deleteInsurance(fleet_id, vehicle_id, insurances_id): Observable<any> {
    return this.http.delete("http://localhost:8080/api/v1/fleets/" + fleet_id + "/vehicles/ " + vehicle_id + "/insurances/" + insurances_id, { withCredentials: true });
  }
}
