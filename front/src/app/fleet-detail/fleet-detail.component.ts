import { Component, OnInit } from '@angular/core';
import { FleetService } from "../fleet/fleet.service";
import { VehicleService } from "../vehicle.service";
import { Vehicle } from "../models/vehicle";
import { Fleet } from "../models/fleet";
import { Location } from '@angular/common';
import { ActivatedRoute, ParamMap } from '@angular/router';

import 'rxjs/add/operator/switchMap';

@Component({
  selector: 'app-fleet-detail',
  templateUrl: './fleet-detail.component.html',
  styleUrls: ['./fleet-detail.component.css'],
  providers: [FleetService, VehicleService]
})
export class FleetDetailComponent implements OnInit {

  constructor(private fleetService: FleetService,
    private route: ActivatedRoute,
    private location: Location) { }
  
  fleet: any;
  data: Array<any>;
  vehicles: Vehicle[];
  field: string = "manufacturer";
  phrase: any;
  date: any;
  
  ngOnInit() {
    this.initFleet();
  }

  public getFutureFleet() {
        this.route.paramMap
        .switchMap((params: ParamMap) => this.fleetService.getFutureFleet(+params.get('id'), this.date))
        .subscribe(result => {
          this.fleet = result;
          this.vehicles = result.vehicles;
          this.data = this.vehicles;
          if (this.fleet.status.status > 92) 
            this.fleet.status.status = 100.0
        });
  }

  public initFleet() {
      this.route.paramMap
      .switchMap((params: ParamMap) => this.fleetService.getFleet(+params.get('id')))
      .subscribe(result => {
        this.fleet = result;
        this.vehicles = result.vehicles;
        this.data = this.vehicles;
        if (this.fleet.status.status > 92) 
          this.fleet.status.status = 100.0
      });
  }

  resetFilter() {
    this.vehicles = this.data;
  }

  filterNoCheckout() {
    this.vehicles = this.data.filter(v => !v.hasValidCheckout);
  }

  filterNoInsurance() {
    this.vehicles = this.data.filter(v => !v.hasActualInsurance);
  }

  search() {
    this.vehicles = this.data.filter(v => v[this.field].toString().includes(this.phrase));
  }
}
