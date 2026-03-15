package service;

import entity.RegistroPonto;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.List;

public class RegistroPontoService {

    public Duration calcularHorasTrabalhadas(List<RegistroPonto> registros) {

        Duration total = Duration.ZERO;

        for (int i = 0; i < registros.size() - 1; i += 2) {

            LocalDateTime entrada = registros.get(i).getDataHora().toLocalDateTime();
            LocalDateTime saida = registros.get(i + 1).getDataHora().toLocalDateTime();

            Duration periodo = Duration.between(entrada, saida);

            total = total.plus(periodo);
        }

        return total;
    }
}