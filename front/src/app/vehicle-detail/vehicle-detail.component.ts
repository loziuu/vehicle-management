import { Vehicle } from '../models/vehicle';
import { VehicleService } from '../vehicle.service';
import { RepairService } from './repair.service';
import { Component, OnInit, Input } from '@angular/core';
import { ActivatedRoute, ParamMap } from '@angular/router';
import { Location } from '@angular/common';
import { Journal } from "../models/journal";

import 'rxjs/add/operator/switchMap';

@Component({
  selector: 'app-vehicle-detail',
  templateUrl: './vehicle-detail.component.html',
  styleUrls: ['./vehicle-detail.component.css'],
  providers: [VehicleService, RepairService]
})
export class VehicleDetailComponent implements OnInit {

  vehicle: Vehicle;
  fleetId: number;
  vehicleId: number;

  constructor(
    private vehicleService: VehicleService,
    private route: ActivatedRoute,
    private repairService: RepairService,
    private location: Location
  ) {}

  ngOnInit(): void {
    this.route.paramMap.subscribe(params => {
        this.fleetId = +params.get("fleetId");
        this.vehicleId = +params.get("vehicleId");
    });
    this.initVehicle();
  }

  public initVehicle() {
    this.vehicleService.getVehicle(this.fleetId, this.vehicleId)
      .subscribe(result => {
        this.vehicle = result;
        console.log(this.vehicle);
        console.log(this.vehicle.journal);
      });
  }
  
  public goBack() {
    this.location.back();
  }
}
