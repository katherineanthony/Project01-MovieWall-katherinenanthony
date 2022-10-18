# Project01-MovieWall-katherineanthony

#### Overall Project Structure:
Overall, the project structure is that each Actor variable has a Movie with that actor's role in that movie in it. Then, in the `MovieWallGenerator` class, a new Actor is added to the arrayList of actors each time an Actor is read. The actors themselves were not sorted because when thinking of the runtime, the runtime of sorting would be big O of n^2, and the runtime of searching for all of an actor's movies would be n, so regardless of if the actor was sorted, the runtime would still be faster to not sort.

#### Movie Class:
This class has two instance variables, both Strings, title and role. In addition to appropriate setters for both of these variables, this class also overrides the `toString()` method in order to print out an actor's movies in an understandable and appealing way.
A few additional notes:
- The constructor for the Movie class takes no arguments because when movies are read from the file, their titles and roles are not read at the same time, so it made more sense to have the role and title be added separately.
- Each Movie contains the role for one Actor
#### Actor Class:
This class has two instance variables, a String name, and an ArrayList of movies. In addition to appropriate setters and getters for these variables, this class also overrides the `toString()` method so that the name of an Actor is printed. Additionally, the `getMovies()` method prints out each movie from the ArrayList of movies when called. (Called when you are printing a movie wall of an actor)
A few additional notes:
- The constructor for the Actor class is like the Movie class in that it takes no arguments because it reads the Movies and the name of the actor from the file at different times, so it just initilializes the ArrayList and adds the name and Movies later.
- `getMovies()` prints out the Movies for that Actor when an Actor is called by the User
- While the Actor in theory never has more than one Movie in it and therefore wouldn't need an ArrayList, in a better implementation the Actor would have all its own movies and there wouldn't be duplicate Actors in the generator ArrayList.
#### Movie Wall Generator Class:
This class has one instance variable, an ArrayList that holds actors.

This class has multiple methods:

- `searchForAlt(String userInput)`
In this method, we loop over each actor in the Arraylsit and compare it against the userInput but splitting at the spaces. This means that we compare by first and last names (and sometimes middle) to check if there are exact matches or if they meant an actor with a different first or last name.
This method returns the index if there is a match that is found, or -1 if there is no match, and `searchForSimilarActor()` will continue to compute the next suggested Actor.

- `searchForSimilarActor(String userInput)`
After calling searchForAlt, if we do not find an Actor with that method (if it returns -1), we then loop over the actor ArrayList and compare the UserInput against each actor, trying to find the one that is alphabetically closest. Using compareTo and Math.abs, we have two different variables, closest and closestIndex. We will return closestIndex as the Actor in the ArrayList that is alphabetically closest to the input. Closest is the calculation using compareTo to find which is the closest.

- `searchForActor(String userInput)`
In this method, we compare the userInput to each Actor in the ArrayList, and if we find the actor, we print it using the `getMovies()` method from the Actor class, and then return true. If we cannot find the actor, we return false.

- `readFile(String filePath)` In this method, we read the file using BufferedReader. Reading each line, we split it by comma first to get the movie title, then split the line again by open brace { to get an array of all the actors and crew members of that specific movies, then split each actor by comma to get their components and loop over their components to read character/job and name and save appropriately to the actor and movie ArrayList instance variables.

- `main`
In the main method, we read the file from args using `readFile()` and then using Scanner get userInput and call `searchForActor()` and `searchForSimilarActor()` if necessary to return the movies or a similar actor's movies.

#### Final Comments:
There are a few things that I would like to have spent more time on and changed, but hit many roadblocks and challenges while working on it. 
I tried many different sorting options, but some of them increased the number of duplicate actors (what!) and due to the mass of information it was hard to debug. 
I would like to go back and find a better way to generate a similar user, because I was trying something similar to the isAnagram where I search through the array of characters and the actor's name with the fewest differences in characters would be suggested user. I ran into problems with this implementation however with null errors and how I was reading Actor's names. 
Additionally, I would like to try to better the way I read Actor's information from the file and include unicode characters and add more error-handling for null Actors. When I was trying to do this initially, it was giving me mass errors, but when I would ignore those lines it would read the file fine. I think going back now and dealing with those special cases might be easier now that I have the bulk of it working.

#### Thank you!