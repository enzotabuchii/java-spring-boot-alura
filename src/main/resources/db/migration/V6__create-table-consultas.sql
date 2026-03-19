CREATE TABLE consultas(
    id bigint NOT NULL auto_increment,
    medico_id bigint NOT NULL,
    paciente_id bigint NOT NULL,
    data datetime NOT NULL,

    primary key(id),
    constraint fk_consulta_medico_id foreign key(medico_id) references medicos(id),
    constraint fk_consulta_paciente_id foreign key(paciente_id) references pacientes(id)
)