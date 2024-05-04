$(document).ready(function() {
  var output = '<h1>Projects Page</h1>';
  $.ajax({
    //Fetches JSON data from lab08.Json.json
    type: "GET",
    url: "../lab08/lab08Json.json",
    datatype: "json",
    //If successfully fetched...
    success: function(responseData, status){ 
      //Iterates through each item in the array menuItem, which is an array from the JSON file
      $.each(responseData.menuItem, function(i, item) {
        //Appends a link, along with an image to be outputted into projects.html.
        //Both are styled.
        output += '<a href="' + item.url + '">' + item.title + '</a>';
        output += '<a>' + '<img class="' + item.class + '" id="' + item.id +'" src="' + item.src
                   +'" alt="' + item.alt +'"></a>' ; 
      });

      //Outputs the content from output into the div tag in projects.html with id "contentTag"
      $('#contentTag').html(output);  
      
      //Attatches a click function to each item with item.id from the JSON data. 
      $.each(responseData.menuItem, function(i, item) {
        $("#" + item.id).click(function(){
          //element with specified id will do the clip animation from jQuery UI
          $("#" + item.id).effect("clip", {}, 650, callback("#" + item.id));
          //after the effect is done, load specified link
          $("#" + item.id).fadeOut(650, function() {
            window.location.assign(item.url);
          });
        });
      });

      //Makes content fade in on load
      $('#contentTag').hide().fadeIn(1000);
    //if JSON data doesn't successfully load, throw an error message
    }, error: function(msg){
      alert("There was a problem: " + msg.status + " " + msg.statusText);
    }
  });
  
  function callback(item){
    setTimeout(function() {
      $( item ).removeAttr( "style" ).hide().fadeIn();
    }, 1000 );
  }

  //Attatches a click handler to each a tag that has a link to the floatLeft tag.
  //When clicked, window smoothly scrolls upward. 
  $("a[href='#floatLeft']").click(function() {
    $('html, body').animate({
        scrollTop: 0
    });
  });
});

