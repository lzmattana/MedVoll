package med.voll.api.controller;

import jakarta.validation.Valid;
import med.voll.api.medico.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RequestMapping("medicos") // url
@RestController // avisar o spring carregar iniciando o projeto
public class MedicoController {

    @Autowired // instancia do atrb repos
    private MedicoRepository repository; // herda da interface, injeção de dependencias



    @PostMapping // se chegar uma req tipo post cadastrar
    @Transactional // deixar transação ativa com DB
    // dizendo q o parametro json vai vir no corpo da req
    // esse metodo recebe o corpo todoo da req atraves da class
    // padrao DTO data transfer object
    // classe DTO de medico
    public ResponseEntity cadastrar(@RequestBody @Valid DadosCadastroMedico dados, UriComponentsBuilder uriBuilder){
        var medico = new Medico(dados);
        repository.save(medico);
        //criando URI
        var uri = uriBuilder.path("/medicos/{id}").buildAndExpand(medico.getId()).toUri();
        return ResponseEntity.created(uri).body(new DadosDetalhamentoMedico(medico));
        // devolver cod 201, cabecalho com URI, corpo da resp respresentacao do recurso
    }

    @GetMapping
    // usando pageable para paginacao
    public ResponseEntity<Page<DadosListagemMedico>> listar(@PageableDefault(size = 10, sort = {"nome"}) Pageable paginacao){
        // metodo para trasf medicos em dadolstmed
        // medoto vem do medrep -> jparepo
        var page = repository.findAllByAtivoTrue(paginacao).map(DadosListagemMedico::new);
        return ResponseEntity.ok(page);
    }

    @PutMapping
    @Transactional
    public ResponseEntity atualiza(@RequestBody @Valid DadosAtualizacaoMedico dados){
        var medico = repository.getReferenceById(dados.id()); // carregar pelo id
        medico.atualizarInfo(dados);
        return ResponseEntity.ok(new DadosDetalhamentoMedico(medico));
    }

    @DeleteMapping("/{id}") // parametro dinamico na URL
    @Transactional // para para sql
    public ResponseEntity exluir(@PathVariable Long id){ // usa pathvar para indicar ao spring
        var medico = repository.getReferenceById(id); // carregar pelo id
        medico.exluir();

        return ResponseEntity.noContent().build(); // metodo para dar resposta no navegador
    }

    @GetMapping("/{id}") // parametro dinamico na URL
    public ResponseEntity detalhar(@PathVariable Long id) { // usa pathvar para indicar ao spring
        var medico = repository.getReferenceById(id); // carregar pelo id

        return ResponseEntity.ok(new DadosDetalhamentoMedico(medico)); // metodo para dar resposta no navegador


    }
}
