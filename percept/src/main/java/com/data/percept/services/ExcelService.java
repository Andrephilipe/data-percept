package com.data.percept.services;

import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.stereotype.Service;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;

@Service
public class ExcelService {

    public void criarArquivoExcel(String nomeDoArquivo) throws IOException {
         // Obtenha os cabeçalhos dinamicamente (por exemplo, a partir de uma lista)
         List<String> cabecalhos = obterCabecalhosDinamicamente();

         // Crie um novo livro de Excel
         Workbook workbook = new XSSFWorkbook();
 
         // Crie uma planilha no livro
         Sheet sheet = workbook.createSheet("Dados");
 
         // Defina os cabeçalhos das colunas dinamicamente
         Row headerRow = sheet.createRow(0);
         for (int i = 0; i < cabecalhos.size(); i++) {
             headerRow.createCell(i).setCellValue(cabecalhos.get(i));
         }
 
         // Adicione dados às colunas (exemplo com loop)
         for (int i = 1; i <= 10; i++) { // Adicione 10 linhas de dados
             Row dataRow = sheet.createRow(i);
             for (int j = 0; j < cabecalhos.size(); j++) {
                 dataRow.createCell(j).setCellValue("Dado " + i + cabecalhos.get(j));
             }
         }
         try (FileOutputStream outputStream = new FileOutputStream("exemplo.xlsx")) {
            workbook.write(outputStream);
        }
         // Defina o cabeçalho da resposta para indicar que é um arquivo Excel
 
         // Escreva o livro de Excel na resposta
         // Feche o livro de Excel
         workbook.close();
     }
 
     // Simule a obtenção de cabeçalhos dinamicamente (a partir de uma lista, banco de dados, etc.)
     private List<String> obterCabecalhosDinamicamente() {
         // Substitua isto pela lógica de obtenção de cabeçalhos dinâmicos
         return List.of("Nome", "Idade", "Cidade", "Email");
     }
 
    public void atualizarArquivoExcel() throws IOException {
        // Abra o arquivo Excel existente
        FileInputStream fileInputStream = new FileInputStream("exemplo.xlsx");
        Workbook workbook = new XSSFWorkbook(fileInputStream);

        // Obtenha a planilha desejada
        Sheet sheet = workbook.getSheet("Exemplo");

        // Crie ou atualize os dados nas células conforme necessário
        Row newRow = sheet.createRow(sheet.getLastRowNum() + 1);
        newRow.createCell(0).setCellValue("Carlos");
        newRow.createCell(1).setCellValue(28);

        // Salve o arquivo Excel atualizado
        try (FileOutputStream outputStream = new FileOutputStream("exemplo.xlsx")) {
            workbook.write(outputStream);
        }

        // Feche o livro de Excel
        workbook.close();
        fileInputStream.close();
    }
}

