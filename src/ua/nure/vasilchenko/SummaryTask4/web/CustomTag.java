package ua.nure.vasilchenko.SummaryTask4.web;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.tagext.SimpleTagSupport;
import java.io.IOException;
import java.io.StringWriter;

/**
 * Tag , that is empty by default and
 * writing that everything will be fine
 */
public class CustomTag extends SimpleTagSupport {
    StringWriter sw = new StringWriter();
    public void doTag() throws IOException, JspException {
        getJspBody().invoke(sw);
        getJspContext().getOut().println(sw.toString());
    }
}
