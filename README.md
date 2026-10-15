# Rizzle Escape Room

A Puzzle Game Application

=======
A text-based, Impossible Quiz-style escape room prototype. The player answers riddle rooms in sequence, loses a life for each wrong answer, and is meant to win by clearing every room. Rooms and fun facts are stored in the generic `LinkedChain<T>`.

## How to compile and run

Source files are in `src/RizzleEscapeRoom/`. From the project root, compile the game classes with:

```bash
mkdir -p /tmp/rizzle-classes
javac -d /tmp/rizzle-classes src/RizzleEscapeRoom/LinkedChain.java src/RizzleEscapeRoom/Room.java src/RizzleEscapeRoom/FunFact.java src/RizzleEscapeRoom/Game.java src/RizzleEscapeRoom/Main.java
java -cp /tmp/rizzle-classes RizzleEscapeRoom.Main
```

## Reflection

### What changed from our specification?

- **Main:** The original spec listed `Main()`, `runGame(Game)`, and `getUserInput(Scanner)`. The implementation uses `runGame(Game, Scanner)` so input can be supplied to the game loop, and adds `buildRooms()`, `buildFunFacts()`, `buildGame()`, and `askReplay(Scanner)` to keep setup and replay handling separate. `getUserInput` trims input, skips blank lines, and returns `null` at end-of-input.
- **Win/loss handling:** `Game.checkWin()` checks whether the room index has reached the end of the room chain, and `Game.checkLoss()` checks whether lives have run out. `Main` uses those methods to control the game loop; the outcome state is not stored in static fields.
- **LinkedChain:** The provided generic class stores both rooms and fun facts. It was not changed from the version that was given to us.

### What changed in the LinkedChain?

Nothing. The project uses the provided `LinkedChain<T>` without changing it.
### What happened to our unknowns?

- **Making `LinkedChain` type-agnostic:** Resolved by using `LinkedChain<T>` for both `Room` and `FunFact` entries.
- **Where skip-ahead logic belongs:** There is no skip-ahead feature in this prototype. Rooms are played sequentially, with the index managed by `Game`.
- **Shuffling room order:** Not implemented. Rooms are listed in a fixed order.
- **Calculating and displaying a rounded percentage:** Not implemented; there is no score or percentage display.
- **Picking a random element from an unordered chain:** Fun facts are selected using a random index with `size()` and `get()`, then removed. This is implemented in `Game.getRandomFunFact()`; random room selection is not implemented.

### What doesn't work yet?

The game flow and current tests cover the prototype's implemented behavior. The content set is still incomplete: there are 3 riddles and 2 fun facts, short of the planned 25–30 rooms and 5–10 facts. The stretch goals below are also unimplemented unless the team adds them.


### Next steps

#### Finish the MVP (proposed order)

1. Add riddles until there are 25–30 rooms total.
2. Add fun facts until there are 5–10 total.
3. Confirm the stretch-goal decisions with the team.

#### Stretch goals


| Stretch goal | Status | Why |
|---|---|---|
| Randomized room order | Committed (priority 1) | Our unknown about picking a random element from a chain is already solved for fun facts with `size()` and `get()`, so the same approach works for rooms. It also makes replays feel different. |
| Disable lives and show percent correct | Committed (priority 2) | This adds a second game mode and answers our open unknown about rounding a percentage in Java. It only needs a correct-answer counter in `Game` and a changed end screen in `Main`. |
| Variable lengths | Committed (priority 3) | The player picks how many rooms to play (for example 5, 10, or all). It builds directly on the chain and the random draw, and it makes the game less frustrating. |
| Progressive difficulty | Probably | Rooms would be ordered easy to hard, which needs a difficulty value on each `Room`. We'll do it once the full set of 25-30 riddles exists and we can rate them. |
| Custom quizzes / `Quiz` class | Probably | A `Quiz` class would pull room-pool setup out of `Main`, so players could build their own quizzes. It's a bigger refactor, so it comes after the committed goals are done and tested. |
| Variable difficulty modes (Troll, Trivia, Riddle, Math) | Only if time allows | Each mode needs its own full question set, which is a lot of content to write on top of the base 25-30. |
| Multiple correct answers | In MVP | `Room` already stores a `String[]` of acceptable answers, so this is not a stretch goal. |


#### New tests needed

- Add a check that the completed content reaches 25–30 rooms and 5–10 fun facts.
- If randomized room order is approved, test that rooms are selected without repeats and the chosen game length is honored.
- If score or difficulty modes are approved, add tests for score calculation, percentage rounding, and mode-specific behavior.

### Who does what


| Item | Owner |
|---|---|
| `Main.java` | Ruhaan |
| `MainTest.java` | Ruhaan |
| `GameTest.java` | Ruhaan |
| `Game.java` | Maple |
| `Room.java` | Maple |
| `FunFact.java` | Katie |
| `FunFactTest.java` | Katie |
| `RoomTest.java` | Katie |
| `LinkedChain.java` | PROVIDED|
| Riddles and fun facts | Everyone |

## Attribution

| File or item | Who | How |
|---|---|---|
| `Main.java` | Ruhaan | Implemented the setup and console flow |
| `MainTest.java` | Ruhaan | Wrote game-flow tests |
| `GameTest.java` | Ruhaan | Expanded the original scaffold with tests for the current `Game` methods |
| `Game.java` | Maple | Wrote the original class. |
| `Room.java` | Maple | Wrote the original class |
| `FunFact.java` | Katie | Wrote the class|
| `FunFactTest.java` | Katie | Wrote tests for construction, accessors, mutation, and string output. |
| `RoomTest.java` | Katie | Wrote the tests; answer test covers case and surrounding whitespace. |
| `LinkedListTest.java` | Maple | Added tests for adding, retrieving, removing, and rejecting null entries, |
| `LinkedChain.java` | Course | Provided unchanged. |
| Riddles and fun facts | Everyone | Team contribution; Claude was used to brainstorm riddles and fun facts. |

### GenAI note

Claude was used to brainstorm project ideas, draft some of the riddles and fun facts, and format comments and README text. Codex assisted with README updates. 
