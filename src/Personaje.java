public abstract class Personaje {

    private String nombre;
    private Raza raza;
    private String apodo;
    private String nacimiento;
    private int edad;
    private Integer salud;

    private int velocidad;
    private int destreza;
    private int fuerza;
    private int lvl;
    private int armadura;

    public Personaje() {
    }

    public Personaje(String nombre, Raza raza, String apodo, String nacimiento, int edad, Integer salud, int velocidad, int destreza, int fuerza, int lvl, int armadura) {
        this.nombre = nombre;
        this.raza = raza;
        this.apodo = apodo;
        this.nacimiento = nacimiento;
        this.edad = edad;
        this.salud = salud;
        this.velocidad = velocidad;
        this.destreza = destreza;
        this.fuerza = fuerza;
        this.lvl = lvl;
        this.armadura = armadura;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public Raza getRaza() {
        return raza;
    }

    public void setRaza(Raza raza) {
        this.raza = raza;
    }

    public String getApodo() {
        return apodo;
    }

    public void setApodo(String apodo) {
        this.apodo = apodo;
    }

    public String getNacimiento() {
        return nacimiento;
    }

    public void setNacimiento(String nacimiento) {
        this.nacimiento = nacimiento;
    }

    public int getEdad() {
        return edad;
    }

    public void setEdad(int edad) {
        this.edad = edad;
    }

    public int getSalud() {
        return salud;
    }

    public void setSalud(int salud) {
        this.salud = salud;
    }

    public int getVelocidad() {
        return velocidad;
    }

    public void setVelocidad(int velocidad) {
        this.velocidad = velocidad;
    }

    public int getDestreza() {
        return destreza;
    }

    public void setDestreza(int destreza) {
        this.destreza = destreza;
    }

    public int getFuerza() {
        return fuerza;
    }

    public void setFuerza(int fuerza) {
        this.fuerza = fuerza;
    }

    public int getLvl() {
        return lvl;
    }

    public void setLvl(int lvl) {
        this.lvl = lvl;
    }

    public int getArmadura() {
        return armadura;
    }

    public void setArmadura(int armadura) {
        this.armadura = armadura;
    }

    public abstract int poderDeDisparo();
    public abstract float efectividadDeDisparo();
    public abstract int valorDeAtaque();
    public abstract int poderDeDefensa();
    public abstract int danioProvocado(Personaje personaje);
    public abstract void restarSalud(int dmg);
    public abstract void recuperarSalud();
    public abstract Personaje personajeAleatorio();
    public abstract Personaje crearPersonaje();
    public abstract String imprimirPersonajes();

    public abstract String mensajesInformativo(int dmg, Personaje personaje);
}
