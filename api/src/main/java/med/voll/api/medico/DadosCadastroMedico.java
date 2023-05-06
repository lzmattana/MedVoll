package med.voll.api.medico;

import med.voll.api.endereco.DadosEndereco;

// so criar uma classe record com todos os parametros o spring ja toma conta de todo o resto
public record DadosCadastroMedico(String nome, String email, String crm, Especialidade especialidade,
                                  DadosEndereco endereco ) {

}
