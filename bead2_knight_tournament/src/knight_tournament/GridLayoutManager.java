package knight_tournament;

import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import java.util.List;
import javax.swing.*;


public class GridLayoutManager extends JFrame {
    private final int basicRow;
    private final int basicCol;
    //Components:
    private final JButton[][] squares;
    //colors:
    private final Color colorBlack = Color.BLACK; //Black squares
    private final Color colorWhite = Color.WHITE; //White squares
    private final Color colorGrey = Color.GRAY;   //Grey squares
    private int chosenKnight;
    private boolean isWhite;
    //first click on the Player's turn or the second?
    boolean firstClick = false;
    List<Knight> knights = new ArrayList<>();


    public GridLayoutManager(int basicRow, int basicCol) {

        super("Lovagi Torna Játék - 8. feladat");

        this.basicRow = basicRow;
        this.basicCol = basicCol;
        this.squares = new JButton[basicRow][basicCol];


        Container contents = getContentPane();
        contents.setLayout(new GridLayout(basicRow, basicCol));

        //Create menu:

        JMenuBar menuBar = new JMenuBar();
        setJMenuBar(menuBar);
        JMenu gameMenu = new JMenu("Játék");
        menuBar.add(gameMenu);
        JMenuItem newGameMenuItem = new JMenuItem("Új játék");
        gameMenu.add(newGameMenuItem);
        JMenuItem exitMenuItem = new JMenuItem("Kilépés");
        gameMenu.add(exitMenuItem);
        JMenu ruleMenu = new JMenu("Játékszabály");
        menuBar.add(ruleMenu);
        JMenuItem ruleMenuItem = new JMenuItem("Szabályok");
        ruleMenu.add(ruleMenuItem);

        //Menu eventHandler
        exitMenuItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });

        ruleMenuItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JOptionPane.showMessageDialog(null, "Íme a játékszabály:\n" +
                        "\n" +
                        "MINDIG a FEHÉR kezd!\n " +
                        "A játékosok felváltva lépnek, a figurák L alakban tudnak mozogni a játéktáblán.\n " +
                        "Kezdetben a teljes játéktábla szürke színű, de minden egyes lépés után az adott mező felveszi a rá lépő\n" +
                        "figura színét (bármilyen színű volt előtte). A játék célja, hogy valamely játékosnak\n" +
                        "függőlegesen, vízszintesen, vagy átlósan egymás mellett 4 ugyanolyan színű mezője legyen.\n" +
                        "A játéknak akkor van vége, ha minden mező kapott valamilyen színt."
                );
            }
        });

        newGameMenuItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                StartGameDatas game = new StartGameDatas();
                int dialogResult = game.startGame();
                GridLayoutManager gui = new GridLayoutManager(dialogResult, dialogResult);
                gui.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            }
        });


        //create board event handler:
        ButtonHandler buttonHandler = new ButtonHandler();

        //Create and add board components:
        for (int i = 0; i < basicRow; i++) {
            for (int j = 0; j < basicCol; j++) {
                squares[i][j] = new JButton();
                squares[i][j].setBackground(colorGrey);
                contents.add(squares[i][j]);
                squares[i][j].addActionListener(buttonHandler);
            }
        }

        //Knights create:

        Knight knight = new Knight(0, 0, true, 0, new ImageIcon("white.jpg"));
        knights.add(knight);
        Knight knight2 = new Knight(0, basicCol - 1, false, 1, new ImageIcon("black.jpg"));
        knights.add(knight2);
        Knight knight3 = new Knight(basicRow - 1, 0, false, 2, new ImageIcon("black.jpg"));
        knights.add(knight3);
        Knight knight4 = new Knight(basicRow - 1, basicCol - 1, true, 3, new ImageIcon("white.jpg"));
        knights.add(knight4);


        //Create Knight picture on board
        for (Knight knightok : knights) {
            squares[knightok.getRow()][knightok.getCol()].setIcon(knightok.getKnight());
        }


        //Size and display window:
        setSize(500, 500);
        setResizable(false);
        setLocationRelativeTo(null); //Centers window
        setVisible(true);
    }

    public boolean hasFourEqualColors() {
        // Bejárjuk az összes sort és oszlopot a mátrixban
        for (int i = 0; i < squares.length; i++) {
            for (int j = 0; j < squares[0].length; j++) {
                Color currColor = squares[i][j].getBackground();
                if (currColor != colorGrey) {
                    // Ellenőrizzük az egyforma színeket függőlegesen
                    if (i + 3 < squares.length
                            && currColor.equals(squares[i + 1][j].getBackground())
                            && currColor.equals(squares[i + 2][j].getBackground())
                            && currColor.equals(squares[i + 3][j].getBackground())) {
                        return true;
                    }

                    // Ellenőrizzük az egyforma színeket vízszintesen
                    if (j + 3 < squares[0].length
                            && currColor.equals(squares[i][j + 1].getBackground())
                            && currColor.equals(squares[i][j + 2].getBackground())
                            && currColor.equals(squares[i][j + 3].getBackground())) {
                        return true;
                    }

                    // Ellenőrizzük az egyforma színeket átlósan (bal felső saroktól jobb alsó sarokig)
                    if (i + 3 < squares.length && j + 3 < squares[0].length
                            && currColor.equals(squares[i + 1][j + 1].getBackground())
                            && currColor.equals(squares[i + 2][j + 2].getBackground())
                            && currColor.equals(squares[i + 3][j + 3].getBackground())) {
                        return true;
                    }

                    // Ellenőrizzük az egyforma színeket átlósan (jobb felső saroktól bal alsó sarokig)
                    if (i + 3 < squares.length && j - 3 >= 0
                            && currColor.equals(squares[i + 1][j - 1].getBackground())
                            && currColor.equals(squares[i + 2][j - 2].getBackground())
                            && currColor.equals(squares[i + 3][j - 3].getBackground())) {
                        return true;
                    }
                }
            }
        }

        return false; // Ha nincs 4 egyforma szín


    }


    private boolean fullBoard() {
        for (int i = 0; i < squares.length; i++) {
            for (int j = 0; j < squares[0].length; j++) {
                if (squares[i][j].getBackground() == Color.GRAY) {
                    System.out.println("Nincs vége a játéknak");
                    return false;
                }
            }
        }
        System.out.println("Vége a játéknak! Döntetlen!");
        return true;
    }

    private boolean isValidMove(int i, int j) {
        for (Knight knight : knights) {
            if (knight.getNumber() == chosenKnight) {
                int rowDelta = Math.abs(i - knight.getRow());
                int colDelta = Math.abs(j - knight.getCol());

                if ((rowDelta == 1) && (colDelta == 2) && (squares[i][j].getIcon() == null)) {
                    return true;
                }
                if ((colDelta == 1) && (rowDelta == 2) && (squares[i][j].getIcon() == null)) {
                    return true;
                }
                return false;
            }
        }
        return false;
    }

    private void processClick(int i, int j) {
        if (!isValidMove(i, j)) {
            firstClick = true;
            return;
        } else {
            for (Knight knight : knights) {
                if (knight.getNumber() == chosenKnight) {
                    squares[knight.getRow()][knight.getCol()].setIcon((null));
                    squares[i][j].setIcon(knight.getKnight());
                    if (knight.isColorWhite()) {
                        squares[i][j].setBackground(colorWhite);
                        squares[knight.getRow()][knight.getCol()].setBackground(colorWhite);
                    } else {
                        squares[knight.getRow()][knight.getCol()].setBackground(colorBlack);
                        squares[i][j].setBackground(colorBlack);
                    }
                    knight.setRow(i);
                    knight.setCol(j);
                }
            }
            if (hasFourEqualColors()) {
                String color;
                String currentPlayer = "white";
                if (currentPlayer == "white") {
                    color = "Fekete";
                } else {
                    color = "Fehér";
                }
                JOptionPane.showMessageDialog(null, "Játék vége! Nyert a " + color + " színű játékos!");
                StartGameDatas game = new StartGameDatas();
                int dialogResult = game.startGame();

                GridLayoutManager gui = new GridLayoutManager(dialogResult, dialogResult);
                gui.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            } else if (fullBoard() == true) {
                JOptionPane.showMessageDialog(null, "Döntetlen!");
                StartGameDatas game = new StartGameDatas();
                int dialogResult = game.startGame();

                GridLayoutManager gui = new GridLayoutManager(dialogResult, dialogResult);
                gui.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            }
        }
    }

    private class ButtonHandler implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            Object source = e.getSource();

            for (int i = 0; i < basicRow; i++) {
                for (int j = 0; j < basicCol; j++) {
                    for (Knight knights : knights) {
                        if (source == squares[knights.getRow()][knights.getCol()] && !firstClick && isWhite != knights.isColorWhite()) {
                            isWhite = knights.isColorWhite();
                            chosenKnight = knights.getNumber();
                            firstClick = true;
                            return;
                        } else if (source == squares[i][j] && firstClick) {
                            firstClick = false;
                            processClick(i, j);
                            return;
                        }
                    }
                }
            }
        }
    }
}
