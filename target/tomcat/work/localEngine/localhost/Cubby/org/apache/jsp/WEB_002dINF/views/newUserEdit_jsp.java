package org.apache.jsp.WEB_002dINF.views;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.jsp.*;

public final class newUserEdit_jsp extends org.apache.jasper.runtime.HttpJspBase
    implements org.apache.jasper.runtime.JspSourceDependent {

  private static final JspFactory _jspxFactory = JspFactory.getDefaultFactory();

  private static java.util.List _jspx_dependants;

  private org.apache.jasper.runtime.TagHandlerPool _005fjspx_005ftagPool_005fsf_005fform_0026_005fmodelAttribute_005fmethod;
  private org.apache.jasper.runtime.TagHandlerPool _005fjspx_005ftagPool_005fsf_005finput_0026_005fsize_005fpath_005fid_005fnobody;
  private org.apache.jasper.runtime.TagHandlerPool _005fjspx_005ftagPool_005fsf_005ferrors_0026_005fpath_005fcssClass_005fnobody;
  private org.apache.jasper.runtime.TagHandlerPool _005fjspx_005ftagPool_005fsf_005fpassword_0026_005fsize_005fshowPassword_005fpath_005fid_005fnobody;

  private javax.el.ExpressionFactory _el_expressionfactory;
  private org.apache.AnnotationProcessor _jsp_annotationprocessor;

  public Object getDependants() {
    return _jspx_dependants;
  }

  public void _jspInit() {
    _005fjspx_005ftagPool_005fsf_005fform_0026_005fmodelAttribute_005fmethod = org.apache.jasper.runtime.TagHandlerPool.getTagHandlerPool(getServletConfig());
    _005fjspx_005ftagPool_005fsf_005finput_0026_005fsize_005fpath_005fid_005fnobody = org.apache.jasper.runtime.TagHandlerPool.getTagHandlerPool(getServletConfig());
    _005fjspx_005ftagPool_005fsf_005ferrors_0026_005fpath_005fcssClass_005fnobody = org.apache.jasper.runtime.TagHandlerPool.getTagHandlerPool(getServletConfig());
    _005fjspx_005ftagPool_005fsf_005fpassword_0026_005fsize_005fshowPassword_005fpath_005fid_005fnobody = org.apache.jasper.runtime.TagHandlerPool.getTagHandlerPool(getServletConfig());
    _el_expressionfactory = _jspxFactory.getJspApplicationContext(getServletConfig().getServletContext()).getExpressionFactory();
    _jsp_annotationprocessor = (org.apache.AnnotationProcessor) getServletConfig().getServletContext().getAttribute(org.apache.AnnotationProcessor.class.getName());
  }

  public void _jspDestroy() {
    _005fjspx_005ftagPool_005fsf_005fform_0026_005fmodelAttribute_005fmethod.release();
    _005fjspx_005ftagPool_005fsf_005finput_0026_005fsize_005fpath_005fid_005fnobody.release();
    _005fjspx_005ftagPool_005fsf_005ferrors_0026_005fpath_005fcssClass_005fnobody.release();
    _005fjspx_005ftagPool_005fsf_005fpassword_0026_005fsize_005fshowPassword_005fpath_005fid_005fnobody.release();
  }

  public void _jspService(HttpServletRequest request, HttpServletResponse response)
        throws java.io.IOException, ServletException {

    PageContext pageContext = null;
    HttpSession session = null;
    ServletContext application = null;
    ServletConfig config = null;
    JspWriter out = null;
    Object page = this;
    JspWriter _jspx_out = null;
    PageContext _jspx_page_context = null;


    try {
      response.setContentType("text/html; charset=ISO-8859-1");
      pageContext = _jspxFactory.getPageContext(this, request, response,
      			null, true, 8192, true);
      _jspx_page_context = pageContext;
      application = pageContext.getServletContext();
      config = pageContext.getServletConfig();
      session = pageContext.getSession();
      out = pageContext.getOut();
      _jspx_out = out;

      out.write("\r\n");
      out.write("    \r\n");
      out.write("\r\n");
      out.write("<!DOCTYPE html PUBLIC \"-//W3C//DTD HTML 4.01 Transitional//EN\" \"http://www.w3.org/TR/html4/loose.dtd\">\r\n");
      out.write("<html>\r\n");
      out.write("<head>\r\n");
      out.write("<meta http-equiv=\"Content-Type\" content=\"text/html; charset=ISO-8859-1\">\r\n");
      out.write("<title>Register</title>\r\n");
      out.write("</head>\r\n");
      out.write("<style>\r\n");
      out.write(".error {\r\n");
      out.write("\tcolor: #ff0000;\r\n");
      out.write("}\r\n");
      out.write("</style>\r\n");
      out.write("<body>\r\n");
      out.write("\r\n");
      out.write("<h2>Register</h2>\r\n");
      out.write("\r\n");
      //  sf:form
      org.springframework.web.servlet.tags.form.FormTag _jspx_th_sf_005fform_005f0 = (org.springframework.web.servlet.tags.form.FormTag) _005fjspx_005ftagPool_005fsf_005fform_0026_005fmodelAttribute_005fmethod.get(org.springframework.web.servlet.tags.form.FormTag.class);
      _jspx_th_sf_005fform_005f0.setPageContext(_jspx_page_context);
      _jspx_th_sf_005fform_005f0.setParent(null);
      // /WEB-INF/views/newUserEdit.jsp(20,0) name = method type = null reqTime = true required = false fragment = false deferredValue = false expectedTypeName = null deferredMethod = false methodSignature = null
      _jspx_th_sf_005fform_005f0.setMethod("POST");
      // /WEB-INF/views/newUserEdit.jsp(20,0) name = modelAttribute type = null reqTime = true required = false fragment = false deferredValue = false expectedTypeName = null deferredMethod = false methodSignature = null
      _jspx_th_sf_005fform_005f0.setModelAttribute("userDetails");
      int[] _jspx_push_body_count_sf_005fform_005f0 = new int[] { 0 };
      try {
        int _jspx_eval_sf_005fform_005f0 = _jspx_th_sf_005fform_005f0.doStartTag();
        if (_jspx_eval_sf_005fform_005f0 != javax.servlet.jsp.tagext.Tag.SKIP_BODY) {
          do {
            out.write("\r\n");
            out.write("\t<fieldset>\r\n");
            out.write("\t\t<table>\r\n");
            out.write("\t\t\t<tr>\r\n");
            out.write("\t\t\t\t<th><label for=\"user_login\">Login</label></th>\r\n");
            out.write("\t\t\t\t<td>");
            if (_jspx_meth_sf_005finput_005f0(_jspx_th_sf_005fform_005f0, _jspx_page_context, _jspx_push_body_count_sf_005fform_005f0))
              return;
            out.write("</td>\r\n");
            out.write("\t\t\t\t<td>");
            //  sf:errors
            org.springframework.web.servlet.tags.form.ErrorsTag _jspx_th_sf_005ferrors_005f0 = (org.springframework.web.servlet.tags.form.ErrorsTag) _005fjspx_005ftagPool_005fsf_005ferrors_0026_005fpath_005fcssClass_005fnobody.get(org.springframework.web.servlet.tags.form.ErrorsTag.class);
            _jspx_th_sf_005ferrors_005f0.setPageContext(_jspx_page_context);
            _jspx_th_sf_005ferrors_005f0.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_sf_005fform_005f0);
            // /WEB-INF/views/newUserEdit.jsp(26,8) name = path type = null reqTime = true required = false fragment = false deferredValue = false expectedTypeName = null deferredMethod = false methodSignature = null
            _jspx_th_sf_005ferrors_005f0.setPath("login");
            // /WEB-INF/views/newUserEdit.jsp(26,8) name = cssClass type = null reqTime = true required = false fragment = false deferredValue = false expectedTypeName = null deferredMethod = false methodSignature = null
            _jspx_th_sf_005ferrors_005f0.setCssClass("error");
            int[] _jspx_push_body_count_sf_005ferrors_005f0 = new int[] { 0 };
            try {
              int _jspx_eval_sf_005ferrors_005f0 = _jspx_th_sf_005ferrors_005f0.doStartTag();
              if (_jspx_th_sf_005ferrors_005f0.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
                return;
              }
            } catch (Throwable _jspx_exception) {
              while (_jspx_push_body_count_sf_005ferrors_005f0[0]-- > 0)
                out = _jspx_page_context.popBody();
              _jspx_th_sf_005ferrors_005f0.doCatch(_jspx_exception);
            } finally {
              _jspx_th_sf_005ferrors_005f0.doFinally();
              _005fjspx_005ftagPool_005fsf_005ferrors_0026_005fpath_005fcssClass_005fnobody.reuse(_jspx_th_sf_005ferrors_005f0);
            }
            out.write("</td>\r\n");
            out.write("\t\t\t</tr>\r\n");
            out.write("\t\t\t<tr>\r\n");
            out.write("\t\t\t\t<th><label for=\"user_pass\">Password</label></th>\r\n");
            out.write("\t\t\t\t<td>");
            if (_jspx_meth_sf_005fpassword_005f0(_jspx_th_sf_005fform_005f0, _jspx_page_context, _jspx_push_body_count_sf_005fform_005f0))
              return;
            out.write("</td>\r\n");
            out.write("\t\t\t\t<td>");
            //  sf:errors
            org.springframework.web.servlet.tags.form.ErrorsTag _jspx_th_sf_005ferrors_005f1 = (org.springframework.web.servlet.tags.form.ErrorsTag) _005fjspx_005ftagPool_005fsf_005ferrors_0026_005fpath_005fcssClass_005fnobody.get(org.springframework.web.servlet.tags.form.ErrorsTag.class);
            _jspx_th_sf_005ferrors_005f1.setPageContext(_jspx_page_context);
            _jspx_th_sf_005ferrors_005f1.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_sf_005fform_005f0);
            // /WEB-INF/views/newUserEdit.jsp(31,8) name = path type = null reqTime = true required = false fragment = false deferredValue = false expectedTypeName = null deferredMethod = false methodSignature = null
            _jspx_th_sf_005ferrors_005f1.setPath("password");
            // /WEB-INF/views/newUserEdit.jsp(31,8) name = cssClass type = null reqTime = true required = false fragment = false deferredValue = false expectedTypeName = null deferredMethod = false methodSignature = null
            _jspx_th_sf_005ferrors_005f1.setCssClass("error");
            int[] _jspx_push_body_count_sf_005ferrors_005f1 = new int[] { 0 };
            try {
              int _jspx_eval_sf_005ferrors_005f1 = _jspx_th_sf_005ferrors_005f1.doStartTag();
              if (_jspx_th_sf_005ferrors_005f1.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
                return;
              }
            } catch (Throwable _jspx_exception) {
              while (_jspx_push_body_count_sf_005ferrors_005f1[0]-- > 0)
                out = _jspx_page_context.popBody();
              _jspx_th_sf_005ferrors_005f1.doCatch(_jspx_exception);
            } finally {
              _jspx_th_sf_005ferrors_005f1.doFinally();
              _005fjspx_005ftagPool_005fsf_005ferrors_0026_005fpath_005fcssClass_005fnobody.reuse(_jspx_th_sf_005ferrors_005f1);
            }
            out.write("</td>\r\n");
            out.write("\t\t\t</tr>\r\n");
            out.write("\t\t\t<tr>\r\n");
            out.write("\t\t\t\t<th></th>\r\n");
            out.write("\t\t\t\t<td><input name=\"commit\" type=\"submit\" value=\"Create Account\"/></td>\r\n");
            out.write("\t\t\t</tr>\r\n");
            out.write("\t\t</table>\r\n");
            out.write("\t</fieldset>\r\n");
            int evalDoAfterBody = _jspx_th_sf_005fform_005f0.doAfterBody();
            if (evalDoAfterBody != javax.servlet.jsp.tagext.BodyTag.EVAL_BODY_AGAIN)
              break;
          } while (true);
        }
        if (_jspx_th_sf_005fform_005f0.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
          return;
        }
      } catch (Throwable _jspx_exception) {
        while (_jspx_push_body_count_sf_005fform_005f0[0]-- > 0)
          out = _jspx_page_context.popBody();
        _jspx_th_sf_005fform_005f0.doCatch(_jspx_exception);
      } finally {
        _jspx_th_sf_005fform_005f0.doFinally();
        _005fjspx_005ftagPool_005fsf_005fform_0026_005fmodelAttribute_005fmethod.reuse(_jspx_th_sf_005fform_005f0);
      }
      out.write("\r\n");
      out.write("\r\n");
      out.write("</body>\r\n");
      out.write("</html>");
    } catch (Throwable t) {
      if (!(t instanceof SkipPageException)){
        out = _jspx_out;
        if (out != null && out.getBufferSize() != 0)
          try { out.clearBuffer(); } catch (java.io.IOException e) {}
        if (_jspx_page_context != null) _jspx_page_context.handlePageException(t);
      }
    } finally {
      _jspxFactory.releasePageContext(_jspx_page_context);
    }
  }

  private boolean _jspx_meth_sf_005finput_005f0(javax.servlet.jsp.tagext.JspTag _jspx_th_sf_005fform_005f0, PageContext _jspx_page_context, int[] _jspx_push_body_count_sf_005fform_005f0)
          throws Throwable {
    PageContext pageContext = _jspx_page_context;
    JspWriter out = _jspx_page_context.getOut();
    //  sf:input
    org.springframework.web.servlet.tags.form.InputTag _jspx_th_sf_005finput_005f0 = (org.springframework.web.servlet.tags.form.InputTag) _005fjspx_005ftagPool_005fsf_005finput_0026_005fsize_005fpath_005fid_005fnobody.get(org.springframework.web.servlet.tags.form.InputTag.class);
    _jspx_th_sf_005finput_005f0.setPageContext(_jspx_page_context);
    _jspx_th_sf_005finput_005f0.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_sf_005fform_005f0);
    // /WEB-INF/views/newUserEdit.jsp(25,8) name = path type = null reqTime = true required = true fragment = false deferredValue = false expectedTypeName = null deferredMethod = false methodSignature = null
    _jspx_th_sf_005finput_005f0.setPath("login");
    // /WEB-INF/views/newUserEdit.jsp(25,8) name = id type = null reqTime = true required = false fragment = false deferredValue = false expectedTypeName = null deferredMethod = false methodSignature = null
    _jspx_th_sf_005finput_005f0.setId("user_login");
    // /WEB-INF/views/newUserEdit.jsp(25,8) name = size type = null reqTime = true required = false fragment = false deferredValue = false expectedTypeName = null deferredMethod = false methodSignature = null
    _jspx_th_sf_005finput_005f0.setSize("15");
    int[] _jspx_push_body_count_sf_005finput_005f0 = new int[] { 0 };
    try {
      int _jspx_eval_sf_005finput_005f0 = _jspx_th_sf_005finput_005f0.doStartTag();
      if (_jspx_th_sf_005finput_005f0.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
        return true;
      }
    } catch (Throwable _jspx_exception) {
      while (_jspx_push_body_count_sf_005finput_005f0[0]-- > 0)
        out = _jspx_page_context.popBody();
      _jspx_th_sf_005finput_005f0.doCatch(_jspx_exception);
    } finally {
      _jspx_th_sf_005finput_005f0.doFinally();
      _005fjspx_005ftagPool_005fsf_005finput_0026_005fsize_005fpath_005fid_005fnobody.reuse(_jspx_th_sf_005finput_005f0);
    }
    return false;
  }

  private boolean _jspx_meth_sf_005fpassword_005f0(javax.servlet.jsp.tagext.JspTag _jspx_th_sf_005fform_005f0, PageContext _jspx_page_context, int[] _jspx_push_body_count_sf_005fform_005f0)
          throws Throwable {
    PageContext pageContext = _jspx_page_context;
    JspWriter out = _jspx_page_context.getOut();
    //  sf:password
    org.springframework.web.servlet.tags.form.PasswordInputTag _jspx_th_sf_005fpassword_005f0 = (org.springframework.web.servlet.tags.form.PasswordInputTag) _005fjspx_005ftagPool_005fsf_005fpassword_0026_005fsize_005fshowPassword_005fpath_005fid_005fnobody.get(org.springframework.web.servlet.tags.form.PasswordInputTag.class);
    _jspx_th_sf_005fpassword_005f0.setPageContext(_jspx_page_context);
    _jspx_th_sf_005fpassword_005f0.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_sf_005fform_005f0);
    // /WEB-INF/views/newUserEdit.jsp(30,8) name = path type = null reqTime = true required = true fragment = false deferredValue = false expectedTypeName = null deferredMethod = false methodSignature = null
    _jspx_th_sf_005fpassword_005f0.setPath("password");
    // /WEB-INF/views/newUserEdit.jsp(30,8) name = id type = null reqTime = true required = false fragment = false deferredValue = false expectedTypeName = null deferredMethod = false methodSignature = null
    _jspx_th_sf_005fpassword_005f0.setId("user_pass");
    // /WEB-INF/views/newUserEdit.jsp(30,8) name = size type = null reqTime = true required = false fragment = false deferredValue = false expectedTypeName = null deferredMethod = false methodSignature = null
    _jspx_th_sf_005fpassword_005f0.setSize("15");
    // /WEB-INF/views/newUserEdit.jsp(30,8) name = showPassword type = null reqTime = true required = false fragment = false deferredValue = false expectedTypeName = null deferredMethod = false methodSignature = null
    _jspx_th_sf_005fpassword_005f0.setShowPassword(true);
    int[] _jspx_push_body_count_sf_005fpassword_005f0 = new int[] { 0 };
    try {
      int _jspx_eval_sf_005fpassword_005f0 = _jspx_th_sf_005fpassword_005f0.doStartTag();
      if (_jspx_th_sf_005fpassword_005f0.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
        return true;
      }
    } catch (Throwable _jspx_exception) {
      while (_jspx_push_body_count_sf_005fpassword_005f0[0]-- > 0)
        out = _jspx_page_context.popBody();
      _jspx_th_sf_005fpassword_005f0.doCatch(_jspx_exception);
    } finally {
      _jspx_th_sf_005fpassword_005f0.doFinally();
      _005fjspx_005ftagPool_005fsf_005fpassword_0026_005fsize_005fshowPassword_005fpath_005fid_005fnobody.reuse(_jspx_th_sf_005fpassword_005f0);
    }
    return false;
  }
}
