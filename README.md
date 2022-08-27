# Project-4---CSCI-145
Roulette Game
FEATURES IMPLEMENTED:
•	Multiple players from 0 to 5 in a table (Wheel’s array of Player objects)
•	Wheel expanded to include 00, 0, and 1 through 36
•	Payoff for number bet is paid 35 to 1
•	Limitations on bets made with Wheel’s minimum and maximum bet values
•	Chips object created that holds an array to keep count of $100, $25, $5 and $1 chips
•	SuperVIP inherits VIP, which inherits Player class
•	VIP has bonus collection based on total cash value of bets made
•	SuperVIP has additional bonus based on number of bets made
•	Transaction history is kept track and outputted into “transactions.txt”
•	When players leave the game, their ending result is shown
•	ArrayList is used to maintain a queue (or the line of players waiting to join a game)
•	Multiple games can be played simultaneously, but there is only one dealer to spin
•	Each Wheel object has a table, which is an array of Player objects with 5 seats
•	Ability to exchange Chips in multiples of 100 (which includes 0)
•	Winning a game is paid out in the largest denominations of Chips first
•	Player enters game with only money
•	If insufficient Chips, they will automatically be forced to exchange $100 worth of Chips
o	Not enough small Chips will automatically breakdown larger Chips for smaller
o	Not enough money will automatically kick player out of table
•	Players can bet up to 3 times per round
•	Casino starts with 0 in cash and gets cash as players exchange for Chips
•	Players must announce they are still in the line to be added into the game
o	If they’re not, they get removed from the queue
o	Next player in line will be asked to announce they’re still in line
•	Both types of files can be used to start the game
•	Many validations of input to tell Player if they have an invalid input
Making comment here
