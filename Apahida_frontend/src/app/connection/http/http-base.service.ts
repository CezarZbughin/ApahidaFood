import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';


@Injectable({providedIn : 'root'})
export abstract class HttpBaseService {

  constructor(protected http: HttpClient) { }

  protected abstract getBasePath(): string;

  public get<T>(entityPath: string): Observable<T> {
    const path = `${this.getBasePath()}${entityPath}`;
    console.log(path)
    return this.http.get<T>(path,{headers: new HttpHeaders({
      'Authorization' : `${sessionStorage.getItem("auth")}`,
      'Access-Control-Allow-Origin' : 'http://localhost:8090'
      })});
  }

  public post<T, U>(entityPath: string, body: U): Observable<T> {
    const path = `${this.getBasePath()}${entityPath}`;
    console.log(path);
    return this.http.post<T>(path, body,{headers: new HttpHeaders({
      'Authorization' : `${sessionStorage.getItem("auth")}`
      })});
  }

  public put<T, U>(entityPath: string, body: U): Observable<T> {
    const path = `${this.getBasePath()}${entityPath}`;
    return this.http.put<T>(path, body,{headers: new HttpHeaders({
      'Authorization' : `${sessionStorage.getItem("auth")}`
      })});
  }

  public delete<T>(entityPath: string): Observable<T> {
    const path = `${this.getBasePath()}${entityPath}`;
    return this.http.delete<T>(path,{headers: new HttpHeaders({
      'Authorization' : `${sessionStorage.getItem("auth")}`
      })});
  }

}
