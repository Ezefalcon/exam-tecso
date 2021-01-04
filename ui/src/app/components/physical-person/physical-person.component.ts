import { Component, Inject, OnInit } from '@angular/core';
import { MAT_DIALOG_DATA, MatDialogRef } from '@angular/material/dialog';
import { MatSnackBar } from '@angular/material/snack-bar';
import { FormControl, FormGroup, Validators } from '@angular/forms';
import { PhysicalPersonService } from '../../services/physical-person.service';

@Component({
  selector: 'app-physical-person',
  templateUrl: './physical-person.component.html',
  styleUrls: ['./physical-person.component.scss']
})
export class PhysicalPersonComponent implements OnInit {

  constructor(
    public dialogRef: MatDialogRef<PhysicalPersonComponent>,
    @Inject(MAT_DIALOG_DATA) public data: any,
    private physicalPersonService: PhysicalPersonService,
    private snackBar: MatSnackBar) {}

  form: FormGroup;

  ngOnInit(): void {
    this.form = new FormGroup({
      rut: new FormControl(this.data ? this.data.rut : '', Validators.required),
      name: new FormControl(this.data ? this.data.name : '', Validators.required),
      lastName: new FormControl(this.data ? this.data.lastName : '', Validators.required),
      cc: new FormControl(this.data ? this.data.cc : '', Validators.required)
    });
  }

  close(): void {
    this.dialogRef.close();
  }

  update() {
    const formValue = this.form.value;
    this.physicalPersonService.update(formValue, this.data.id).subscribe(res => {
      this.snackBar.open("Se actualizo correctamente", "Ok", {duration: 2000});
    });
    this.close();
  }

  add() {
    const formValue = this.form.value;
    this.physicalPersonService.save(formValue).subscribe(res => {
      this.snackBar.open("Se agrego correctamente", "Ok", {duration: 2000});
    });
    this.close();
  }

}
