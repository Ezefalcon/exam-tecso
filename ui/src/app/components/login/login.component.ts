import { Component, OnInit } from '@angular/core';
import { FormControl, FormGroup, Validators } from "@angular/forms";
import { UserService } from "../../services/user.service";
import { SessionService } from "../../services/session.service";
import { Router } from "@angular/router";
import { UserAuth } from '../../shared/models/user-auth.model';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.scss']
})
export class LoginComponent implements OnInit {
  loginInvalid: boolean = false;
  form: any;
  showSingUp: boolean = false;
  animate: boolean = false;

  constructor(private userService: UserService,
              private sessionService: SessionService,
              private router: Router) { }

  ngOnInit(): void {
    this.form = new FormGroup({
        username: new FormControl('', Validators.required),
        password: new FormControl('', Validators.required),
      });
  }

  onSubmit() {
    const rawValue = this.form.getRawValue();
    const userAuth = new UserAuth(rawValue);
    this.userService.login(userAuth).subscribe(res => {
      if(res.token) {
        this.sessionService.addUserSession(res);
        this.router.navigate(["/"]);
      } else {
        this.loginInvalid = true;
      }
    }, err => {
      console.log(err);
      this.loginInvalid = true;
    });
  }

  toggleSingUp() {
    this.animate = !this.animate;
    this.showSingUp = !this.showSingUp;
  }
}
