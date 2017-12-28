import { Component, OnInit } from '@angular/core';
import { environment } from '../../environments/environment';
import { HttpClient } from '@angular/common/http';
import { ResponseType } from '@angular/http/src/enums';

@Component({
  selector: 'app-users',
  templateUrl: './users.component.html',
  styleUrls: ['./users.component.css']
})
export class UsersComponent implements OnInit {

  constructor(private http: HttpClient) { }

  users: any;
  newUser: any;
  chosenUser: any;
  role: any;

  ngOnInit() {
    this.newUser = { login: "", password: ""};
    this.initUsers();
  }

  public initUsers() {
    this.http.get(environment.apiUrl + "/users")
      .subscribe(result => {
        this.newUser = { login: "", password: ""};
        this.users = result;
      });
  }

  public addNewUser() {
    this.http.post(environment.apiUrl + "/users", this.newUser)
      .subscribe(result => this.initUsers());
  }

  public changeEnabledState(user) {
    if (user.enabled) {
      this.deactivate(user.id)
      .subscribe(() => this.initUsers());
    } else {
      this.activate(user.id)
      .subscribe(() => this.initUsers());
    }
  }

  private activate(id) {
    return this.http.put(environment.apiUrl + "/users/" + id + "/activate", {}, { responseType: "text" });
  }

  private deactivate(id) {
    return this.http.put(environment.apiUrl + "/users/" + id + "/deactivate", {}, { responseType: "text" });
  }

  public changePassword() {
    var request = { password: this.newUser.password };
    this.http.put(environment.apiUrl + "/users/" + this.chosenUser.id + "/change-password", request, { responseType: "text" })
      .subscribe(result => { window.location.reload() })
  }

  public changeRole() {
    var request  = { role: this.role };
    this.http.put(environment.apiUrl + "/users/" + this.chosenUser.id + "/change-role", request, { responseType: "text" })
    .subscribe(result => { window.location.reload() })
  }
}
