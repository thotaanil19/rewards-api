# Read Me First
Customer rewards-api with H2 DB

## Getting Started
1. Run this rewards-api as SpringBoot Application.
2. Open web browser and hit following URls.

	http://localhost:8080/customer/1/rewards (Invalid customer) - 404 status code 
	http://localhost:8080/customer/2/rewards (Valid customer)   - 200 status code
	http://localhost:8080/customer/2/rewards (Valid/In Valid customer)   - 500 if no data found in REWARDS_CONFIGURATION table

## Database
1. This application is using H2 DB in memory database.
2. Schema and Test data are available in src/main/resources/schema.sql and src/main/resources/data.sql files.
3. These files will be loaded into in memory H2 DB on server start up and after server stopping the loaded data will be vanished.
4. Table "REWARDS_CONFIGURATION" contain the rewards percentage for purchase amount.
5. Table "ORDERS" contains the customer orders.
6. Upon server startup, we can access the H2 DB console using following URL.
 
	http://localhost:8080/h2-console/
	
	JDBC URL: jdbc:h2:mem:demodb
	UserName: sa
	password: 
