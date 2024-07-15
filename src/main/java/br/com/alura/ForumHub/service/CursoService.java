package br.com.alura.ForumHub.service;

import br.com.alura.ForumHub.infra.dto.curso.DadosAtualizaCurso;
import br.com.alura.ForumHub.infra.dto.curso.DadosCadastroCurso;
import br.com.alura.ForumHub.infra.dto.curso.DadosDetalhamentoCurso;
import br.com.alura.ForumHub.infra.model.Curso.Curso;
import br.com.alura.ForumHub.infra.security.ValidacaoException;
import br.com.alura.ForumHub.repository.CursoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CursoService {

    @Autowired
    private CursoRepository cursoRepository;

    public DadosDetalhamentoCurso cadastrar(DadosCadastroCurso dados) {

        var optionalCurso = cursoRepository.findByNome(dados.nome());
        if (optionalCurso.isPresent()) {
            throw new ValidacaoException("Este curso já existe!");
        }

        var curso = cursoRepository.save(new Curso(dados));
        return new DadosDetalhamentoCurso(curso);
    }

    public DadosDetalhamentoCurso atualizar(DadosAtualizaCurso dados) {

        var curso = confereCursoExiste(dados.id());

        curso.atualizarInformacoes(dados);
        return new DadosDetalhamentoCurso(curso);
    }

    public void deletar(Long id) {
        confereCursoExiste(id);
        cursoRepository.deleteById(id);
    }



    public Curso confereCursoExiste(Long id) {

        var optionalCurso = cursoRepository.findById(id);

        if (optionalCurso.isEmpty()){
            throw new ValidacaoException("Não dá para deletar um curso que não existe!");
        } else {
            return cursoRepository.getReferenceById(id);
        }
    }
}
