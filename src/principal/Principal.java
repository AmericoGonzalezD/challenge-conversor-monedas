package principal;

import calculos.CalculoValor;
import com.google.gson.Gson;
import conexionApi.ConexionApi;
import modelos.Divisas;
import modelos.DivisasApi;
import java.io.IOException;
import java.util.Map;
import java.util.Scanner;


public class Principal {
    public static void main(String[] args) throws IOException, InterruptedException {
        Scanner lectura = new Scanner(System.in);
        ConexionApi conexionApi=new ConexionApi();
        System.out.println("*Bienvenidos al conversor de monedas de Americo*");

        while(true){
            System.out.println("Seleccione la divisa de la cantidad a convertir. En otro caso escriba 'salir'");
            var divisaSeleccionada = lectura.nextLine();

            if(divisaSeleccionada.equalsIgnoreCase("salir")){
                break;
            }
            var respuesta=conexionApi.respuetaDatos(divisaSeleccionada);
            String json = respuesta;

            Gson gson = new Gson();

            DivisasApi miDivisaOmdb = gson.fromJson(json, DivisasApi.class);
            Divisas miDivisa=new Divisas(miDivisaOmdb);

            System.out.println("*Estas son las divisas que se tienen disponibles en este conversor*");
            System.out.println(miDivisaOmdb.conversion_rates().keySet());
            System.out.println("*Ingrese la cantidad a contertir*");
            var cantidadConvertir = lectura.nextDouble();
            lectura.nextLine();
            System.out.println("*Ingrese a que divisa quiere convertir ese valor*");
            var divisaFinal = lectura.nextLine().toUpperCase();

            CalculoValor valorConversion = new CalculoValor();
            Map<String, Double> conversion = miDivisa.getValoresConversion();

            if (conversion.containsKey(divisaFinal)){
                System.out.println(valorConversion.getConversion(divisaFinal,conversion,cantidadConvertir));
            }else{
                System.out.println("La divisa no esta disponible");
            }

        }
    }
}
