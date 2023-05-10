package med.voll.api.domain.medico;

import jakarta.validation.Valid;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import med.voll.api.domain.endereco.DadosEndereco;


// so criar uma classe record com todos os parametros o spring ja toma conta de todoo o resto
public record DadosCadastroMedico(
        @NotBlank
        String nome,
        @NotBlank
        @Email
        String email,
        @NotBlank
        String telefone,
        @NotBlank
        @Pattern(regexp = "\\d{4,6}") // crm sao de 4 a 6 dig
        String crm,
        @NotNull
        Especialidade especialidade,
        @NotNull
        @Valid // validar o outro obj
        DadosEndereco endereco ) {

}
