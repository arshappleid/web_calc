window.onload = function () {
	var calculate_button = document.getElementById('calculate_button')
	var http = new XMLHttpRequest();
	calculate_button.onclick = function () {
		// Grab the url , and pass it into parser.java
		var num1 = document.getElementById('left_operand');
		var num2 = document.getElementById('right_operand');
		var operator = document.querySelector('input[name="operator"]:checked').value;
		var server_address = "127.0.0.1:5502";

		// send the above data , to the server as a string.
		var data = "leftOperand=" + num1 + "&rightOperand=" + num2 + "&operator=" + operator;
		http.open("GET",server_address,true);
		http.send(data);
		console.log("Data sent");
	};

	http.onreadystatechange = function () {
		// once we recieve the request back.
		if (this.readyState == 4 && this.status == 200) {
			var datain = JSON.parse(http.response); // convert the data to , a dictionary
			document.getElementById('expression').innerHTML = datain['Experssion'];
			document.getElementById('expression').innerHTML = datain['Result'];
			console.log("Data Recieved");
		}
	}

	
}