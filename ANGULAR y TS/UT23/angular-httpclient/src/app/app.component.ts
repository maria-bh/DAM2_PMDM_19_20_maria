import { Component, OnInit } from '@angular/core';
import { PostService } from './services/post.service';
import { Post } from './interfaces/post';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css']
})
export class AppComponent implements OnInit {
  title = 'angular-httpclient';
  comments: Array<any>;
  id: number; // id del post a consultar
  post: Post;

  constructor(private _postService: PostService) { }

  ngOnInit() {
    this.getAllPosts();
    this.post = { userId: 10, id: 200, title: 'mi post', body: 'hola que tal' };

  }


  getAllPosts() {
    this._postService.getAllPosts()
      .subscribe(
        data => {
          console.log(data);
        },
        error => console.log(error as any));
  }

  getAllComentsPost(idPost) {
    this._postService.getAllComentsPost(idPost)
      .subscribe(
        comments => {
          this.comments = comments;
          if (!this.comments) {
            console.log('No hay comentarios');
          }
        },
        error => console.log(error as any)
      );
  }

  addPost(post: Post) {
    this._postService.addPost(post)
      .subscribe(postAdded => {
        console.log(postAdded);
        this.getAllPosts();
      }
      );

  }
}
