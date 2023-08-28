package knight_tournament;

import javax.swing.*;

public class Knight {
    private int row;
    private int col;

    private boolean colorWhite;
    private int number;

    private ImageIcon knight;

    public Knight(int row, int col, boolean colorWhite, int number, ImageIcon knight) {
        this.row = row;
        this.col = col;
        this.colorWhite = colorWhite;
        this.number = number;
        this.knight = knight;
    }

    public int getRow() {
        return row;
    }

    public int getCol() {
        return col;
    }

    public boolean isColorWhite() {
        return colorWhite;
    }

    public void setRow(int row) {
        this.row = row;
    }

    public void setCol(int col) {
        this.col = col;
    }



    public int getNumber() {
        return number;
    }


    public ImageIcon getKnight() {
        return knight;
    }
}
