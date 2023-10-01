package ventaDeVehiculos;

import com.j256.ormlite.dao.Dao;
import com.j256.ormlite.dao.DaoManager;
import com.j256.ormlite.jdbc.JdbcConnectionSource;
import com.j256.ormlite.support.ConnectionSource;
import com.j256.ormlite.logger.LoggerFactory;
import com.j256.ormlite.logger.LogBackendType;

import java.sql.SQLException;
import java.util.Scanner;

public class Concesionario {
// Atributo del programa
    static Dao<Vehiculo, String> tablaVehiculos;
    public static void main(String[] args) throws Exception {
        LoggerFactory.setLogBackendFactory(LogBackendType.NULL);
        Scanner teclado = new Scanner(System.in);
        String url = "jdbc:h2:file:./vehiculos";
        //Conectar base de datos
        ConnectionSource con = new JdbcConnectionSource(url);
        // Configurar tabla a través de un DAO (Data Access Object)
        tablaVehiculos = DaoManager.createDao(con, Vehiculo.class);

        int opcion = 0;
        String placa;

        do {
            System.out.println("\n====SELECCIONA ALGUNA DE LAS SIGUIENTES OPCIONES====");
            System.out.println("0. Salir"); ////////////////////////////////////////////////////// OK
            System.out.println("1. Ver placas de vehículos a la venta"); ////////////////////////////////////////////////////// OK
            System.out.println("2. Buscar información por placa"); ////////////////////////////////////////////////////// OK
            System.out.println("3. Registrar vehículo a la venta"); ////////////////////////////////////////////////////// OK
            System.out.println("4. Ordenar lista de vehículos por modelo, por marca o por año");
            System.out.println("5. Buscar placa por modelo y año del vehículo"); ////////////////////////////////////////////////////// OK
            System.out.println("6. Comprar un vehículo"); ////////////////////////////////////////////////////// OK
            System.out.println("7. Aplicar descuento de 10% a vehículos con precios superiores a..."); ////////////////////////////////////////////////////// OK
            System.out.println("8. Encontrar el vehículo más antiguo"); ////////////////////////////////////////////////////// OK
            System.out.println("9. Encontrar vehículo con mayor cilindrada"); ////////////////////////////////////////////////////// OK
            System.out.println("10. Localizar vehículo más barato"); ////////////////////////////////////////////////////// OK
            System.out.print("\nIngresa la opción seleccionada: ");
            opcion = teclado.nextInt();
            teclado.nextLine();

            switch (opcion) {
                case 1:
                    mostrarVehiculos();
                    break;
                case 2:
                    System.out.print("Ingresa la placa del vehículo que deseas buscar: ");
                    placa = teclado.nextLine();
                    buscarPorPlaca(placa);
                    break;
                case 3:
                    System.out.print("Placa del vehículo: ");
                    placa = teclado.nextLine();

                    System.out.print("Tipo de vehículo: ");
                    String tipoVehiculo = teclado.nextLine();

                    System.out.print("Marca del vehículo: ");
                    String marca = teclado.nextLine();

                    System.out.print("Nombre del modelo del vehículo: ");
                    String nombreModelo = teclado.nextLine();

                    System.out.print("Año de producción del vehículo: ");
                    int anioProduccion = teclado.nextInt();

                    System.out.print("Número de ejes del vehículo: ");
                    int numeroEjes = teclado.nextInt();

                    System.out.print("Cilindrada del vehículo: ");
                    int cilindrada = teclado.nextInt();

                    System.out.print("Valor del vehículo: ");
                    Float valor = teclado.nextFloat();

                    Vehiculo vehiculo = new Vehiculo(tipoVehiculo, marca, nombreModelo, anioProduccion, numeroEjes, cilindrada, valor, placa);
                    if (!tablaVehiculos.idExists(placa)) {
                        // Guardar vehículo si la placa no existe
                        tablaVehiculos.create(vehiculo);
                        System.out.println("Vehículo guardado en la base de datos");
                        System.out.println("Ahora hay " + tablaVehiculos.countOf() + " vahículos");
                    } else {
                        // Si ya existe la placa
                        System.out.println("El vehículo con esa placa ya existe!");
                    }
                    break;
                case 5:
                    System.out.print("Ingresa el modelo del vehículo a buscar: ");
                    String modelo = teclado.nextLine();
                    System.out.print("Ingresa el año del vehículo a buscar: ");
                    int anio = teclado.nextInt();
                    buscarPorModeloYAnio(modelo, anio);
                    break;
                case 6:
                    System.out.print("Ingresa la placa del vehículo que deseas vender: ");
                    placa = teclado.nextLine();
                    comprarVehiculo(placa);
                    break;

                case 7:
                    System.out.print("Ingresa el valor a partir del cual deseas aplicar el descuento del 10%: ");
                    float valorParaDescuento = teclado.nextFloat();
                    aplicarDescuento(valorParaDescuento);
                    break;
                case 8:
                    encontrarMasAntiguo();
                    break;
                case 9:
                    encontrarMayorCilindrada();
                    break;
                case 10:
                    encontrarMasBarato();
                    break;

            }

            con.close();
        } while (opcion!=0);
    }


    static void printVehiculoInfo(Vehiculo v) throws SQLException{
        System.out.println();
        System.out.println("ESTA ES LA INFORMACIÓN DEL VEHÍCULO:");
        System.out.println("Placa del vehículo: " + v.getPlaca());
        System.out.println("Tipo de vehículo: " + v.getTipoVehiculo());
        System.out.println("Marca del vehículo: " + v.getMarca());
        System.out.println("Nombre del modelo del vehículo: " + v.getNombreModelo());
        System.out.println("Año de producción del vehículo: " + v.getAnioProduccion());
        System.out.println("Número de ejes del vehículo: " + v.getNumeroEjes());
        System.out.println("Cilindrada del vehículo: " + v.getCilindrada());
        System.out.println("Valor del vehículo: " + v.getValor());
        System.out.println();
    }
    static void mostrarVehiculos(){
        // 1. Ver placas de vehículos a la venta
        System.out.println("Las placas de los vehículos a la venta son:");
        for (  Vehiculo v: tablaVehiculos) {
            System.out.println(v.getPlaca());
        }
    }

    static void buscarPorPlaca(String placa) throws SQLException {
        // 2. Buscar información por placa
        Vehiculo v = tablaVehiculos.queryForId(placa);
        if (v == null) {
            System.out.println("No existe el vehículo de placas " + placa);
        }
        else {
            printVehiculoInfo(v);
        }
    }

    static void comprarVehiculo(String placa) throws SQLException{
        // 6. Comprar un vehículo
        int v = tablaVehiculos.deleteById(placa);
        if (v == 1){
            System.out.println("Vehículo vendido y removido de la base de datos\n");
        }
    }

    private static void aplicarDescuento(float valorParaDescuento) throws SQLException {
        // 7. Aplicar descuento de 10% a vehículos con precios superiores a...
        int counter = 0;
        for (Vehiculo v : tablaVehiculos) {
            if (v.getValor() >= valorParaDescuento){
                v.setValor((float) (v.getValor() * 0.9));
                tablaVehiculos.update(v);
                counter += 1;
            }
        }
        if (counter>0){
            System.out.println("Precio ajustado en " + counter + " vehículos");
        }
        else {
            System.out.println("No se ajustó el precio a ningún vehículo");
        }
    }

    private static void encontrarMasAntiguo() throws SQLException {
        // 8. Encontrar el vehículo más antiguo
        int anioMasAntiguo = 0;
        String placaMasAngituo = "";

        for (Vehiculo v : tablaVehiculos) {
            if (v.getAnioProduccion() < anioMasAntiguo || anioMasAntiguo == 0){
                anioMasAntiguo = v.getAnioProduccion();
                placaMasAngituo = v.getPlaca();
            }
        }
        if (anioMasAntiguo != 0) {
            System.out.println("El vehículo de placas '" + placaMasAngituo + "' es el más más antiguo, con un año de producción: " + anioMasAntiguo);
        }
        else{
            System.out.println("No se encontraron vehículos en la búsqueda");
        }
    }

    private static void encontrarMayorCilindrada() throws SQLException {
        // 8. Encontrar el vehículo más antiguo
        int mayorCilindrada = 0;
        String placaMayorCilindrada = "";

        for (Vehiculo v : tablaVehiculos) {
            if (v.getCilindrada() > mayorCilindrada || mayorCilindrada == 0){
                mayorCilindrada = v.getCilindrada();
                placaMayorCilindrada = v.getPlaca();
            }
        }
        if (mayorCilindrada != 0) {
            System.out.println("El vehículo de placas '" + placaMayorCilindrada + "' es el de mayor cilindrada, con un motor de " + mayorCilindrada + "CC");
        }
        else{
            System.out.println("No se encontraron vehículos en la búsqueda");
        }
    }

    private static void encontrarMasBarato() throws SQLException {
        // 8. Encontrar el vehículo más antiguo
        float precioMasBarato = 0;
        String placaMasBarato = "";

        for (Vehiculo v : tablaVehiculos) {
            if (v.getValor() < precioMasBarato || precioMasBarato == 0){
                precioMasBarato = v.getValor();
                placaMasBarato = v.getPlaca();
            }
        }
        if (precioMasBarato != 0) {
            System.out.println("El vehículo de placas '" + placaMasBarato + "' es el más más barato, con un precio de: COP $" + precioMasBarato);
        }
        else{
            System.out.println("No se encontraron vehículos en la búsqueda");
        }
    }

    private static void buscarPorModeloYAnio(String modelo, int anio) {
        int found = 0;
        for (Vehiculo v : tablaVehiculos) {
            if (v.getNombreModelo().equals(modelo) && v.getAnioProduccion() == anio){
                System.out.println("El vehículo de placas " + v.getPlaca() + " es de modelo " + modelo + " y año de producción " + anio);
                found+=1;
            }
        }
        if (found == 0){
            System.out.println("No se encontraron vehículos con esas características...");
        }
    }
}
