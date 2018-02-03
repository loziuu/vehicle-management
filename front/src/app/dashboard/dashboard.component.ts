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
  data = [];
  weekData = [ { "name" : "Flota", "series": [ 
    { "name": "24-01-2018", "value": "50" } ] } ];
    date = Date.now();

  constructor(private service: FleetService) {
    this.service = service;
  }

  ngOnInit() {
    var today = new Date();
    console.log(today.getFullYear() + "-" + today.getMonth()+1 + "-" + today.getDate());
    this.service.getFleets()
      .subscribe(result => {
        this.data = [];
        this.fleets = result;
        this.fleets.forEach(fleet => {
          this.data.push(this.getChartColumn(fleet));
        console.log(this.data);
        })
      });
  }

  getChartColumn(fleet): any {
    return { "name": fleet.name, "value": fleet.vehiclesCount };
  }

  showGraph(fleet) {
    var series = [];
    
    this.service.getFutureFleet(fleet.id, this.getJsonDateWithOffset(0)).subscribe(result => {
      pushSeries(result);
      this.weekData = [ { "name": fleet.name,  series}];
      this.service.getFutureFleet(fleet.id, this.getJsonDateWithOffset(1)).subscribe(result => {
        pushSeries(result);
        this.weekData = [ { "name": fleet.name,  series}];
        this.service.getFutureFleet(fleet.id, this.getJsonDateWithOffset(2)).subscribe(result => {
          pushSeries(result);
          this.weekData = [ { "name": fleet.name,  series}];
          this.service.getFutureFleet(fleet.id, this.getJsonDateWithOffset(3)).subscribe(result => {
            pushSeries(result);
            this.weekData = [ { "name": fleet.name,  series}];
          });
        });
      });
    });
    
    function pushSeries(object) {
      series.push({"name": object.date, "value": object.status.status});
      console.log(series);
      console.log(object.date);
    }
  }

  getJsonDateWithOffset(days) {
    var today = new Date();
    return today.getFullYear() + "-" + today.getMonth()+1 + "-" + (today.getDate()+days);
  }
}
