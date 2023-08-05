import java.util.Random;

public class GeneradorDeNumero {
    int pasado = 1723;
    int actual = 2023;

    Random random = new Random();

    public int generarNumeroUnoDos(){
        int numero = (1+random.nextInt( (2+1) - 1));
        return numero;
    }

    public int generarNumeroUnoTres(){
        int numero = (1+random.nextInt( (3+1) - 1));
        return numero;
    }

    public int generarNumeroUnoCinco(){
        int numero = (1+random.nextInt( (5+1) - 1));
        return numero;
    }

    public int generarNumeroUnoDiez(){
        int numero = (1+random.nextInt( (10+1) - 1));
        return numero;
    }

    public int generarNumeroUnoDoce(){
        int numero = (1+random.nextInt( (12+1) - 1));
        return numero;
    }

    public int generarNumeroUnoCien(){
        int numero = (1+random.nextInt( (100+1) - 1));
        return numero;
    }

    public float generarNumeroPorcentual(){
        float numero = (1+random.nextFloat(2+1)-1);
        return numero;
    }

    public int crearAnio(){
        int anio = random.nextInt(actual - pasado) + pasado;
        return anio;
    }

    public String generarFechaNacimiento(){
        int dia = (1+random.nextInt( (30+1) - 1));
        int mes = (1+random.nextInt( (12+1) - 1));
        return dia + "/" + mes + "/";
    }
}
