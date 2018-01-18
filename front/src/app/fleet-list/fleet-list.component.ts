import { Component, OnInit } from '@angular/core';
import { FleetService } from '../fleet/fleet.service';

@Component({
  selector: 'app-fleet-list',
  templateUrl: './fleet-list.component.html',
  styleUrls: ['./fleet-list.component.css'],
  providers: [FleetService]
})
export class FleetListComponent implements OnInit {

  fleets: Array<any>;
  
  constructor(private service: FleetService) {
    this.service = service;
  }

  ngOnInit() {
    this.service.getFleets()
      .subscribe(result => this.fleets = result);
  }
}
