$(document).ready(function() {
  $("html").css({
    
    "background-image": "url('../../resources/skyBackground.jpg')",
    "background-size": "cover",
    "overflow-y": "scroll",
    "overflow-x": "hidden"
  });

  var output = "<h1>Atom</h1>";
  $.ajax({
    //Fetches JSON data from lab08.Json.json
    type: "GET",
    url: "../lab04/barvea-Atom.xml",
    datatype: "xml",
    //If successfully fetched...
    success: function(xml){ 
      //goes through each entry section in the ATOM file
      $(xml).find("entry").each(function() {
        //Gets the title
        var title = $(this).find("title").text(); 
        //Gets the link URL
        var link = $(this).find("link").attr("href");
        // Appends a link to the output
        output += '<a href="' + link + '" class="linkPadding">' + title + '</a><br>';
      });

      // Outputs the content into the div tag in projects.html with id "contentTag"
      $('#contentTag').html(output)
      //sets the links that use the linkPadding class to have bottom padding, and sets all the links
      //to be inline block
      $(".linkPadding a").css({
        "padding-bottom": "2em",
        "display": "inline-block"
      });

    }, error: function(msg){ //throws an error message if can't opend file
      alert("There was a problem: " + msg.status + " " + msg.statusText);
    }
  });

  var output2 = "<h1>RSS</h1>";
  $.ajax({
    //Fetches JSON data from lab08.Json.json
    type: "GET",
    url: "../lab04/barvea-RSS.xml",
    datatype: "xml",
    //If successfully fetched...
    success: function(xml){
      //goes through each item section in the RSS file 
      $(xml).find("item").each(function() {
        //Gets the title
        var title = $(this).find("title").text();
        //Gets the link URL
        var link = $(this).find("link").text();
        // Appends a link to the output
        output2 += '<a href="' + link + '" class="linkPadding">' + title + '</a><br>';
      });

      // Outputs the content into the div tag in projects.html with id "contentTag"
      $('#contentTag').append(output2);

      //sets the links that use the linkPadding class to have bottom padding, and sets all the links
      //to be inline block
      $("a.linkPadding").css({
        "padding-bottom": "2em",
        "display": "inline-block"
      });
    }, error: function(msg){ //throws an error if can't open file
      alert("There was a problem: " + msg.status + " " + msg.statusText);
    }
  });

  //below sets styling for all the classes
  $(".center").css({
    "font-family":" Diphylleia, sans-serif",
    "margin-left": "auto",
    "margin-right": "auto",
    "text-align": "center",
    "background-color": "rgba(128, 128, 128, 0.2)",
    "min-width": "40%",
    "width": "fit-content",
    "padding":" 0em 1em 0.7em 1em"
  });

  $(".header").css({
    "overflow": "hidden",
    "padding": "0.5em 0 0 0.5em",
    "text-align": "center",
    "font-size": "1.5em",
    "width": "100%",
    "background-color": "rgba(128, 128, 128, 0.2)",
    "word-spacing": "0.625em"
  });

  $("#homeButtonImg").css({
    "max-width": "3em",
    "max-height": "3em",
    "border": "10em",
    "border": "solid",
    "border-radius": "2pc",
    "border-color": "black",
    "background-color": "grey"
  });

  $("#floatLeft").css({
    "float": "left"
  });

  $("a").css({
    "font-family":" Diphylleia, sans-serif",
    "color": "black",
    "cursor": "pointer"
  });

});

