import { Component, EventEmitter, OnInit, Output } from '@angular/core';
import { FormControl, FormGroup, ValidationErrors, ValidatorFn, Validators } from "@angular/forms";
import { UserSignupModel } from "../../shared/models/user-signup.model";
import { UserService } from "../../services/user.service";
import { MatSnackBar } from "@angular/material/snack-bar";
import { debounceTime, distinctUntilChanged, map, switchMap, tap } from "rxjs/operators";

@Component({
  selector: 'app-sing-up',
  templateUrl: './sing-up.component.html',
  styleUrls: ['./sing-up.component.scss']
})
export class SingUpComponent implements OnInit {

  form: FormGroup;
  @Output() toggleSingUp: EventEmitter<any> = new EventEmitter<any>();
  singUpInvalid: boolean = false;
  usernameLoader: boolean = false;
  readonly USERNAME_LENGTH: number = 2;
  showErrorUsername: boolean;
  showTickUsername: boolean;

  constructor(private userService: UserService,
              private snackBar: MatSnackBar) { }

  ngOnInit(): void {
    this.form = new FormGroup({
      username: new FormControl('', [Validators.required, Validators.minLength(this.USERNAME_LENGTH)]),
      email: new FormControl('', [Validators.required, Validators.email]),
      password: new FormControl('', [Validators.required, Validators.minLength(5)]),
      confirmPassword: new FormControl('', [Validators.required]),
    });
    this.checkUsernameAvailability();
  }


  checkUsernameAvailability() {
      this.form.get('username').valueChanges.pipe(
        debounceTime(500), // replaces your setTimeout
        distinctUntilChanged(), // wait until it's different than what we last checked
        map(username => {
          this.showErrorUsername = false;
          this.showTickUsername = false;
          this.usernameLoader = true;
          return this.userService.checkUsernameAvailability(username);
        }),
      ).subscribe(obs => {
        obs.subscribe(res => {
          if(res) {
            this.showErrorUsername = false;
            this.showTickUsername = true;
          } else {
            this.showErrorUsername = true;
            this.showTickUsername = false;
          }
          this.usernameLoader = false;
        })

      });
  }

  onSubmit() {
    const rawValue = this.form.getRawValue();
    const userSignupModel = UserSignupModel.fromForm(rawValue);
    this.userService.singUp(userSignupModel).subscribe(res => {
      this.snackBar.open("Your registration was successful", "Okay!");
      this.toggleSingUp.emit();
    }, err => {
      console.log(err);
      this.singUpInvalid = true;
    });
  }

  isShowTickUsername() {
    return this.showTickUsername && this.form.get('username').valid;
  }

  isShowErrorUsername() {
    return this.showErrorUsername && this.form.get('username').valid;
  }
}
