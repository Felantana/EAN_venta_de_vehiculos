package ventaDeVehiculos;

import com.j256.ormlite.dao.Dao;
import com.j256.ormlite.dao.DaoManager;
import com.j256.ormlite.jdbc.JdbcConnectionSource;
import com.j256.ormlite.support.ConnectionSource;
import com.j256.ormlite.table.TableUtils;

public class CrearBaseDeDatos {
    public static void main(String[] args) throws Exception {
        String url = "jdbc:h2:file:./vehiculos";

        //Conectar base de datos
        ConnectionSource con = new JdbcConnectionSource(url);

        // Configurar tabla a través de un DAO (Data Access Object)
        Dao<Vehiculo, String> tablaVehiculos =
                DaoManager.createDao(con, Vehiculo.class);

        // Crear archivo en donde se guardan los vehículos
        TableUtils.createTable(tablaVehiculos);
        System.out.println("Tabla creada exitosamente!");

        //Cerrar la conexión
        con.close();
    }
}
