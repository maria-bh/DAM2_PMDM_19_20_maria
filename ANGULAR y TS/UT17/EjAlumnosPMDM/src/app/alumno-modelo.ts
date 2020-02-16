export class AlumnoModelo {
    constructor(public nombre: string,
                public apellidos: string,
                public nota: number) { }
    isAprobado(): boolean {
        return (this.nota >= 5);
    }
}
