# graphicalMineSweeper
A fully GUI minesweeper game built in java using jframe and jpanel. Implementing menus allowing players to select their difficulty (Easy, medium, or Hard) each progressively increasing the map size and bomb count. The end menu allows players to re-try a new game at the same difficulty, select a new difficulty or exit the application. The gameplay loop is that of the classic minesweeper game with players clearing cells by left clicking them. These cells cascade if no bombs are nearby. Once a cell is revealed it will display how many mines exist adjacent to the cell. Players can right click to flag a square to mark it as containing a mine. if a player left clicks on a mined cell the game is over and all mine locations are revealed. If a player successfully clears all cells then they win the game. Upon game over win or loss the game's result (win or loss, the games difficulty and the remaining unrevealed safe cells are saved into a saveFile.
  
  TO DO:
  - Improve graphics
  - Add save functionality for high scores 
  - add limited flag count and display for this
