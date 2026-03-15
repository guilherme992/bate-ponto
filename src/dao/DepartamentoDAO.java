package dao;

import entity.Departamento;
import factory.FabricaDeConexao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class DepartamentoDAO {

    FabricaDeConexao fabricaDeConexao = new FabricaDeConexao();

    public boolean insert(Departamento departamento) throws SQLException {

        String sqlInsert = """
            INSERT INTO departamento(nome)
            VALUES (?)
            """;

        try (Connection conn = fabricaDeConexao.con();
             PreparedStatement stmt = conn.prepareStatement(sqlInsert)) {

            stmt.setString(1, departamento.getNome());

            int linhasAfetadas = stmt.executeUpdate();

            return linhasAfetadas > 0;
        }
    }
}
