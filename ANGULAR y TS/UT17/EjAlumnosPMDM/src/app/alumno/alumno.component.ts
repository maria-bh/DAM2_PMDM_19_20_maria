import { Component, OnInit, Input, Output, EventEmitter} from '@angular/core';
import { AlumnoModelo } from '../alumno-modelo';



@Component({
  selector: 'app-alumno',
  templateUrl: './alumno.component.html',
  styleUrls: ['./alumno.component.css']
})
export class AlumnoComponent implements OnInit {
  @Input() alumno: AlumnoModelo;
  @Output() borrar = new EventEmitter<AlumnoModelo> ();

constructor() { }

ngOnInit() {
  }


  borrarAlumno(): void {
    this.borrar.emit(this.alumno);
  }

}
