/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.ifnmg.universidade.apresentacao;

import br.edu.ifnmg.universidade.entidade.Curso;
import br.edu.ifnmg.universidade.excecao.UniversidadeException;
import br.edu.ifnmg.universidade.negocio.CursoBO;
import java.sql.SQLException;
import java.util.List;
import java.util.Scanner;

/**
 *
 * @author aluno
 */
public class SistemaUniversidade {

    private Scanner teclado;

    public static void main(String parametros[]) {
        SistemaUniversidade sistemaUniversidade = new SistemaUniversidade();
        sistemaUniversidade.exibirMenu();
    }

    public void exibirMenu() {
        teclado = new Scanner(System.in);
        int opcao = 0;

        do {

            System.out.println("Sistema Universidade - Menu");
            System.out.println("1 - Cadastrar curso");
            System.out.println("2 - Alterar curso");
            System.out.println("3 - Excluir curso");
            System.out.println("4 - Cadastrar aluno");
            System.out.println("5 - Alterar aluno");
            System.out.println("6 - Remover aluno");
            System.out.println("7 - Exibir alunos de um curso");
            System.out.println("0 - Sair");

            opcao = teclado.nextInt();
            teclado.nextLine();
            try {
                if (opcao == 1) {
                    this.cadastrarCurso();
                } else if (opcao == 2) {
                    this.alterarCurso();
                } else if (opcao == 3) {
                    this.removerCurso();
                }
            } catch (UniversidadeException e) {
                System.out.println("Erro ao realizar operação: " + e.getMessage());
            } catch (Exception e) {
                System.out.println("Erro inesperado! Informe a mensagem de erro ao administrador do sistema.");
                e.printStackTrace(System.out);
            }
        } while (opcao != 0);
    }

    public void lerDadosCurso(Curso curso) {
        System.out.println("*Campos obrigatórios.");
        System.out.println("Nome*: ");
        String nome = teclado.nextLine();
        System.out.println("Sigla*: ");
        String sigla = teclado.nextLine();
        System.out.println("Área*: ");
        String area = teclado.nextLine();
        curso.setNome(nome);
        curso.setSigla(sigla);
        curso.setArea(area);
    }

    public void cadastrarCurso() throws SQLException {
        System.out.println("Cadastro de curso");
        Curso curso = new Curso();
        this.lerDadosCurso(curso);
        if (!curso.getNome().trim().isEmpty() && !curso.getSigla().trim().isEmpty() && !curso.getArea().trim().isEmpty()) {
            CursoBO cursoBO = new CursoBO();
            cursoBO.inserir(curso);
            System.out.println("Curso cadastrado com sucesso!");
        } else {
            System.out.println("Curso não cadastrado. Favor informar todos os campos obrigatórios.");
        }
    }

    private void alterarCurso() throws SQLException {
        System.out.println("Atualização de curso");
        this.exibirCursosCadastrados();
        System.out.println("Informe a sigla do curso: ");
        String sigla = teclado.nextLine();

        CursoBO cursoBO = new CursoBO();
        Curso curso = cursoBO.buscarSigla(sigla);

        System.out.println("Informe os novos dados do curso.");
        this.lerDadosCurso(curso);
        if (!curso.getNome().trim().isEmpty() && !curso.getSigla().trim().isEmpty() && !curso.getArea().trim().isEmpty()) {
            cursoBO.atualizar(curso);
            System.out.println("Curso atualizado com sucesso!");
        } else {
            System.out.println("Curso não atualizado. Favor informar todos os campos obrigatórios.");
        }
    }

    private void removerCurso() throws SQLException {
        System.out.println("Remoção de curso");
        this.exibirCursosCadastrados();
        System.out.println("Informe a sigla do curso: ");
        String sigla = teclado.nextLine();

        CursoBO cursoBO = new CursoBO();
        Curso curso = cursoBO.buscarSigla(sigla);

        cursoBO.remover(curso.getId());
        System.out.println("Curso removido com sucesso!");

    }

    private void exibirCursosCadastrados() throws SQLException {
        System.out.println("Cursos cadastrados: ");
        CursoBO cursoBO = new CursoBO();
        List<Curso> cursos = cursoBO.buscarTodos();
        if (cursos.isEmpty()) {
            System.out.println("Nenhum curso cadastrado!");
        } else {
            System.out.println("Sigla\tNome");
            for (Curso curso : cursos) {
                System.out.println(curso.getSigla() + "\t" + curso.getNome());
            }
        }
    }

}
