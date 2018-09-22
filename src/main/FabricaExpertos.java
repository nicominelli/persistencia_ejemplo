package main;

public class FabricaExpertos {

    private static FabricaExpertos instancia;

    public FabricaExpertos() {
    }

    public static FabricaExpertos getInstancia() {
        if (instancia == null) {
            instancia = new FabricaExpertos();
        }
        return instancia;
    }

    public Object crearExperto(String casoDeUso) {
        switch (casoDeUso) {
            case "CU1":
                return new Decorador();
            default:
                return null;
        }
    }

}
