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
    private vehicleService: VehicleService,
    private location: Location) { }
  
  fleet: Fleet;
  vehicles: Vehicle[];
  
  ngOnInit() {
    this.initFleet();
    this.initVehicles();
  }

  private initFleet() {
      this.route.paramMap
      .switchMap((params: ParamMap) => this.fleetService.getFleet(+params.get('id')))
      .subscribe(result => this.fleet = result);
  }

  private initVehicles() {
    this.route.paramMap
      .switchMap((params: ParamMap) => this.vehicleService.getVehicles(+params.get('id')))
      .subscribe(result => this.vehicles = result);
  }
}
