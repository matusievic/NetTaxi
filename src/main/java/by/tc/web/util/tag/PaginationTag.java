package by.tc.web.util.tag;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.tagext.TagSupport;
import java.io.IOException;

public class PaginationTag extends TagSupport {
    private static final long serialVersionUID = 1279202325322286182L;

    private String command;

    public void setCommand(String command) {
        this.command = command;
    }

    @Override
    public int doStartTag() throws JspException {
        Integer currentPage = (Integer) pageContext.getRequest().getAttribute("currentPage");
        if (currentPage == null) { return SKIP_BODY; }

        Integer lastPage = (Integer) pageContext.getRequest().getAttribute("lastPage");
        if (lastPage == null) { return SKIP_BODY; }

        StringBuilder builder = new StringBuilder();
        if (currentPage != 1) {
            builder.append("<a href=\"/controller?command=" + command + "&page=1\"><-</a>");
        }

        builder.append("<table><tr>");
        for (int i = 1; i <= lastPage; i++) {
            if (i != currentPage) {
                builder.append("<td><a href=\"/controller?command=").append(command).append("&page=").append(i).append("\">").append(i).append("</td>");
            } else {
                builder.append("<td>").append(i).append("</td>");
            }
        }
        builder.append("</tr></table>");

        if (currentPage != lastPage) {
            builder.append("<a href=\"/controller?command=").append(command).append("&page=").append(lastPage).append("\">-></a>");
        }

        try {
            pageContext.getOut().print(builder.toString());
        } catch (IOException e) {
            //TODO
        }
        return SKIP_BODY;
    }
}
