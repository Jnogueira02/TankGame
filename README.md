
# Tank Game

## Description
**Tank Game** is a strategic action game where players control a tank to navigate through a dynamic environment, engage enemy tanks, and survive as long as possible. The game features a rich set of interactions, including obstacles like walls, power-ups in the form of MedPacks, and various enemy tanks that use intelligent strategies to challenge the player.

## Features
- **Dynamic Game Environment:** The game environment updates in real-time, responding to player actions and game events.
- **Enemy AI:** Enemy tanks have automated movement and attack patterns, providing a challenging experience.
- **Power-Ups:** Players can collect MedPacks to repair their tanks and extend their playtime.
- **Customizable Tank Strategies:** Players can change their tank's behavior dynamically, adapting to different situations in the game.
- **Observer Pattern:** The game uses the observer pattern to update game components like health bars and enemy count displays without tightly coupling the classes.
- **Strategy Pattern:** Missile movements are handled using the strategy pattern, allowing for easy addition of new missile types.

## Technologies
- **JavaFX:** For rendering the game interface, managing game states, and handling user inputs.
- **Java:** All game logic and structures are implemented using Java.

## Controls
- **Arrow Keys/WASD:** Move the tank around the game arena.
- **Spacebar:** Fire a missile in the direction the tank is facing.
