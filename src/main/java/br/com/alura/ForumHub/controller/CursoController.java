package br.com.alura.ForumHub.controller;

import br.com.alura.ForumHub.infra.dto.curso.DadosAtualizaCurso;
import br.com.alura.ForumHub.infra.dto.curso.DadosCadastroCurso;
import br.com.alura.ForumHub.infra.dto.curso.DadosDetalhamentoCurso;
import br.com.alura.ForumHub.infra.dto.curso.DadosListagemCursos;
import br.com.alura.ForumHub.repository.CursoRepository;
import br.com.alura.ForumHub.service.CursoService;
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
@RequestMapping("/cursos")
@SecurityRequirement(name = "bearer-key")
@Secured("ROLE_ADMIN")
public class CursoController {

    @Autowired
    private CursoRepository cursoRepository;

    @Autowired
    private CursoService cursoManager;

    @PostMapping
    @Transactional
    public ResponseEntity cadastrar(@RequestBody @Valid DadosCadastroCurso dados, UriComponentsBuilder uriBuilder) {
        var dto = cursoManager.cadastrar(dados);
        var uri = uriBuilder.path("/cursos/{id}").buildAndExpand(dto.id()).toUri();
        return ResponseEntity.created(uri).body(dto);
    }

    @GetMapping
    public ResponseEntity<Page<DadosListagemCursos>> listar(
            @PageableDefault(size = 10, sort = {"nome"})
            Pageable paginacao) {
        var page = cursoRepository.findAll(paginacao).map(DadosListagemCursos::new);
        return ResponseEntity.ok(page);
    }

    @GetMapping("/{id}")
    public ResponseEntity detalhar(@PathVariable Long id) {
        var curso = cursoRepository.getReferenceById(id);
        return ResponseEntity.ok(new DadosDetalhamentoCurso(curso));
    }

    @PutMapping
    @Transactional
    public ResponseEntity atualizar(@RequestBody @Valid DadosAtualizaCurso dados) {

        var dto = cursoManager.atualizar(dados);
        return ResponseEntity.ok(dto);
    }

    @DeleteMapping("/{id}")
    @Transactional
    public ResponseEntity excluir(@PathVariable Long id) {

        cursoManager.deletar(id);
        return ResponseEntity.noContent().build();
    }

}
