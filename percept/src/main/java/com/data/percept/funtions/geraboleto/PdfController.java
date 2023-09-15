package com.data.percept.funtions.geraboleto;

import com.data.percept.models.OrderPaymentsBoleto;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Element;
import com.itextpdf.text.PageSize;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfWriter;

import net.glxn.qrgen.QRCode;
import net.glxn.qrgen.image.ImageType;

import java.io.ByteArrayOutputStream;
import java.io.FileOutputStream;
import java.io.IOException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

import com.itextpdf.text.Font;
import com.itextpdf.text.Image;

@RestController
public class PdfController {
    public static Logger logger = LoggerFactory.getLogger(PdfController.class);


    public static Boolean generatePdf(OrderPaymentsBoleto remessaBoleto) throws IOException, DocumentException {
        Document document = new Document(PageSize.A4);

        try {
            String diretorioPdfs = "C://Users//andre.p.cassiano//";
            String diretorioImages = "images/";

            logger.info("createBoleto: generatePdf" + remessaBoleto.getNomeTitular());

            String textoParaQRCode = "Este é um exemplo de texto para o QR Code.";
            String nameIgems = diretorioImages + remessaBoleto.getId() + "qrcode.png";

            PdfWriter.getInstance(document, new FileOutputStream(diretorioPdfs + remessaBoleto.getNomeTitular()+ "-" + remessaBoleto.getId() + "-"+ "boleto.pdf"));
            document.open();
            // Você também pode gerar o código QR como um array de bytes
            ByteArrayOutputStream byteArrayOutputStream = QRCode.from(textoParaQRCode).to(ImageType.PNG).stream();
            byte[] byteArray = byteArrayOutputStream.toByteArray();
            // Carregar a imagem a ser adicionada ao PDF
            try (FileOutputStream fos = new FileOutputStream(nameIgems)) {
                fos.write(byteArray);
            }
            Image imagem = Image.getInstance(nameIgems);
            // Definir a posição e o tamanho da imagem no PDF
            imagem.setAbsolutePosition(100f, 100f); // coordenadas X e Y
            imagem.scaleAbsolute(200f, 200f); // largura e altura

            // Adicionar a imagem ao documento
            document.add(imagem);

            // Título

            Font fontTitulo = new Font(Font.FontFamily.HELVETICA, 14, Font.BOLD);
            Paragraph titulo = new Paragraph("Boleto Bancário", fontTitulo);
            titulo.setAlignment(Element.PTABLE);
            document.add(titulo);

            // Dados do Beneficiário
            Font fontDados = new Font(Font.FontFamily.HELVETICA, 12);
            Paragraph dadosBeneficiario = new Paragraph();
            dadosBeneficiario.add(new Paragraph("Nome do Beneficiário: " + remessaBoleto.getNomeTitular(), fontDados));
            dadosBeneficiario.add(new Paragraph("CNPJ: " + remessaBoleto.getCpf(), fontDados));
            dadosBeneficiario.add(new Paragraph("Endereço: " + remessaBoleto.getMunicipio(), fontDados));
            dadosBeneficiario.add(new Paragraph("Cidade/Estado - CEP: 12345-678", fontDados));
            document.add(dadosBeneficiario);

            // Dados do Pagador
            Paragraph dadosPagador = new Paragraph();
            dadosPagador.add(new Paragraph("Nome do Pagador: Cliente XYZ", fontDados));
            dadosPagador.add(new Paragraph("CPF: 123.456.789-00", fontDados));
            dadosPagador.add(new Paragraph("Endereço: Rua do Cliente, 456", fontDados));
            dadosPagador.add(new Paragraph("Cidade/Estado - CEP: 54321-987", fontDados));
            document.add(dadosPagador);

            // Informações de Pagamento
            Paragraph infoPagamento = new Paragraph();
            infoPagamento.add(new Paragraph("Data de Vencimento: " + remessaBoleto.getDataVencimento(), fontDados));
            infoPagamento.add(new Paragraph("Valor: " +  remessaBoleto.getValor(), fontDados));
            document.add(infoPagamento);

            document.close();
            RemoverArquivo.deletearquivos(nameIgems);

            System.out.println("Boleto gerado com sucesso!");
            return true;
        } catch (DocumentException | IOException e) {
            e.printStackTrace();
            return false;
        }
    }
}
