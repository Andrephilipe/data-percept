package com.data.percept.funtions.downpayments;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.data.percept.funtions.connection.DatabaseConnection;
import com.data.percept.funtions.geraboleto.PdfController;
import com.data.percept.models.RemessaBoleto;
import com.itextpdf.text.DocumentException;

public class DownPaymentsFuntions {

    public static final Logger logger = LoggerFactory.getLogger(DownPaymentsFuntions.class);

    private DownPaymentsFuntions() {
        logger.info("DownPaymentsFuntions start");
        throw new IllegalStateException("DownPaymentsFuntions");
    }

    public static void executePayment (long idRecept, String nameTable) throws IOException, DocumentException {

        String sql = "SELECT * FROM \""+ nameTable + "\" where id = " + idRecept ;
        logger.info("executePayment: generatePdf");

        try (Connection connection = DatabaseConnection.getConnection();
                PreparedStatement preparedStatement = connection.prepareStatement(sql)) {

            ResultSet resultSet = preparedStatement.executeQuery();
            RemessaBoleto geraBoletoNew = new RemessaBoleto();

            while (resultSet.next()) {
                String id = resultSet.getString("id_remessa");
                String email = resultSet.getString("nome_titular");
                System.out.println("Nome: " + id + ", Email: " + email);

                geraBoletoNew.setCpf(resultSet.getString("cpf_titutar"));
                geraBoletoNew.setDataVencimento(resultSet.getString("data_vencimento"));
                geraBoletoNew.setNomeTitular(resultSet.getString("nome_titular"));
                geraBoletoNew.setMunicipio(resultSet.getString("municipio"));
                geraBoletoNew.setId(resultSet.getLong("id_remessa"));
                geraBoletoNew.setValor(resultSet.getBigDecimal("valor"));
                Boolean results = PdfController.generatePdf(geraBoletoNew);

                if (Boolean.TRUE.equals(results)) {
                    System.out.println("boleto gerado");
                    String updatSql = "UPDATE pessoa.remessa_boleto set status_boleto= \"boleto_gerado\" where id_remessa ="
                            + id;
                    try (PreparedStatement preparedStatement2 = connection.prepareStatement(updatSql)) {

                        preparedStatement2.executeUpdate();

                    } catch (Exception e) {
                        logger.error("DownPaymentsFuntions: erro : while : " , e);
                    }

                }else{
                    logger.info("DownPaymentsFuntions: erro : ");
                    System.out.println("boleto nao gerado");
                }

            }

        } catch (SQLException e) {
            logger.error("DownPaymentsFuntions: erro : " , e);
            e.printStackTrace();
        }
    }
}
