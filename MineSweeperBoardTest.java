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
    public MineSweeperBoard b1;
    public MineSweeperBoard b2;


    public void setUp()
    {
        b1 = new MineSweeperBoard(2, 2, 4);
        b2 = new MineSweeperBoard(2, 2, 0);
    }


    /**
     * Tests the width() method
     */
    public void testWidth()
    {
        assertEquals(b1.width(), 2);
    }


    /**
     * Tests the height() method
     */
    public void testHeight()
    {
        assertEquals(b1.height(), 2);
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



}
