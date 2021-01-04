import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { LoginComponent } from "./components/login/login.component";
import { AuthGuardService } from "./interceptors/auth-guard.service";
import { HomeComponent } from './components/home/home.component';
import { AccountHolderComponent } from './components/account-holder/account-holder.component';

const routes: Routes = [
  { path: '', component: HomeComponent , canActivate: [AuthGuardService]},
  { path: 'account-holder', component: AccountHolderComponent , canActivate: [AuthGuardService]},
  { path: 'login', component: LoginComponent }
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
