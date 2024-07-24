1. Project Title :
 Transaction Management System
 
2. Project Description :
	. A Spring Boot application designed to manage cardholder transactions. 
	. The system includes features such as account creation, transaction management (purchase, withdrawal, credit voucher), 
	and event-driven architecture using Kafka. 
	. The application is containerized using Docker for easy deployment and scalability.



3.Features
	. Account creation and management
	. Transaction processing (purchase, withdrawal, credit voucher)
	. Event-driven architecture with Kafka
	. Dockerized application for easy deployment
	. Secure password management with Docker secrets or environment variables
	. Comprehensive error handling
	
4. Prerequisites
	. Java 17 or later
	. Spring Data JPA: For ORM and database interactions
	. Spring Security: For securing the application - Oauth2
	. Docker: Containerization
	. Maven for building the project
	. Docker and Docker Compose for containerization
	. Kafka and Zookeeper for event-driven messaging
	. MySQL for database management
	
5. Installation and Setup
      . Cloning the Repository :
      
               git clone https://github.com/muthukumar-git-tech/cardTransaction-dev.git
 
	. Building the Project
      			Ensure you have Maven and Java installed. To build the project, run:
     			 mvn clean install
      
 	. Docker Setup
                        Environment Variables
      			To manage sensitive information,use Docker secrets or an .env file
      
     . Building and Running the Application :
       		Instructions to Use run.sh :
      			 1. Set the executable permission using the following command,
            			chmod +x run.sh
            	 2  Run the Script:
            	        ./run.sh
            
       This script will build the Docker images and start the services defined in docker-compose.yml
       
      . Building and Running the Docker Containers
      
      		1. Build the Docker Image	
               			docker build -t -transaction-routine .
            2. Start the Containers with Docker Compose
            			docker-compose up --build
            3. Stopping the Containers
            			docker-compose down
            	  
       
6.	Usage

	. Accessing the Application
		Once the application is running, you can access it at http://localhost:8080.

     . API Endpoints
		1. Create Account

			POST /accounts
			Request Body: { "document_number": "12345678900" }
			Response: { "account_id": 1, "document_number": "12345678900" }
			
        2. Get Account

			GET /accounts/{accountId}
			Response: { "account_id": 1, "document_number": "12345678900" }
			
		3. Create Transaction

			POST /transactions
			Request Body: { "account_id": 1, "operation_type_id": 4, "amount": 123.45 }
			Response: { "transaction_id": 1, "account_id": 1, "amount": -123.45, "type": "PURCHASE" }
       
  7. Error Handling :
  
        The application uses a global exception handler to manage exceptions and provide meaningful error messages. 
        Common exceptions include:

         1. ResourceNotFoundException: When a requested resource is not found.
         2.InvalidRequestException: For invalid request data.
         
8. Testing:
 		The project includes unit tests for controllers and services. You can run the tests using:    
 		  -> mvn test
 		
 		
9.	Contribution Guidelines
		Contributions are welcome! Please follow these steps:

		Fork the repository.
		Create a new branch (git checkout -b feature-branch).
		Make your changes.
		Commit your changes (git commit -m 'Add new feature').
		Push to the branch (git push origin feature-branch).
		Create a Pull Request.
		
		
10.	Test the Endpoints:
	1. Create an Account:
		curl -u user:password -X 
		POST http://localhost:8080/accounts -H 
		"Content-Type: application/json" -d 
		'{"document_number":"12345678900"}'
		
	2. Retrieve Account Information:
		curl -u user:password -X GET
		 http://localhost:8080/accounts/1

	3. Create a Transaction:
	 	curl -u user:password -X 
	 	POST 
	 	http://localhost:8080/transactions -H 
	 	"Content-Type: application/json" -d 
	 	'{
	 	"account_id":1,
	 	"operation_type_id":4,
	 	"amount":123.45
	 	}'
	 	
11.	API Documentation
		Endpoints
		Account Management
			1. Create Account:

         		POST /accounts
				Request Body: { "document_number": "12345678900" }
				Response: { "account_id": 1, "document_number": "12345678900" }
				
			2. Get Account:
			
				GET /accounts/{accountId}
				Response: { "account_id": 1, "document_number": "12345678900" }
				
            3. Transaction Management
                Create Transaction
                    POST /transactions
                         Request Body: { "account_id": 1, "operation_type_id": 4, "amount": 123.45 }
                         Response: { "transaction_id": 1, "account_id": 1, "amount": -123.45, "type": "PURCHASE" }

		
         
         
         
         
         
         
         
          
