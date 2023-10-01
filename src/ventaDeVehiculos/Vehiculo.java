package ventaDeVehiculos;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

@DatabaseTable(tableName = "vehiculos")
public class Vehiculo {

    @DatabaseField
    private String tipoVehiculo;

    @DatabaseField
    private String marca;

    @DatabaseField
    private String nombreModelo;

    @DatabaseField
    private int anioProduccion;

    @DatabaseField
    private int numeroEjes;

    @DatabaseField
    private int cilindrada;

    @DatabaseField
    private float valor;

    @DatabaseField (id = true)
    private String placa; // Llave primaria

    public Vehiculo(String tipoVehiculo, String marca, String nombreModelo, int anioProduccion, int numeroEjes, int cilindrada, float valor, String placa) {
        this.tipoVehiculo = tipoVehiculo;
        this.marca = marca;
        this.nombreModelo = nombreModelo;
        this.anioProduccion = anioProduccion;
        this.numeroEjes = numeroEjes;
        this.cilindrada = cilindrada;
        this.valor = valor;
        this.placa = placa;
    }

    public Vehiculo(){
        // Constructor vacío
        this.tipoVehiculo = "";
        this.marca = "";
        this.nombreModelo = "";
        this.anioProduccion = 0;
        this.numeroEjes = 0;
        this.cilindrada = 0;
        this.valor = 0;
        this.placa = "";
    }

    public Vehiculo(String placa, String tipoVehiculo, String marca, int anioProduccion, int numeroEjes, int cilindrada, Float valor) {
    }

    public String getTipoVehiculo() {
        return tipoVehiculo;
    }

    public String getMarca() {
        return marca;
    }

    public String getNombreModelo() {
        return nombreModelo;
    }

    public int getAnioProduccion() {
        return anioProduccion;
    }

    public int getNumeroEjes() {
        return numeroEjes;
    }

    public int getCilindrada() {
        return cilindrada;
    }

    public float getValor() {
        return valor;
    }

    public String getPlaca() {
        return placa;
    }

    public void setTipoVehiculo(String tipoVehiculo) {
        this.tipoVehiculo = tipoVehiculo;
    }

    public void setMarca(String marca) {
        this.marca = marca;
    }

    public void setNombreModelo(String nombreModelo) {
        this.nombreModelo = nombreModelo;
    }

    public void setAnioProduccion(int anioProduccion) {
        this.anioProduccion = anioProduccion;
    }

    public void setNumeroEjes(int numeroEjes) {
        this.numeroEjes = numeroEjes;
    }

    public void setCilindrada(int cilindrada) {
        this.cilindrada = cilindrada;
    }

    public void setValor(float valor) {
        this.valor = valor;
    }

    public void setPlaca(String placa) {
        this.placa = placa;
    }
}
