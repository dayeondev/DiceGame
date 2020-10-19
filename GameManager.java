public class GameManager {
    public static void main(String[] args) {
        PlayerRead reader = new PlayerRead();
        Dice dice = new Dice();
        String name_p1 = reader.readUserName(1);
        String name_p2 = reader.readUserName(2);
        PlayerView view_p1 = new PlayerView("P1 - " + name_p1, dice);
        PlayerView view_p2 = new PlayerView("P2 - " + name_p2, dice);
        DiceController controller = new DiceController(reader, dice, view_p1, view_p2);
        controller.playGame();
    }
}
