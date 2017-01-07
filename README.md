Rules for "Scrabble Meets Minefield":

- a new word can cross ones already on the board but in the intersection point(s) must contain the same letters
- any sequence of letters adjacent to the new word must form a valid word (vertically and horizontally)
- there will be mines randomly placed on the board. If a word goes over a mine it explodes and the play is invalidated. There's no limit for how many times a mine can explode. The game doesn't provide feedback where the mine was. It's up to the player to figure it out where the mines are.
- the new word has to join (intersect or be adjacent) to at least one other word on the board for the play to be valid.

##### Examples:
- INVALID PLAY (new word PORT is does not join any existing word):

A I R □ P O R T

- VALID PLAY (new word PORT joins the existing word AIR):

A I R P O R T □

or

□ □ P □ □ □ □ □

□ □ O □ □ □ □ □

A I R □ □ □ □ □

□ □ T □ □ □ □ □