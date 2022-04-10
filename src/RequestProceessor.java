import java.io.*;
import org.json.simple.JSONObject;
import java.net.Socket;

class RequestProcessor implements Runnable {
	private Socket socket = null;
	private DataOutputStream os = null;
	private BufferedReader in = null;
	private DataInputStream dis = null;
	private String msgToClient = "HTTP/1.1 200 OK\n"
			+ "Server: HTTP server/0.1\n"
			+ "Access-Control-Allow-Origin: *\n\n";
	private JSONObject jsonObject = new JSONObject();

	public RequestProcessor(Socket Socket) {
		super();
		try {
			socket = Socket;
			in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
			os = new DataOutputStream(socket.getOutputStream());

		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void run() {
		try {
			// write your code here
			String string_query = "";
			while (!in.ready())
				string_query = in.readLine();

			JSONObject jsonObject = parser.parse_and_Process(string_query); // send the data to our library.
			// end of your code
			String response = msgToClient + jsonObject.toString();
			os.write(response.getBytes());
			os.flush();
			socket.close();
		} catch (IOException e) {
			System.out.println("Error occured, while reading from client");
			System.out.println(e.toString());
		}

	}

}