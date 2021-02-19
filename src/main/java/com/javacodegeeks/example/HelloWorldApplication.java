package com.javacodegeeks.example;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import java.sql.SQLException;

@SpringBootApplication
public class HelloWorldApplication {

	public static void main(String[] args) {
		SpringApplication.run(HelloWorldApplication.class, args);
	}

	public static boolean badCode() {
		boolean test;
		test = false;
		String password;
		password = "secret";

		// duplicate the follwing bad code so we some duplication metrics
		// as well as code smells
		if (test == true) {/* don't test against boolean values */
			System.out.println(-0.0f == 0.0f);
			System.out.println(Float.compare(-0.0f, 0.0f) == 0 ? true : false);
		}
		if (test == true) {/* don't test against boolean values */
			System.out.println(-0.0f == 0.0f);
			System.out.println(Float.compare(-0.0f, 0.0f) == 0 ? true : false);
		}
		if (test == true) {/* don't test against boolean values */
			System.out.println(-0.0f == 0.0f);
			System.out.println(Float.compare(-0.0f, 0.0f) == 0 ? true : false);
		}
		int day = 5;
		String dayString;
		dayString = "";
		// switch statement with int data type
		switch (day) {
		case 1:
			dayString = "Monday";
			break;
		case 2:
			dayString = "Tuesday";
			break;
		case 3:
			dayString = "Wednesday";
			break;
		case 4:
			dayString = "Thursday";
			break;
		case 5:
			dayString = "Friday";
			break;
		case 6:
			dayString = "Saturday";
			break;
		case 7:
			dayString = "Sunday";
			break;
		// no default, should trigger code issue
		}
		System.out.println(dayString);

		// multiple returns...bad idea!
		if (test) {
			return true;
		} else {
			return false;
		}
	}
	// this code should trigger a security violation
	public boolean authenticate(javax.servlet.http.HttpServletRequest request, java.sql.Connection connection) throws SQLException {
		String user = request.getParameter("user");
		String pass = request.getParameter("pass");
	  
		String query = "SELECT * FROM users WHERE user = '" + user + "' AND pass = '" + pass + "'"; // Unsafe
	  
		// If the special value "foo' OR 1=1 --" is passed as either the user or pass, authentication is bypassed
		// Indeed, if it is passed as a user, the query becomes:
		// SELECT * FROM users WHERE user = 'foo' OR 1=1 --' AND pass = '...'
		// As '--' is the comment till end of line syntax in SQL, this is equivalent to:
		// SELECT * FROM users WHERE user = 'foo' OR 1=1
		// which is equivalent to:
		// SELECT * FROM users WHERE 1=1
		// which is equivalent to:
		// SELECT * FROM users
	  
		java.sql.Statement statement = connection.createStatement();
		java.sql.ResultSet resultSet = statement.executeQuery(query); // Noncompliant
		return resultSet.next();
	  }

	  // code below should trigger security violations too
	  public class Reflection {

		public void run(java.lang.ClassLoader loader, String className, String methodName, String fieldName,
				Class<?> parameterTypes)
				throws NoSuchMethodException, SecurityException, ClassNotFoundException, NoSuchFieldException {
	
			Class<?> clazz = Class.forName(className); // Questionable
			clazz.getMethod(methodName, parameterTypes); // Questionable
			clazz.getMethods(); // Questionable
			clazz.getField(fieldName); // Questionable
			clazz.getFields(); // Questionable
			clazz.getDeclaredField(fieldName); // Questionable
			clazz.getDeclaredFields(); // Questionable
			clazz.getDeclaredClasses(); // Questionable
			loader.loadClass(className); // Questionable
		}
	}

}
