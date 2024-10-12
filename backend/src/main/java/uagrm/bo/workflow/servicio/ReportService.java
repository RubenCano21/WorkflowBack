package uagrm.bo.workflow.servicio;

import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.kernel.pdf.PdfWriter;
import com.itextpdf.layout.Document;
import com.itextpdf.layout.element.Paragraph;
import org.springframework.stereotype.Service;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;

@Service
public class ReportService {

    public byte[] generarReportePDF(String contenido){

        ByteArrayOutputStream out = new ByteArrayOutputStream();

        try (PdfWriter writer = new PdfWriter(out);
             PdfDocument pdfDoc = new PdfDocument(writer);
             Document document = new Document(pdfDoc)){

            document.add(new Paragraph(contenido));
        } catch (Exception e) {
            e.printStackTrace();
        }
        return out.toByteArray();
    }
}
