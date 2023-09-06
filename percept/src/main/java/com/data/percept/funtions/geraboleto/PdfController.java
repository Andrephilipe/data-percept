package com.data.percept.funtions.geraboleto;

import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Element;
import com.itextpdf.text.PageSize;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfWriter;

import net.glxn.qrgen.QRCode;
import net.glxn.qrgen.image.ImageType;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import com.itextpdf.text.Font;
import com.itextpdf.text.Image;

public class PdfController {

    public static void generatePdf() throws IOException, DocumentException {
        Document document = new Document(PageSize.A4);

        try {
            String textoParaQRCode = "Este é um exemplo de texto para o QR Code.";
            File arquivoQRCode = QRCode.from(textoParaQRCode).to(ImageType.PNG).file();

            PdfWriter.getInstance(document, new FileOutputStream("boleto.pdf"));
            document.open();
            // Você também pode gerar o código QR como um array de bytes
            ByteArrayOutputStream byteArrayOutputStream = QRCode.from(textoParaQRCode).to(ImageType.PNG).stream();
            byte[] byteArray = byteArrayOutputStream.toByteArray();
            // Carregar a imagem a ser adicionada ao PDF
            try (FileOutputStream fos = new FileOutputStream("qrcode.png")) {
                fos.write(byteArray);
            }
            Image imagem = Image.getInstance("qrcode.png");
            // Definir a posição e o tamanho da imagem no PDF
            imagem.setAbsolutePosition(100f, 100f); // coordenadas X e Y
            imagem.scaleAbsolute(200f, 100f); // largura e altura

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
            dadosBeneficiario.add(new Paragraph("Nome do Beneficiário: Nome da Empresa", fontDados));
            dadosBeneficiario.add(new Paragraph("CNPJ: 12.345.678/0001-90", fontDados));
            dadosBeneficiario.add(new Paragraph("Endereço: Rua da Empresa, 123", fontDados));
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
            infoPagamento.add(new Paragraph("Data de Vencimento: 10/09/2023", fontDados));
            infoPagamento.add(new Paragraph("Valor: R$ 500,00", fontDados));
            document.add(infoPagamento);

            document.close();

            System.out.println("Boleto gerado com sucesso!");
        } catch (DocumentException | IOException e) {
            e.printStackTrace();
        }
    }
}
