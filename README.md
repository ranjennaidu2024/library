
# Library Management System

This project is a simple library management system implemented using Spring Boot, Java 17, and H2 in-memory database. The system allows you to manage books and borrowers, providing RESTful APIs to register borrowers, register books, borrow books, return books, and list all books and borrowers.

## Justification for Choice of Database

We have chosen the H2 in-memory database for this application due to the following reasons:

1. **Lightweight and Fast**: H2 is an in-memory database, making it very lightweight and fast. It is well-suited for development and testing purposes.
2. **Easy Integration**: H2 integrates seamlessly with Spring Boot, allowing for quick setup and minimal configuration.
3. **Zero Configuration**: Being an in-memory database, H2 requires no installation or setup. It runs within the JVM of the application.
4. **Persistence Option**: H2 can also be configured to store data on disk, providing flexibility to switch between in-memory and persistent storage as needed.

H2 Database Console can be accessed by:

```
http://localhost:8081/h2-console
```


### Running the Application using Docker

1. **Pull the Docker Image from Docker Hub**: docker pull ranjennaidu2024/library:latest
2. **Run the Docker Container**: docker run -d -p 8081:8081 ranjennaidu2024/library:latest
3. **Accessing the Application**: You can now access the application API below using the curl command.


## API Documentation

### Base URL

```
http://localhost:8081/api
```

### Register a New Borrower

**Endpoint**: `/borrowers`

**Method**: `POST`

**JSON Request Body**:
{
    "name": "Alex Wong",
    "email": "alex@example.com"
}


**Curl Command**:
curl -X POST "http://localhost:8081/api/borrowers" -H "Content-Type: application/json" -d "{\"name\":\"Alex Wong\", \"email\":\"alex@example.com\"}"

### Register a New Book

**Endpoint**: `/books`

**Method**: `POST`

**JSON Request Body**:
{
    "isbn": "1231231230",
    "title": "Book Title 3",
    "author": "Author 3"
}

**Curl Command**:
curl -X POST "http://localhost:8081/api/books" -H "Content-Type: application/json" -d "{\"isbn\":\"1231231230\", \"title\":\"Book Title 3\", \"author\":\"Author 3\"}"

### Get a List of All Books

**Endpoint**: `/books`

**Method**: `GET`

**Curl Command**:
curl -X GET "http://localhost:8081/api/books"

### Get a List of All Borrowers

**Endpoint**: `/borrowers`

**Method**: `GET`

**Curl Command**:
curl -X GET "http://localhost:8081/api/borrowers"

### Borrow a Book

**Endpoint**: `/books/{bookId}/borrow`

**Method**: `POST`

**JSON Request Body**:
{
    "borrowerId": 1
}

**Curl Command**:
curl -X POST "http://localhost:8081/api/books/1/borrow" -H "Content-Type: application/json" -d "{\"borrowerId\":1}"

### Return a Borrowed Book

**Endpoint**: `/books/{bookId}/return`

**Method**: `POST`

**Curl Command**:
curl -X POST "http://localhost:8081/api/books/1/return"

## Testing the API Requirements

To test the specific requirements related to ISBN and book borrowing:

### Multiple Books with Same Title and Author but Different ISBNs

**Register Book 1**:
curl -X POST "http://localhost:8081/api/books" -H "Content-Type: application/json" -d "{\"isbn\":\"1111111111\", \"title\":\"Same Title\", \"author\":\"Same Author\"}"

**Register Book 2**:
curl -X POST "http://localhost:8081/api/books" -H "Content-Type: application/json" -d "{\"isbn\":\"2222222222\", \"title\":\"Same Title\", \"author\":\"Same Author\"}"

### Multiple Books with Same ISBN Number Should Have the Same Title and Author

**Register Book 3**:
curl -X POST "http://localhost:8081/api/books" -H "Content-Type: application/json" -d "{\"isbn\":\"1234567890\", \"title\":\"Correct Title\", \"author\":\"Correct Author\"}"

**Attempt to Register Book with Same ISBN but Different Title/Author**:
curl -X POST "http://localhost:8081/api/books" -H "Content-Type: application/json" -d "{\"isbn\":\"1234567890\", \"title\":\"Wrong Title\", \"author\":\"Wrong Author\"}"

This should fail validation.

### Multiple Copies of Books with Same ISBN

**Register Multiple Copies of a Book**:
curl -X POST "http://localhost:8081/api/books" -H "Content-Type: application/json" -d "{\"isbn\":\"3333333333\", \"title\":\"Multiple Copies Title\", \"author\":\"Multiple Copies Author\"}"
curl -X POST "http://localhost:8081/api/books" -H "Content-Type: application/json" -d "{\"isbn\":\"3333333333\", \"title\":\"Multiple Copies Title\", \"author\":\"Multiple Copies Author\"}"

### Ensure No More Than One Borrower Borrows the Same Book at the Same Time

**Borrow Book with ID 1**:
curl -X POST "http://localhost:8081/api/books/1/borrow" -H "Content-Type: application/json" -d "{\"borrowerId\":1}"

**Attempt to Borrow the Same Book Again**:
curl -X POST "http://localhost:8081/api/books/1/borrow" -H "Content-Type: application/json" -d "{\"borrowerId\":2}"

This should fail with an error message indicating the book is already borrowed.

### Borrowing and Returning Books

**Borrow a Book**:
curl -X POST "http://localhost:8081/api/books/2/borrow" -H "Content-Type: application/json" -d "{\"borrowerId\":2}"

**Return a Book**:
curl -X POST "http://localhost:8081/api/books/2/return"






