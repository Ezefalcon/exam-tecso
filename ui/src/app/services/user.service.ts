import { Injectable } from '@angular/core';
import { HttpClient } from "@angular/common/http";
import { environment } from "../../environments/environment";
import { UserAuth } from "../shared/models/user-auth.model";
import { Observable } from "rxjs";
import { UserSignupModel } from "../shared/models/user-signup.model";

@Injectable({
  providedIn: 'root'
})
export class UserService {

  url: string = environment.apiUrl + '/users';

  constructor(private httpClient:HttpClient) { }

  login(userAuth: UserAuth): Observable<any> {
    return this.httpClient.post<any>(this.url + "/login", userAuth);
  }

  singUp(userSignup: UserSignupModel): Observable<any> {
    return this.httpClient.post<any>(this.url + "/register", userSignup);
  }

  checkUsernameAvailability(username): Observable<any> {
    return this.httpClient.get<any>(this.url + "/checkUsernameAvailability/" + username);
  }
}
