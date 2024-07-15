package br.com.alura.ForumHub.controller;

import br.com.alura.ForumHub.infra.dto.resposta.DadosAtualizaResposta;
import br.com.alura.ForumHub.infra.dto.resposta.DadosCadastroResposta;
import br.com.alura.ForumHub.infra.dto.resposta.DadosListagemRespostas;
import br.com.alura.ForumHub.repository.RespostaRepository;
import br.com.alura.ForumHub.service.RespostaService;
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
@RequestMapping("/respostas")
@SecurityRequirement(name = "bearer-key")
@Secured({"ROLE_USER", "ROLE_ADMIN"})
public class RespostaController {

    @Autowired
    private RespostaService respostaService;

    @Autowired
    private RespostaRepository respostaRepository;

    @PostMapping
    @Transactional
    public ResponseEntity cadastrar(@RequestBody @Valid DadosCadastroResposta dados, UriComponentsBuilder uriBuilder) {
        var dto = respostaService.cadastrar(dados);
        var uri = uriBuilder.path("/respostas/{id}").buildAndExpand(dto.id()).toUri();
        return ResponseEntity.created(uri).body(dto);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Page<DadosListagemRespostas>> listar(
            @PathVariable Long id,
            @PageableDefault(size = 10, sort = {"data"})
            Pageable paginacao) {
        var page = respostaRepository.findAllByTopicoId(id, paginacao).map(DadosListagemRespostas::new);
        return ResponseEntity.ok(page);
    }

    @PutMapping
    @Transactional
    public ResponseEntity atualizar(@RequestBody @Valid DadosAtualizaResposta dados) {

        var dto = respostaService.atualizar(dados);
        return ResponseEntity.ok(dto);
    }

    @DeleteMapping("/{id}")
    @Transactional
    public ResponseEntity excluir(@PathVariable Long id) {

        respostaService.deletar(id);
        return ResponseEntity.noContent().build();
    }

}
