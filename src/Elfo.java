import java.util.Scanner;
import java.util.logging.Logger;

public class Elfo extends Personaje{
    Scanner sc = new Scanner(System.in);

    @Override
    public int poderDeDisparo() { return getDestreza()*getFuerza()*getLvl(); }

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
        return (int) (((((valorDeAtaque()*efectividadDeDisparo())-personaje.poderDeDefensa())/500)*100)*1.05);
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
        Elfo elfo = new Elfo();
        GeneradorDeNumero numero = new GeneradorDeNumero();
        GeneradorDeNombre nombre = new GeneradorDeNombre();
        elfo.setNombre(nombre.nombreAleatorio());
        elfo.setRaza(Raza.ELFO);
        elfo.setApodo(nombre.apodoAleatorio());
        int anio = numero.crearAnio();
        elfo.setNacimiento(numero.generarFechaNacimiento() + anio);
        elfo.setEdad(2023 - anio);
        elfo.setSalud(100);
        elfo.setVelocidad(numero.generarNumeroUnoDiez());
        elfo.setDestreza(numero.generarNumeroUnoCinco());
        elfo.setFuerza(numero.generarNumeroUnoDiez());
        elfo.setLvl(numero.generarNumeroUnoDiez());
        elfo.setArmadura(numero.generarNumeroUnoDiez());

        return elfo;
    }

    @Override
    public Personaje crearPersonaje() {
        Elfo elfo = new Elfo();
        GeneradorDeNumero numero = new GeneradorDeNumero();
        System.out.print("Nombre del Elfo: ");
        elfo.setNombre(sc.nextLine());

        elfo.setRaza(Raza.ELFO);
        int anio = numero.crearAnio();
        elfo.setNacimiento(numero.generarFechaNacimiento() + anio);
        elfo.setEdad(2023 - anio);

        System.out.print("Apodo: ");
        setApodo(sc.nextLine());

        System.out.print("Salud: ");
        elfo.setSalud(sc.nextInt());

        System.out.print("Velocidad: ");
        elfo.setVelocidad(sc.nextInt());

        System.out.print("Destreza: ");
        elfo.setDestreza(sc.nextInt());

        System.out.print("Fuerza: ");
        elfo.setFuerza(sc.nextInt());

        System.out.print("Nivel: ");
        elfo.setLvl(sc.nextInt());

        System.out.print("Armandura:");
        elfo.setArmadura(sc.nextInt());

        return elfo;
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
