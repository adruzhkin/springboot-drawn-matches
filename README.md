# Spring Boot Rest API with JWT Example

### Application exposes three API endpoints:

- ##### {host}/api/public/matches/drawn/{year}
  You can access public API endpoint without authorization.
  This endpoint allows to get the number of matches
  for a given year that ended in a draw.
  <br/><br/>
  You can query the data for the year of 2011
  with the following code:
  <br/>
  `curl -w "\n" -H 'Accept: application/json' 
  http://localhost:8080/api/public/matches/drawn/2011`
  <br/><br/>
  It will send the response in the following form:
  <br/>
  `{
  "year": 2011,
  "drawn_matches": 516
  }`
  <br/><br/>

- ##### {host}/api/auth
  Authentication endpoint allows to obtain the JWT token
  in order to make calls to the private endpoint. Username
  and password are hardcoded for demo purposes:
  <br/>
  `TOKEN=$(curl -s -X POST -H 'Accept: application/json' 
  -H 'Content-Type: application/json' 
  --data '{"username": "test", "password": "password"}' 
  http://localhost:8080/api/auth | jq -r '.token')`
  <br/><br/>
  
  You need jq package to be installed in order
  to execute the above command. Token will be stored
  in `$TOKEN` variable.
  <br/><br/>
  
  Alternatively, you can copy the token manually from
  the response to the following command:
  <br/>
  `curl -w "\n" -s -X POST -H 'Accept: application/json' 
  -H 'Content-Type: application/json' 
  --data '{"username": "test", "password": "password"}' 
  http://localhost:8080/api/auth`
  <br/><br/>
  
  and save it to the `$TOKEN` variable:
  <br/>
  `TOKEN=copied_token_field_value`
  <br/><br/>
  
  Token is valid for 90 seconds. Then you need to obtain 
  fresh token again.
  <br/><br/>
  
- ##### {host}/api/private/matches/drawn/{year}
  To make the call to the private endpoint you need a valid
  token:
  <br/>
  `curl -w "\n" -H 'Accept: application/json' 
  -H "Authorization: Bearer ${TOKEN}" 
  http://localhost:8080/api/private/matches/drawn/2011`
  <br/><br/>
  
  You will receive the response in the same format as from
  the call above:
  <br/>
  `{
  "year": 2011,
  "drawn_matches": 516
  }`
  