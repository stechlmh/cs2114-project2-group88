# Rizzle Escape Room

A text-based escape room with riddles, trivia, math questions, and troll questions. Players can choose a category, quiz length, room order, and lives or score mode. The standard quiz has 27 rooms and 8 fact, hint, and troll cards. Rooms, accepted answers, and fun facts use the generic `LinkedChain<T>` class.

## How to compile and run

Source files are in `src/RizzleEscapeRoom/`. From the project root, compile and run the game with:

```bash
mkdir -p /tmp/rizzle-classes
javac -d /tmp/rizzle-classes src/RizzleEscapeRoom/LinkedChain.java src/RizzleEscapeRoom/Room.java src/RizzleEscapeRoom/FunFact.java src/RizzleEscapeRoom/Game.java src/RizzleEscapeRoom/Quiz.java src/RizzleEscapeRoom/Main.java
java -cp /tmp/rizzle-classes RizzleEscapeRoom.Main
```

The tests use Virginia Tech's `student.TestCase` library. Add the course test library to the classpath:

```bash
mkdir -p /tmp/rizzle-classes
javac -cp ".:path/to/test-libraries/*" -d /tmp/rizzle-classes src/RizzleEscapeRoom/*.java
java -cp ".:/tmp/rizzle-classes:path/to/test-libraries/*" student.TestRunner RizzleEscapeRoom.MainTest RizzleEscapeRoom.GameTest RizzleEscapeRoom.RoomTest RizzleEscapeRoom.FunFactTest RizzleEscapeRoom.QuizTest RizzleEscapeRoom.LinkedChainTest
```

On Windows, replace `:` in the classpath with `;`.

## Reflection

### What changed from our specification?

- **Main:** `runGame(Game, Scanner)` accepts a scanner so game flow can be tested with supplied input. `Main` also builds the standard quiz, collects custom quiz questions, and asks for game settings.
- **Game flow:** `Game.checkWin()` checks whether the room index reached the end of the selected room chain. `Game.checkLoss()` checks whether lives ran out. Score mode records submitted answers and displays a rounded percentage.
- **Room answers:** Accepted answers are stored in `LinkedChain<String>` instead of `String[]`. The array constructor remains as a convenience for existing callers. Answer matching ignores case and surrounding whitespace.
- **Question formats:** Rooms have a category and difficulty. Fun fact cards use `questionType` to display as facts, hints, or troll cards.
- **LinkedChain:** The provided generic class was not modified. It stores rooms, answers, and fun facts.

### What happened to our unknowns?

- `LinkedChain<T>` stores different types through its generic type parameter.
- `Game` manages room progression. There is no skip-ahead action in the game.
- Random room order uses a shuffled array of selected rooms; progressive order sorts by difficulty.
- Score mode calculates correct answers divided by submitted answers and rounds to the nearest whole percent.
- Random selection uses an index from `0` to `size() - 1`; selected fun facts are removed so they do not repeat during one game.

### What doesn't work yet?

This is a class project prototype. Custom quizzes exist for the current play session and are not saved. The question and fact sets are fixed in the source. There is no separate difficulty-mode selector; players choose question categories, and progressive ordering uses the difficulty ratings. The standard quiz has 27 rooms, at the lower end of the planned 25–30 range.

### Stretch goals

| Stretch goal | Status | Implementation |
|---|---|---|
| Randomized room order | Complete | Fisher-Yates shuffle selects rooms without repeats. |
| Disable lives and show percent correct | Complete | Score mode tracks submitted and correct answers and displays a rounded percentage. |
| Progressive difficulty | Complete | Each room has a difficulty rating; progressive order sorts easiest to hardest. |
| Variable lengths | Complete | The player can choose a number of rooms or the full selected set. |
| Custom quizzes / `Quiz` class | Complete | Players can enter questions, accepted answers, and difficulty ratings for a session quiz. |
| Question categories (Troll, Trivia, Riddle, Math) | Complete | The standard quiz can be filtered by category. |
| Variable difficulty modes | Not implemented | Categories are question formats; there is no separate easy/medium/hard mode selector. |
| Multiple correct answers | MVP | Each room accepts multiple answers stored in `LinkedChain<String>`. |

### New tests

`MainTest`, `GameTest`, `RoomTest`, `FunFactTest`, and `QuizTest` cover game setup and flow, win/loss behavior, empty fact pools, answer matching, score tracking, question formats, category and length selection, progressive ordering, random selection, and the standard content counts. `LinkedChainTest` covers the provided collection implementation.

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
| `Quiz.java` / `QuizTest.java` | Ruhaan |
| `LinkedChain.java` | Course staff |
| Questions and fact cards | Everyone |

## Attribution

| File or item | Who | How |
|---|---|---|
| `Main.java` | Ruhaan | Wrote the original game setup and console flow; revised it for quiz choices and custom quizzes. |
| `MainTest.java` | Ruhaan | Wrote and revised game-flow tests. |
| `GameTest.java` | Ruhaan | Wrote tests for game state, fact selection, and score mode. |
| `Game.java` | Maple | Wrote the original class; revised it for score tracking. |
| `Room.java` | Maple | Wrote the original class; revised answer storage to use `LinkedChain<String>`. |
| `FunFact.java` | Katie | Wrote the class; added fact, hint, and troll display formats. |
| `FunFactTest.java` | Katie | Wrote tests for construction, accessors, mutation, and display formats. |
| `RoomTest.java` | Katie | Wrote tests for question, answers, matching, and display. |
| `Quiz.java` / `QuizTest.java` | Ruhaan | Added quiz configuration, selection, and tests. |
| `LinkedChain.java` | Course staff | Provided unchanged. |
| Questions and fact cards | Everyone | Team contribution; Claude was used to brainstorm riddles and fun facts. |

### GenAI note

Claude was used to brainstorm project ideas, draft some riddles and fun facts, and format comments and README text. Codex assisted with feature implementation, tests, and README updates. Team members should edit this note to describe their own use and review accurately.
