import java.util.*;
public interface PlayerModel {

    public void attack(HashMap<Integer, PlayerModel> models);
    public void choose(HashMap<Integer, PlayerModel> models);
    public void pass();
    public void setLife(int life);
    public void getDamage(int damge);
    public int getLife();
    public void specialAbility();
    public String getName();
}
