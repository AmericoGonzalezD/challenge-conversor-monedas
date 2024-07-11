package calculos;

import java.util.Map;

public class CalculoValor {

    public double getConversion(String divisa, Map<String, Double>  conversion,Double cantidadConvertir){
        var valorFinal = conversion.get(divisa);
        var resultado=cantidadConvertir*valorFinal;
        return resultado;
    }
}
