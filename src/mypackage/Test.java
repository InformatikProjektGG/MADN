package mypackage;


public class Test {

    public static void main(String[] args) {
        // TODO Auto-generated method stub
        System.out.println("hello world");
        Game game = new Game(4);
        Actions actions = game.checkActions(6, 0);
        game.moveFigur(6, 0, 0);
        game.moveFigur(5, 0, 0);
        game.moveFigur(5, 0, 0);
        game.moveFigur(5, 0, 0);
        game.moveFigur(5, 0, 0);
        game.moveFigur(5, 0, 0);
        game.moveFigur(5, 0, 0);
        game.moveFigur(5, 0, 0);
        game.moveFigur(5, 0, 0);
        
        Actions actions1 = game.checkActions(6, 0);
        int figur = KI.decideAction(actions1);
        game.moveFigur(6, 0, figur);
        //game.moveFigur(6, 1, 0);
        System.out.println("the end");
    }

}
