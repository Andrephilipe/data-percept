package com.data.percept.funtions.geraarquivoexel;

import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.data.percept.funtions.connection.DatabaseConnection;
import com.data.percept.models.RemessaCarnet;

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

    public static void criarArquivoExcel(String nomeDoArquivo, String datainicial, String datafinal)
            throws IOException {
        ;
        String sql = "SELECT * FROM pessoa." + retornaTable(nomeDoArquivo) + " WHERE data_criacao BETWEEN \'"
                + datainicial + "\' AND \'"
                + datafinal + "\'";
        logger.info("criarArquivoExcel: inicio." + sql);
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

            try (FileOutputStream outputStream = new FileOutputStream(nomeDoArquivo + ".xlsx")) {
                workbook.write(outputStream);
            }
            workbook.close();

            processaExel(resultSet, nomeDoArquivo);

        } catch (Exception e) {

            logger.error("criarArquivoExcel: erro.", e);
        }

    }

    private static List<String> obterCabecalhosDinamicamente() throws SQLException {
        // Substitua isto pela lógica de obtenção de cabeçalhos dinâmicos
        return List.of("id_remessa", "nome_titular", "data_criacao", "data_vencimento", "valor", "parcelas");
    }

    public static void atualizarArquivoExcel(String nomeArquivo, String id, String mesCompetencia, String mesReferencia,
            String datavencimento, BigDecimal valor, Integer parcelas) throws IOException {

        FileInputStream fileInputStream = new FileInputStream(nomeArquivo + ".xlsx");
        Workbook workbook = new XSSFWorkbook(fileInputStream);

        Sheet sheet = workbook.getSheet(nomeArquivo);

        Row newRow = sheet.createRow(sheet.getLastRowNum() + 1);
        newRow.createCell(0).setCellValue(id);
        newRow.createCell(1).setCellValue(mesCompetencia);
        newRow.createCell(2).setCellValue(mesReferencia);
        newRow.createCell(3).setCellValue(datavencimento);
        double valorDouble = valor.doubleValue();
        newRow.createCell(4).setCellValue(valorDouble);
        newRow.createCell(5).setCellValue(parcelas);

        try (FileOutputStream outputStream = new FileOutputStream(nomeArquivo + ".xlsx")) {
            workbook.write(outputStream);
        }

        workbook.close();
        fileInputStream.close();
    }

    public void analisarResults(List<RemessaCarnet> results, String nomeArquivo) throws IOException {
        // Substitua isto pela lógica de obtenção de cabeçalhos dinâmicos
        for (RemessaCarnet result : results) {

            atualizarArquivoExcel(nomeArquivo, result.getId().toString(), result.getDataCriacao(),
                    result.getDataVencimento(), result.getDataCriacao(), result.getValor(), result.getParcelas());
        }

    }

    public static String retornaTable(String nomeType) {
        switch (nomeType) {
            case "boleto":
                nomeType = "remessa_boleto";
                break;
            case "carnet":
                nomeType = "remessa_carnet";
                break;
            case "pix":
                nomeType = "remessa_pix";
                break;
            default:
                break;
        }
        return nomeType;
    }

    public static void processaExel(ResultSet resultSet, String nomeArquivo) throws IOException, SQLException {
        switch (nomeArquivo) {
            case "boleto":
                while (resultSet.next()) {
                    logger.info("processaExel: whele for.");
                    String id = resultSet.getString("id_remessa");
                    logger.info("processaExel: whele for." +id);

                    String email = resultSet.getString("nome_titular");
                    String data = resultSet.getString("data_criacao");
                    String vencimento = resultSet.getString("data_vencimento");
                    BigDecimal valor = resultSet.getBigDecimal("valor");
                    Integer parcelas = resultSet.getInt("quantidade_parcelas");
                    System.out.println("Nome: " + id + ", Email: " + email);
                    atualizarArquivoExcel("boleto", id, email, data, vencimento, valor, parcelas);
                }
                break;
            case "carnet":
                while (resultSet.next()) {
                    logger.info("criarArquivoExcel: whele for.");
                    String id = resultSet.getString("id_remessa");
                    String email = resultSet.getString("nome_titular");
                    String data = resultSet.getString("data_criacao");
                    String vencimento = resultSet.getString("data_vencimento");
                    BigDecimal valor = resultSet.getBigDecimal("valor");
                    Integer parcelas = resultSet.getInt("quantidade_parcelas");
                    System.out.println("Nome: " + id + ", Email: " + email);
                    atualizarArquivoExcel(nomeArquivo, id, email, data, vencimento, valor, parcelas);
                }

                break;
            case "pix":
                while (resultSet.next()) {
                    logger.info("criarArquivoExcel: whele for.");
                    String id = resultSet.getString("id_pix");
                    String email = resultSet.getString("nome_titular");
                    String data = resultSet.getString("data_criacao");
                    String vencimento = resultSet.getString("data_vencimento");
                    BigDecimal valor = resultSet.getBigDecimal("valor");
                    Integer parcelas = 7;
                    System.out.println("Nome: " + id + ", Email: " + email);
                    atualizarArquivoExcel(nomeArquivo, id, email, data, vencimento, valor, parcelas);
                }
                break;
            default:
                break;
        }

    }
}
