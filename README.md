# Farkle!
## Description
### About the Game
Farkle is a game of chance and strategy where two or more players roll dice which earn points. The goal is to be the player with 10,000 or more points on the final turn.
- Each player starts their turn by rolling six dice.
- After rolling, the player sets aside specific dice combinations which have a score value.
- The player can either bank the points earned that turn to their total points and pass the dice to the next player or risk the points earned that turn and roll the ramaining dice again, hoping to earn more points.
- If the remaining dice rolled do not have a scoring combination, then the player has “FARKLED” and points earned that turn are gone foreever.
- If the player has successfully with some luck and strategy used all six dice to score, then the player gets to roll all six dice again for a chance to earn more points. This “hot dice” move can be repeated over and over.
- When the player has either banked their points or Farkled, the dice are passed to the next  player.
- The next player rolls all six dice. Play continues until it is your turn again.
- To win at Farkle you must be the player with the highest score above 10,000 points on the final round of play.

The following combinations can be scored:
- a singe dice showing a 1 or a 5,
- three of a kind, such as, 2 2 2

You must select at least one scoring die after each roll. After you select the dice you want to keep you can either risk all the points earned this turn and roll the remaining dice (the fewer dice you roll the greater the chance you will Farkle) or bank those hard-earned points on your way to 10,000+ points.

When 10,000 or more points are scored, that player goes out. Each player gets one more turn to beat the high score.

### Scoring
- A *1* earns 100 points
- A *5* earns 50 points
- Three of kind earn the face value times 100, e.g., *2, 2, 2* = 200 points
- Three *1*s are special and earn 1,000 points

## Build and Run the Project
### Run using Gradle
You can run the game directly from the CLI using the gradle wrapper build script. Based on which OS you are running, it differs slightly.

#### CMD (Windows Command Line)
```shell
gradlew run
```

#### Bash (Linux/Mac, or Git Bash on Windows)
```shell
./gradlew run
```

### Build using Gradle
To build an executable Jar, the project uses the "Application" Gradle plugin. This greatly simplifies the commands needed to build a Java project and also provides scripts that can be used for distribution.

#### CMD (Windows Command Line)
```shell
gradlew clean build 
```

#### Bash (Linux/Mac, or Git Bash on Windows)
```shell
./gradlew clean build
```

#### Jar and Distributions
The executable jar will be located under `build/libs/`.  

You can also find the generated distributions under `build/distributions`.  
By unzipping either the .zip or the .tar, you will find this folder structure
- bin
  - farkle (use this to run in UNIX environment)
  - farkle.bat (Use this to run in Windows environment)
- libs
  - farkle-1.0.0.jar (the executable jar that is run using the scrips in the `bin` folder)