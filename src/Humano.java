import java.util.Scanner;
import java.util.logging.Logger;

public class Humano extends Personaje{
    Scanner sc = new Scanner(System.in);

    @Override
    public int poderDeDisparo() {
       return getDestreza()*getFuerza()*getLvl();
    }

    @Override
    public float efectividadDeDisparo() {
        GeneradorDeNumero numero = new GeneradorDeNumero();
        return numero.generarNumeroPorcentual();
    }

    @Override
    public int valorDeAtaque() { return (int) (poderDeDisparo()*efectividadDeDisparo());}

    @Override
    public int poderDeDefensa() { return getVelocidad()*getArmadura(); }

    @Override
    public int danioProvocado(Personaje personaje) {
        return (int) ((((valorDeAtaque()*efectividadDeDisparo())-personaje.poderDeDefensa())/500)*100);
    }

    @Override
    public void restarSalud(int dmg) {
        if (dmg > 0) {
            setSalud(getSalud() - dmg);
        }
    }

    @Override
    public void recuperarSalud() {
        Logger logger = Logger.getLogger("WarCard");
        GeneradorDeNumero numero = new GeneradorDeNumero();
        int vida = numero.generarNumeroUnoDiez();
        setSalud(getSalud()+vida);
        logger.info(getNombre() + " " + getApodo() + " Recupero " + vida + " de vida por ganar la ronda!");
    }

    @Override
    public Personaje personajeAleatorio() {
        Humano humano = new Humano();
        GeneradorDeNumero numero = new GeneradorDeNumero();
        GeneradorDeNombre nombre = new GeneradorDeNombre();
        humano.setNombre(nombre.nombreAleatorio());
        humano.setRaza(Raza.HUMANO);
        humano.setApodo(nombre.apodoAleatorio());
        int anio = numero.crearAnio();
        humano.setNacimiento(numero.generarFechaNacimiento() + anio);
        humano.setEdad(2023 - anio);
        humano.setSalud(100);
        humano.setVelocidad(numero.generarNumeroUnoDiez());
        humano.setDestreza(numero.generarNumeroUnoCinco());
        humano.setFuerza(numero.generarNumeroUnoDiez());
        humano.setLvl(numero.generarNumeroUnoDiez());
        humano.setArmadura(numero.generarNumeroUnoDiez());

        return humano;
    }

    @Override
    public Personaje crearPersonaje() {
        Humano humano = new Humano();
        GeneradorDeNumero numero = new GeneradorDeNumero();
        System.out.print("Nombre del Humano: ");
        humano.setNombre(sc.nextLine());

        humano.setRaza(Raza.HUMANO);
        int anio = numero.crearAnio();
        humano.setNacimiento(numero.generarFechaNacimiento() + anio);
        humano.setEdad(2023 - anio);

        System.out.print("Apodo: ");
        humano.setApodo(sc.nextLine());

        System.out.print("Salud: ");
        humano.setSalud(sc.nextInt());

        System.out.print("Velocidad: ");
        humano.setVelocidad(sc.nextInt());

        System.out.print("Destreza: ");
        humano.setDestreza(sc.nextInt());

        System.out.print("Fuerza: ");
        humano.setFuerza(sc.nextInt());

        System.out.print("Nivel: ");
        humano.setLvl(sc.nextInt());

        System.out.print("Armandura:");
        humano.setArmadura(sc.nextInt());

        return humano;
    }

    @Override
    public String imprimirPersonajes() {
        return  "-----------------------------------------\n"+
                "Carta: " + getNombre() +
                "\nRaza: " + getRaza() +
                "\nApodo: " + getApodo() +
                "\nFecha de Nacimiento: " + getNacimiento() +
                "\nEdad: " + getEdad() +
                "\nSalud : " + getSalud() +
                "\nVelocidad : " + getVelocidad() +
                "\nDestreza : " + getDestreza() +
                "\nFuerza : " + getFuerza() +
                "\nNivel : " + getLvl() +
                "\nArmadura: " + getArmadura();
    }

    @Override
    public String mensajesInformativo(int dmg, Personaje personaje) {
        if (dmg > 0) {
            return getNombre() + " " + getApodo() + " ataca a " + personaje.getNombre() + " " + personaje.getApodo()
                    + " por " + dmg + ". " + personaje.getNombre() + " " + personaje.getApodo() + " queda con " + personaje.getSalud() + " de vida";
        }
        else {
            return "El Ataque de "+ getNombre() + " " + getApodo() + " no fue efectivo, " + personaje.getNombre() + " " + personaje.getApodo() + " sigue con "+ personaje.getSalud() + " de vida";
        }
    }
}
