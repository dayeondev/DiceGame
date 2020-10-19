import javax.swing.*;

public class PlayerRead {

    private String input_line;
    private int number = 0;
    private String tmp;

    public String readUserName(int n){
        tmp = "player" + n + "의 이름을 입력해주세요.";
        return JOptionPane.showInputDialog(tmp);
    }
    public char readCommand(String message){
        input_line = JOptionPane.showInputDialog(message);
        return input_line.charAt(0);
    }

    public int readNumber(){
        tmp = input_line.substring(1, input_line.length());
        tmp = tmp.trim();
        if(tmp.length() > 0){
            number = Integer.parseInt(tmp);
        }
        else{
            JOptionPane.showMessageDialog(null, "입력 값을 다시 확인해주세요.");
        }
        return number;
    }


}
