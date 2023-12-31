package com.data.percept.funtions.geraarquivoexel;

import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.data.percept.models.InfoResultsGOV;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;

@Service
public class ExcelService {
    public static final Logger logger = LoggerFactory.getLogger(ExcelService.class);

    public void criarArquivoExcel(String nomeDoArquivo, String nomeTabela) throws IOException {
        try {
            List<String> cabecatest = obterCabecalhosDinamicamente();
            logger.info("criarArquivoExcel: inicio.");

            logger.info("criarArquivoExcel: list cabecatest." + cabecatest);

            Workbook workbook = new XSSFWorkbook();

            Sheet sheet = workbook.createSheet(nomeTabela);

            Row headerRow = sheet.createRow(0);
            for (int i = 0; i < cabecatest.size(); i++) {
                logger.info("criarArquivoExcel: primeiro for.");
                headerRow.createCell(i).setCellValue(cabecatest.get(i));
            }

            try (FileOutputStream outputStream = new FileOutputStream(nomeDoArquivo + ".xlsx")) {
                workbook.write(outputStream);
            }
            workbook.close();
        } catch (Exception e) {

            logger.info("criarArquivoExcel: erro." + e);
        }

    }

    private List<String> obterCabecalhosDinamicamente() {
        // Substitua isto pela lógica de obtenção de cabeçalhos dinâmicos
        return List.of("Id", "Mes Competencia", "Mes Referencia");
    }

    public void atualizarArquivoExcel(String id, String mesCompetencia, String mesReferencia) throws IOException {
        FileInputStream fileInputStream = new FileInputStream("teste.xlsx");
        Workbook workbook = new XSSFWorkbook(fileInputStream);

        Sheet sheet = workbook.getSheet("gov");

        Row newRow = sheet.createRow(sheet.getLastRowNum() + 1);
        newRow.createCell(0).setCellValue(id);
        newRow.createCell(1).setCellValue(mesCompetencia);
        newRow.createCell(3).setCellValue(mesReferencia);

        try (FileOutputStream outputStream = new FileOutputStream("teste.xlsx")) {
            workbook.write(outputStream);
        }

        workbook.close();
        fileInputStream.close();
    }

    public void analisarResults(List<InfoResultsGOV> results) throws IOException {
        // Substitua isto pela lógica de obtenção de cabeçalhos dinâmicos
        for (InfoResultsGOV result : results) {

            atualizarArquivoExcel(result.getId().toString(), result.getDataMesCompetencia(),
                    result.getDataMesReferencia());
        }

    }
}
