import { Component, OnInit } from '@angular/core';
import { AlumnoModelo } from '../alumno-modelo';

@Component({
  selector: 'app-lista-alumnos',
  templateUrl: './lista-alumnos.component.html',
  styleUrls: ['./lista-alumnos.component.css']
})
export class ListaAlumnosComponent implements OnInit {
  tituloComponente: string;
  alumnos: Array<AlumnoModelo>;

  constructor() {
    this.tituloComponente = 'ALUMNOS PDMD';
    this.alumnos = [
      new AlumnoModelo('Lucas', 'García Martínez', 8),
      new AlumnoModelo('María', 'Vázquez', 9.2),
      new AlumnoModelo('Efren', 'González', 3)
    ];
  }

  ngOnInit() {
  }


  borrarAlumno(alumno: AlumnoModelo): void {
    let i = this.alumnos.findIndex(a => a.nombre === alumno.nombre);
    if (i !== -1) {
      this.alumnos.splice(i, 1);
    }
  }

}
