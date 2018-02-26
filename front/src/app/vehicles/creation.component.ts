import { VehicleService } from '../vehicle.service';
import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, ParamMap, Router } from '@angular/router';
import { Location } from '@angular/common';
import { CreateVehicleRequest, Details } from './vehiclerequest';

@Component({
  selector: 'app-creation',
  template: `
    <div *ngIf="isSuccess" class="alert alert-success">
      Successfuly added vehicle!
    </div>

    <label for="details.model">Model</label>
    <input [(ngModel)]="vehicle.details.model" type="text" class="form-control" name="details.model"><br/>
    <label for="details.manufacturer">Manufacturer</label>
    <input [(ngModel)]="vehicle.details.manufacturer" type="text" class="form-control" name="details.manufacturer"><br/>
    <label for="details.productionYear">Production Year</label>
    <input [(ngModel)]="vehicle.details.productionYear" type="number" class="form-control" name="details.productionYear"><br/>
    <label for="registration">Registration</label>
    <input [(ngModel)]="vehicle.registration" type="text" class="form-control" name="registration"><br/>
    <button (click)="submit()" class="btn btn-primary">Add new vehicle</button>
    <button (click)="reset()" class="btn btn-warning">Reset</button>
  `,
  styles: [],
  providers: [VehicleService]
})
export class CreationComponent implements OnInit {

  constructor(private vehicleService: VehicleService,
              private route: ActivatedRoute,
              private location: Location,
              private router: Router) { }
  vehicle: CreateVehicleRequest;
  isSuccess: boolean;
  fleetId: number;

  ngOnInit() {
    this.route.paramMap.subscribe(params => this.fleetId =  + params.get("id"));
    this.initVehicle();
  }

  public submit() {
    this.vehicleService.createVehicle(this.fleetId, this.vehicle).subscribe(() => {
     this.isSuccess = true;
     this.initVehicle();
    });
  }

  public reset() {
    this.initVehicle();
  }

  private initVehicle() {
    if (this.isSuccess)
      this.router.navigate(['/fleet', this.fleetId]);
    this.vehicle = new CreateVehicleRequest();
    this.vehicle.details = new Details();
  }
}
