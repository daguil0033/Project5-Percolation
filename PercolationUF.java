import java.util.LinkedList;
import java.util.Queue;

public class PercolationUF implements IPercolate {

    private IUnionFind myFinder;
    private boolean[][] myGrid;
    private final int VTOP;
    private final int VBOTTOM;
    private int myOpenCount;

    /**
     * this is a constructor for PercolationUF
     */
    public PercolationUF(IUnionFind finder, int size) {
        myGrid = new boolean[size][size];
        myFinder = finder;
        myFinder.initialize(size*size+2);
        VTOP = size*size;
        VBOTTOM = size*size+1;
        myOpenCount = 0;

    }
    /**
     * opens a cell if it is clicked one and if it is in the top row, it updates the cells to be full
     * because it is connected to VTOP, putting them in the same union
     * This method also checks if an opened cell is connected to VTOP and if it is will fill it
     */
    @Override
    public void open(int row, int col) {

        if(inBounds(row, col) && myGrid[row][col] == true)return;
        if (! inBounds(row,col)) {
            throw new IndexOutOfBoundsException("not in bounds");
        }
        myGrid[row][col] = true;
        myOpenCount += 1;
        if(row == 0){
            myFinder.union(findI(row, col),VTOP);
        }
        if(row == myGrid.length -1 ){
            myFinder.union(findI(row, col),VBOTTOM);
        }
        int[] rowD = {-1,1,0,0};
        int[] colD = {0,0,-1,1};
        Queue<Integer> qp = new LinkedList<>();
        qp.add(findI(row, col));
        while (qp.size() != 0) {
            int val = qp.remove();
            int tRow = val / myGrid.length;
            int tCol = val % myGrid.length;
            for (int k = 0; k < rowD.length; k++) {
                int row2 = tRow + rowD[k];
                int col2 = tCol + colD[k];
                if (inBounds(row2, col2) && isOpen(row2,col2) && !myFinder.connected(findI(row, col), findI(row2, col2))) {
                    qp.add(findI(row2, col2));
                    if(row2 == 0){
                        myFinder.union(findI(row, col),VTOP);
                        continue;
                    }
                    if(row2 == myGrid.length -1 ){
                        myFinder.union(findI(row, col),VBOTTOM);
                        continue;
                    }
                    myFinder.union(findI(row, col),findI(row2, col2));
                }
            }
        }
    }

    /**
     * helper method that returns the index of a cell given row and col points
     */

    private int findI(int row, int col) {
        return row * myGrid.length + col;
    }
    /**
     * returns whether or not a cell is open
     */
    @Override
    public boolean isOpen(int row, int col) {
        if (!inBounds(row, col)) {
            throw new IndexOutOfBoundsException("out of bounds");
        }
        return myGrid[row][col];
    }
    /**
     * this method returns whether or not a cell is full by checking if its connected to the top
     */
    @Override
    public boolean isFull(int row, int col) {
        if (!inBounds(row, col)) {
            throw new IndexOutOfBoundsException("out of bounds");
        }
        int x = findI(row, col);

        return myFinder.connected(x, VTOP);
    }
    /**
     * returns whether or not a system percolates by checking if VTOP and VBOTTOM are in the same union
     */
    @Override
    public boolean percolates() {
        return myFinder.connected(VTOP, VBOTTOM);
    }

    /**
     * returns how many cells are open
     */

    @Override
    public int numberOfOpenSites() {
        return myOpenCount;
    }

    /**
     * helper method copied over from PercolationDFS, it returns false if cell isnt in grid, true if its in
     */
    private boolean inBounds(int row, int col) {
        if (row < 0 || row >= myGrid.length) return false;
        if (col < 0 || col >= myGrid[0].length) return false;
        return true;
    }
}