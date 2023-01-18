Geekseat Witch Saga

From the assignment, the objective is to average number of people the witch killed on year of birth of those people.
Then I decided to create a service and unit test to get the average number. I'm also creating the controller if you want to test the code in API through Postman.

API Documentation:
- URL: POST localhost:8080/witch-saga/average-killing
- Request body: in JSON string list of object
  Parameter:
  - ageOfDeath  : Integer type
  - yearOFDeath : Integer type
- Response body: BigDecimal value type
- Example:
  - Request:
    [
        {
            "ageOfDeath": 10,
            "yearOfDeath": 12
        },
        {
            "ageOfDeath": 13,
            "yearOfDeath": 17
        }
    ]
  - Response:
    4.50
