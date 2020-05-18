# Model
### Objects
* Task:
    * UUID
    * Name
    * Number of points
    * Deadline
* Group:
    * Number
    * Count of students
    * List of all students
* Student”
    * Name and Surname
    * Repository URL
    * UUID
    * List of given tasks
    * List of complited tasks
* Current global task:
    * UUID
    * Deadline
* Сontrol:
    * Points
    * Date
* Workshops:
    * Date.
    * Number
    * Attendance


### Processes and their implementation

* Filling the structures of progran in java
    * Implementation - At the beginning, files like config.groovy finded by main program. They are transferred to the execution of the subroutine at groovy, this progran returns to java ready structures.     
* Upload necessary tasks files from git repo into directiory: 
    * Implementation - Call (from java code) “download repositories” with all necessary  arguments with are taken from the filled stuctures. 
* Run compilation process
    * Implementation - java Code calls from the project dir "gradle build"
* Generate documentatiom
    * Implementation - Gradle doc
* Check Google Java Style
    * Implementation - java calls necessary program, which is check that.
* Run tests
    * Implementation - Call from the project dir gradle test      
* Count the number of
    * Successful
    * Failed
    * Not runned
    * Fill the stucture based on that.
    * Implementation - Count the number while gradle test.
* Count the number of points for this task.
    * Implementation - Call method, which will count it somehow, using earlier given logic. 
* Calculate the total number of points and grades for each control point and the final certification. (The sum of points to the control point) Based on this, calculate the final grade at the moment
    * Implementation - Call a function that will go through all the tasks for which the date is less than the date of the control point and sum their points. After that, according to some logic. 
* Generate a report in HTML.
    * Call method, which will check all the students and print HTML report.


