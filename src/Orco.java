import java.util.Scanner;
import java.util.logging.Logger;

public class Orco extends Personaje{
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
    public int poderDeDefensa() {

        return 0;
    }

    @Override
    public int danioProvocado(Personaje personaje) {
        return (int) (((((valorDeAtaque()*efectividadDeDisparo())-personaje.poderDeDefensa())/500)*100)*1.1);
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
        Orco orco =  new Orco();
        GeneradorDeNumero numero = new GeneradorDeNumero();
        GeneradorDeNombre nombre = new GeneradorDeNombre();
        orco.setNombre(nombre.nombreAleatorio());
        orco.setRaza(Raza.ORCO);
        orco.setApodo(nombre.apodoAleatorio());
        int anio = numero.crearAnio();
        orco.setNacimiento(numero.generarFechaNacimiento() + anio);
        orco.setEdad(2023 - anio);
        orco.setSalud(100);
        orco.setVelocidad(numero.generarNumeroUnoDiez());
        orco.setDestreza(numero.generarNumeroUnoCinco());
        orco.setFuerza(numero.generarNumeroUnoDiez());
        orco.setLvl(numero.generarNumeroUnoDiez());
        orco.setArmadura(numero.generarNumeroUnoDiez());

        return orco;
    }

    @Override
    public Personaje crearPersonaje() {
        Orco orco = new Orco();
        GeneradorDeNumero numero = new GeneradorDeNumero();
        System.out.print("Nombre del Orco: ");
        orco.setNombre(sc.nextLine());

        orco.setRaza(Raza.ORCO);
        int anio = numero.crearAnio();
        setNacimiento(numero.generarFechaNacimiento() + anio);
        orco.setEdad(2023 - anio);

        System.out.print("Apodo: ");
        orco.setApodo(sc.nextLine());

        System.out.print("Salud: ");
        orco.setSalud(sc.nextInt());

        System.out.print("Velocidad: ");
        setVelocidad(sc.nextInt());

        System.out.print("Destreza: ");
        orco.setDestreza(sc.nextInt());

        System.out.print("Fuerza: ");
        orco.setFuerza(sc.nextInt());

        System.out.print("Nivel: ");
        orco.setLvl(sc.nextInt());

        System.out.print("Armandura:");
        orco.setArmadura(sc.nextInt());

        return orco;
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
