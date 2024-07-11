package principal;

import calculos.CalculoValor;
import com.google.gson.Gson;
import conexionApi.ConexionApi;
import excepcion.ErrorIngresoDivisa;
import modelos.Divisas;
import modelos.DivisasApi;
import java.io.IOException;
import java.util.InputMismatchException;
import java.util.Map;
import java.util.Scanner;


public class Principal {
    public static void main(String[] args) throws IOException, InterruptedException {
        Scanner lectura = new Scanner(System.in);
        ConexionApi conexionApi=new ConexionApi();
        System.out.println("*Bienvenidos al conversor de monedas de Americo*");

        while(true){
            try{
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

                System.out.println("*Ingrese la cantidad a convertir*");
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

            }catch (InputMismatchException e){
                System.out.println("Entrada incorrecta, porfavor ingrese una divisa correcta");

            }catch (NumberFormatException e){
                System.out.println("Ocurrio un error: ");
                System.out.println(e.getMessage());
            }catch (IllegalArgumentException e){
                System.out.println("Error en la URI, verifique la direccion");
            }catch (Exception e){//no conviene ser tan generico en la excepcion
                System.out.println(e.getMessage());
            };
        }
    }
}
