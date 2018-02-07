import { Vehicle } from '../models/vehicle';
import { VehicleService } from '../vehicle.service';
import { RepairService } from './repair.service';
import { Component, OnInit, Input } from '@angular/core';
import { ActivatedRoute, ParamMap } from '@angular/router';
import { Location } from '@angular/common';
import { Journal } from "../models/journal";

import 'rxjs/add/operator/switchMap';
import { InsuranceService } from './insurance.service';
import { CheckoutService } from './checkout.service';
import { FleetService } from '../fleet/fleet.service';
import { sample } from 'rxjs/operator/sample';

@Component({
  selector: 'app-vehicle-detail',
  templateUrl: './vehicle-detail.component.html',
  styleUrls: ['./vehicle-detail.component.css'],
  providers: [VehicleService, RepairService, InsuranceService, CheckoutService, FleetService]
})
export class VehicleDetailComponent implements OnInit {

  vehicle: any;
  fleetId: number;
  vehicleId: number;
  fleets: Array<any>;
  selectedFleet: any;

  constructor(
    private vehicleService: VehicleService,
    private route: ActivatedRoute,
    private repairService: RepairService,
    private insuranceService: InsuranceService,
    private checkoutService: CheckoutService,
    private fleetService: FleetService,
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
    this.getFleets();
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

  public removeInsurance(insuranceId) {
    this.insuranceService.deleteInsurance(this.fleetId, this.vehicleId, insuranceId)
    .subscribe(result => {
      this.initVehicle();
    })
  }

  public removeCheckout(insuranceId) {
    this.checkoutService.deleteCheckout(this.fleetId, this.vehicleId, insuranceId)
    .subscribe(result => {
      this.initVehicle();
    })
  }
  
  public removeRepair(repairId) {
    this.repairService.deleteRepair(this.fleetId, this.vehicleId, repairId)
    .subscribe(result => {
      this.initVehicle();
    })
  }

  public getFleets() {
    this.fleetService.getFleets().subscribe(result => this.fleets = result);
  }

  public moveVehicle() {
    console.log(this.selectedFleet);
    this.fleetService.moveVehicle(this.fleetId, this.vehicleId, this.selectedFleet)
      .subscribe();
  }
}
