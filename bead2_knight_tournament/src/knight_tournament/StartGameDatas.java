package knight_tournament;

import javax.swing.*;

public class StartGameDatas {
    private String[] options = {"4x4", "6x6", "8x8"};
    private int defaultOption = 2;

    public int startGame(){
        int dialogResult = JOptionPane.showOptionDialog(null, "Milyen nagy legyen a játéktábla?", "Játéktér beállítása", JOptionPane.DEFAULT_OPTION, JOptionPane.QUESTION_MESSAGE, null, options, options[defaultOption]);

        if(dialogResult == 0){
        dialogResult = 4;
        System.out.println(dialogResult);
        }else if(dialogResult == 1){
        dialogResult = 6;
        System.out.println(dialogResult);
        }else {
            dialogResult = 8;
            System.out.println(dialogResult);
        }
        return dialogResult;
    }
}
