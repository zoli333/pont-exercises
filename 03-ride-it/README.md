

This is my current implementation uploaded for the 3rd task in pont-excersises.

You can import this in eclipse, but make sure you also import the database (like into mysql workbench)

The project was written in eclipse ide, so the recommended program for build and run is eclipse (oxygen enterprise edition)

The project is still under developement.

Usage:

  - register users into database,
  - log in / sign in as driver or passenger as your role.
  - you will be redirected to passenger.html or driver.html depending on which role you have passegner or driver. 
     Any car will be listed for you, when at least it has one driver or one passenger, else you won't see it.
  
Completed:
  - ~refactor the java code put,post,delete~
  - ~make driver.html work~
  - ~define destination for the car with role driver~ 
  - ~Separate the city, from the specific adress, make different columns for them (make them searchable)~
  - ~make the buttons dynamic, dont show the 2 apply or remove buttons, just for those tables where it is relevant.~


Todos:
  - make a search form, and button where the passenger can search for cars.
  - make the page listable - this means if the user dont want to search for specific destination, it can scroll between them.
  - limit the page to not to list more than 5 cars, for example.

More Todos:
  - make the add, remove buttons' behaviour dynamic, by not refreshing the page and listing all the cars all the time when a button is clicked, figure out a new way to do this,without having to refresh the page, and querying allcars object all the time.
  - (later should be given intermediate city destinations)
  
