# TodoManager

A Java Maven project for the Software Construction assignment.

## Features
- Display a list of tasks.
- Filter tasks containing "Spring".
- Delete tasks that do not contain "Spring".

## Project Structure
- TodoService: interface for retrieving and deleting tasks.
- SimpleTodoService: in-memory implementation of TodoService.
- TodoBusinessImpl: uses TodoService and includes the main method.

## Tests
Seven JUnit 4 tests:
- Six tests using the real SimpleTodoService implementation.
- One existing test using TodoServiceStub.

## Build and Test
```shell
mvn clean
mvn compile
mvn test
mvn package
```

## Run
After compilation:
```shell
java -cp target/classes com.todomanager.business.TodoBusinessImpl
```

## Source and License
Adapted from:
https://github.com/in28minutes/MockitoTutorialForBeginners

Changes include renamed packages, a real in-memory service implementation,
six additional tests, and a runnable demonstration.

The original MIT license is retained in LICENSE.