import java.util.Scanner;

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
    public void personajeAleatorio() {
        GeneradorDeNumero numero = new GeneradorDeNumero();
        GeneradorDeNombre nombre = new GeneradorDeNombre();
        setNombre(nombre.nombreAleatorio());
        setRaza(Raza.ORCO);
        setApodo(nombre.apodoAleatorio());
        int anio = numero.crearAnio();
        setNacimiento(numero.generarFechaNacimiento() + anio);
        setEdad(2023 - anio);
        setSalud(100);
        setVelocidad(numero.generarNumeroUnoDiez());
        setDestreza(numero.generarNumeroUnoCinco());
        setFuerza(numero.generarNumeroUnoDiez());
        setLvl(numero.generarNumeroUnoDiez());
        setArmadura(numero.generarNumeroUnoDiez());
    }

    @Override
    public void crearPersonaje() {
        GeneradorDeNumero numero = new GeneradorDeNumero();
        System.out.print("Nombre del Orco: ");
        setNombre(sc.nextLine());

        setRaza(Raza.ORCO);
        int anio = numero.crearAnio();
        setNacimiento(numero.generarFechaNacimiento() + anio);
        setEdad(2023 - anio);

        System.out.print("Apodo: ");
        setApodo(sc.nextLine());

        System.out.print("Salud: ");
        setSalud(sc.nextInt());

        System.out.print("Velocidad: ");
        setVelocidad(sc.nextInt());

        System.out.print("Destreza: ");
        setDestreza(sc.nextInt());

        System.out.print("Fuerza: ");
        setFuerza(sc.nextInt());

        System.out.print("Nivel: ");
        setLvl(sc.nextInt());

        System.out.print("Armandura:");
        setArmadura(sc.nextInt());
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
