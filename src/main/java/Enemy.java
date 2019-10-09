import java.util.*;

public class Enemy implements  PlayerModel{
    private Role role;
    private String type;
    private PlayerModel target;

    public Enemy(Role role, String type) {
        this.role = role;
        this.type = type;
    }

    public Role getRole() {
        return role;
    }
    public void getDamage(int damge) {
        this.role.takeDamage(damge);
    }

    @Override
    public int getLife() {
        return this.role.getLife();
    }

    @Override
    public void specialAbility(){
        this.role.specialAbility(this.target);
    }

    @Override
    public String getName() {
        return this.getRole().getName();
    }


    public int getAttackPower(){
        return this.role.getAttackPower();
    }

    @Override
    public void attack(HashMap<Integer, PlayerModel> models) {

        Random rand = new Random();
        int randomNumber = rand.nextInt(2);
        if (randomNumber == 0){
            models.get(0).getDamage(this.role.getAttackPower());
            this.getRole().getView().print("El " + this.type+" ha atacado.");
            this.getRole().getView().print("Vida restante del jugador " + models.get(0).getName() + " " + String.valueOf(models.get(0).getLife()));

        }else {
            this.getRole().getView().print("El " + this.type+" fallo al atacar.");
        }
    }

    @Override
    public void choose(HashMap<Integer, PlayerModel> models) {
        HashMap<Integer, String> stringModels = new HashMap<>();
        models.forEach((key, model) -> {
            stringModels.put(key, model.getName());
        });
        int index =  this.getRole().getView().selectKey(stringModels);
        this.target = models.get(index);
    }


    @Override
    public void pass() {
        this.role.getView().print("El enemigo " +" de tipo "+ this.getRole().getAttackPower()+" ha pasado el turno.");

    }

    public PlayerModel getTarget() {
        return target;
    }

    public String getType() {
        return type;
    }

    @Override
    public void setLife(int life) {
        this.getRole().setLife(life);
    }
}
