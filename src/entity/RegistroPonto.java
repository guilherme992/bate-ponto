package entity;

import java.sql.Timestamp;

public class RegistroPonto {

    private int id;
    private int fkfuncionario;
    private Timestamp dataHora;

    public RegistroPonto(int fkfuncionario, Timestamp dataHora) {
        this.fkfuncionario = fkfuncionario;
        this.dataHora = dataHora;
    }

    public int getFkFuncionario() {
        return fkfuncionario;
    }

    public Timestamp getDataHora() {
        return dataHora;
    }
}
