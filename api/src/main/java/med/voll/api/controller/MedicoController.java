package med.voll.api.controller;

import med.voll.api.medico.DadosCadastroMedico;
import med.voll.api.medico.Medico;
import med.voll.api.medico.MedicoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("medicos") // url
@RestController // avisar o spring carregar iniciando o projeto
public class MedicoController {

    @Autowired // instancia do atrb repos
    private MedicoRepository repository; // herda da interface, injeção de dependencias



    @PostMapping // se chegar uma req tipo post cadastrar
    // dizendo q o parametro json vai vir no corpo da req
    // esse metodo recebe o corpo todo da req atraves da class
    // padrao DTO data transfer object
    // classe DTO de medico
    public void cadastrar(@RequestBody DadosCadastroMedico dados){
        repository.save(new Medico(dados));
        System.out.println(dados);


    }
}
