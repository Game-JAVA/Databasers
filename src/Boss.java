import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.scene.image.Image;
import javafx.util.Duration;

/*
    Classe Boss possui as configurações especiais do Chefe do jogo.

 */


public class Boss extends Enemy{
    private double life;
    private double damage;
    private double speed = 1;
    private Image[] boss_idle_rage_r;
    private Image[] boss_idle_rage_l;
    private Image[] boss_atk_rage_r;
    private Image[] boss_atk_rage_l;
    private Image[] boss_idle_l;
    private Image[] boss_idle_r;
    private Image[] boss_atk_l;
    private Image[] boss_atk_r;
    private Timeline atkrageL;
    private Timeline atkrageR;
    private Timeline idlerageL;
    private Timeline idlerageR;
    private Timeline idleL;
    private Timeline idleR;
    private Timeline atkL;
    private Timeline atkR;
    private int indexAtk = 0;
    private int indexIdle = 0;
    public Boss(double x, double y) {
        super(x, y);
        include_boss_image();
        include_boss_animations();
    }

    private void include_boss_image(){
          boss_idle_rage_l = new Image[6];
          boss_idle_rage_r = new Image[6];
          boss_atk_rage_l = new Image[11];
          boss_atk_rage_r = new Image[11];
          boss_idle_r = new Image[6];
          boss_idle_l = new Image[6];
          boss_atk_l = new Image[10];
          boss_atk_r = new Image[10];
        for (int i = 0; i < boss_atk_rage_l.length; i++) {
            boss_atk_rage_l[i] = new Image("res/boss_res/rage_boss/boss_atk_rage_l" + i + ".png");
            boss_atk_rage_r[i] = new Image("res/boss_res/rage_boss/boss_atk_rage_r" + i + ".png");
        }
        for (int i = 0; i < boss_idle_rage_l.length; i++) {
            boss_idle_rage_l[i] = new Image("res/boss_res/rage_boss/boss_idle_rage_l" + i + ".png");
            boss_idle_rage_r[i] = new Image("res/boss_res/rage_boss/boss_idle_rage_r" + i + ".png");
//            boss_idle_r[i] = new Image("res/boss_res/normal_boss/"+ i + ".png");
        }
        for (int i = 0; i < boss_idle_l.length; i++) {
            boss_idle_l[i] = new Image("res/boss_res/normal_boss/boss" + i + ".png");
        }
    }

    private void include_boss_animations(){
        // movimentação no modo raiva
        idlerageL = new Timeline(new KeyFrame(Duration.millis(100), e -> {
            indexIdle = (indexIdle + 1) % boss_idle_rage_l.length;
            setImage(boss_idle_rage_l[indexIdle]);
        }));
        idlerageL.setCycleCount(Timeline.INDEFINITE);
        idlerageR = new Timeline(new KeyFrame(Duration.millis(100), e -> {
            indexIdle = (indexIdle + 1) % boss_idle_rage_r.length;
            setImage(boss_idle_rage_r[indexIdle]);
        }));
        idlerageR.setCycleCount(Timeline.INDEFINITE);

        // ataque no modo raiva
        atkrageL = new Timeline(new KeyFrame(Duration.millis(200), e -> {
            indexAtk = (indexAtk + 1) % boss_atk_rage_l.length;
            setImage(boss_atk_rage_l[indexAtk]);
        }));
        atkrageL.setCycleCount(boss_atk_rage_l.length);
        atkrageR = new Timeline(new KeyFrame(Duration.millis(200), e -> {
            indexAtk = (indexAtk + 1) % boss_atk_rage_r.length;
            setImage(boss_atk_rage_r[indexAtk]);
        }));
        atkrageR.setCycleCount(boss_atk_rage_r.length);

        // movimentação no modo normal
        idleL = new Timeline(new KeyFrame(Duration.millis(100), e -> {
            indexIdle = (indexIdle + 1) % boss_idle_l.length;
            setImage(boss_idle_l[indexIdle]);
        }));
        idleL.setCycleCount(Timeline.INDEFINITE);
    }

    public void run(Player player) {
        movement(player);
        if (boss_ready_to_atk(player)) {
            atkL();
        }
    }

    public boolean boss_ready_to_atk(Player player){
        return getX() < player.getImageView().getX() + player.getWidth();
    }

    public void atkL(){}

    public void atk_rageL(){
        atkrageL.play();
    }
    public void atk_rageR(){
        atkrageR.play();
    }
    public void idle_rageL(){
        idlerageL.play();
    }
    public void idle_rageR(){
        idlerageR.play();
    }

    public void movement(Player player){
        // Posição do Player
        double playerX = player.getImageView().getX() + player.getWidth(); //este é o p1 - 'atemporal_document'
//        double playerX_new = player.getImageView().getX(); //este é o p0
        double playerY = player.getImageView().getY();

        // Distância entre o Boss e o player
        double deltaX = playerX - getX();
        double deltaY = playerY - getY();

        // Verificar a maior distância entre o Player e o Boss
        if (Math.abs(deltaX) > Math.abs(deltaY)) {
            idleL.play();
            if (deltaX > 0) {
                setX(getX() + speed);
            } else {
                setX(getX() - speed);
            }
        } else {
            if (deltaY > 0) {
                setY(getY() + speed);
            } else {
                setY(getY() - speed);
            }
        }
    }

    public double getLife() {
        return life;
    }

    public double getSpeed(){
        return speed;
    }

    public Timeline getAtkrage() {
        return atkrageL;
    }

    public Timeline getIdlerage() {
        return idlerageL;
    }
}
