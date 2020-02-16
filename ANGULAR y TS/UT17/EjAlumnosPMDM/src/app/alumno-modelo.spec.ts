import { AlumnoModelo } from './alumno-modelo';

describe('AlumnoModelo', () => {
  it('should create an instance', () => {
    expect(new AlumnoModelo('Pedro', 'aaa', 33)).toBeTruthy();
  });
});
