package domain;
public abstract class Vehiculo {
    private String patente;
    private Marca marca; // Ahora es tipo Marca
    private String modelo;
    private int anio;
    private double capacidadCarga;
    private Sucursal sucursal;
    private VehiculoTipo tipo;

    public Vehiculo(VehiculoTipo tipo, String patente, Marca marca, String modelo, int anio, double capacidadCarga, Sucursal sucursal) {
        this.patente = patente;
        this.marca = marca;
        this.modelo = modelo;
        this.anio = anio;
        this.capacidadCarga = capacidadCarga;
        this.sucursal = sucursal;
        this.tipo = tipo;
    }

    public String getPatente() {
        return patente;
    }
    
    public VehiculoTipo getTipo(){
        return tipo;
    }
    
    public double getCapacidadCarga(){
        return capacidadCarga;
    }
    
    public int getAnio(){
        return anio;
    }
    
    public String getCodigoSucursal(){
        return sucursal.getCodigo();
    }
    
    public Marca getMarca() {
        return marca;
    }
    
    public double calcularConsumo(double kilometros) {
        return 0;
    }

    public boolean esDe(VehiculoTipo tipo){
        return this.tipo == tipo;
    }
    @Override
    public String toString() {
        return marca.getNombre() + " " + modelo + " - Sucursal: " + sucursal.getCodigo(); 
    }
}
