import { Injectable } from '@angular/core';
import { UserAuth } from "../shared/models/user-auth.model";
import { Router } from "@angular/router";

@Injectable({
  providedIn: 'root'
})
export class SessionService {

  user: UserAuth;

  readonly SESSION_KEY = "token";

  constructor(private router: Router) { }

  isLoggedIn(): boolean {
    return sessionStorage.getItem(this.SESSION_KEY) ? true : false;
  }

  addUserSession(res: any) {
    sessionStorage.setItem(this.SESSION_KEY, res.token);
  }

  getToken() {
    return sessionStorage.getItem(this.SESSION_KEY);
  }

  logout() {
    sessionStorage.removeItem(this.SESSION_KEY);
    this.router.navigate(["/login"])
  }
}
