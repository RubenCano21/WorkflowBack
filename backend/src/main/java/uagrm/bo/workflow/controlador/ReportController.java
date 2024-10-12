package uagrm.bo.workflow.controlador;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import uagrm.bo.workflow.servicio.EmailService;
import uagrm.bo.workflow.servicio.ExcelReportService;
import uagrm.bo.workflow.servicio.ReportService;

@RestController
@RequestMapping("/api/reports")
public class ReportController {

    @Autowired
    private ReportService reportService;

    @Autowired
    private ExcelReportService excelReportService;

    @Autowired
    private EmailService emailService;

    @GetMapping("/pdf")
    public ResponseEntity<byte[]> generarPdf() {
        byte[] pdf = reportService.generarReportePDF("Este es un reporte PDF");
        return ResponseEntity.ok()
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=reporte.pdf")
                .contentType(MediaType.APPLICATION_PDF)
                .body(pdf);
    }

    @GetMapping("/excel")
    public ResponseEntity<byte[]> generarExcel() {
        byte[] excel = excelReportService.generarReporteExcel();
        return ResponseEntity.ok()
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=reporte.xlsx")
                .contentType(MediaType.APPLICATION_OCTET_STREAM)
                .body(excel);
    }

    @PostMapping("/send-email")
    public ResponseEntity<String> enviarEmailConReporte(@RequestParam String email) {
        byte[] pdf = reportService.generarReportePDF("Este es el reporte PDF que te enviamos por correo");
        emailService.enviarReporteEmail(email, "Reporte", "Adjunto se encuentra el reporte", pdf, "reporte.pdf");
        return ResponseEntity.ok("Correo enviado exitosamente");
    }
}
