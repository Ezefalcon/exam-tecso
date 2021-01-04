import { Component, OnInit } from '@angular/core';
import { MatTableDataSource } from '@angular/material/table';
import { SelectionModel } from '@angular/cdk/collections';
import { JuridicPerson } from '../../shared/models/juridic-person';
import { JuridicPersonService } from '../../services/juridic-person.service';
import { MatDialog } from '@angular/material/dialog';
import { JuridicPersonComponent } from '../juridic-person/juridic-person.component';
import { MatSnackBar } from '@angular/material/snack-bar';
import { PhysicalPersonService } from '../../services/physical-person.service';
import { PhysicalPerson } from '../../shared/models/physical-person';
import { PhysicalPersonComponent } from '../physical-person/physical-person.component';

@Component({
  selector: 'app-account-holder',
  templateUrl: './account-holder.component.html',
  styleUrls: ['./account-holder.component.scss']
})
export class AccountHolderComponent implements OnInit {

  constructor(private juridicPersonService: JuridicPersonService,
              private physicalPersonService: PhysicalPersonService,
              public dialog: MatDialog,
              private snackBar: MatSnackBar) { }
  juridicPeopleData;
  physicalPeopleData;
  displayedColumnsJuridic: string[] = ['rut', 'name', 'foundationYear', 'util'];
  displayedColumnsPhysical: string[] = ['rut', 'name', 'lastName', 'cc', 'util'];
  selection = new SelectionModel<JuridicPerson>(true, []);

  ngOnInit(): void {
    this.findData();
  }

  findData() {
    this.juridicPersonService.findAll().subscribe(jPersons => {
      this.juridicPeopleData = new MatTableDataSource<JuridicPerson>(jPersons);
    });
    this.physicalPersonService.findAll().subscribe(pPersons => {
      this.physicalPeopleData = new MatTableDataSource<PhysicalPerson>(pPersons);
    });
  }

  showEdit(element) {
    const dialogRef = this.dialog.open(JuridicPersonComponent, {
      width: '450px',
      data: element
    });

    dialogRef.afterClosed().subscribe(result => {
      this.findData();
    });
  }

  delete(element) {
    this.juridicPersonService.deleteById(element.id).subscribe(res =>{
      this.snackBar.open("Se elimino correctamente");
      this.findData();
    });
  }

  addJuridicPerson() {
    const dialogRef = this.dialog.open(JuridicPersonComponent, {
      width: '450px'
    });

    dialogRef.afterClosed().subscribe(result => {
      this.findData();
    });
  }

  addPhysicPerson() {
    const dialogRef = this.dialog.open(PhysicalPersonComponent, {
      width: '450px'
    });

    dialogRef.afterClosed().subscribe(result => {
      this.findData();
    });
  }

  showEditPhysical(element) {
    const dialogRef = this.dialog.open(PhysicalPersonComponent, {
      width: '450px',
      data: element
    });

    dialogRef.afterClosed().subscribe(result => {
      this.findData();
    });
  }

  deletePhysicalPerson(element) {
    this.physicalPersonService.deleteById(element.id).subscribe(res =>{
      this.snackBar.open("Se elimino correctamente");
      this.findData();
    });
  }
}
