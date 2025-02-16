Docker image is available at Docker Hub. You can pull the image with the following command:
docker image pull muratballikaya/loan-application:latest


Once you have source code, you can run the application with the following commands
Source code is available at https://github.com/muratballikaya/loan-application

1- Build application with following command 
    mvn clean package 

2- Docker build command should be run to create docker image 
    docker build -t loan-application .

3- Run the Docker container: Start a container from the Docker image.  
docker run -p 8080:8080 loan-application

4- Access the application: Open a web browser and go to http://localhost:8080 to see the application running.

Postman Collections have been sent you via email.


Loan Application API copmosed of authentication and loan endpoints.

User should be authenticated to access loan and customer endpoints.
1-  To be able to authenticate, user should be signed up first.
To signup , user should send a POST request to /signup endpoint with username and password in the request body.
 http://localhost:8080/api/auth/signup
       POST:  
        {   
        "username" : "admin1",
        "role" : ["admin","user"] ,
        "password" : "123456"
        }
2- Already authenticated user should send a POST request to /signin endpoint with username and password in the request body.
    Signin endpoint provides a JWT token which should be used in the header of the request as Baarer Authentication in order to access loan and customer endpoints.
    http://localhost:8080/api/auth/signin
      POST:  {
        "username" : "admin1",
        "password" : "123456"
          }
    Response: {
            "jwt": "eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJhZG1pbjEiLCJpYXQiOjE3Mzk3MTYzNzQsImV4cCI6MTczOTgwMjc3NH0.UWPIl4UGj2o1uES5nDq7v6CGtYq8DD5YX0sm1xUKOyI"
            }
3- Customer should be created first. 
    To create a customer, user should send a POST request to /customers endpoint with customer information in the request body.
    Never forget jwt token in the header of the request as Baarer Authentication.
    Creation can be done just  users that have ADMIN role.
    http://localhost:8080/api/customers
    POST: {
          "name" : "murat",
         "surname" : "ballıkaya",
         "creditLimit" : 100000,
         "userCreditLimit" : 0
        }
     Response: {
        "username": "22",
        "password": "6RQyc88jv8"
        } 
    Response includes username and password which will be used to be signed in as a USER role.

4- Create loan endpoint is available for users that have USER or ADMIN role.
    To create a loan, user should send a POST request to /loans endpoint with loan information in the request body.
    Never forget jwt token in the header of the request as Baarer Authentication.
    http://localhost:8080/api/loan/create
    POST: {
        "customerId" : 22,
        "amount" : 20000,
        "interestRate" : 0.8,
        "numberOfInstallments" : 6
        }
5-  You are able to list loans by following endpoint:
        http://localhost:8080/api/loan/list
    GET: 
    Response: [
            {
                "id": 1,
                "customerId": 22,
                "amount": 20000,
                "interestRate": 0.8,
                "numberOfInstallments": 6,
                "status": "APPROVED"
            }
            ]
Response: [
        {
        "id": 12,
        "customer": {
        "id": 20,
        "name": "murat",
        "surname": "ballıkaya",
        "creditLimit": 100000.0,
        "usedCreditLimit": 20000.0
        },
        "loanAmount": 20000.0,
        "numberOfInstalment": 6,
        "createDate": "2025-02-16T12:16:49.971+00:00",
        "paid": false
        }
        ]
6-  You are able to pay installments via following endpoint:
    http://localhost:8080/api/loan/pay
      POST:  {
        "loanId" : 12,
        "amount" : 10000
        }
    Response:
        {
        "payedInstallments": 2,
        "totalAmountSpent": 16000.000635782877,
        "amountPaid": 8000.0003178914385,
        "paidCompletely": false
        }
