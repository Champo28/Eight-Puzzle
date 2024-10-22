import java.util.LinkedList;
import java.util.List;
public class Board implements Ilayout,Cloneable{
    private static final int dim = 3;
    private int[][] board;
    public Board(){
        board = new int[dim][dim];
    }
    private int[] blankSpace = new int[2];
    public Board(String str) throws IllegalStateException{
         if(str.length() != dim*dim) throw new IllegalStateException("Invalid arg in Board Constructor");
         board = new int[dim][dim];
         int k = 0;
         for(int i = 0; i < dim; i++){
             for(int j = 0; j < dim; j++){
                 int n = Character.getNumericValue(str.charAt(k++));
                 board[i][j] = n;
                 if(n == 0){
                     blankSpace[0] = i;
                     blankSpace[1] = j;
                 }
             }
         }
    }

    public Board(int[][] board){
        this.board = new int[dim][dim];
        for(int i = 0; i < dim; i++){
            for(int j = 0; j < dim; j++){
                if(board[i][j] == 0){
                    blankSpace[0] = i;
                    blankSpace[1] = j;
                }
                this.board[i][j] = board[i][j];
            }
        }
    }

    public String toString(){
        StringBuilder s = new StringBuilder();
        for(int i = 0; i < dim; i++){
            for(int j = 0; j < dim; j++) {
                if (board[i][j] == 0) s.append(" ");
                else s.append(board[i][j]);
            }
            s.append("\r\n");
        }
        return s.toString();
    }

    public int hashCode(){
        return toString().hashCode();
    }

    public boolean equals(Object o){
        if (o == null) return false;
        if (this.getClass() != o.getClass()) return false;
        Board b = (Board) o;
        for(int i = 0; i < dim; i++){
            for(int j = 0; j < dim; j++){
                if(b.board[i][j] != this.board[i][j]){
                    return false;
                }
            }
        }
        return true;
    }
    public void exchangeBlankSpace(int x, int y){
        int aux = this.board[x][y];
        this.board[x][y] = this.board[blankSpace[0]][blankSpace[1]];
        this.board[blankSpace[0]][blankSpace[1]] = aux;
        this.blankSpace = new int[]{x,y};
    }
    @Override
    public List<Ilayout> children() {
        List<Ilayout> children = new LinkedList<>();
        if(blankSpace[0]-1 >= 0){
           Board child1 = new Board(this.board);
           child1.exchangeBlankSpace(blankSpace[0]-1,blankSpace[1]);
           children.add(child1);
        }
        if(blankSpace[0]+1 < dim){
            Board child2 = new Board(this.board);
            child2.exchangeBlankSpace(blankSpace[0]+1,blankSpace[1]);
            children.add(child2);
        }
        if(blankSpace[1]-1 >= 0){
            Board child3 = new Board(this.board);
            child3.exchangeBlankSpace(blankSpace[0],blankSpace[1]-1);
            children.add(child3);
        }
        if(blankSpace[1]+1 < dim){
            Board child4 = new Board(this.board);
            child4.exchangeBlankSpace(blankSpace[0],blankSpace[1]+1);
            children.add(child4);
        }
        return children;
    }

    @Override
    public boolean isGoal(Ilayout l) {
        return l.equals(this);
    }

    @Override
    public double getG() {
        return 1;
    }
}
