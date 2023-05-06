package med.voll.api.controller;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("medicos") // url
@RestController // avisar o spring carregar iniciando o projeto
public class MedicoController {
    @PostMapping // se chegar uma req tipo post cadastrar
    // dizendo q o parametro json vai vir no corpo da req
    public void cadastrar(@RequestBody String json){ // esse metodo recebe o corpo todo dareq
        System.out.println(json);

    }
}
