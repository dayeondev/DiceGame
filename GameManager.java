public class GameManager {
    public static void main(String[] args) {
        PlayerRead reader = new PlayerRead();
        Dice dice = new Dice();
        String name_p1 = reader.putUserName(1);
        String name_p2 = reader.putUserName(2);
        PlayerView view_p1 = new PlayerView(name_p1, dice);
        PlayerView view_p2 = new PlayerView(name_p2, dice);
        DiceController controller = new DiceController(reader, dice, view_p1, view_p2);
        controller.playGame();
    }
}
