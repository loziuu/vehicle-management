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
  date = Date.now();
  weekData = [ { "name" : "Flota", "series": [ 
    { "name": this.date.toString(), "value": "0" } ] } ];  
  i = 3;
  selectedFleet: any;

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
        if (this.fleets.length > 0)
          this.showGraph(this.fleets[0]);
        })
      });
  }

  getChartColumn(fleet): any {
    return { "name": fleet.name, "value": fleet.vehiclesCount };
  }

  showGraph(fleet) {
    this.selectedFleet = fleet;
    var series = [];
    this.data = [this.getChartColumn(fleet)];
    var i = 0;
    do {
      this.service.getFutureFleet(fleet.id, this.getJsonDateWithOffset(i)).toPromise().then(result => {
        series.push({"name": result.date, "value": result.status.result})
        this.weekData = [ { "name": fleet.name,  series} ];
        if (i == this.i) {
          series = series.sort(function(a,b){
            if (a.name > b.name) return 1;
            if (a.name < b.name) return -1;
            return 0;
          });
          console.log(i);
          console.log(series);
        }
      });
      i = i + 1;
    } while(i < this.i);
  }

  getJsonDateWithOffset(days) {
    var today = new Date();
    today.setDate(today.getDate() + days);
    return today.toJSON().substring(0, 10);
  }

  public changeDays(days) {
    this.i = days;
    this.showGraph(this.selectedFleet);
  }
}