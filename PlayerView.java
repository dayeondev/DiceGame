import javax.swing.*;
import java.awt.*;

public class PlayerView extends JPanel {

    private int WIDTH = 600;
    private int HEIGHT = 300;
    private Dice dice;
    private String message = "주사위나라에 오신 것을 환영합니다. 창이 겹쳐 있으니 주의해주세요.";
    private int dice1_num = 0;
    private int dice2_num = 0;

    private int margin_left = 50;
    private int margin_top = 50;

    public PlayerView(String user_name, Dice _dice){
        dice = _dice;
        JFrame f = new JFrame();
        f.getContentPane().add(this);
        f.setTitle(user_name);
        f.setSize(WIDTH, HEIGHT);
        f.setVisible(true);
        f.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
    }

    private void drawDice(Graphics g, int x, int y, int dice_num){
        // 주사위 그리는 기능
        g.drawString(Integer.toString(dice1_num), margin_left, margin_top + 20);
        g.drawString(Integer.toString(dice2_num), margin_left + 20, margin_top + 20);
    }

    private void drawRules(Graphics g, int x, int y){
        g.drawString("규칙", x, y);
        y = y + 20;
        x = x - 10;
        g.drawString("- 주사위 두개가 같은 수가 나오면 이긴다. 둘 다 같은 수이면 큰 수가 이긴다.", x, y);
        y = y + 15;
        g.drawString("- 다른 수 끼리는 두 주사위의 합이 크면 이긴다.", x, y);
        y = y + 15;
        g.drawString("- 합이 동률인 경우에는 두 수의 차이가 작으면 이긴다.", x, y);
        y = y + 15;
        g.drawString("- 두수의 차이도 동률이면 비긴다.", x, y);
    }

    public void paintComponent(Graphics g) {
        System.out.println("paintComponents Called");
        g.setColor(Color.white);
        g.fillRect(0, 0, WIDTH, HEIGHT);

        g.setColor(Color.black);
        g.drawString(message, margin_left, margin_top);
        drawDice(g, margin_left, margin_top + 20, dice1_num);
        drawDice(g, margin_left + 20, margin_top + 20, dice2_num);
        drawRules(g, margin_left, margin_top + 50);

    }

    public void showDices(int _dice1_num, int _dice2_num){
        dice1_num = _dice1_num;
        dice2_num = _dice2_num;
        this.repaint();
    }

    public void showMessage(String _message){
        System.out.println("showMessage Called");
        message = _message;
        this.repaint();
    }


    public void showAlert(String message){
        JOptionPane.showMessageDialog(null, message);
    }



}
