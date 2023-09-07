package com.data.percept.funtions.geraboleto;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.data.percept.models.RemessaBoleto;
import com.itextpdf.text.DocumentException;

public class GeradorDeBoletos {

    public static Logger logger = LoggerFactory.getLogger(GeradorDeBoletos.class);

    public static void geraBoleto(String status) throws IOException, DocumentException {

        String sql = "SELECT * FROM pessoa.remessa_boleto where status_boleto = \"" + status + "\"";
        logger.info("UsuarioDAO: generatePdf");

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
                geraBoletoNew.setValor(resultSet.getString("valor"));
                Boolean results = PdfController.generatePdf(geraBoletoNew);

                if (results) {
                    System.out.println("boleto gerado");
                    String updatSql = "UPDATE pessoa.remessa_boleto set status_boleto= \"boleto_gerado\" where id_remessa ="
                            + id;
                    try (PreparedStatement preparedStatement2 = connection.prepareStatement(updatSql)) {

                        preparedStatement2.executeUpdate();

                    } catch (Exception e) {
                        logger.info("UsuarioDAO: erro : " + e);
                        // TODO: handle exception
                    }

                }else{
                    logger.info("UsuarioDAO: erro : ");
                    System.out.println("boleto nao gerado");
                }

            }

        } catch (SQLException e) {
            logger.info("UsuarioDAO: erro : " + e);
            e.printStackTrace();
        }
    }
}
