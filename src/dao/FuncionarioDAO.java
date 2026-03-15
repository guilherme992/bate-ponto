package dao;
import entity.Funcionario;
import factory.FabricaDeConexao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class FuncionarioDAO {

    FabricaDeConexao fabricaDeConexao = new FabricaDeConexao();

    public boolean insert(Funcionario funcionario) throws SQLException {

        String sqlInsert = """
            INSERT INTO funcionario(nome, matricula, fkdepartamento)
            VALUES (?, ?, ?)
            """;

        try (Connection conn = fabricaDeConexao.con();
             PreparedStatement stmt = conn.prepareStatement(sqlInsert)) {

            stmt.setString(1, funcionario.getNome());
            stmt.setInt(2, funcionario.getMatricula());
            stmt.setInt(3, funcionario.getFkDepartamento());

            int linhasAfetadas = stmt.executeUpdate();

            return linhasAfetadas > 0;
        }
    }
    public Funcionario buscarPorMatricula(int matricula) throws SQLException {

        String sql = """
        SELECT nome, matricula
        FROM funcionario
        WHERE matricula = ?
        """;

        try (Connection conn = fabricaDeConexao.con();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, matricula);

            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                return new Funcionario(
                        rs.getString("nome"),
                        rs.getInt("matricula"),
                        0
                );
            }
        }

        return null;
    }
}
