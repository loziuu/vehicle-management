import { Injectable } from "@angular/core";
import { Http } from "@angular/http";
import { Insurance } from "../insurance";

import 'rxjs/add/operator/toPromise';

@Injectable()
export class InsuranceService {
  constructor(private http: Http) {}

  public addInsurance(vehicle_id, insurance): Promise<Insurance> {
    return this.http.post("http://localhost:8080/vehicles/" + vehicle_id + "/insurances", insurance)
      .toPromise()
      .then(response => response.json() as Insurance);
    }

  public deleteInsurance(vehicle_id, insurances_id): Promise<any> {
    return this.http.delete("http://localhost:8080/vehicles" + vehicle_id + "/insurances/" + insurances_id)
      .toPromise();
  }
}