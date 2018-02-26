import { Component, OnInit } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { HttpHeaders } from '@angular/common/http';
import { RestError } from './error';
import { FleetService } from './fleet/fleet.service';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css']
})
export class AppComponent implements OnInit {
  constructor(private http: HttpClient) {}
  logged: boolean;
  username: string;
  password: string;
  error: RestError;
  user: any;

  ngOnInit(): void {
    this.isLogged();
    this.error = new RestError();
  }

  isLogged() {
    this.http.get("http://localhost:8080/auth").subscribe(
      result => {
        this.user = result;
        this.logged = true;
      }, err => {
        this.logged = false;
      }
    );
  }

  public logout() {
    this.http.post("http://localhost:8080/logout", {}, { responseType: "text" }).subscribe(() => window.location.reload());
  }

  
  public tryToLogIn() {
    var headers = new HttpHeaders( { 'content-type': 'application/x-www-form-urlencoded' } );
    var body = "username=" + this.username + "&password=" + this.password;
    this.http.post("http://localhost:8080/login", body, { headers: headers, responseType: "text" })
      .subscribe(result => {
        this.user = result;
        window.location.reload();
      }, err => {
        this.error.occured = true;
        this.error.message = err.error;
      });
  }

}
