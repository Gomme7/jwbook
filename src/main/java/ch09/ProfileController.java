package ch09;

import java.io.IOException;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.beanutils.BeanUtils;

/**
 * Servlet implementation class StudentController
 */
@WebServlet("/profileControl")
public class ProfileController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	ProfileDAO dao;
	public void init(ServletConfig config) throws ServletException {
		super.init(config);
		dao = new ProfileDAO();
	}

	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		String action = request.getParameter("action");
		String view = "";
		
		if(action == null) {
			getServletContext().getRequestDispatcher("/profileControl?action=list").forward(request, response);
		} else {
			switch(action) {
			case "list": view = list(request, response);break;
			case "insert": view = insert(request, response);break;
			}
			getServletContext().getRequestDispatcher("/ch09/"+view).forward(request, response);		
		}
	}
	
	public String list(HttpServletRequest request, HttpServletResponse response) {
		request.setAttribute("profiles", dao.getAll());
		return "ProfileList.jsp";		
	}
	
	public String insert(HttpServletRequest request, HttpServletResponse response) {
		Profile p = new Profile();
		try {
			BeanUtils.populate(p, request.getParameterMap());
		} catch (Exception e) {
			e.printStackTrace();
		}
		dao.insert(p);
		return list(request, response);
	}
}