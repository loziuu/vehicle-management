import { Component, OnInit } from '@angular/core';
import { Fleet } from "../models/fleet";
import { FleetService } from "./fleet.service";
import { Router } from "@angular/router";

@Component({
  selector: 'app-fleet',
  templateUrl: './fleet.component.html',
  styleUrls: ['./fleet.component.css'],
  providers: [FleetService]
})
export class FleetComponent implements OnInit {

  fleet: Fleet;
  isSuccess: boolean;

  constructor(private service: FleetService) {
    this.fleet = new Fleet();
    this.isSuccess = false;
  }

  ngOnInit() {
  }

  private submit() {
    this.service.createFleet(this.fleet)
      .subscribe(() => {
        this.isSuccess = true;
        this.reset();
      });
  }

  private reset() {
    this.fleet = new Fleet();
  }
}
