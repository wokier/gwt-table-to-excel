package com.googlecode.gwtTableToExcel.server;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.io.IOUtils;
/**
 * Servlet which do the magic conversion.<br>
 * Dont forget to declare it in your web.xml<br>
 * < servlet><br>
 *    < servlet-name>gwtTableToExcelServlet< /servlet-name><br>
 *    < servlet-class>com.googlecode.gwtTableToExcel.server.TableToExcelServlet< /servlet-class><br>
 * </ servlet><br>
 * < servlet-mapping><br>
 *    < servlet-name>gwtTableToExcelServlet< /servlet-name><br>
 *    < url-pattern>/gtte/excel< /url-pattern><br>
 * < /servlet-mapping><br>
 * @author Francois Wauquier 'wokier'
 */
@SuppressWarnings("serial")
public class TableToExcelServlet extends HttpServlet {

	/**
	 * Constructor
	 */
	public TableToExcelServlet() {
		super();
	}
	
	/**
	 * @see HttpServlet#init()
	 */
	@Override
	public void init() throws ServletException {
		super.init();
	}
	
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		export(request, response);
	}
	
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		export(request, response);
	}
	
	private void export(HttpServletRequest request, HttpServletResponse response) throws IOException {
		String html = request.getParameter("html");
		String fileName = request.getParameter("fileName");
		response.setContentType("application/vnd.ms-excel");//magic is here
		response.setHeader( "Content-Disposition", "attachment; filename=\""+fileName+"\"" );
		response.setContentLength(html.length());
		IOUtils.write(html, response.getOutputStream(),"ISO-8859-1");
	}
	
}
