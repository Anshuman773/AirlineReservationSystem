# Airline Reservation System

This Java-based application manages an airline reservation system, allowing users to reserve seats, cancel reservations, and display records. The system uses Oracle Database for persistent storage of passenger information.

## Prerequisites

- Java Development Kit (JDK)
- Oracle Database
- Oracle JDBC Driver

## Features

- **Reservation**: Allows users to reserve a seat by entering their passport number, name, and email address.
- **Cancellation**: Allows users to cancel a reservation by providing their passport number.
- **Display Records**: Displays all the current reservations with details such as passport number, name, email address, and seat number.
- **Database Integration**: Uses Oracle Database to store and manage passenger details.

## Installation and Setup

1. **Install Java and Oracle Database**: Ensure that JDK and Oracle Database are installed on your system.
2. **Download Oracle JDBC Driver**: Download the Oracle JDBC driver (`ojdbc8.jar`) and include it in your project classpath.
3. **Database Configuration**: Update the `DB_URL`, `DB_USER`, and `DB_PASSWORD` variables with your Oracle Database connection details.

## Usage

1. **Compile the Code**: Compile the Java code using a Java compiler.
    ```bash Code Explanation
1.	Passenger Class: This class holds the details of each passenger (passport number, name, seat number, email).
2.	Database Connection:
o	establishConnection(): Establishes a connection to the Oracle database using JDBC.
o	closeConnection(): Closes the database connection.
3.	Table Creation:
o	createTable(): Creates the Passenger table in the database if it doesn't already exist.
4.	Reservation:
o	reserve(): Reserves a seat for a passenger, storing their details in the database and assigning them a seat number.
5.	Cancellation:
o	cancel(): Cancels a reservation by deleting the passenger record from the database based on the passport number.
6.	Display:
o	display(): Retrieves and displays all passenger records from the database.
with additional features and validations as needed.
