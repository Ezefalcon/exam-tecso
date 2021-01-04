import { ComponentFixture, TestBed } from '@angular/core/testing';

import { JuridicPersonComponent } from './juridic-person.component';

describe('AccountHolderDialogFormComponent', () => {
  let component: JuridicPersonComponent;
  let fixture: ComponentFixture<JuridicPersonComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ JuridicPersonComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(JuridicPersonComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
