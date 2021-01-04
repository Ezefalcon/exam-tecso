import { TestBed } from '@angular/core/testing';

import { JuridicPersonService } from './juridic-person.service';

describe('JuridicPersonService', () => {
  let service: JuridicPersonService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(JuridicPersonService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
