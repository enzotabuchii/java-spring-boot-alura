package enzo.tabuchi.api.domain.consulta;

import enzo.tabuchi.api.domain.medico.Medico;
import enzo.tabuchi.api.domain.medico.MedicoRepository;
import enzo.tabuchi.api.domain.paciente.PacienteRepository;
import jakarta.validation.ValidationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AgendamentoConsultas {

    @Autowired
    private ConsultaRepository consultaRepository;

    @Autowired
    private MedicoRepository medicoRepository;

    @Autowired
    private PacienteRepository pacienteRepository;

    public void agendar(DadosAgendamentoConsulta dados) {

        if (!pacienteRepository.existsById(dados.idPaciente()))
            throw new ValidationException("ID do paciente não existe");

        if (dados.idMedico() != null && !medicoRepository.existsById(dados.idMedico()))
            throw new ValidationException("ID do médico não existe");

        var paciente = pacienteRepository.getReferenceById(dados.idPaciente());
        var medico = escolherMedico(dados);
        var consulta = new Consulta(null, medico, paciente, dados.data(), null);
        consultaRepository.save(consulta);
    }

    public void cancelar(DadosCancelamentoConsulta dados) {
        if (!consultaRepository.existsById(dados.idConsulta()))
            throw new ValidationException("Id da consulta informado não existe!");

        var consulta = consultaRepository.getReferenceById(dados.idConsulta());
        consulta.cancelar(dados.motivo());
    }

    private Medico escolherMedico(DadosAgendamentoConsulta dados) {
        if (dados.idMedico() != null)
            return medicoRepository.getReferenceById(dados.idMedico());

        if (dados.especialidade() == null)
            throw new ValidationException("Especialidade é obrigatória quando médico não for escolhido");

        return medicoRepository.escolherMedicoAleatorioLivreNaData(dados.especialidade(), dados.data());
    }
}
