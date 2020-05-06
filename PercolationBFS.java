public class PercolationBFS extends PercolationDFSFast{
    /**
     * Initialize a grid so that all cells are blocked.
     *
     * @param n is the size of the simulated (square) grid
     */
    public PercolationBFS(int n) {
        super(n);
    }

    /**
     * attempt to rewrite dfs without recursion
     */
    @Override
    public void dfs(int row, int col) {
        // out of bounds?
        if (! inBounds(row,col)) return;

        // full or NOT open, don't process
        if (isFull(row, col) || !isOpen(row, col))
            return;

        myGrid[row][col] = FULL;
        dfs(row - 1, col);
        dfs(row, col - 1);
        dfs(row, col + 1);
        dfs(row + 1, col);
    }
}
