package knight_tournament;
import javax.swing.*;

public class Game {
    public static void main(String[] args)
    {

        StartGameDatas game = new StartGameDatas();
        int dialogResult = game.startGame();
        GridLayoutManager gui = new GridLayoutManager(dialogResult, dialogResult);
        gui.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
}
