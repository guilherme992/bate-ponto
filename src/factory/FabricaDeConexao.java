package factory;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

// ConnectionFactory
// Padrões de Projeto
// Factory -> Fábrica para criar objetos
// Singleton -> Permite criar apenas uma instância da classe
public class FabricaDeConexao {

    public Connection con() throws SQLException {
        var url = "jdbc:mysql://localhost:3306/BatePonto";
        var user = "root";
        var password = "root";
        return DriverManager.getConnection(url, user, password);
    }

}
