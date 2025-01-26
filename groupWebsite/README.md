# About Nexus@RPI
Nexus@RPI is a comprehensive resource hub designed to help RPI students seamlessly navigate college life. The website offers an array of features aimed at enhancing student engagement and simplifying access to resources:
* **Event Hub:** 
  * An organized tool for managing and promoting campus events.
  * Users can browse a list of various clubs at Rensselaer Polytechnic Institute. Clicking on a club displays a short description.
  * With Google Calendar integration, users can add a club's meeting time directly to their calendar.
  * Users can request the addition of new clubs, which will be reviewed by website administrators.
  * Displays the user’s current location alongside the club’s meeting location, providing insights into the distance between them. 

* **Discussion Forum:** 
  * A collaborative space for students to share ideas, ask questions, and engage with peers.
  * Students can create new discussion forums for study groups or contribute to pre-existing forums linked to specific professors and classes.
  * Pre-existing forums are dynamically updated with information about currently taught classes and their professors.
  * Public discussion forums are searchable via a search bar, while private forums can be joined using a "Group Access Code" in the "My Study Groups" section.

* **Professor Picker:** 
  * A feature allowing students to rate professors for the classes they are teaching.
  * The main page displays all departments at Rensselaer Polytechnic Institute. Clicking on a department reveals the courses offered, along with their respective professors.
  * Users can provide feedback and ratings for professors based on their teaching performance in specific classes.

* **Mental Health Resources:** 
  * Provides easy access to academic, social, and administrative resources to support student well-being.
  * Users can recommend new resources, which are subject to administrator approval.

* **Admin Tools:**
  * Administrators can approve user-recommended additions of clubs and mental health resources.
  * Manage content by deleting user comments, ratings on the Professor Picker page, and discussion posts in forums.
  * Admins can also remove clubs and resources from the Event Hub and Mental Health Resources pages.

# Technologies
## Frontend
* Developed using HTML, CSS, and JavaScript for a visually appealing and interactive user experience.
* JavaScript is used to call PHP files that retrieve and display data from the MySQL database.
 
## Backend
* Integrated with the Google Calendar API using the Google-API-PHP-Client for calendar functionalities.
* Utilized the Here Maps API to display the user’s current location and the selected club’s meeting location.
* Built PHP scripts to interact with the MySQL database for data retrieval and updates.
* Included dedicated PHP scripts to reset specific database tables, such as club and teacher data, ensuring recovery of initial data in case of accidental deletion.