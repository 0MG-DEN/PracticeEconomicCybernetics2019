package servlet;

import java.io.*;
import java.util.*;
import java.text.*;

import javax.servlet.*;
import javax.servlet.http.*;

public class RegServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	private Integer nextID;
	private Map<Integer, Person> map;
	private static final String WARNING =
	"<font title=\"Name and surname you've entered are already registered.\" color=\"red\">Invalid data!</font>";

	public void init() {
		nextID = 1;
		map = new HashMap<>();
	}

	private static Person createPerson(final HttpServletRequest request) {
		final String inName = request.getParameter("name");
		final String inSurname = request.getParameter("surname");
		final String inTelephone = request.getParameter("telephone");
		final String inCity = request.getParameter("city");
		final String inAddress = request.getParameter("address");

		final SimpleDateFormat dateFormat = new SimpleDateFormat("dd.MM.yyyy", Locale.US);
		Date inBirthDate;
		try {
			String inBirthDateStr = request.getParameter("birthDate");
			if(inBirthDateStr != null) {				
				inBirthDate = dateFormat.parse(inBirthDateStr);
			} else {
				inBirthDate = new Date();
			}
		} catch (ParseException e) {
			inBirthDate = new Date();
		}
		
		return new Person(inName, inSurname, inBirthDate, inTelephone, inCity, inAddress);
	}
	
	@Override
	protected void doGet(final HttpServletRequest request, final HttpServletResponse response)
			throws ServletException, IOException {
		final ServletOutputStream out = response.getOutputStream();

		final Person newPerson = createPerson(request);
		
		boolean register = true;
		if (newPerson.check()) {
			for (final Map.Entry<Integer, Person> entry : map.entrySet()) {
				final Person value = entry.getValue();
				if (value.name.equals(newPerson.name) && value.surname.equals(newPerson.surname)) {
					register = false;
					break;
				}
			}
			if (register) {
				map.put(nextID++, newPerson);
			}
		}
		
		final String path = "D:/index.html";
		final FileReader input = new FileReader(path);
		final BufferedReader reader = new BufferedReader(input);

		String line = reader.readLine();
		while (line != null) {
			out.println(line);
			if ("<!--map-->".equals(line)) {
				for (Map.Entry<Integer, Person> entry : map.entrySet()) {
					out.println("<tr>");
					final Person value = entry.getValue();
					out.println(value.toHTMLTableRow());
					out.println("</tr>");
				}
			} else if ("<!--warning-->".equals(line) && !register) {
				out.println(WARNING);
			}
			line = reader.readLine();
		}
		reader.close();
		input.close();
		out.close();
	}

	@Override
	protected void doPost(final HttpServletRequest request, final HttpServletResponse response)
			throws ServletException, IOException {
		this.doGet(request, response);
	}

	private static class Person {
		private final String name;
		private final String surname;
		private final Date birthDate;
		private final String telephone;
		private final String city;
		private final String address;
		private final Date regDate;

		public Person(final String name, final String surname, final Date birthDate,
				final String telephone, final String city, final String address) {
			this.name = name;
			this.surname = surname;
			this.birthDate = birthDate;
			this.telephone = telephone;
			this.city = city;
			this.address = address;
			this.regDate = new Date();
		}

		public boolean check() {
			return name != null && surname != null && telephone != null && city != null && address != null;
		}

		public String toHTMLTableRow() {
			final StringBuilder builder = new StringBuilder();
			final String openTag = "<td>";
			final String closeTag = "</td>";

			builder.append(openTag);
			builder.append(name);
			builder.append(closeTag);
			builder.append(openTag);
			builder.append(surname);
			builder.append(closeTag);

			SimpleDateFormat dateFormat = new SimpleDateFormat("dd.MM.yyyy", Locale.US);
			builder.append(openTag);
			builder.append(dateFormat.format(birthDate));
			builder.append(closeTag);

			builder.append(openTag);
			builder.append(telephone);
			builder.append(closeTag);
			builder.append(openTag);
			builder.append(city);
			builder.append(closeTag);
			builder.append(openTag);
			builder.append(address);
			builder.append(closeTag);

			dateFormat = new SimpleDateFormat("HH:mm:ss dd.MM.yyyy", Locale.US);
			builder.append(openTag);
			builder.append(dateFormat.format(regDate));
			builder.append(closeTag);

			return builder.toString();
		}
	}
}
