package ua.nure.vasilchenko.SummaryTask4.web.command.admin;

import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.PageSize;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfWriter;
import ua.nure.vasilchenko.SummaryTask4.Path;
import ua.nure.vasilchenko.SummaryTask4.db.DBManager;
import ua.nure.vasilchenko.SummaryTask4.db.entity.Payment;
import ua.nure.vasilchenko.SummaryTask4.exception.AppException;
import ua.nure.vasilchenko.SummaryTask4.web.command.base.Command;

import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;

import java.rmi.server.ExportException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.sql.Date;
import java.util.List;

public class CreateSelectionPayment extends Command {
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException, AppException {
        String selection = request.getParameter("selection");
        if (selection == null || selection.isEmpty()) {
            throw new AppException("Error");
        }
        DBManager manager = DBManager.getInstance();
        List<Payment> payments = manager.getPaymentsByDate(selection);
//        System.out.println(selection.toString());
//        for (Payment payment : payments) {
//            System.out.println(payment);
//        }
        Document document = new Document(PageSize.A4, 50, 50, 50, 50);
        String filename = "selection.pdf";
        PdfWriter writer = null;
        try {
            writer = PdfWriter.getInstance(document,
                    new FileOutputStream(filename));
        } catch (DocumentException e) {
            e.printStackTrace();
        }
        document.open();
        if (payments.size() != 0) {
        try {
            for (Payment payment : payments) {
                document.add(new Paragraph(payment.toString()));
            }
        } catch (DocumentException e) {
            e.printStackTrace();
        }
        } else {
            try {
                document.add(new Paragraph("Null"));
            } catch (DocumentException e) {
                e.printStackTrace();
            }
        }
        document.close();
        File file = new File(filename);
        ServletOutputStream outputStream = null;
        BufferedInputStream inputStream = null;
        try {
            outputStream = response.getOutputStream();
            response.setContentType("report/pdf");
            response.setHeader("Content-Disposition", "attachment; filename=\"" + filename + "\"");
            response.setContentLength((int) file.length());
            FileInputStream fileInputStream = new FileInputStream(file);
            inputStream = new BufferedInputStream(fileInputStream);
            int readBytes;
            while ((readBytes = inputStream.read()) != -1)
                outputStream.write(readBytes);
        } catch (ExportException e) {
            e.printStackTrace();
        } finally {
            outputStream.flush();
            outputStream.close();
            inputStream.close();
        }
        if (file.delete()) {
            System.out.println("Successes");
        } else {
            System.out.println("Error");
        }
        return Path.COMMAND_LIST_USERS;
    }
}
