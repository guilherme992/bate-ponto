package view;

import dao.DepartamentoDAO;
import dao.FuncionarioDAO;
import dao.RegistroPontoDAO;
import entity.Departamento;
import entity.Funcionario;
import entity.RegistroPonto;
import service.RegistroPontoService;

import java.sql.SQLException;
import java.sql.Timestamp;
import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Scanner;

public class Menu {

    Scanner scanner = new Scanner(System.in);

    public void iniciar() throws SQLException {

        int opcao;

        do {

            System.out.println("\n===== MENU =====");
            System.out.println("1 - Cadastrar Departamento");
            System.out.println("2 - Cadastrar Funcionário");
            System.out.println("3 - Registrar Ponto");
            System.out.println("4 - Gerar Relatório");
            System.out.println("0 - Sair");

            opcao = scanner.nextInt();
            scanner.nextLine();

            switch (opcao) {

                case 1 -> cadastrarDepartamento();
                case 2 -> cadastrarFuncionario();
                case 3 -> registrarPonto();
                case 4 -> gerarRelatorio();
                case 0 -> System.out.println("Encerrando...");
                default -> System.out.println("Opção inválida");

            }

        } while (opcao != 0);
    }

    private void cadastrarDepartamento() {

        System.out.print("Nome do departamento: ");
        String nome = scanner.nextLine();

        Departamento departamento = new Departamento(nome);

        DepartamentoDAO dao = new DepartamentoDAO();

        try {

            dao.insert(departamento);
            System.out.println("Departamento cadastrado!");

        } catch (Exception e) {
            System.out.println("Erro ao cadastrar departamento");
        }
    }

    private void cadastrarFuncionario() {

        System.out.print("Nome: ");
        String nome = scanner.nextLine();

        System.out.print("Matrícula: ");
        int matricula = scanner.nextInt();

        System.out.print("ID do departamento: ");
        int fkDepartamento = scanner.nextInt();

        Funcionario funcionario =
                new Funcionario(nome, matricula, fkDepartamento);

        FuncionarioDAO dao = new FuncionarioDAO();

        try {

            dao.insert(funcionario);
            System.out.println("Funcionário cadastrado!");

        } catch (Exception e) {
            System.out.println("Erro ao cadastrar funcionário");
        }
    }
    private void registrarPonto() {

        System.out.print("Matrícula do funcionário: ");
        int fkFuncionario = scanner.nextInt();
        Timestamp dataHora = Timestamp.valueOf(LocalDateTime.now());

        RegistroPonto registro = new RegistroPonto(fkFuncionario,dataHora);

        RegistroPontoDAO dao = new RegistroPontoDAO();

        try {

            dao.insert(registro);
            System.out.println("Ponto registrado!");

        } catch (Exception e) {
            System.out.println("Erro ao registrar ponto:" + e);
        }
    }
    private void gerarRelatorio() throws SQLException {

        System.out.print("Matrícula do funcionário: ");
        int fkFuncionario = scanner.nextInt();
        scanner.nextLine();

        System.out.print("Data (DD-MM-AAAA): ");
        String dataInput = scanner.nextLine();

        DateTimeFormatter formatter =
                DateTimeFormatter.ofPattern("dd-MM-yyyy");

        LocalDate data = LocalDate.parse(dataInput, formatter);

        RegistroPontoDAO dao = new RegistroPontoDAO();
        FuncionarioDAO funcionarioDAO = new FuncionarioDAO();
        Funcionario funcionario = funcionarioDAO.buscarPorMatricula(fkFuncionario);

        try {

            List<RegistroPonto> registros =
                    dao.buscarPorFuncionarioEData(fkFuncionario, data);

            RegistroPontoService service = new RegistroPontoService();

            Duration total = service.calcularHorasTrabalhadas(registros);

            System.out.println("\nRELATÓRIO DE PONTO");
            System.out.println("Nome: " + funcionario.getNome());
            System.out.println("Matrícula: " + fkFuncionario);
            System.out.println("Data: " + data.format(formatter));
            System.out.println();

            for (RegistroPonto r : registros) {

                System.out.println(
                        r.getDataHora()
                                .toLocalDateTime()
                                .toLocalTime()
                );
            }

            System.out.println();

            long horas = total.toHours();
            long minutos = total.toMinutesPart();
            System.out.println("Total trabalhado: " + horas + ":" + String.format("%02d", minutos));

        } catch (Exception e) {

            System.out.println("Erro ao gerar relatório");
        }
    }
}