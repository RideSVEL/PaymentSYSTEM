package ua.nure.vasilchenko.SummaryTask4.web.command.client;

import com.itextpdf.text.Anchor;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Font;
import com.itextpdf.text.FontFactory;
import com.itextpdf.text.Image;
import com.itextpdf.text.PageSize;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.CMYKColor;
import com.itextpdf.text.pdf.PdfWriter;
import org.apache.log4j.Logger;
import ua.nure.vasilchenko.SummaryTask4.Path;
import ua.nure.vasilchenko.SummaryTask4.db.DBManager;
import ua.nure.vasilchenko.SummaryTask4.db.entity.Payment;
import ua.nure.vasilchenko.SummaryTask4.exception.AppException;
import ua.nure.vasilchenko.SummaryTask4.exception.DBException;
import ua.nure.vasilchenko.SummaryTask4.exception.Messages;
import ua.nure.vasilchenko.SummaryTask4.web.command.base.Command;

import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.rmi.server.ExportException;
import java.util.Date;

/**
 * Command generated PDF report by
 *
 * @author S. Vasilchenko
 */
public class GetPDFCmd extends Command {

    private static final long serialVersionUID = 78548525105L;

    private static final Logger LOG = Logger.getLogger(GetPDFCmd.class);

    /**
     * Generate pdf file, by data in DB.
     * Using library ITEXTPDF-5.5.13.1.
     *
     * @param paymentId - identifier payment on DB.
     * @return name, of final file with report.
     * @throws DocumentException
     * @throws IOException
     * @throws DBException
     */
    private String getPdf(long paymentId) throws DocumentException, IOException, DBException {
        Document document = new Document(PageSize.A4, 50, 50, 50, 50);

        DBManager manager = DBManager.getInstance();
        Payment payment = manager.findPayment(paymentId);
        LOG.trace("found in DB: payment --> " + payment);
        String pdfPaymentId = "Id of your payment - " + payment.getId();
        String pdfCardNumber = "Number of your card with which payment was made - " + manager.findCard(payment.getCardId()).getNumber();
        String pdfCardDestinationNumber = "Beneficiary card number - " + manager.findCard(payment.getCardDestinationId()).getNumber();
        String pdfDate = "Date of your payment - " + payment.getDate();
        String pdfMoney = "Sum of your payment - " + payment.getMoney() + " UAN";
        String pdfBalance = "Balance of your card after sending payment - " + payment.getBalance();

        String filename = "reportPayment" + paymentId + ".pdf";
        // Listing 2. Creation of PdfWriter object
        PdfWriter writer = PdfWriter.getInstance(document,
                new FileOutputStream(filename));

        document.open();

        document.add(new Paragraph("PAYMENT SYSTEM",
                FontFactory.getFont(FontFactory.TIMES_ROMAN, 30, Font.BOLD,
                        new CMYKColor(0, 255, 0, 0))));
        Image image2 = Image.getInstance("D:\\JAVA\\PaymentSystem\\web\\img\\moneyicom.png");
        image2.scaleAbsolute(120f, 120f);
        document.add(image2);
        Anchor anchorTarget = new Anchor("Your successfully send payment!\n");
        anchorTarget.setName("BackToTop");
        Paragraph paragraph1 = new Paragraph();
        paragraph1.setSpacingBefore(50);
        paragraph1.add(anchorTarget);
        document.add(paragraph1);
        document.add(new Paragraph("Date of forming report: " + new Date().toString()));
        document.add(new Paragraph(pdfPaymentId));
        document.add(new Paragraph(pdfCardNumber));
        document.add(new Paragraph(pdfCardDestinationNumber));
        document.add(new Paragraph(pdfDate));
        document.add(new Paragraph(pdfMoney));
        document.add(new Paragraph(pdfBalance));
        document.add(new Paragraph("Thank you for choosing us."));
        LOG.trace("add content on pdf --> " + document);
        document.close();
        LOG.debug("closing file --> " + document);
        return filename;
    }

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException, AppException {
        LOG.debug("Command starts");

        String paymentId = request.getParameter("payment_id");
        LOG.trace("getting request parament: paymentId --> " + paymentId);
        if (paymentId == null || paymentId.isEmpty()) {
            throw new AppException(Messages.ERROR);
        }
        String fileName = null;
        try {
            fileName = getPdf(Integer.parseInt(paymentId));
        } catch (DocumentException e) {
            e.printStackTrace();
        }
        File file = new File(fileName);
        ServletOutputStream outputStream = null;
        BufferedInputStream inputStream = null;
        try {
            outputStream = response.getOutputStream();
            response.setContentType("report/pdf");
            response.setHeader("Content-Disposition", "attachment; filename=\"" + fileName + "\"");
            response.setContentLength((int) file.length());
            FileInputStream fileInputStream = new FileInputStream(file);
            inputStream = new BufferedInputStream(fileInputStream);
            int readBytes;
            LOG.debug("sending to user pdf --> ");
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
            LOG.debug("File delete successfully with name - " + fileName);
        } else {
            LOG.debug("File with name - " + fileName + " not found");
        }

        LOG.debug("Command finished");
        return Path.COMMAND_USER_PAYMENTS;
    }
}

