package com.data.percept.funtions.geraboleto;

import java.io.ByteArrayInputStream;
import java.io.IOException;

import com.itextpdf.layout.element.Paragraph;
import com.data.percept.services.ReportUtils;
import com.itextpdf.io.font.constants.StandardFonts;
import com.itextpdf.kernel.font.PdfFontFactory;
import com.itextpdf.layout.property.TextAlignment;


public class RelatorioController {


    public static ByteArrayInputStream gerarPdf() throws IOException {
        // Crie um PDF (exemplo usando iText)
        ReportUtils report = ReportUtils.getInstance();
        report.addParagraph(new Paragraph("Valores parcelados")
                .setFontSize(13)
                .setTextAlignment(TextAlignment.CENTER)
                .setFont(PdfFontFactory.createFont(StandardFonts.COURIER_BOLD)));

                
        report.addNewLine();
        report.openTable(4);
        report.addTableHeader("NAME", "STUDENTS", "CREATED AT", "VALOR");

        report.closeDocument();
        // Retorne o PDF na resposta HTTP
        return report.getByteArrayInputStream();
    }
}
