package domain;

public class VehiculoElectrico extends Vehiculo {
    private double kwhBase;

    public VehiculoElectrico(String patente, Marca marca, String modelo, int anio, double capacidadCarga,
                             Sucursal sucursal, double kwhBase) {
        super(VehiculoTipo.ELECTRICO, patente, marca, modelo, anio, capacidadCarga, sucursal);
        this.kwhBase = kwhBase;
    }

    public double calcularConsumo() {
        double total = kwhBase;

        if (getCapacidadCarga() >= 1200) {
            total = total + 0.15;
        }
        return total;
    }
}



