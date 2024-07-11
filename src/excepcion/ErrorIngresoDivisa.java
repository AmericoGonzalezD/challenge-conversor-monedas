package excepcion;

public class ErrorIngresoDivisa extends RuntimeException {
    private String mensajeError;
    public ErrorIngresoDivisa(String mensajeError){
        this.mensajeError=mensajeError;
    }

    public String getMensajeError(){return this.mensajeError;}
}
