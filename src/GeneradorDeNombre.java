import java.util.ArrayList;
import java.util.List;

public class GeneradorDeNombre {
    ArrayList<String> ListaNombres = new ArrayList<>(List.of("Thor","Jonhy", "Karen", "Ragnar", "Arthas","Fiona", "Lux","Lich", "Juggernaut", "Aslan","Voldemort","Miranda"));
    ArrayList<String> ListaApodos = new ArrayList<>(List.of("Guerrero","Vikingo","Matador","La Pulga","Conquistador","El Magias","La Maquina", "La MoliMoli","El Innombrable","Support","Gladiador","El Pichichi"));

    public String nombreAleatorio(){
        GeneradorDeNumero numero = new GeneradorDeNumero();
        return ListaNombres.get(numero.generarNumeroUnoDoce()-1);
    }
    public String apodoAleatorio(){
        GeneradorDeNumero numero = new GeneradorDeNumero();
        return ListaApodos.get(numero.generarNumeroUnoDoce()-1);
    }
}
