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
public class ReportsCarnet {
    public static final Logger logger = LoggerFactory.getLogger(ReportsCarnet.class);


    public static void criarArquivoExcel(String nomeDoArquivo, String numberContract)
            throws IOException {
        String nameExel = nomeDoArquivo + "-percept" + numberContract + ".xlsx";
        String sql = "SELECT * FROM percept." + retornaTable(nomeDoArquivo) + " where numero_contrato = \"" + numberContract + "\"";

        logger.info("criarArquivoExcel: inicio."+ sql);

        try (Connection connection = DatabaseConnection.getConnection();
                PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            ResultSet resultSet = preparedStatement.executeQuery();

            List<String> cabecatest = obterCabecalhosDinamicamente();
            logger.info("criarArquivoExcel: inicio.");
            logger.info("criarArquivoExcel: inicio."+ resultSet);

            Workbook workbook = new XSSFWorkbook();

            Sheet sheet = workbook.createSheet(retornaTable(nomeDoArquivo));
            CellStyle style = workbook.createCellStyle();
            style.setFillForegroundColor(IndexedColors.GREEN.getIndex());
            style.setFillPattern(FillPatternType.SOLID_FOREGROUND);

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
                String cpf = resultSet.getString("cpf_titular");
                String data = resultSet.getString("data_criacao");
                String vencimento = resultSet.getString("data_vencimento");
                String nome = resultSet.getString("nome_titular");
                Integer numeroParcelas = resultSet.getInt("numero_parcela");
                BigDecimal saldoDevedor = resultSet.getBigDecimal("saldo_devedor");
                BigDecimal valorParcelas = resultSet.getBigDecimal("valor_parcelas");
                String status = resultSet.getString("status_parcela");
                
                atualizarArquivoExcel(nameExel, retornaTable(nomeDoArquivo), id, cpf, data, vencimento, nome, numeroParcelas,
                        saldoDevedor,valorParcelas,status);

                workbook.close();
            }

        } catch (Exception e) {

            logger.error("criarArquivoExcel: erro.", e);
        }

    }

    private static List<String> obterCabecalhosDinamicamente() throws SQLException {
        // Substitua isto pela lógica de obtenção de cabeçalhos dinâmicos
        return List.of("id_remessa", "cpf_titular", "data_criacao", "data_vencimento", "nome_titular", "numero_parcela",
                "saldo_devedor", "valor_parcelas", "status_parcela");
    }

    public static void atualizarArquivoExcel(String nameExel, String nomeTabela, Long id, String cpf,
            String datacriacao, String datavencimento, String nome, Integer numberParcelas, BigDecimal saldoDevedor, BigDecimal valorParcelas, String status) throws IOException {

        FileInputStream fileInputStream = new FileInputStream(nameExel);
        Workbook workbook = new XSSFWorkbook(fileInputStream);

        Sheet sheet = workbook.getSheet(nomeTabela);

        Row newRow = sheet.createRow(sheet.getLastRowNum() + 1);
        newRow.createCell(0).setCellValue(id);
        newRow.createCell(1).setCellValue(cpf);
        newRow.createCell(2).setCellValue(datacriacao);
        newRow.createCell(3).setCellValue(datavencimento);

        newRow.createCell(4).setCellValue(nome);
        newRow.createCell(5).setCellValue(numberParcelas);

        Cell cellSaldoDevedor = newRow.createCell(6);
        DataFormat df = workbook.createDataFormat();
        CellStyle style = workbook.createCellStyle();
        style.setDataFormat(df.getFormat("#,##0.00"));

        style.setFillForegroundColor(IndexedColors.YELLOW.getIndex()); // Cor de preenchimento
        style.setFillPattern(FillPatternType.SOLID_FOREGROUND);

        double valorDouble = saldoDevedor.doubleValue();
        cellSaldoDevedor.setCellValue(valorDouble);
        cellSaldoDevedor.setCellStyle(style);

        Cell cellValorParcelas = newRow.createCell(7);
        double valorParcelasDouble = valorParcelas.doubleValue();
        cellValorParcelas.setCellValue(valorParcelasDouble);
        cellValorParcelas.setCellStyle(style);

        newRow.createCell(8).setCellValue(status);

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
