/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.ifnmg.universidade.negocio;

import br.edu.ifnmg.universidade.entidade.Curso;
import br.edu.ifnmg.universidade.excecao.ConsultaSemResultadoException;
import br.edu.ifnmg.universidade.excecao.CursoNomeDuplicadoException;
import br.edu.ifnmg.universidade.excecao.CursoSiglaDuplicadaException;
import br.edu.ifnmg.universidade.excecao.CursoSiglaInvalidaException;
import br.edu.ifnmg.universidade.persistencia.CursoDAO;
import java.sql.SQLException;
import java.util.List;

/**
 *
 * @author helder
 */
public class CursoBO {

    public void inserir(Curso curso) throws SQLException {
        curso.setSigla(curso.getSigla().toUpperCase());

        this.validarSigla(curso);
        this.validarNomeDuplicado(curso);
        this.validarSiglaDuplicada(curso);

        CursoDAO cursoDAO = new CursoDAO();
        cursoDAO.inserir(curso);
    }

    public void atualizar(Curso curso) throws SQLException {
        curso.setSigla(curso.getSigla().toUpperCase());

        this.validarSigla(curso);
        this.validarNomeDuplicado(curso);
        this.validarSiglaDuplicada(curso);

        CursoDAO cursoDAO = new CursoDAO();
        cursoDAO.atualizar(curso);
    }
    
    public void remover(int idCurso) throws SQLException{
        //TODO: falta implementar regra que verifica se há alunos matriculados no curso
        CursoDAO cursoDAO = new CursoDAO();
        cursoDAO.remover(idCurso);
    }

    private void validarSigla(Curso curso) {
        if (curso.getSigla().trim().length() < 3 || curso.getSigla().trim().length() > 4) {
            throw new CursoSiglaInvalidaException();
        }
    }

    private void validarNomeDuplicado(Curso curso) throws SQLException {
        try {
            CursoDAO cursoDAO = new CursoDAO();
            Curso cursoEncontrado = cursoDAO.buscarNome(curso.getNome());
            if (!cursoEncontrado.getId().equals(curso.getId())) {
                throw new CursoNomeDuplicadoException(curso.getNome());
            }
        } catch (ConsultaSemResultadoException e) {
            //Se não encontrou o nome está ok!
        }

    }

    private void validarSiglaDuplicada(Curso curso) throws SQLException {
        try {
            CursoDAO cursoDAO = new CursoDAO();
            Curso cursoEncontrado = cursoDAO.buscarSigla(curso.getSigla());
            if (!cursoEncontrado.getId().equals(curso.getId())) {
                throw new CursoSiglaDuplicadaException(curso.getSigla());
            }
        } catch (ConsultaSemResultadoException e) {
            //Se não encontrou a sigla está ok!
        }
    }

    public List<Curso> buscarTodos() throws SQLException {
        CursoDAO cursoDAO = new CursoDAO();
        return cursoDAO.buscarTodos();
    }

    public Curso buscarSigla(String sigla) throws SQLException {
        CursoDAO cursoDAO = new CursoDAO();
        return cursoDAO.buscarSigla(sigla);
    }

}
