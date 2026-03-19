package enzo.tabuchi.api.domain.consulta.validacoes;

import enzo.tabuchi.api.domain.consulta.DadosAgendamentoConsulta;
import jakarta.validation.ValidationException;

import java.time.DayOfWeek;

public class ValidadorHorarioFuncionamentoClinica {
    public void validar(DadosAgendamentoConsulta dados) {
        var dataConsulta = dados.data();

        var domingo = dataConsulta.getDayOfWeek().equals(DayOfWeek.SUNDAY);
        var antesDaAberturaDaClinica = dataConsulta.getHour() < 7;
        var depoisDoEncerramentoDaClinica = dataConsulta.getHour() > 18;

        if (domingo || antesDaAberturaDaClinica || depoisDoEncerramentoDaClinica)
            throw new ValidationException("Consulta fora do horário de funcionamento da clínica");
    }
}
