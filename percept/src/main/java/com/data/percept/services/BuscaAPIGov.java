package com.data.percept.services;

import java.io.IOException;
import java.net.URI;

import com.data.percept.PerceptApplication;
import com.data.percept.implement.ConnectionDataImpl;
import com.data.percept.interfaces.BuscaAPIGovServices;
import com.data.percept.models.InfoResultsGOVList;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
/**
 * BuscaAPIGov
 */
@Service
public class BuscaAPIGov implements BuscaAPIGovServices {

public static Logger logger = LoggerFactory.getLogger(PerceptApplication.class);
    @Override
    public InfoResultsGOVList consultaInfo(String codeige, String mesAno)
            throws IOException, InterruptedException {
            logger.info("Inicio consultaInfo");
            ExcelService excelService = new ExcelService();
        try {

            URI endereco = URI.create("https://api.portaldatransparencia.gov.br/api-de-dados/auxilio-brasil-sacado-beneficiario-por-municipio?codigoIbge=" + codeige + "&mesAno=" + mesAno +"&pagina=1");
            logger.info("consultaInfo" + endereco);
            
            ConnectionDataImpl test = new ConnectionDataImpl();
            logger.info("consultaInfo chamando o conectaApiGov");

            excelService.criarArquivoExcel(mesAno);
            excelService.atualizarArquivoExcel();
            return test.conectaApiGog(endereco);
                
        } catch (Exception e) {
            // TODO: handle exception
            logger.info("Erro na consultaInfo" + e);
        }
        return null;
    }

}