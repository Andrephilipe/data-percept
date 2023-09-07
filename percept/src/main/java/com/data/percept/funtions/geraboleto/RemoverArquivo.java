package com.data.percept.funtions.geraboleto;

import java.io.File;

public class RemoverArquivo {
    public static void deletearquivos(String nameArquivo){
        // Especifique o caminho do arquivo que deseja remover

        // Crie um objeto File com o caminho do arquivo
        File arquivo = new File(nameArquivo);

        // Verifique se o arquivo existe antes de tentar removê-lo
        if (arquivo.exists()) {
            // Tente remover o arquivo
            if (arquivo.delete()) {
                System.out.println("O arquivo foi removido com sucesso.");
            } else {
                System.err.println("Não foi possível remover o arquivo.");
            }
        } else {
            System.err.println("O arquivo não existe.");
        }
    }
}
