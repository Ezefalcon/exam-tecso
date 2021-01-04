import { Injectable } from '@angular/core';
import { JuridicPerson } from '../shared/models/juridic-person';
import { Observable } from 'rxjs';
import { environment } from '../../environments/environment';
import { HttpClient } from '@angular/common/http';
import { PhysicalPerson } from '../shared/models/physical-person';

@Injectable({
  providedIn: 'root'
})
export class PhysicalPersonService {

  url: string = environment.apiUrl + '/physical-person';

  constructor(private httpClient:HttpClient) { }

  save(physicalPerson: PhysicalPerson): Observable<any> {
    console.log(physicalPerson)
    return this.httpClient.post<any>(this.url, physicalPerson);
  }

  findAll(): Observable<PhysicalPerson[]> {
    return this.httpClient.get<PhysicalPerson[]>(this.url);
  }

  update(physicalPerson: PhysicalPerson, id: number): Observable<PhysicalPerson> {
    return this.httpClient.put<PhysicalPerson>(this.url+ "/" + id, physicalPerson);
  }

  deleteById(id: number) {
    return this.httpClient.delete<any>(this.url+ "/" + id);
  }
}
