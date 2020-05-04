# FinalProject-
Final project for COSI 12b
Patrick Lee, Denise Zhong, Caelan Gadwah-Meaden, Kyra Rivest

Checkers game!

The program allows for two players: either two people or one person and an AI
- You can name both players, including the computer
- The game board will be displayed on the console and the whole game will be played here

You can have instructions displayed before you start playing if you don't know how to play, but basically:
- to move, type the coordinates of the checker you want to move follow by a space follow by the coordiante of the space you want to move the checker to   (ex. type: "C1 D2")
- KingCheckers are indicated by a capital "X" or "O", can move backwards, and also have two lives 

If you are playing the AI, it will automatically take its turn each turn. There is an algorithm involving the Moveset class that compares thousands of moves to find the best one 

If you are playing with another person, each person will take their turns as normal

At the end of the game, the game stats will be displayed showing the total number of turns each player took throughout the game.


An additional feature:

Launch PlayGame_cheatcode version instead of that regular one (who wants it anyway)

The cheatcode are as follows:

ababcd: turn all play X's checkers to KingCheckers 

blacksheep: allow Play X's to move random 0-3 steps each turn (there is a chance that you might not be able to move)
                                                              (big risk and big reward)

Enjoy the cheatcode version. 
