# introsde-2017-assignment-3-server
University of Trento

Introduction of Service Design Engineering 

2017 Fall

#### Name: Julia Hermann
#### Email: julia.hermann@studenti.unitn.it
#### Client code: https://github.com/julcsii/introsde-2017-assignment-3-client
#### Server base URL on Heroku: https://sde-assignment-3.herokuapp.com/

#### Requirements
The system is designed to be used by Universities who want the students to register as Users and every User has a list of preferred activities. The Users have first name, last name and birth date. The activities have name, description, type, place start date and rating. In my understanding every user can have multiple activities, but an activity can only be linked to one person. (Every activity done by a person is unique. However it can by chance have the same attributes as another person's activity.)

##### Model
Users are represented with Person.java class, Activities are represented with Activities.java class. They both define tables in the database using annotations of the Java Persistence API. The following activity types are supported: SPORT, SOCIAL, ACADEMIC, WORK, CULTURE. Activity types are not stored in the database, but implemented with the ActivityType.java enum class. 

#### Implementation
SQLite database is used via JDBC driver and EclipseLink JPA implementation. The tables are filled with a databaseInit() method run by the client. The server is deployed on Heroku.

The following SOAP operations are implemented:

Method #1: readPersonList() => List<Person> | lists all the people in the database

Method #2: readPerson(Long id) => Person | gives all the information of one Person identified by {id}

Method #3: updatePerson(Person p) => Person | updates the information of the Person identified by {id}

Method #4: createPerson(Person p) => Person | creates a new Person and returns the newly created Person

Method #5: deletePerson(Long id) | deletes Person identified by {id}

Method #6: readPersonPreferences(Long id, String activity_type) => List<Activity> | returns the list of values of {activity_type} (e.g. sport) for a Person identified by {id}

Method #7: readPreferences() => List<Activity> | returns the list of all activities

Method #8: readPersonPreferences(Long id, Long activity_id) => Activity | returns the value identified by {activity_id} for a Person identified by {id}

Method #9: savePersonPreferences(Long id, Activity activity) | saves a new activity object {activity} of a Person identified by {id}

Method #10: updatePersonPreferences(Long id, Activity activity) => Activity | updates the activity identified with {activity.id}, related to the Person identified by {id}

Method #11 (Extra): evaluatePersonPreferences(Long id, Activity activity, int value) => Activity | updates the activity identified with {activity.id}, related to the Person identified by {id} with the value that define a specific value of preferences

Method #12 (Extra): getBestPersonPreference(Long id) => List<Activity> |  returns the best preference (or preferences if there are more) of the Person identified by {id}

#### Code execution locally with Eclipse
1. Clone repository
2. Create new Dynamic Web Project referring the location where you put the source code (select generating web.xml)
3. Configure JPA properties (Eclipselink, add connection with SQLite JDBC driver)
4. Add Ivy Library
5. Compile with build.xml
6. Run UniversityPublisher.java
(7. To fill database, run DatabaseInit.java)




