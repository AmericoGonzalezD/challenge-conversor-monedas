package modelos;

import java.util.Map;

public record DivisasApi(String base_code, Map<String,Double> conversion_rates) {
}
