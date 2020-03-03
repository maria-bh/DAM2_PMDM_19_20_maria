import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { Post } from '../interfaces/post';

@Injectable({
  providedIn: 'root'
})
export class PostService {
  public baseUrlPost = 'https://jsonplaceholder.typicode.com/posts';

  constructor(private _http: HttpClient) {
    console.log('Servicio post funcionando');
  }

  getAllPosts(): Observable<Post[]> {
    return this._http.get<Post[]>(this.baseUrlPost);
  }


  // obtener todos los comentarios del post cuyo id se pase
  getAllComentsPost(idPost: number): Observable<any[]> {
    return this._http.get<any[]>(`${this.baseUrlPost}/${idPost}/comments`);

  }

  addPost(post: Post): Observable<Post> {
    return this._http.post<Post>(this.baseUrlPost, post);
  }

}
