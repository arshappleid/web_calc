import java.util.ArrayList;
import org.json.simple.JSONObject;

public class parser {
	/*
	 * 1. Extract the relevant info , from the string_query.
	 * 2. Perform the calculation operation.
	 * 3. Convert and return the response as json object.
	 */

	public static void main(String[] args) {
		////////////// code goes here ////////////////
		String string_query = "http://localhost:8080/leftOperand=5&rightOperand=0&operation=/";
		String res = parse_and_Process(string_query).toString();
		System.out.println(res);
		/////////////////////////////////////////////
	}

	public static JSONObject parse_and_Process(String string_query) {
		ArrayList<String> elements = new ArrayList<>();
		for (int i = 0; i < string_query.length();) {
			// time complexity O(n)
			char curr = string_query.charAt(i);
			String item = "";
			if (curr == '=') {
				i++;
				while (i < string_query.length() && string_query.charAt(i) != '&') {
					item += string_query.charAt(i);
					i++;
				}
				elements.add(item);
				continue;
			}
			i++;
		}
		double num1 = Double.parseDouble(elements.get(0));
		double num2 = Double.parseDouble(elements.get(1));
		String operator = elements.get(2);

		String[] ans = interpret(num1, num2, operator);
		return convertJsObject(ans);

	}

	public static String[] interpret(double num1, double num2, String operator) {
		String resultstr = "";
		String expressionstr = "";

		if (operator.equals("+")) {
			resultstr = (num1 + num2) + "";
			expressionstr = num1 + " + " + num2;

		} else if (operator.equals("-")) {
			resultstr = (num1 - num2) + "";
			expressionstr = num1 + " - " + num2;

		} else if (operator.equals("*")) {
			resultstr = (num1 * num2) + "";
			expressionstr = num1 + " * " + num2;

		} else if (operator.equals("/")) {
			if (num2 != 0) {
				resultstr = (num1 / num2) + "";
				expressionstr = num1 + " / " + num2;

			} else {
				expressionstr = "Division by zero not allowed.";
			}
		} else if (operator.equals("%")) {
			expressionstr = num1 + " % " + num2 + " = ";
			resultstr = (num1 % num2) + "";
		}

		String[] resp = new String[2];
		resp[0] = expressionstr;
		resp[1] = resultstr;

		return resp;
	}

	public static JSONObject convertJsObject(String[] info) {
		JSONObject obj = new JSONObject();
		obj.put("Experssion", info[0]);
		obj.put("Result", info[1]);
		return obj;
	}
}
