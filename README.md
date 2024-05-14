### Stick Hero Game by Aditya Bagri (2022029) and Prakhar (2022360)

# Stick Hero Game

This is a simple implementation of the Stick Hero game using JavaFX.

## Table of Contents
- [Introduction](#introduction)
- [Features](#features)
- [Getting Started](#getting-started)
- [Gameplay](#gameplay)
- [Code Structure](#code-structure)
- [Dependencies](#dependencies)
- [License](#license)

## Introduction
Stick Hero is a popular mobile game where the player controls a character who must extend a stick to bridge gaps between platforms. The goal is to cover the maximum distance without falling.

## Bonus
Timer using multithreading, Moving background, Changed the color of blocks according to the background, Made the background moving with parallax in account.

## Features
- Dynamic block generation.
- Stick extension mechanism.
- Score tracking.
- Game over conditions.
- Smooth animation using JavaFX.

## Getting Started
### Prerequisites
- Java Development Kit (JDK) installed.
- JavaFX library.



1. **Open the project in your favorite Java IDE.**
2. **Run the `Main.java` class.**
3. **Enjoy playing Stick Hero!**

## Gameplay
- Press and hold the mouse button to extend the stick.
- Release the mouse button to make the stick fall.
- The stick must bridge the gap between blocks.
- Score increases for successful bridge extensions.
- The game ends if the stick falls short or extends too far.

## Code Structure
The code is organized into the following classes:
- `inGameController`: The main controller class handling game logic.
- `Player`: Represents the player entity.
- `ScoreManager`: Manages the scoring system.
- `Blocks`: Handles block generation and movement.
- `Stick`: Represents the stick entity.

## Dependencies
The game relies on JavaFX for its graphical user interface. Make sure to have the necessary dependencies installed.
