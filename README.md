## Main File Locations
Java Server File : ./src/server.java
HTML file :  ./src/index.html

## Classes Used
1. calculate - Will handle all the calculations , to make code easier to understand.
2. parser - This will parse the string query , and then perform the appropriate math operation. Then return the response as JSONObject.
3. RequestProcessor - Will simply ready the string query , and pass it to the parser class , to recieve the JSON response. It will also send back the JSON response, as a response to the call to the server.
