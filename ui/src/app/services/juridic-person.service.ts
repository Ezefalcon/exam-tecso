import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { UserAuth } from '../shared/models/user-auth.model';
import { Observable } from 'rxjs';
import { environment } from '../../environments/environment';
import { JuridicPerson } from '../shared/models/juridic-person';

@Injectable({
  providedIn: 'root'
})
export class JuridicPersonService {

  url: string = environment.apiUrl + '/juridic-person';

  constructor(private httpClient:HttpClient) { }

  save(juridicPerson: JuridicPerson): Observable<any> {
    return this.httpClient.post<any>(this.url, juridicPerson);
  }

  findAll(): Observable<any[]> {
    return this.httpClient.get<any>(this.url);
  }

  update(juridicPerson: JuridicPerson, id: number): Observable<any> {
    return this.httpClient.put<any>(this.url+ "/" + id, juridicPerson);
  }

  deleteById(id: number) {
    return this.httpClient.delete<any>(this.url+ "/" + id);
  }
}
