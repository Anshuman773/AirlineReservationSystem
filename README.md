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
    ```bash
    javac airline.java
    ```

2. **Run the Application**: Run the compiled Java program.
    ```bash
    java airline
    ```

3. **Menu Options**:
    - **1. Reservation**: Enter passport number, name, and email to reserve a seat.
    - **2. Cancel**: Enter passport number to cancel the reservation.
    - **3. Display Records**: View all reservations.
    - **4. Exit**: Exit the application.

## Code Overview

### Passenger Class

```java
class Passenger {
    String passport;
    String name;
    int seatNum;
    String email;
}
```

\
Code Explanation
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


- **Passenger Class**: A simple class to hold passenger details.
- **airline Class**: The main class with methods to handle reservations, cancellations, and displaying records. It also includes methods to manage the database connection and create the Passenger table.

This code provides a basic structure for an airline reservation system with database integration using JDBC. The system can be further enhanced with additional features and validations as needed.
