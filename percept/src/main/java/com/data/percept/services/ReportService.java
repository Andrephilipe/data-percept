package com.data.percept.services;

import com.data.percept.models.OrderPaymentsCarnet;
import com.data.percept.repository.PaymentsCarnetRepository;

import com.itextpdf.io.font.constants.StandardFonts;
import com.itextpdf.kernel.font.PdfFontFactory;
import com.itextpdf.layout.element.Paragraph;
import com.itextpdf.layout.property.TextAlignment;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.util.Comparator;

@Service
@AllArgsConstructor
public class ReportService {

    private PaymentsCarnetRepository schoolRepository;
    private PaymentsCarnetRepository studentRepository;

    public ByteArrayInputStream report() throws IOException {
        ReportUtils report = ReportUtils.getInstance();

        report.addParagraph(new Paragraph("Valores parcelados")
                .setFontSize(13)
                .setTextAlignment(TextAlignment.CENTER)
                .setFont(PdfFontFactory.createFont(StandardFonts.COURIER_BOLD)));

        report.addNewLine();
        report.openTable(3);
        report.addTableHeader("NAME", "STUDENTS", "CREATED AT");

        report.closeTable();
        report.closeDocument();

        return report.getByteArrayInputStream();
    }

}
