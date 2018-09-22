package main;

public class Decorador extends Experto {

    @Override
    public void Metodo() {
        FachadaInterna.getInstance().iniciarTransaccion();
        super.Metodo();
        FachadaInterna.getInstance().finalizarTransaccion();
    }

}
