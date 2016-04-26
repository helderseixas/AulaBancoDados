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
public class CursoSiglaInvalidaException extends UniversidadeException{

    public CursoSiglaInvalidaException() {
        super("A sigla do curso deve ter no mínimo 3 caracteres e no máximo 4.");
    }
    
}
