package RizzleEscapeRoom;

import student.TestCase;

/**
 * Tests quiz selection and setup.
 *
 * @author Ruhaan
 * @version Oct 22, 2026
 */
public class QuizTest extends TestCase
{
    /**
     * Checks the standard quiz content meets the prototype targets.
     */
    public void testStandardQuizContent()
    {
        assertEquals(27, Main.buildRooms().size());
        assertEquals(8, Main.buildFunFacts().size());
    }

    /**
     * Checks that category and length settings select matching rooms.
     */
    public void testCategoryAndLengthSelection()
    {
        Quiz quiz = Main.buildQuiz();
        LinkedChain<Room> selected = quiz.selectRooms(Room.MATH, 2,
            Quiz.SEQUENTIAL);
        assertEquals(2, selected.size());
        assertEquals(Room.MATH, selected.get(0).getQuestionType());
        assertEquals(Room.MATH, selected.get(1).getQuestionType());
    }

    /**
     * Checks progressive selection starts with the easiest questions.
     */
    public void testProgressiveSelection()
    {
        LinkedChain<Room> selected = Main.buildQuiz().selectRooms(Quiz.ALL,
            27, Quiz.PROGRESSIVE);
        for (int i = 1; i < selected.size(); i += 1)
        {
            assertTrue(selected.get(i - 1).getDifficulty()
                <= selected.get(i).getDifficulty());
        }
    }

    /**
     * Checks random selection honors the requested length without duplicates.
     */
    public void testRandomSelectionHasNoDuplicates()
    {
        LinkedChain<Room> selected = Main.buildQuiz().selectRooms(Quiz.ALL,
            27, Quiz.RANDOM);
        assertEquals(27, selected.size());
        for (int i = 0; i < selected.size(); i += 1)
        {
            for (int j = i + 1; j < selected.size(); j += 1)
            {
                assertFalse(selected.get(i) == selected.get(j));
            }
        }
    }

    /**
     * Bad-input case: creating a quiz with null data is rejected.
     */
    public void testConstructorRejectsNull()
    {
        Exception thrown = null;
        try
        {
            new Quiz("Quiz", null, new LinkedChain<FunFact>());
        }
        catch (IllegalArgumentException e)
        {
            thrown = e;
        }
        assertNotNull(thrown);
    }
}
