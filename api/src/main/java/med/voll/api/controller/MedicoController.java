package med.voll.api.controller;

import jakarta.validation.Valid;
import med.voll.api.medico.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("medicos") // url
@RestController // avisar o spring carregar iniciando o projeto
public class MedicoController {

    @Autowired // instancia do atrb repos
    private MedicoRepository repository; // herda da interface, injeção de dependencias



    @PostMapping // se chegar uma req tipo post cadastrar
    @Transactional // deixar transação ativa com DB
    // dizendo q o parametro json vai vir no corpo da req
    // esse metodo recebe o corpo todo da req atraves da class
    // padrao DTO data transfer object
    // classe DTO de medico
    public void cadastrar(@RequestBody @Valid DadosCadastroMedico dados){
        repository.save(new Medico(dados));
        System.out.println(dados);
    }

    @GetMapping
    // usando pageable para paginacao
    public Page<DadosListagemMedico> listar(@PageableDefault(size = 10, sort = {"nome"}) Pageable paginacao){
        // metodo para trasf medicos em dadolstmed
        return repository.findAllByAtivoTrue(paginacao).map(DadosListagemMedico::new); // medoto vem do medrep -> jparepo
    }
    @PutMapping
    @Transactional
    public void atualiza(@RequestBody @Valid DadosAtualizacaoMedico dados){
        var medico = repository.getReferenceById(dados.id()); // carregar pelo id
        medico.atualizarInfo(dados);
    }

    @DeleteMapping("/{id}") // parametro dinamico na URL
    @Transactional // para para sql
    public void exluir(@PathVariable Long id){ // usa pathvar para indicar ao spring
        var medico = repository.getReferenceById(id); // carregar pelo id
        medico.exluir();
    }




}
