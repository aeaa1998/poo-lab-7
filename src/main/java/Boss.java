import java.util.ArrayList;
import java.util.HashMap;
import java.util.Random;

public class Boss extends Enemy {
    public Boss(Role role) {
        super(role, "boss");
        int random = new Random().nextInt(30);
        this.getRole().setLife(this.getRole().getLife() * 2);
        this.getRole().setAttackPower(this.getRole().getAttackPower() + random);
    }
    @Override
    public void attack(HashMap<Integer, PlayerModel> models) {
        Random rand = new Random();
        int randomNumber = rand.nextInt(2);
        if (randomNumber == 2){
            this.specialBossAttack( models.get(0));
            this.getRole().getView().print("Vida restante del jugador " + models.get(0).getName() + " " + String.valueOf(models.get(0).getLife()));

        }else {
            models.get(0).getDamage(this.getRole().getAttackPower());
            this.getRole().getView().print("El boss ha atacado.");
            this.getRole().getView().print("Vida restante del jugador " + models.get(0).getName() + " " + String.valueOf(models.get(0).getLife()));

        }
    }

    private void specialBossAttack(PlayerModel player){
        int random = new Random().nextInt(3);
        for (int i = 0; i < random; i++) {
            this.getRole().specialAbility(player);
        }
        if (random > 0)
            this.getRole().getView().print("El " + this.getType()+" ha atacado "+ random+" veces.");
        else
            this.getRole().getView().print("El" + this.getType()+" intento atacar pero fallo su ataque especial.");

    }

}
