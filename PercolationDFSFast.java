public class PercolationDFSFast extends PercolationDFS {
    /**
     * Initialize a grid so that all cells are blocked.
     *
     * @param n is the size of the simulated (square) grid
     */

    /**
     * this is a constructor for PercolationDFSFast
     * @param n
     */
    public PercolationDFSFast(int n) {
        super(n);
    }

    /**
     * this method updates cells if they are open and adjacent to a full cell
     * makes calls to dfs
     */
    @Override
    public void updateOnOpen(int row, int col) {
        if (row == 0) {
            dfs(row, col);
            return;
        }
        if (row == myGrid.length-1 && col == 0) {
            if (isFull(row - 1, col) || isFull(row, col + 1)) {
                dfs(row, col);

            }
        }
        if (row == myGrid.length-1 && col == myGrid[0].length-1) {
            if (isFull(row - 1, col) || isFull(row, col - 1)) {
                dfs(row, col);
            }
        }
        if (col == 0 && row != myGrid.length-1) {
            if (isFull(row-1,col) || isFull(row,col+1) || isFull(row+1,col)) {
                dfs(row, col);
            }
        }
        if (col == myGrid[0].length-1 && row != myGrid.length-1) {
            if (isFull(row-1,col) || isFull(row,col-1) || isFull(row+1,col)) {
                dfs(row, col);
            }
        }
        if (row == myGrid.length-1 && col != 0 && col != myGrid[0].length-1) {
            if (isFull(row-1,col) || isFull(row,col-1) || isFull(row,col+1)) {
                dfs(row, col);
            }
        }
        if (inBounds(row+1,col+1) && inBounds(row-1,col-1)) {
            if (isFull(row-1,col) || isFull(row,col-1) || isFull(row,col+1) || isFull(row+1,col)) {
                dfs(row, col);
            }
        }

    }
}

// Daniel Aguilar