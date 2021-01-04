import { Component, Inject, OnInit } from '@angular/core';
import { MAT_DIALOG_DATA, MatDialogRef } from '@angular/material/dialog';
import { Person } from '../../shared/models/person';
import { JuridicPersonService } from '../../services/juridic-person.service';
import { MatSnackBar } from '@angular/material/snack-bar';
import { FormControl, FormGroup, Validators } from '@angular/forms';

@Component({
  selector: 'app-account-holder-dialog-form',
  templateUrl: './juridic-person.component.html',
  styleUrls: ['./juridic-person.component.scss']
})
export class JuridicPersonComponent implements OnInit {

  constructor(
    public dialogRef: MatDialogRef<JuridicPersonComponent>,
    @Inject(MAT_DIALOG_DATA) public data: any,
    private juridicPersonService: JuridicPersonService,
    private snackBar: MatSnackBar) {}

  form: FormGroup;

  ngOnInit(): void {
    this.form = new FormGroup({
      rut: new FormControl(this.data ? this.data.rut : '', Validators.required),
      name: new FormControl(this.data ? this.data.name : '', Validators.required),
      foundationYear: new FormControl(this.data ? this.data.foundationYear : '', Validators.required)
    })
  }

  close(): void {
    this.dialogRef.close();
  }

  update() {
    const formValue = this.form.value;
    this.juridicPersonService.update(formValue, this.data.id).subscribe(res => {
      this.snackBar.open("Se actualizo correctamente", "Ok", {duration: 2000});
    });
    this.close();
  }

  add() {
    const formValue = this.form.value;
    this.juridicPersonService.save(formValue).subscribe(res => {
      this.snackBar.open("Se agrego correctamente", "Ok", {duration: 2000});
    });
    this.close();
  }
}
