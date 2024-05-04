<?php 
  include('includes/init.inc.php'); // include the DOCTYPE and opening tags
  include('includes/functions.inc.php'); // functions
?>
<title>PHP &amp; MySQL - ITWS</title>   

<?php include('includes/head.inc.php'); ?>

<h1>PHP &amp; MySQL</h1>
      
<?php include('includes/menubody.inc.php'); ?>

<?php
// We'll need a database connection both for retrieving records and for
// inserting them.  Let's get it up front and use it for both processes
// to avoid opening the connection twice.  If we make a good connection,
// we'll change the $dbOk flag.
$dbOk = false;

/* Create a new database connection object, passing in the host, username,
     password, and database to use. The "@" suppresses errors. */
@$db = new mysqli('localhost', 'phpmyadmin','Valencia317$%', 'iit');

if ($db->connect_error) {
   echo '<div class="messages">Could not connect to the database. Error: ';
   echo $db->connect_errno . ' - ' . $db->connect_error . '</div>';
} else {
   $dbOk = true;
}

// Now let's process our form:
// Have we posted?
$havePost = isset($_POST["save"]);

// Let's do some basic validation
$errors = '';
if ($havePost) {

   // Get the output and clean it for output on-screen.
   // First, let's get the output one param at a time.
   // Could also output escape with htmlentities()
   $title = htmlspecialchars(trim($_POST["title"]));
   $year = htmlspecialchars(trim($_POST["year"]));

   // special handling for the date of birth
   // Now convert the $dobTime into a date using the specfied format.
   // Does the outcome match the input the user supplied?
   // The right side will evaluate true or false, and this will be assigned to $dobOk

   $focusId = ''; // trap the first field that needs updating, better would be to save errors in an array

   if ($title == '') {
      $errors .= '<li>Title may not be blank</li>';
      if ($focusId == '') $focusId = '#firstNames';
   }
   if ($year == '') {
      $errors .= '<li>Year may not be blank</li>';
      if ($focusId == '') $focusId = '#lastName';
   }
   if (strlen($year) != 4) {
      $errors .= '<li>Enter a valid date in yyyy format</li>';
      if ($focusId == '') $focusId = '#dob';
   }

   if ($errors != '') {
      echo '<div class="messages"><h4>Please correct the following errors:</h4><ul>';
      echo $errors;
      echo '</ul></div>';
      echo '<script type="text/javascript">';
      echo '  $(document).ready(function() {';
      echo '    $("' . $focusId . '").focus();';
      echo '  });';
      echo '</script>';
   } else {
      if ($dbOk) {
         // Let's trim the input for inserting into mysql
         // Note that aside from trimming, we'll do no further escaping because we
         // use prepared statements to put these values in the database.
         $title = trim($_POST["title"]);
         $year = trim($_POST["year"]);
        //  $dobForDb = trim($_POST["dob"]);

         // Setup a prepared statement. Alternately, we could write an insert statement - but
         // *only* if we escape our data using addslashes() or (better) mysqli_real_escape_string().
         $insQuery = "insert into movies (`title`,`year`) values(?,?)";
         $statement = $db->prepare($insQuery);
         // bind our variables to the question marks
         $statement->bind_param("ss", $title, $year);
         // make it so:
         $statement->execute();

         // give the user some feedback
         echo '<div class="messages"><h4>Success: ' . $statement->affected_rows . ' movie added to database.</h4>';
         echo $title . ' ' . ', year ' . $year . '</div>';

         // close the prepared statement obj
         $statement->close();
      }
   }
}
?>

<h3>Add Movie</h3>
<form id="addForm" name="addForm" action="movies.php" method="post" onsubmit="return validate(this);">
   <fieldset>
      <div class="formData">

         <label class="field" for="titleName">Title:</label>
         <div class="value"><input type="text" size="60" value="<?php if ($havePost && $errors != '') {
                                                                     echo $title;
                                                                  } ?>" name="title" id="titleName" /></div>

         <label class="field" for="yearName">Year:</label>
         <div class="value"><input type="text" size="60" value="<?php if ($havePost && $errors != '') {
                                                                     echo $year;
                                                                  } ?>" name="year" id="yearName" /></div>


         <input type="submit" value="save" id="save" name="save" />
      </div>
   </fieldset>
</form>

<h3>Movies</h3>
<table id="actorTable">
   <?php
   if ($dbOk) {

      $query = 'select * from movies order by year';
      $result = $db->query($query);
      $numRecords = $result->num_rows;

      echo '<tr><th>Title:</th><th>Year:</th><th></th></tr>';
      for ($i = 0; $i < $numRecords; $i++) {
         $record = $result->fetch_assoc();
         if ($i % 2 == 0) {
            echo "\n" . '<tr id="movies-' . $record['movieid'] . '"><td>';
         } else {
            echo "\n" . '<tr class="odd" id="movies-' . $record['movieid'] . '"><td>';
         }
         echo htmlspecialchars($record['title']);
         echo '</td><td>';
         echo htmlspecialchars($record['year']);
         echo '</td><td>';
         echo '<img src="resources/delete.png" class="deleteMovie" width="16" height="16" alt="delete movie"/>';
         echo '</td></tr>';
         // Uncomment the following three lines to see the underlying
         // associative array for each record.
         // echo '<tr><td colspan="3" style="white-space: pre;">';
         // print_r($record);
         // echo '</td></tr>';
      }

      $result->free();

      // Finally, let's close the database
      $db->close();
   }
  ?>
</table>
<?php include('includes/foot.inc.php'); 
  // footer info and closing tags
?>
