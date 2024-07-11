package modelos;

import java.util.Map;

public class Divisas {
    private String nombreDivisa;
    private Map<String, Double> valoresConversion;

    public Divisas(DivisasApi miDivisaOmdb) {
        this.nombreDivisa = miDivisaOmdb.base_code();
        this.valoresConversion = miDivisaOmdb.conversion_rates();
    }

    public Map<String, Double> getValoresConversion() {
        return valoresConversion;
    }

    public String getNombreDivisa() {
        return nombreDivisa;
    }

    @Override
    public String toString() {
        return "Divisas disponibles" +
                "valoresConversion=" + valoresConversion +
                '}';
    }
}
