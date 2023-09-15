package com.data.percept.funtions.geraarquivoexel;

import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.data.percept.funtions.connection.DatabaseConnection;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@Service
public class ExcelServiceBoleto {
    public static final Logger logger = LoggerFactory.getLogger(ExcelServiceBoleto.class);

    private ExcelServiceBoleto() {
        logger.info("ExcelServiceBoleto start");
        throw new IllegalStateException("ExcelServiceBoleto");
    }

    public static void criarArquivoExcel(String nomeDoArquivo, String datainicial)
            throws IOException {
        String nameExel = nomeDoArquivo + "-percept" + datainicial+ ".xlsx";
        String sql = "SELECT * FROM percept." + retornaTable(nomeDoArquivo) + " where data_criacao = \'" + datainicial
                + "\'";

        logger.info("criarArquivoExcel: inicio.");

        try (Connection connection = DatabaseConnection.getConnection();
                PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            ResultSet resultSet = preparedStatement.executeQuery();

            List<String> cabecatest = obterCabecalhosDinamicamente();
            logger.info("criarArquivoExcel: inicio.");

            Workbook workbook = new XSSFWorkbook();

            Sheet sheet = workbook.createSheet(retornaTable(nomeDoArquivo));

            Row headerRow = sheet.createRow(0);
            for (int i = 0; i < cabecatest.size(); i++) {
                logger.info("criarArquivoExcel: primeiro for.");
                headerRow.createCell(i).setCellValue(cabecatest.get(i));
            }

            try (FileOutputStream outputStream = new FileOutputStream(nameExel)) {
                workbook.write(outputStream);
            }

            while (resultSet.next()) {
                logger.info("processaExel: while.");
                Long id = resultSet.getLong("id_remessa");
                String nome = resultSet.getString("nome_titular");
                String data = resultSet.getString("data_criacao");
                String vencimento = resultSet.getString("data_vencimento");
                BigDecimal valor = resultSet.getBigDecimal("valor");
                String status = resultSet.getString("status_boleto");
                String cpf = resultSet.getString("cpf_titular");
                atualizarArquivoExcel(nameExel, retornaTable(nomeDoArquivo), id, nome, data, vencimento, valor, status,
                        cpf);

                workbook.close();
            }

        } catch (Exception e) {

            logger.error("criarArquivoExcel: erro.", e);
        }

    }

    private static List<String> obterCabecalhosDinamicamente() throws SQLException {
        // Substitua isto pela lógica de obtenção de cabeçalhos dinâmicos
        return List.of("id_remessa", "nome_titular", "data_criacao", "data_vencimento", "valor", "status_boleto",
                "cpf_titular", "valor_total");
    }

    public static void atualizarArquivoExcel(String nameExel, String nomeTabela, Long id, String nome,
            String datacriacao, String datavencimento, BigDecimal valor, String status, String cpf) throws IOException {

        FileInputStream fileInputStream = new FileInputStream(nameExel);
        Workbook workbook = new XSSFWorkbook(fileInputStream);

        Sheet sheet = workbook.getSheet(nomeTabela);

        Row newRow = sheet.createRow(sheet.getLastRowNum() + 1);
        newRow.createCell(0).setCellValue(id);
        newRow.createCell(1).setCellValue(nome);
        newRow.createCell(2).setCellValue(datacriacao);
        newRow.createCell(3).setCellValue(datavencimento);

        Cell cell = newRow.createCell(4);
        DataFormat df = workbook.createDataFormat();
        CellStyle style = workbook.createCellStyle();
        style.setDataFormat(df.getFormat("#,##0.00"));

        style.setFillForegroundColor(IndexedColors.YELLOW.getIndex()); // Cor de preenchimento
        style.setFillPattern(FillPatternType.SOLID_FOREGROUND);

        double valorDouble = valor.doubleValue();
        cell.setCellValue(valorDouble);
        cell.setCellStyle(style);

        newRow.createCell(5).setCellValue(status);
        newRow.createCell(6).setCellValue(cpf);

        double soma = 0;
        for (int rowIndex = 1; rowIndex <= sheet.getLastRowNum(); rowIndex++) {
            Row row = sheet.getRow(rowIndex);

            // Obtenha o valor da coluna "Valores" (supondo que seja a coluna A, ou seja,
            // índice 0)
            Cell cellSoma = row.getCell(4, Row.MissingCellPolicy.CREATE_NULL_AS_BLANK);

            // Verifique se a célula contém um valor numérico
            if (cellSoma.getCellType() == CellType.NUMERIC) {
                soma += cell.getNumericCellValue();
            }
        }
        Cell somaCell = newRow.createCell(7);
        somaCell.setCellValue(soma);
        somaCell.setCellStyle(style);

        try (FileOutputStream outputStream = new FileOutputStream(nameExel)) {
            workbook.write(outputStream);
        }

        workbook.close();
        fileInputStream.close();
    }

    public static String retornaTable(String nomeType) {
        switch (nomeType) {
            case "boleto":
                nomeType = "order_payments_boleto";
                break;
            case "carnet":
                nomeType = "order_payments_carnet";
                break;
            case "pix":
                nomeType = "order_payments_pix";
                break;
            default:
                break;
        }
        return nomeType;
    }

}
