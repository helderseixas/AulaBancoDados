/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package br.edu.ifnmg.universidade.excecao;

/**
 *
 * @author helder
 */
public class CursoNomeDuplicadoException extends UniversidadeException{

    public CursoNomeDuplicadoException(String nomeCurso) {
        super("Já existe um curso com o nome "+nomeCurso+".");
    }
    
}
