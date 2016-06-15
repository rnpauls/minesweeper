package cs2114.minesweeper;

import java.util.Random;

// -------------------------------------------------------------------------
/**
 * A class representing the board of a game of Minesweeper.
 *
 * @author Rich Pauls
 * @version 06 13 2016
 */

public class MineSweeperBoard
    extends MineSweeperBoardBase
{
    private Random              rand           = new Random();
    private int                 width;
    private int                 height;
    private boolean             mineFound      = false;
    private int                 numberOfMines;
    private int                 flaggedMines   = 0;
    private int                 incorrectFlags = 0;
    private int                 coveredCells;                 // excluding the
                                                              // cells
                                                              // with bombs

    private MineSweeperCell[][] board;                        // the board is
                                                              // made up
                                                              // of cells
    // ----------------------------------------------------------


    /**
     * Create a new MineSweeperBoard object.
     *
     * @param width
     *            the width of the board
     * @param height
     *            the height of the board
     * @param numberOfMines
     *            the number of mines
     */
    public MineSweeperBoard(int width, int height, int numberOfMines)
    {
        this.width = width;
        this.height = height;
        this.numberOfMines = numberOfMines;
        this.coveredCells = width * height - numberOfMines;
        board = new MineSweeperCell[width][height];

        for (int i = 0; i < width; i++)
        {
            for (int j = 0; j < height; j++)
            {
                board[i][j] = MineSweeperCell.COVERED_CELL;
            }
        }

        for (int i = 0; i < numberOfMines; i++)
        {
            int bombw = rand.nextInt(width);
            int bombh = rand.nextInt(height);

            while (board[bombw][bombh] == MineSweeperCell.MINE)
            {
                bombw = rand.nextInt(width);
                bombh = rand.nextInt(height);
            }

            board[bombw][bombh] = MineSweeperCell.MINE;
        }
    }


    // ----------------------------------------------------------
    @Override
    public int width()
    {
        return this.width;
    }


    // ----------------------------------------------------------
    @Override
    public int height()
    {
        return this.height;
    }


    // ----------------------------------------------------------
    @Override
    public MineSweeperCell getCell(int x, int y)
    {
        if ((x < 0 || x >= width) || (y < 0 || y >= height))
        {
            return MineSweeperCell.INVALID_CELL;
        }
        return board[x][y];
    }


    // ----------------------------------------------------------
    @Override
    public void uncoverCell(int x, int y)
    {
        if (board[x][y] == MineSweeperCell.COVERED_CELL)
        {
            board[x][y] =
                MineSweeperCell.adjacentTo(numberOfAdjacentMines(x, y));
        }

        if (board[x][y] == MineSweeperCell.MINE)
        {
            board[x][y] = MineSweeperCell.UNCOVERED_MINE;
            mineFound = true;
        }

    }


    // ----------------------------------------------------------
    @Override
    public void flagCell(int x, int y)
    {
        if (board[x][y] == MineSweeperCell.COVERED_CELL)
        {
            board[x][y] = MineSweeperCell.FLAG;
            incorrectFlags++;

        }

        if (board[x][y] == MineSweeperCell.FLAG)
        {
            board[x][y] = MineSweeperCell.COVERED_CELL;
            incorrectFlags--;
        }

        if (board[x][y] == MineSweeperCell.MINE)
        {
            board[x][y] = MineSweeperCell.FLAGGED_MINE;
            flaggedMines++;
        }

        if (board[x][y] == MineSweeperCell.FLAGGED_MINE)
        {
            board[x][y] = MineSweeperCell.MINE;
            flaggedMines--;
        }

    }


    // ----------------------------------------------------------
    @Override
    public boolean isGameLost()
    {
        return mineFound;
    }


    // ----------------------------------------------------------
    @Override
    public boolean isGameWon()
    {
        if (flaggedMines == numberOfMines && incorrectFlags == 0
            && coveredCells == 0)
        {
            return true;
        }
        return false;
    }


    // ----------------------------------------------------------
    @Override
    public int numberOfAdjacentMines(int x, int y)
    {
        int n = 0;
        for (int i = x - 1; i <= x + 1; i++)
        {
            for (int j = y - 1; j <= j + 1; j++)
            {
                if (getCell(i, j) == MineSweeperCell.MINE
                    || getCell(i, j) == MineSweeperCell.UNCOVERED_MINE)
                {
                    n++;
                }
            }
        }
        return n;
    }


    // ----------------------------------------------------------
    @Override
    public void revealBoard()
    {
        for (int i = 0; i < board.length; i++)
        {
            for (int j = 0; j < board[0].length; j++)
            {
                uncoverCell(i, j);
            }
        }

    }


    // ----------------------------------------------------------
    @Override
    protected void setCell(int x, int y, MineSweeperCell value)
    {
        this.board[x][y] = value;
    }

}
