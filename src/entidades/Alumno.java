package entidades;

import java.util.*;

public class Alumno extends Entidad {

    private String nombre;
    private String apellido;
    private List<Materia> materiaList = new ArrayList<>();

    public Alumno() {
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public List<Materia> getMateriaList() {
        return materiaList;
    }

    public void setMateriaList(List<Materia> materiaList) {
        this.materiaList = materiaList;
    }

    public void addMateria(Materia materia) {
        materiaList.add(materia);
    }

}
