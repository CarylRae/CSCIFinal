public class GameStarter { //main for players
    public static void main(String[] args) {

        GameFrame gf = new GameFrame(843,675);
        gf.connectToServer();
        gf.setUpGUI();
        gf.addKeyBindings();
    
    }

}
