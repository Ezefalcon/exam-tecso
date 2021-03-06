import { Component, EventEmitter, OnInit, Output } from '@angular/core';
import { SessionService } from "../../services/session.service";
import { Router } from "@angular/router";

@Component({
  selector: 'app-header',
  templateUrl: './header.component.html',
  styleUrls: ['./header.component.css']
})
export class HeaderComponent implements OnInit {

  constructor(private sessionService: SessionService,
              private router: Router) { }

  ngOnInit(): void {
  }

  @Output()
  readonly darkModeSwitched = new EventEmitter<boolean>();

  logout() {
    this.sessionService.logout();
  }

  goHome() {
    this.router.navigate(['/'])
  }
}
