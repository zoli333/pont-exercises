

This is a first implementation uploaded for the 3rd task in pont-excersises. This is my first version.

You can import this in eclipse, but make sure you also import the database (like into mysql workbench)

The project was written in eclipse ide, so the recommended program for build and run is eclipse (oxygen enterprise edition)

The project is still under developement.

Usage:

  -register users into database,
  -log in / sign in as driver or passenger as your role.
  -you will be redirected to index.html or driver.html depending on which role you have passegner or driver. 
    Any car will be listed for you, when at least it has one driver or one passenger, else you won't see it.
  
Todos:
  - make a search form, and button where the passenger can search for cars.
  - define destination for the car with role driver (later should be given intermediate city destinations). With this separate the city, from the specific adress, make different columns for them -> make these searchable.
  - make the page listable - this means if the user dont want to search for specific destination, it can scroll between them.
  - limit the page to not to list more than 5 cars, for example.
  - make the buttons dynamic, dont show the 2 apply or remove buttons, just for those tables where it is relevant.
  - make the add, remove buttons' behaciour dynamic, by not refreshing the page and listing all the cars all the time when a button is clicked, figure out a new way to do this,without having to refresh the page, and querying allcars object all the time.

Z.
