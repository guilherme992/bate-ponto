package entity;

public class Funcionario {

    private int id;
    private String nome;
    private int matricula;
    private int fkdepartamento;

    public Funcionario(String nome, int matricula, int fkdepartamento) {
        this.nome = nome;
        this.matricula = matricula;
        this.fkdepartamento = fkdepartamento;
    }

    public String getNome() {
        return nome;
    }

    public int getMatricula() {
        return matricula;
    }

    public int getFkDepartamento() {
        return fkdepartamento;
    }
}
