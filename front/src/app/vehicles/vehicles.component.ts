import { Vehicle } from '../vehicle';
import { VehicleService } from '../vehicle.service';
import { Component, OnInit } from '@angular/core';

@Component({
  selector: 'app-vehicles',
  templateUrl: './vehicles.component.html',
  styleUrls: ['./vehicles.component.css'],
  providers: [VehicleService]
})
export class VehiclesComponent implements OnInit {

  constructor(private vehicleService: VehicleService) { }
  
  vehicles: Vehicle[];
  
  ngOnInit() {
    this.getVehicles();
  }
  
  public getVehicles() {
    this.vehicleService.getVehicles()
      .then(vehicles => this.vehicles = vehicles);
  }
  
  public delete(id) {
    this.vehicleService.deleteVehicle(id).then(() => this.getVehicles());
  }
}
