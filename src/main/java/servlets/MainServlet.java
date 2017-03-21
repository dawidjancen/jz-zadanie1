package servlets;

import java.io.IOException;
import java.text.DecimalFormat;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/main")
public class MainServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

    public MainServlet() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String kredyt = request.getParameter("kredyt");
		String raty = request.getParameter("raty");
		String oprocentowanie = request.getParameter("oprocentowanie");
		String oplata = request.getParameter("oplata");
		String rodzaj = request.getParameter("rodzaj");
		
		response.setContentType("text/html; charset=UTF-8");

		if (
			!isValid(kredyt) ||
			!isValid(raty) ||
			!isValid(oprocentowanie) ||
			!isValid(oplata) ||
			!isValid(rodzaj)
		) {
			response.sendRedirect("/");
		}
		
		response.getWriter().println("<table><tbody>");
		
		String tableHeader = "";
		
		tableHeader += "<tr><th>";
		tableHeader += "Nr raty";
		tableHeader += "</th><th>";
		tableHeader += "Czêœæ kapita³owa";
		tableHeader += "</th><th>";
		tableHeader += "Czêœæ odsetkowa";
		tableHeader += "</th><th>";
		tableHeader += "Op³ata sta³a";
		tableHeader += "</th><th>";
		tableHeader += "Ca³kowita rata";
		tableHeader += "</tr><th>";
		
		response.getWriter().println(tableHeader);
		
		DecimalFormat f = new DecimalFormat("##.00");
		
		for (int i = 0; i < Integer.parseInt(raty); i++) {
			String textLine = "";
			
			if (rodzaj.equals("stale")) {
				Double q = 1 + ((Double.parseDouble(oprocentowanie) / 100) / 12);
				
				Double calkowitaRata = Double.parseDouble(kredyt) * Math.pow(q, Double.parseDouble(raty)) * (q - 1) / (Math.pow(q, Double.parseDouble(raty)) - 1);
				Double czescOdsetkowa = ((Double.parseDouble(kredyt) - (i * (Double.parseDouble(kredyt) / Double.parseDouble(raty)))) * ((Double.parseDouble(oprocentowanie) / 100) / 12));
				Double czescKapitalowa = calkowitaRata - czescOdsetkowa;
				
				textLine += "<tr><td>";
				textLine += (i + 1);
				textLine += "</td><td>";
				textLine += f.format(czescKapitalowa);
				textLine += "</td><td>";
				textLine += f.format(czescOdsetkowa);
				textLine += "</td><td>";
				textLine += f.format(Double.parseDouble(oplata));
				textLine += "</td><td>";
				textLine += f.format(calkowitaRata);
				textLine += "</td></tr>";		
			} else {
				Double czescKapitalowa = Double.parseDouble(kredyt) / Double.parseDouble(raty);
				Double czescOdsetkowa = (Double.parseDouble(kredyt) - (i * czescKapitalowa)) * ((Double.parseDouble(oprocentowanie) / 100) / 12);
				Double calkowitaRata = czescKapitalowa + czescOdsetkowa + Double.parseDouble(oplata);
				
				textLine += "<tr><td>";
				textLine += (i + 1);
				textLine += "</td><td>";
				textLine += f.format(czescKapitalowa);
				textLine += "</td><td>";
				textLine += f.format(czescOdsetkowa);
				textLine += "</td><td>";
				textLine += f.format(Double.parseDouble(oplata));
				textLine += "</td><td>";
				textLine += f.format(calkowitaRata);
				textLine += "</td></tr>";
			}
			
			response.getWriter().println(textLine);
		}
		
		response.getWriter().println("</tbody></table>");
	}
	
	private Boolean isValid(String value) {
		if (value == null || value.equals("")) {
			return false;
		}

		return true;
	}
}
