import java.io.IOException;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;
import java.util.logging.FileHandler;
import java.util.logging.Logger;
import java.util.logging.SimpleFormatter;

public class Main {
    public static void main(String[] args) throws IOException {
        Scanner sc = new Scanner(System.in);
        Logger logger = Logger.getLogger("WarCard");
        FileHandler fh = new FileHandler("WarCard.log");

        SimpleFormatter formatter = new SimpleFormatter();
        fh.setFormatter(formatter);
        logger.addHandler(fh);

        ArrayList<Personaje> JugadorUnoCartas = new ArrayList<>();
        ArrayList<Personaje> JugadorDosCartas = new ArrayList<>();

        int menu = 0;
        boolean validador;
        Elfo elfo = new Elfo();
        Orco orco = new Orco();
        Humano humano = new Humano();

        Elfo elfo2 = new Elfo();
        Orco orco2 = new Orco();
        Humano humano2 = new Humano();
        do {
            int ronda = 1;
            do {
                validador = false;
                try {
                    System.out.println("-------------[ Warcards Game ]-------------");
                    System.out.println("1) Nueva partida");
                    System.out.println("2) Partida personalizada");
                    System.out.println("3) Historial de partidas");
                    System.out.println("4) Borrar historial de partidas");
                    System.out.println("0) Salir");
                    System.out.print( "Opcion: ");
                    menu = sc.nextInt();
                } catch (InputMismatchException e) {
                    validador = true;
                    sc.nextLine();
                    System.out.println("Opcion invalida!");
                }
            } while (validador);
            try {
                switch (menu) {
                    case 1: // Iniciar partida y que el sistema genere los 6 personajes aleatorios
                        JugadorUnoCartas.clear();
                        JugadorDosCartas.clear();
                        logger.info("Preparativos para la guerra!");
                        System.out.println("Preparativos para la guerra!");
                        // Creo cartas random para jugador UNO
                        elfo.personajeAleatorio();
                        orco.personajeAleatorio();
                        humano.personajeAleatorio();
                        JugadorUnoCartas.add(elfo);
                        JugadorUnoCartas.add(orco);
                        JugadorUnoCartas.add(humano);

                        // Creo cartas random para jugador DOS
                        elfo2.personajeAleatorio();
                        orco2.personajeAleatorio();
                        humano2.personajeAleatorio();
                        JugadorDosCartas.add(elfo2);
                        JugadorDosCartas.add(orco2);
                        JugadorDosCartas.add(humano2);

                        System.out.println("Cartas del Jugador UNO:");
                        mostrarCartasJugador(JugadorUnoCartas);
                        enterParaContinuar();

                        System.out.println("Cartas del Jugador DOS:");
                        mostrarCartasJugador(JugadorDosCartas);
                        enterParaContinuar();

                        System.out.println("Comienza la batalla por el trono de hierro!");
                        do {
                            System.out.println("El Jugador Uno tiene: " + JugadorUnoCartas.size());
                            System.out.println("El Jugador Dos tiene: " + JugadorDosCartas.size());
                            System.out.println("----------- [ RONDA " + ronda + " ] -----------");
                            ronda = ronda + 1;
                            tiposDeRondas(JugadorUnoCartas, JugadorDosCartas);
                            enterParaContinuar();
                        } while (condicionalSwitch(JugadorUnoCartas, JugadorDosCartas) != 0);
                        ganador(JugadorUnoCartas, JugadorDosCartas);
                        enterParaContinuar();
                        break;
                            /* VERCION VIEJA QUE NO ANDABA
                            if (JugadorUnoCartas.size() == JugadorDosCartas.size()) {
                                System.out.println("Tienen la misma cantidad de cartas, La ronda se sorteara!");
                                int numero = random.generarNumeroUnoCien();
                                if (numero > 50){
                                    System.out.println("Arranca el jugador UNO");
                                    System.out.println("-------------------------------------------------------------");
                                    ronda(JugadorUnoCartas, JugadorDosCartas);
                                }
                                else{
                                    System.out.println("Arranca el jugador DOS");
                                    System.out.println("-------------------------------------------------------------");
                                    ronda(JugadorDosCartas, JugadorUnoCartas);
                                }
                            }
                            if (JugadorUnoCartas.size() > JugadorDosCartas.size()) {
                                System.out.println("Como el jugador uno tiene mas cartas que el jugador DOS");
                                System.out.println("Arranca el jugador DOS");
                                System.out.println("-------------------------------------------------------------");
                                ronda(JugadorDosCartas, JugadorUnoCartas);
                                break;
                            }
                            if (JugadorDosCartas.size() > JugadorUnoCartas.size()){
                                System.out.println("Como el jugador DOS tiene mas cartas que el jugador UNO");
                                System.out.println("Arranca el jugador UNO");
                                System.out.println("-------------------------------------------------------------");
                                ronda(JugadorUnoCartas, JugadorDosCartas);
                            } */

                    case 2: // Iniciar partida pero los personajes los ingresa el usuario
                        JugadorUnoCartas.clear();
                        JugadorDosCartas.clear();
                        System.out.println("Partida custom, Eliga sus personajes");
                        do {
                            System.out.println("Jugador UNO le toca crear sus tres cartas");
                            System.out.println("Numero de cartas del jugador UNO: " + JugadorUnoCartas.size());
                            System.out.println("Raza de carta a elegir");
                            System.out.println("1)Elfo 2)Orco 3)Humano");
                            System.out.print("Eleccion: ");
                            menu = sc.nextInt();
                            switch (menu) {
                                case 1 -> { // Elfo
                                    elfo.crearPersonaje();
                                    JugadorUnoCartas.add(elfo);
                                }
                                case 2 -> { // Orco
                                    orco.crearPersonaje();
                                    JugadorUnoCartas.add(orco);
                                }
                                case 3 -> { // Humano
                                    humano.crearPersonaje();
                                    JugadorUnoCartas.add(humano);
                                }
                            }
                        } while (JugadorUnoCartas.size() < 3);
                        do {
                            System.out.println("Jugador DOS le toca crear sus tres cartas");
                            System.out.println("Numero de cartas del jugador DOS: " + JugadorDosCartas.size());
                            System.out.println("Raza de carta a elegir");
                            System.out.println("1)Elfo 2)Orco 3)Humano");
                            System.out.print("Eleccion: ");
                            menu = sc.nextInt();
                            switch (menu) {
                                case 1 -> { // Elfo
                                    elfo.crearPersonaje();
                                    JugadorDosCartas.add(elfo);
                                }
                                case 2 -> { // Orco
                                    orco.crearPersonaje();
                                    JugadorDosCartas.add(orco);
                                }
                                case 3 -> { // Humano
                                    humano.crearPersonaje();
                                    JugadorDosCartas.add(humano);
                                }
                            }
                        } while (JugadorDosCartas.size() < 3);
                        enterParaContinuar();
                        mostrarCartasJugador(JugadorUnoCartas);
                        enterParaContinuar();
                        mostrarCartasJugador(JugadorDosCartas);
                        enterParaContinuar();
                        System.out.println("Comienza la batalla por el trono de hierro!");
                        do {
                            System.out.println("El Jugador Uno tiene: " + JugadorUnoCartas.size());
                            System.out.println("El Jugador Dos tiene: " + JugadorDosCartas.size());
                            System.out.println("----------- [ RONDA " + ronda + " ] -----------");
                            ronda = ronda + 1;
                            tiposDeRondas(JugadorUnoCartas, JugadorDosCartas);
                            enterParaContinuar();
                        } while (condicionalSwitch(JugadorUnoCartas, JugadorDosCartas) != 0);
                        ganador(JugadorUnoCartas, JugadorDosCartas);
                        enterParaContinuar();

                    case 3: // Leer desde el archivo los LOGS de TODAS las partidas jugadas

                    case 4: // Borar archivo de LOGS

                    case 0: // Salir
                        System.out.println("Thanks for playing!");
                        break;
                }
            } catch (InputMismatchException e) {
                System.out.println("Opcion invalida!");
            }
        } while (menu !=0);

    }

    // METODOS PARA EL MENU

    public static void mostrarCartasJugador(ArrayList<Personaje> ArrayList) {
        for (Personaje carta : ArrayList) {
            System.out.println(carta.imprimirPersonajes());
        }
    }

    // Enter para continuar
    public static void enterParaContinuar(){
        Scanner scanner = new Scanner(System.in);
        System.out.print("\nPresiona ENTER para continuar.");
        scanner.nextLine();
        System.out.println();
    }

    public static int cartaElegida(ArrayList<Personaje> ArrayList){
        GeneradorDeNumero numero = new GeneradorDeNumero();
        if (ArrayList.size() == 3){
            return numero.generarNumeroUnoTres()-1;
        }
        if (ArrayList.size() == 2){
            return numero.generarNumeroUnoDos()-1;
        }
        else
            return 0;
    }

    public static void eliminarCarta(ArrayList<Personaje> ArrayList, int carta){
        System.out.println(ArrayList.get(carta).getNombre() + " " + ArrayList.get(carta).getApodo() + " quedo fuera de combate");
        ArrayList.remove(carta);
    }

    // ATACA PRIMERO LA PRIMERA ARRAYLIST
    public static void ronda(ArrayList<Personaje> ArrayList1, ArrayList<Personaje> ArrayList2){
        int ataques = 0;
        int carta1 = cartaElegida(ArrayList1);
        int carta2 = cartaElegida(ArrayList2);
        do {
            // Ataca jugador UNO
            int dmg = ArrayList1.get(carta1).danioProvocado(ArrayList2.get(carta2));
            ArrayList2.get(carta2).restarSalud(dmg);
            ataques = ataques + 1;
            // Mensaje info
            System.out.println(ArrayList1.get(carta1).mensajesInformativo(dmg, ArrayList2.get(carta2)));
            if (ArrayList2.get(carta2).getSalud() <= 0){
                eliminarCarta(ArrayList2, carta2);
                break;
            }

            // Ataca jugador DOS
            int dmg2 = ArrayList2.get(carta2).danioProvocado(ArrayList1.get(carta1));
            ArrayList1.get(carta1).restarSalud(dmg2);

            // Mensaje info
            System.out.println(ArrayList2.get(carta2).mensajesInformativo(dmg2, ArrayList1.get(carta1)));
            if (ArrayList1.get(carta1).getSalud() <= 0){
                eliminarCarta(ArrayList1, carta1);
                break;
            }
            ataques = ataques + 1;


        } while ((ataques <7));
    }

    // CONDICIONAL PARA EL SWITCH
    public static int condicionalSwitch(ArrayList<Personaje> ArrayList1, ArrayList<Personaje> ArrayList2){
        if (ArrayList1.isEmpty() || ArrayList2.isEmpty()) {
            return 0;
        }
        if (ArrayList1.size() == ArrayList2.size()) {
            return 1;
        }
        if (ArrayList1.size() > ArrayList2.size()) {
            return 2;
        }
        if (ArrayList2.size() > ArrayList1.size()){
            return 3;
        }
        return 0;
    }

    public static void tiposDeRondas(ArrayList<Personaje> JugadorUnoCartas, ArrayList<Personaje> JugadorDosCartas){
        GeneradorDeNumero random = new GeneradorDeNumero();
        switch (condicionalSwitch(JugadorUnoCartas, JugadorDosCartas)) {
            case 1: // Caso cartas IGUALES
                System.out.println("Tienen la misma cantidad de cartas, La ronda se sorteara!");
                int numero = random.generarNumeroUnoCien();
                if (numero > 50) {
                    System.out.println("Arranca el jugador UNO");
                    System.out.println("-------------------------------------------------------------");
                    ronda(JugadorUnoCartas, JugadorDosCartas);
                } else {
                    System.out.println("Arranca el jugador DOS");
                    System.out.println("-------------------------------------------------------------");
                    ronda(JugadorDosCartas, JugadorUnoCartas);
                }
                break;
            case 2: // Caso en el que el jugador UNO tenga mas cartas
                System.out.println("Como el jugador uno tiene mas cartas que el jugador DOS");
                System.out.println("Arranca el jugador DOS");
                System.out.println("-------------------------------------------------------------");
                ronda(JugadorDosCartas, JugadorUnoCartas);
                break;
            case 3: // Caso en el que el jugador DOS tenga mas cartas
                System.out.println("Como el jugador DOS tiene mas cartas que el jugador UNO");
                System.out.println("Arranca el jugador UNO");
                System.out.println("-------------------------------------------------------------");
                ronda(JugadorUnoCartas, JugadorDosCartas);
                break;
        }
    }

    public static void ganador(ArrayList<Personaje> ArrayList1, ArrayList<Personaje> ArrayList2){
        System.out.println("Felicidades al ganador del trono de Hierro!");
        System.out.println("Nuestros campeones :");
        if (ArrayList1.size() > ArrayList2.size()){
            mostrarCartasJugador(ArrayList1);
        }
        if (ArrayList2.size() > ArrayList1.size()){
            mostrarCartasJugador(ArrayList2);
        }
    }

    public static void rondaJugadorDos(ArrayList<Personaje> ArrayList1, ArrayList<Personaje> ArrayList2){
        int ataques = 0;
        int carta1 = cartaElegida(ArrayList1);
        int carta2 = cartaElegida(ArrayList2);
        do {
            // Ataca jugador DOS
            int dmg2 = ArrayList2.get(carta2).danioProvocado(ArrayList1.get(carta1));
            ArrayList1.get(carta1).restarSalud(dmg2);
            System.out.println(dmg2);
            // Mensaje info
            System.out.println(ArrayList2.get(carta2).mensajesInformativo(dmg2, ArrayList1.get(carta1)));
            if (ArrayList1.get(carta1).getSalud() <= 0){
                eliminarCarta(ArrayList1, carta1);
                break;
            }
            ataques = ataques + 1;

            // Ataca jugador UNO
            int dmg = ArrayList1.get(carta1).danioProvocado(ArrayList2.get(carta2));
            ArrayList2.get(carta2).restarSalud(dmg);
            System.out.println(dmg);
            // Mensaje info
            System.out.println(ArrayList1.get(carta1).mensajesInformativo(dmg,ArrayList2.get(carta2)));
            if (ArrayList2.get(carta2).getSalud() <= 0){
                eliminarCarta(ArrayList2, carta2);
                break;
            }
            ataques = ataques + 1;

            //JugadorDosCartas.get(carta2).restarSalud(JugadorUnoCartas.get(carta1));
            //System.out.println(JugadorUnoCartas.get(carta1).mensajesInformativo(JugadorDosCartas.get(carta2)));

            //JugadorDosCartas.get(carta2).restarSalud(JugadorUnoCartas.get(carta1));
            //System.out.println(JugadorUnoCartas.get(carta1).mensajesInformativo(JugadorDosCartas.get(carta2)));
        } while (ataques <=7 || (ArrayList1.size() > ArrayList2.size() || ArrayList2.size() > ArrayList1.size()));
    }

    public static void rondaRandom(ArrayList<Personaje> ArrayList1, ArrayList<Personaje> ArrayList2){
        GeneradorDeNumero numero = new GeneradorDeNumero();

        int ataques = 0;
        int carta1 = cartaElegida(ArrayList1);
        int carta2 = cartaElegida(ArrayList2);

        System.out.println("La carta del Jugador UNO elegida para pelear es:\n" + ArrayList1.get(carta1).imprimirPersonajes());
        System.out.println("La carta del Jugador DOS elegida para pelear es:\n" + ArrayList2.get(carta2).imprimirPersonajes());
        if (numero.generarNumeroUnoCien() <= 50){
            System.out.println("Comienza el jugador UNO atacando!");
            do {

                // Ataca jugador UNO
                int dmg = ArrayList1.get(carta1).danioProvocado(ArrayList2.get(carta2));
                System.out.println(dmg);
                ArrayList2.get(carta2).restarSalud(dmg);

                // Mensaje info
                System.out.println(ArrayList1.get(carta1).mensajesInformativo(dmg, ArrayList2.get(carta2)));
                if (ArrayList2.get(carta2).getSalud() <= 0){
                    eliminarCarta(ArrayList2, carta2);
                    break;
                }
                ataques = ataques + 1;

                // Ataca jugador DOS
                int dmg2 = ArrayList2.get(carta2).danioProvocado(ArrayList1.get(carta1));
                ArrayList1.get(carta1).restarSalud(dmg2);
                System.out.println(dmg2);
                // Mensaje info
                System.out.println(ArrayList2.get(carta2).mensajesInformativo(dmg2, ArrayList1.get(carta1)));
                if (ArrayList1.get(carta1).getSalud() <= 0){
                    eliminarCarta(ArrayList1, carta1);
                    break;
                }
                ataques = ataques + 1;

                //JugadorDosCartas.get(carta2).restarSalud(JugadorUnoCartas.get(carta1));
                //System.out.println(JugadorUnoCartas.get(carta1).mensajesInformativo(JugadorDosCartas.get(carta2)));

                //JugadorUnoCartas.get(carta2).restarSalud(JugadorUnoCartas.get(carta1));
                //System.out.println(JugadorUnoCartas.get(carta1).mensajesInformativo(JugadorDosCartas.get(carta2)));

            } while (ataques <=7);
        }
        else {
            System.out.println("Comienza el jugador DOS atacando!");
            do {
                // Ataca jugador DOS
                int dmg2 = ArrayList2.get(carta2).danioProvocado(ArrayList1.get(carta1));
                ArrayList1.get(carta1).restarSalud(dmg2);
                System.out.println(dmg2);
                // Mensaje info
                System.out.println(ArrayList2.get(carta2).mensajesInformativo(dmg2, ArrayList1.get(carta1)));
                if (ArrayList1.get(carta1).getSalud() <= 0){
                    eliminarCarta(ArrayList1, carta1);
                    break;
                }
                ataques = ataques + 1;

                // Ataca jugador UNO
                int dmg = ArrayList1.get(carta1).danioProvocado(ArrayList2.get(carta2));
                ArrayList2.get(carta2).restarSalud(dmg);
                System.out.println(dmg);
                // Mensaje info
                System.out.println(ArrayList1.get(carta1).mensajesInformativo(dmg,ArrayList2.get(carta2)));
                if (ArrayList2.get(carta2).getSalud() <= 0){
                    eliminarCarta(ArrayList2, carta2);
                    break;
                }
                ataques = ataques + 1;

                //JugadorDosCartas.get(carta2).restarSalud(JugadorUnoCartas.get(carta1));
                //System.out.println(JugadorUnoCartas.get(carta1).mensajesInformativo(JugadorDosCartas.get(carta2)));

                //JugadorDosCartas.get(carta2).restarSalud(JugadorUnoCartas.get(carta1));
                //System.out.println(JugadorUnoCartas.get(carta1).mensajesInformativo(JugadorDosCartas.get(carta2)));
            } while (ataques <=7 || (ArrayList1.size() > ArrayList2.size() || ArrayList2.size() > ArrayList1.size()));
        }
    }
}