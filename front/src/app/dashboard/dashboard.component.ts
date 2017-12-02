import { Component, OnInit } from '@angular/core';
import { FleetService } from '../fleet/fleet.service';
import { Fleet } from "../models/fleet";

@Component({
  selector: 'app-dashboard',
  templateUrl: './dashboard.component.html',
  styleUrls: ['./dashboard.component.css'],
  providers: [FleetService]
})
export class DashboardComponent implements OnInit {

  fleets: Array<any>;

  constructor(private service: FleetService) {
    this.service = service;
  }

  ngOnInit() {
    this.service.getFleets()
      .subscribe(result => this.fleets = result);
  }
}
