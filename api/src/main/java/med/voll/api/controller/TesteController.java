package med.voll.api.controller;


import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController // controller api rest
@RequestMapping("/teste") // dizer qual url
public class TesteController {
    @GetMapping // metodo para chamar url
    public String Teste() {
        return  "TESTE INICIAL";
    }
}
