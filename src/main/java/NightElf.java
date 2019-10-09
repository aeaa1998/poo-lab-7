import java.util.Random;

public class NightElf extends Role {
    public NightElf() {
        super(129, 10, "Elfo de noche");
    }

    @Override
    public void specialAbility(PlayerModel player){
        int random = new Random().nextInt(35);
        this.setLife(this.getLife() + random);
        this.getView().print("Se ha curado " + random + " de vida el " +this.getName() + ".");
    }
}
