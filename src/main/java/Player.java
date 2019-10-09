import java.util.*;

public class Player implements PlayerModel {
    private Role role;
    private String name;
    private String type;
    private PlayerModel target;
    private int targetIndex;
    private boolean alive = true;

    public Player(Role role, String name, String type) {
        this.role = role;
        this.name = name;
        this.type = type;
    }

    @Override
    public void specialAbility(){
        this.role.specialAbility(this.role);
    }

    @Override
    public String getName() {
        return this.name;
    }

    @Override
    public void attack(HashMap<Integer, PlayerModel> models) {
        HashMap<Integer, String> stringModels = new HashMap<>();
        models.forEach((key, model) -> {
            stringModels.put(key, model.getName());
        });
        int index =  this.getRole().getView().selectKey(stringModels);
        models.get(index).getDamage(this.role.getAttackPower());
        if (models.get(index).getLife() > 0)
            this.getRole().getView().print("Vida restante del " + models.get(index).getName() + " " + String.valueOf(models.get(index).getLife()));
        else
            this.getRole().getView().print("El " + models.get(index).getName()+" ha muerto.");
        if (models.get(index).getLife() <= 0){
            models.remove(index);

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
        this.targetIndex = index;
    }


    @Override
    public void pass() {
        this.role.getView().print("El jugador "+ this.name +" de tipo "+ this.type+" ha atacado.");

    }

    @Override
    public void setLife(int life) {
        this.getRole().setLife(life);

    }


    @Override
    public void getDamage(int damge) {
        this.role.takeDamage(damge);
    }


    @Override
    public int getLife() {
        return this.role.getLife();
    }


    public Role getRole() {
        return role;
    }
}

