package by.tc.web.util.tag;

import by.tc.web.domain.user.User;
import by.tc.web.domain.user.impl.TaxiDriver;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.tagext.TagSupport;

public class DriverTag extends TagSupport {
    private static final long serialVersionUID = 863432719100740085L;

    @Override
    public int doStartTag() throws JspException {
        User user = (User) pageContext.getSession().getAttribute("user");
        if (user == null) {
            return SKIP_BODY;
        }

        Class userClass = user.getClass();
        if (userClass == TaxiDriver.class) {
            return EVAL_BODY_INCLUDE;
        } else {
            return SKIP_BODY;
        }
    }
}
