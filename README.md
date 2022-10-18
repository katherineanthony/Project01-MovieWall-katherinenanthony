# Project01-MovieWall-katherineanthony

#### Overall Project Structure:
Overall, the project structure is that each Actor variable has a Movie with that actor's role in that movie in it. Then, in the `MovieWallGenerator` class, a new Actor is added to the arrayList of actors each time an Actor is read.

#### Movie Class:
This class has two instance variables, both Strings, title and role. In addition to appropriate setters for both of these variables, this class also overrides the `toString()` method in order to print out an actor's movies in an understandable and appealing way.

#### Actor Class:
This class has two instance variables, a String name, and an ArrayList of movies. In addition to appropriate setters and getters for these variables, this class also overrides the `toString()` method so that the name of an Actor is printed. Additionally, the `getMovies()` method prints out each movie from the ArrayList of movies when called. (Called when you are printing a movie wall of an actor)

#### Movie Wall Generator Class:
This class has two instance variables, both ArrayLists that hold movies and actors.

This class has multiple methods:

- `searchForSimilarActor(String userInput)`
In this method, we loop over the actor ArrayList and compare the UserInput against each actor, trying to find the one that is alphabetically closest. Using compareTo and Math.abs, we have two different variables, closest and closestIndex. We will return closestIndex as the Actor in the ArrayList that is alphabetically closest to the input. Closest is the calculation using compareTo to find which is the closest.

- `searchForActor(String userInput)`
In this method, we compare the userInput to each Actor in the ArrayList, and if we find the actor, we print it using the `getMovies()` method from the Actor class, and then return true. If we cannot find the actor, we return false.

- `readFile(String filePath)` In this method, we read the file using BufferedReader. Reading each line, we split it by comma first to get the movie title, then split the line again by open brace { to get an array of all the actors and crew members of that specific movies, then split each actor by comma to get their components and loop over their components to read character/job and name and save appropriately to the actor and movie ArrayList instance variables.

- `main`
In the main method, we read the file from args using `readFile()` and then using Scanner get userInput and call `searchForActor()` and `searchForSimilarActor()` if necessary to return the movies or a similar actor's movies.