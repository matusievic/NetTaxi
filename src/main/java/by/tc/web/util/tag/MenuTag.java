package by.tc.web.util.tag;

import by.tc.web.domain.user.User;
import by.tc.web.domain.user.impl.Customer;
import by.tc.web.domain.user.impl.TaxiDriver;
import org.apache.log4j.Logger;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.tagext.TagSupport;
import java.io.IOException;
import java.util.Locale;
import java.util.ResourceBundle;

public class MenuTag extends TagSupport {
    private static final long serialVersionUID = 6535337274424481284L;
    private static final Logger logger = Logger.getLogger(MenuTag.class);

    @Override
    public int doStartTag() throws JspException {
        User user = (User) pageContext.getSession().getAttribute("user");

        String lang = (String) pageContext.getSession().getAttribute("locale");
        if (lang == null) {
            lang = "en";
        }

        ResourceBundle local = ResourceBundle.getBundle("localization/local", new Locale(lang));

        StringBuilder builder = new StringBuilder();
        if (user == null) {
            builder.append("<li><a href=\"/signin\">" + local.getString("account.sign_in") + "</a></li>");
            builder.append("<li><a href=\"/signup\">" + local.getString("account.sign_up") + "</a></li>");
        } else if (user.getClass() == Customer.class) {
            builder.append("<li><a href=\"/order\">" + local.getString("menu.order") + "</a></li>");
            builder.append("<li><a href=\"/controller?command=display_orders\">" + local.getString("menu.orders")+ "</a></li>");
            builder.append("<li><a href=\"/account\">" + local.getString("menu.account")+ "</a></li>");
            builder.append("<li><a href=\"/controller?command=close_session\">" + local.getString("menu.sign_out") + "</a></li>");
        } else if (user.getClass() == TaxiDriver.class) {
            builder.append("<li><a href=\"/controller?command=display_orders\">" + local.getString("menu.orders") + "</a></li>");
            builder.append("<li><a href=\"/account\">" + local.getString("menu.account") + "</a></li>");
            builder.append("<li><a href=\"/controller?command=close_session\">" + local.getString("menu.sign_out") + "</a></li>");
        } else {
            builder.append("<li><a href=\"/controller?command=display_customers\">" + local.getString("menu.customers") + "</a></li>");
            builder.append("<li><a href=\"/controller?command=display_taxidrivers\">" + local.getString("menu.drivers") + "</a></li>");
            builder.append("<li><a href=\"/controller?command=display_orders\">" + local.getString("menu.orders") + "</a></li>");
            builder.append("<li><a href=\"/account\">" + local.getString("menu.account") + "</a></li>");
            builder.append("<li><a href=\"/controller?command=close_session\">" + local.getString("menu.sign_out") + "</a></li>");
        }

        try {
            pageContext.getOut().print(builder.toString());
        } catch (IOException e) {
            logger.error("Cannot write to out -> an exception thrown", e);
        }
        return SKIP_BODY;
    }
}
