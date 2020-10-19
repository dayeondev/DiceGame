public class DiceController{
    private PlayerRead reader;
    private Dice dice;
    private PlayerView view_p1;
    private PlayerView view_p2;

    private int round1_p1 = 0;
    private int round1_p2 = 0;
    private int round2_p1 = 0;
    private int round2_p2 = 0;

    private char command;

    public DiceController(PlayerRead _reader, Dice _dice,
                          PlayerView _view_p1, PlayerView _view_p2){
        reader = _reader;
        dice = _dice;
        view_p1 = _view_p1;
        view_p2 = _view_p2;

    }

    public void reset(){
        round1_p1 = 0;
        round1_p2 = 0;
        round2_p1 = 0;
        round2_p2 = 0;

        view_p1.showDices(round1_p1, round2_p1);
        view_p2.showDices(round1_p2, round2_p2);
        view_p1.showMessage("초기화되었습니다.");
        view_p2.showMessage("초기화되었습니다.");
    }




    public void victory(int _round1_p1, int _round1_p2, int _round2_p1, int _round2_p2){

        String message = "메시지가 지정되지 않았습니다.";

        /*
        * 큰 범주로 나누어보자.
        * 1. 두 주사위가 같으면 이김
        *   1) 큰 수가 이김
        * 2. 큰 수가 이김
        * 3. 차이가 작으면 이김
        *
        * */

        if(_round1_p1 == _round2_p1){       // P1의 두 주사위가 같음
            if(_round2_p2 == _round1_p2){   // P2의 두 주사위가 같음
                if((_round1_p1 + _round2_p1) == (_round1_p2 + _round2_p2)){ // P1과 P2의 합이 같음
                    // 비김
                    message = "비김. P1과 P2의 두 주사위가 같고, P1과 P2의 합이 같습니다.";
                }
                else if((_round1_p1 + _round2_p1) > (_round1_p2 + _round2_p2)){ // P1의 합이 P2의 합보다 큼
                    // P1이 이김
                    message = "P1 승. P1과 P2의 두 주사위가 같지만, P1의 합이 P2의 합보다 큽니다.";
                }
                else{   // P2의 합이 P1의 합보다 큼
                    // P2가 이김
                    message = "P2 승. P1과 P2의 두 주사위가 같지만, P2의 합이 P1의 합보다 큽니다.";
                }
            }
            else {   // P1의 주사위"만" 같음
                // P1이 이김
                message = "P1 승. P1의 두 주사위가 같습니다.";
            }
        }
        else if(_round1_p2 == _round2_p2){  // P2의 주사위"만" 같음
            // P2가 이김
            message = "P2 승. P2의 두 주사위가 같습니다.";
        }   // 1에 대한 처리 끝

        else if((_round1_p1 + _round2_p1) != (_round1_p2 + _round2_p2)){ // P1과 P2의 합이 다름
            if((_round1_p1 + _round2_p1) > (_round1_p2 + _round2_p2)){ // P1의 합이 P2의 합보다 큼
                // P1이 이김
                message = "P1 승. P1의 합이 P2의 합보다 큽니다.";
            }
            else{   // P2의 합이 P1의 합보다 큼
                // P2가 이김
                message = "P2 승. P2의 합이 P1의 합보다 큽니다.";
            }
        } // 2에 대한 처리 끝
        else{ //
            int tmp1 = _round1_p1 - _round2_p1;
            int tmp2 = _round1_p2 - _round2_p2;
            
            // 절대값 처리
            if(tmp1 < 0){
                tmp1 = -tmp1;
            }
            if(tmp2 < 0){
                tmp2 = -tmp2;
            }
            
            if(tmp1 < tmp2){
                // 1이 이김
                message = "P1 승. P1과 P2의 합이 같지만 P1의 차가 작습니다.";
            }
            else if(tmp1 > tmp2){
                // 2가 이김
                message = "P2 승. P1과 P2의 합이 같지만 P2의 차가 작습니다.";
            }
            else if(tmp1 == tmp2){
                // 비김
                message = "비김. P1과 P2의 합이 같고 두 주사위의 차가 같습니다.";
            }
            else{
                message = "지정되지 않은 결과입니다. 관리자에게 연락해주세요.";
            }
        }
        view_p1.showMessage(message + " 다시 시작하려면 R을 입력해주세요.");
        view_p2.showMessage(message + " 다시 시작하려면 R을 입력해주세요.");


    }

    public void playGame(){
        int number = 0;

//        System.out.println("커맨드 입력됨. command: " + command);
        System.out.println("11: " + round1_p1 + ", 21: " + round2_p1 + ", 12: " + round1_p2 + ", 22: " + round2_p2);
        boolean end = (round1_p1!=0) && (round1_p2!=0) && (round2_p1!=0) && (round2_p2!=0);
        if(end){
//            System.out.println("here");
            victory(round1_p1, round1_p2, round2_p1, round2_p2);
            round1_p1 = 0;
            round2_p1 = 0;
            round1_p2 = 0;
            round2_p2 = 0;
        }
        else {
            command = Character.toUpperCase(reader.readCommand("player1던지기 P1, player2던지기 P2, 다시하기 R"));
            if (command == 'P') {
                number = reader.readNumber();
                if (number == 1) {
                    if (round1_p1 == 0) {
                        round1_p1 = dice.throwDice();
                        view_p1.showMessage("주사위1");
                        view_p1.showDices(round1_p1, 0);
                    } else if (round2_p1 == 0) {
                        round2_p1 = dice.throwDice();
                        view_p1.showMessage("주사위2");
                        view_p1.showDices(round1_p1, round2_p1);
                    } else {
                        view_p1.showMessage("이미 모든 주사위를 던졌습니다.");
                    }
                } else if (number == 2) {
                    if (round1_p2 == 0) {
                        round1_p2 = dice.throwDice();
                        view_p2.showMessage("주사위1");
                        view_p2.showDices(round1_p2, 0);
                    } else if (round2_p2 == 0) {
                        round2_p2 = dice.throwDice();
                        view_p2.showMessage("주사위2");
                        view_p2.showDices(round1_p2, round2_p2);
                    } else {
                        view_p2.showMessage("이미 모든 주사위를 던졌습니다.");
                    }
                } else {
                    view_p1.showAlert("입력값에 문제가 있습니다.");
                }
            } else if (command == 'R') {
                reset();
            }

        }
        this.playGame();

    }



}
