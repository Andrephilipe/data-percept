package com.data.percept.funtions;

/**
 * Servicos oferecidos para conexao com Datasus para acesso ao CNS.
 * 
 * @author Andre Philipe
 *
 */

public interface ConnectionData {
    String requisicao(String xmlRequisicao);

}
