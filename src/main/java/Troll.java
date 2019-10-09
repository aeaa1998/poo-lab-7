public class Troll extends Role {
    public Troll() {
        super(100, 55, "Troll");
    }


    @Override
    public void specialAbility(PlayerModel player){
        player.getDamage(super.getAttackPower() * 2);
        this.getView().print("El "+this.getName() +" ha hecho un daño de  " + super.getAttackPower() * 2 + " de vida.");

    }
}
