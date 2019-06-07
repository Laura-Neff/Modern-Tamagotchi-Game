# Modern-Tamagotchi-Game
Java-based Tamagotchi style game 

Background:
This interactive, event-based GUI game is coded in Java and relies heavily on abstract classes, interfaces, and polymorphism made for the final class.
It also draws on built-in interfaces, timers, and classes made by other users.
This was submitted as a group project for CS 170, the introductory computer science course taught at Emory University.
It reflects a successful growth of a supportive team getting hands-on with GUIs and having fun with it.
Laura Neff was responsible for the FinalTomogatchi.java, GameOver.java, ComboBox.java, Pet.java, Dog.java, and Parrot.java classes as the giveCandy(), giveVirus(), giveSoda(), and givePill(), set<Pet>Scale, set<Pet>Location methods for all pets in the game.
Jhaelle Payne was responsible for the FrontPage.java, Stitch.java, and Snail.java classes.
The GUI draws on the acm.graphics package, as well as the AbstractBreadboard class created by Paul Oser, an instructor at Emory.
The sound class was also designed by Paul Oser.

Objective:
In this game, the user is given a selection of either a parrot, dog, Stitch, or Gary the snail to have as a pet.
The gender of the pet is randomly chosen from an array, and the user is asked to input a name for the pet.
The goal of the game is for the user to make sure that all of the pet's health requirements are met in order to not get a game-over screen.
These requirements include physical health, energy, happiness, and fun.
The pet's health in these categories decrease over time and according to a timer.
If the pet loses all their health in one category, the user is brought to a game-over screen.
The pet's level of health for each of these categories is reflected in bars at the top of the screen.
If the pet is healthy in a category, the bar is green.
If the pet is losing health and is "okay" in a category, the bar is yellow.
If the pet is extremely unhealthy in a category, the bar is red.
While the pet is losing health, teasing, tongue-in-cheek jokes based on a day in the life of a CS student at Emory are randomly displayed on the screen.
Physical health is increased by giving the pet medicine or food (which is intentionally hidden from the user).
Energy is increased by letting the pet sleep.
Happiness is increased by giving the pet affection and petting the pet.
If one pets the pet by clicking and dragging, the pet makes an interesting, fun noise!
Fun is increased by doing an activity with the pet.
Each pet requires a different food, medicine, and activity.
The choice of food and activity the user does with the pet is pre-determined by the specific pet's abstract methods.
However, when giving the pet medicine, a "mini-game" is started.
The user must click on the correct medicine to give the pet.
If the user clicks on the wrong medicine, the pet "dies" and the user is brought to the game-over screen.
Some medicines are neutral for some pets.
For example, giving a virus to a Stitch will actually cure the Stitch, while it would kill other pets, while giving a pill to the dog would cure the dog.






