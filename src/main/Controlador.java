package main;

public class Controlador {

    private Experto experto = (Experto) FabricaExpertos.getInstancia().crearExperto("CU1");

    public void Metodo() {
        experto.Metodo();
    }

}
