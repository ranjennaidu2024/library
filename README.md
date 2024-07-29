
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

For simple api test you can use curl command.
To run curl commands on Windows, you need to have the curl utility installed where you can refer to:

```
https://kb.naverisk.com/en/articles/5569958-how-to-install-curl-in-windows 
```

### 1. Register a New Book

**Register a New Book with isbn=1201211234 , title=Harry Potter and Author Name=J.K.Rowling**:

curl -X POST "http://localhost:8081/api/books" -H "Content-Type: application/json" -d "{\"isbn\":\"1201211234\", \"title\":\"Harry Potter\", \"author\":\"J.K.Rowling\"}"

### 2. Register a New Borrower

**Register a New Borrower with name=Alex Wong and email=alex@example.com**:

curl -X POST "http://localhost:8081/api/borrowers" -H "Content-Type: application/json" -d "{\"name\":\"Alex Wong\", \"email\":\"alex@example.com\"}"


### 3. Get a List of All Books

curl -X GET "http://localhost:8081/api/books"

### 4. Get a List of All Borrowers

curl -X GET "http://localhost:8081/api/borrowers"

### 5. Borrow a Book

**Borrow a Book with bookId=3 by a borrower with borrowerId=3**:

curl -X POST "http://localhost:8081/api/books/3/borrow" -H "Content-Type: application/json" -d "{\"borrowerId\":3}"

### 6. Return a Borrowed Book

**Return a Borrowed Book with bookId=3**:

curl -X POST "http://localhost:8081/api/books/3/return"




## Testing the API Requirements

To test the specific requirements related to ISBN and book borrowing:

### 7. Multiple Books with Same Title and Author but Different ISBNs are considered as different books

**Register a New Book with title=Harry Potter and Author Name=J.K.Rowling but different ISBN where isbn=1201211235**:

curl -X POST "http://localhost:8081/api/books" -H "Content-Type: application/json" -d "{\"isbn\":\"1201211235\", \"title\":\"Harry Potter\", \"author\":\"J.K.Rowling\"}"

Now this will be registered as new book as it is using different ISBNs eventhough the title and author are same with previously registered book.


### 8. Multiple Books with Same ISBN Number Should Have the Same Title and Author

**Attempt to Register Book with Same ISBN where isbn=1201211234,  but Different Title/Author**:

curl -X POST "http://localhost:8081/api/books" -H "Content-Type: application/json" -d "{\"isbn\":\"1201211234\", \"title\":\"Cartoon Network\", \"author\":\"Mr.Bean\"}"

This should fail validation and return "A book with the same ISBN must have the same title and author" message.

### 9. Multiple Copies of Books with Same ISBN are allowed in the system and books allowed to be registered with different ids.

**Register Multiple Copies of a Book**:

curl -X POST "http://localhost:8081/api/books" -H "Content-Type: application/json" -d "{\"isbn\":\"1201211235\", \"title\":\"Harry Potter\", \"author\":\"J.K.Rowling\"}"

### 10. Ensure No More Than One Borrower Borrows the Same Book at the Same Time

**Borrow a Book with bookId=3 by a borrower with borrowerId=3**:

curl -X POST "http://localhost:8081/api/books/3/borrow" -H "Content-Type: application/json" -d "{\"borrowerId\":3}"

**Borrower with borrowerId=1 attempt to Borrow the Same Book which was already borrowed by Borrower with borrowerId=3**:

curl -X POST "http://localhost:8081/api/books/3/borrow" -H "Content-Type: application/json" -d "{\"borrowerId\":1}"

This should fail with an error message "Book is already borrowed" indicating the book is already borrowed.





