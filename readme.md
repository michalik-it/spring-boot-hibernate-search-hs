## Spring Boot and Hibernate Search integration

Project based on: http://blog.netgloo.com/2014/11/23/spring-boot-and-hibernate-search-integration/
Additionally:
- lucene query parser allows search query like 'group.name:Grupa%20AND%20name:amy'
- related entities
- lecene query tests

### Build and run

#### Configurations

Open the `application.properties` file and set your own configurations:

- Database connection parameters (this example use MySQL5 as DBMS)
- Hibernate Search's index directory

#### Prerequisites

- Java 8
- Maven > 3.0

#### From terminal

Go on the project's root folder, then type:

    $ mvn spring-boot:run

#### From Eclipse (Spring Tool Suite)

Import as *Existing Maven Project* and run it as *Spring Boot App*.


### Usage

- Run the application
- Type the url:
`http://localhost:8080/search?q=group.name:Grupa%20AND%20name:amy`	//users by group name and user name
`http://localhost:8080/search?q=group.altName:alt` //user by ale grup name
`http://localhost:8080/search?q=group.name:Grupa` //users by group name
`http://localhost:8080/search?q=amy`  //default by name
			   

  
  