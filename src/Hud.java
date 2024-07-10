import javafx.scene.Node;
import javafx.scene.image.ImageView;

public class Hud extends ImageView {
    private ImageView[] player_life;
    private ImageView[] enemy_life;
    private ImageView[] boss_life;
    private ImageView boss_dead;

    public Hud(double x, double y){
        setX(x);
        setY(y);
        include_hud_images();
    }

    private void include_hud_images(){
        player_life = new ImageView[3];
        enemy_life = new ImageView[2];
        boss_life = new ImageView[6];
        boss_dead = new ImageView("res/hud_res/boss_dead.png");
        for (int i = 0; i < player_life.length; i++) {
            player_life[i] = new ImageView("res/hud_res/player_heart" + i + ".png");
            // Hud do player
            player_life[i].setFitHeight(50);
            player_life[i].setFitWidth(188);
        }
        for (int i = 0; i < boss_life.length; i++) {
            boss_life[i] = new ImageView("res/hud_res/boss_heart" + i + ".png");
        }
        enemy_life[0] = new ImageView("res/hud_res/enemy_heart0.png");
        // Hud do player
        enemy_life[0].setFitWidth(20);
        enemy_life[0].setFitHeight(26);
//        for (int i = 0; i < enemy_life.length; i++) {
//            enemy_life[i] = new ImageView("res/hud_res/enemy_heart" + i + ".png");
//        }
    }

    public Node getPlayer_life(int n){return player_life[n];}

    public Node getEnemy_life(int n,double x,double y){
        enemy_life[n].setX(x);
        enemy_life[n].setY(y);
        return enemy_life[n];}

    public Node getBoss_life(int n, double x, double y){
        boss_life[n].setX(x);
        boss_life[n].setY(y);
        return boss_life[n];}

    public void update_enemy_hud_position(int option, double x, double y) {
            enemy_life[option].setX(x);
            enemy_life[option].setY(y);
    }

    public void set_hud_visible(boolean s){
            enemy_life[0].setVisible(s);
    }

    public Node getBoss_dead(){return boss_dead;}
}

