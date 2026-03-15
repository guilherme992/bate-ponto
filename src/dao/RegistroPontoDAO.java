package dao;

import entity.RegistroPonto;
import factory.FabricaDeConexao;

import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class RegistroPontoDAO {
    FabricaDeConexao fabricaDeConexao = new FabricaDeConexao();

    public boolean insert(RegistroPonto registroPonto) throws SQLException {

        String sqlInsert = """
            INSERT INTO registroponto(fkfuncionario,dataHora)
            VALUES (?,?)
            """;

        try (Connection conn = fabricaDeConexao.con();
             PreparedStatement stmt = conn.prepareStatement(sqlInsert)) {

            stmt.setInt(1, registroPonto.getFkFuncionario());
            stmt.setTimestamp(2, registroPonto.getDataHora());

            int linhasAfetadas = stmt.executeUpdate();

            return linhasAfetadas > 0;
        }
    }

    public List<RegistroPonto> buscarPorFuncionarioEData(int fkFuncionario, LocalDate data) throws SQLException {

        String sqlSelect = """
            SELECT r.fkfuncionario, r.datahora
            FROM registroponto r
            WHERE r.fkfuncionario = ?
            AND DATE(r.datahora) = ?
            ORDER BY r.datahora
            """;

        List<RegistroPonto> registros = new ArrayList<>();

        try (Connection conn = fabricaDeConexao.con();
             PreparedStatement stmt = conn.prepareStatement(sqlSelect)) {

            stmt.setInt(1, fkFuncionario);
            stmt.setDate(2, Date.valueOf(data));

            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {

                Timestamp dataHora = rs.getTimestamp("datahora");

                RegistroPonto registro = new RegistroPonto(fkFuncionario, dataHora);

                registros.add(registro);
            }
        }

        return registros;
    }

}
