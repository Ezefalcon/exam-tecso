import { Injectable } from '@angular/core';
import { ActivatedRouteSnapshot, CanActivate, Router, RouterStateSnapshot } from "@angular/router";
import { SessionService } from "../services/session.service";
import { MatSnackBar } from "@angular/material/snack-bar";

@Injectable({
  providedIn: 'root'
})
export class AuthGuardService implements CanActivate{

  constructor(private router: Router,
              private sessionService: SessionService,
              private _snackBar: MatSnackBar) { }

  canActivate(route: ActivatedRouteSnapshot, state: RouterStateSnapshot): boolean {
    // This won't allow the user to enter the login page if its already logged in
    if (this.sessionService.isLoggedIn()) {
      if(route.url[0] && route.url[0].path == 'login') {
        // this.router.navigate(['/']);
        this._snackBar.open("You are already logged in", "OK", {
          duration: 3000,
        });
      }
      return true;
    }
    // navigate to login page as user is not authenticated
    this.router.navigate(['/login']);
    return false;
  }
}
