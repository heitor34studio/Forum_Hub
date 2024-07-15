package br.com.alura.ForumHub.controller;

import br.com.alura.ForumHub.infra.dto.topico.DadosAtualizaTopico;
import br.com.alura.ForumHub.infra.dto.topico.DadosCadastroTopico;
import br.com.alura.ForumHub.infra.dto.topico.DadosDetalhamentoTopico;
import br.com.alura.ForumHub.infra.dto.topico.DadosListagemTopicos;
import br.com.alura.ForumHub.repository.TopicoRepository;
import br.com.alura.ForumHub.service.TopicosManager;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

@RestController
@RequestMapping("/topicos")
@SecurityRequirement(name = "bearer-key")
@Secured({"ROLE_USER", "ROLE_ADMIN"})
public class TopicoController {

    @Autowired
    private TopicosManager topicosManager;

    @Autowired
    private TopicoRepository topicoRepository;

    @PostMapping
    @Transactional
    public ResponseEntity cadastrar(@RequestBody @Valid DadosCadastroTopico dados, UriComponentsBuilder uriBuilder) {
        var dto = topicosManager.cadastrar(dados);
        var uri = uriBuilder.path("/topicos/{id}").buildAndExpand(dto.id()).toUri();
        return ResponseEntity.created(uri).body(dto);
    }

    @GetMapping
    public ResponseEntity<Page<DadosListagemTopicos>> listar(
            @PageableDefault(size = 10, sort = {"titulo"})
            Pageable paginacao) {
        var page = topicoRepository.findAll(paginacao).map(DadosListagemTopicos::new);
        return ResponseEntity.ok(page);
    }

    @GetMapping("/{id}")
    public ResponseEntity detalhar(@PathVariable Long id) {
        var topico = topicoRepository.getReferenceById(id);
        return ResponseEntity.ok(new DadosDetalhamentoTopico(topico));
    }

    @PutMapping
    @Transactional
    public ResponseEntity atualizar(@RequestBody @Valid DadosAtualizaTopico dados) {

        var dto = topicosManager.atualizar(dados);
        return ResponseEntity.ok(dto);
    }

    @DeleteMapping("/{id}")
    @Transactional
    public ResponseEntity excluir(@PathVariable Long id) {

        topicosManager.deletar(id);
        return ResponseEntity.noContent().build();
    }
}