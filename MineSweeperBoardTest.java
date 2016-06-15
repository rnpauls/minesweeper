package cs2114.minesweeper;

import student.TestCase;

// -------------------------------------------------------------------------
/**
 * Tests the MineSweeperBoard class
 *
 * @author Rich Pauls
 * @version 06 14 2016
 */

public class MineSweeperBoardTest
    extends TestCase
{
    public MineSweeperBoard b1; // filled with mines
    public MineSweeperBoard b2; // no mines


    public MineSweeperBoardTest()
    {
        /**
         * Nothing needs to be here, it's a test case
         */
    }


    public void setUp()
    {
        b1 = new MineSweeperBoard(3, 3, 9); // 3x3 filled with all mines
        b2 = new MineSweeperBoard(3, 3, 0); // 3x3 with no mines
    }


    /**
     * Tests if two boards are equal Thanks professor!
     *
     * @param theBoard
     *            A MineSweeperBoard variable
     * @param expected
     *            A String to compare the board against
     */
    public void assertBoard(MineSweeperBoard theBoard, String... expected)
    {
        MineSweeperBoard expectedBoard =
            new MineSweeperBoard(expected[0].length(), expected.length, 0);
        expectedBoard.loadBoardState(expected);
        assertEquals(expectedBoard, theBoard); // uses equals() from
                                               // MineSweeperBoardB
    }


    /**
     * Tests the width() method
     */
    public void testWidth()
    {
        assertEquals(b1.width(), 3);
    }


    /**
     * Tests the height() method
     */
    public void testHeight()
    {
        assertEquals(b1.height(), 3);
    }


    /**
     * Tests the getCell() method
     */
    public void testGetCell()
    {
        assertEquals(b2.getCell(1, 1), MineSweeperCell.COVERED_CELL);
        assertEquals(b2.getCell(3, 1), MineSweeperCell.INVALID_CELL);
        assertEquals(b2.getCell(-1, 1), MineSweeperCell.INVALID_CELL);
        assertEquals(b2.getCell(1, -1), MineSweeperCell.INVALID_CELL);
        assertEquals(b2.getCell(1, 3), MineSweeperCell.INVALID_CELL);
    }


    /**
     * Tests the uncoverCell() method
     */
    public void testUncoverCell()
    {
        b2.uncoverCell(0, 0);
        assertEquals(b2.getCell(0, 0), MineSweeperCell.ADJACENT_TO_0);

        b1.uncoverCell(0, 0);
        assertEquals(b1.getCell(0, 0), MineSweeperCell.UNCOVERED_MINE);

    }


    public void testFlagCell()
    {
        b2.flagCell(0, 0);
        assertEquals(b2.getCell(0, 0), MineSweeperCell.FLAG);

        b2.flagCell(0, 0);
        assertEquals(b2.getCell(0, 0), MineSweeperCell.COVERED_CELL);

        b1.flagCell(0, 0);
        assertEquals(b1.getCell(0, 0), MineSweeperCell.FLAGGED_MINE);

        b1.flagCell(0, 0);
        assertEquals(b1.getCell(0, 0), MineSweeperCell.MINE);
    }


    public void testIsGameLost()
    {
        assertEquals(b1.isGameLost(), false);
        b1.uncoverCell(0, 0);
        assertEquals(b1.isGameLost(), true);
    }


    public void testIsGameWon()
    {
        assertEquals(b2.isGameWon(), false); // game with all covered cells

        b2.flagCell(0, 0);
        assertEquals(b2.isGameWon(), false); // one cell mis-flagged
        b2.flagCell(0, 0);

        b2.revealBoard();
        assertEquals(b2.isGameWon(), true); // winning game with no bombs

        assertEquals(b1.isGameWon(), false); // game with uncovered mines
    }


    public void testNumberOfAdjacentMines()
    {
        b1.revealBoard();
        b2.revealBoard();
        assertEquals(b1.numberOfAdjacentMines(1, 1), 9); // Tests for 9 because
                                                         // the way the method
                                                         // works, it would
                                                         // increment the
                                                         // count if it had a
                                                         // mine in the cell as
                                                         // well. However, this
                                                         // will never happen in
                                                         // a real game
        assertEquals(b2.numberOfAdjacentMines(1, 1), 0);
    }


    public void testRevealBoard()
    {
        b2.revealBoard();
        assertBoard(b2, "   ", "OOO", "OOO", "OOO");
    }


    public void testSetCell()
    {
        b2.setCell(0, 0, MineSweeperCell.MINE);
        assertBoard(b2, "   ", "+OO", "OOO", "OOO");
    }

}
