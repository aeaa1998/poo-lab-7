import java.util.ArrayList;

public abstract class Role {
    private View view;
    private int life;
    private int bagSize;
    private String roleName;
    private int attackPower;
    ArrayList<Item> items = new ArrayList<>();


    public Role(int life, int bagSize, int attackPower, String rolename) {
        this.life = life;
        this.bagSize = bagSize;
        this.attackPower = attackPower;
        this.view = new View();
        this.setItem(bagSize);
        this.roleName = rolename;

    }
    public Role(int life, int attackPower, String rolename) {
        this.life = life;
        this.view = new View();
        this.bagSize = 0;
        this.attackPower = attackPower;
        this.roleName = rolename;
    }

    public int getLife() {
        return life;
    }
    public String getName() {
        return this.roleName;
    }

    public int getBagSize() {
        return bagSize;
    }

    public int getAttackPower() {
        return attackPower;
    }
    public void specialAbility(PlayerModel model){};
    public void specialAbility(Role role){
        ArrayList<String> itemsList = new ArrayList<>();
        itemsList.add("Elixir");
        itemsList.add("Pocion");
        if (this.items.size() == 1){
            this.items.get(0).use(role);
            this.view.print("El" + this.getName() + "ha utlizado el item.");
        }else{
            String option = this.view.selectOptions(itemsList);
            if (option.equalsIgnoreCase("Elixir")){
                this.items.get(0).use(role);
                this.view.print("El" + this.getName() +" ha utlizado el elixir y ha incrementado su ataque.");

            }else{
                this.items.get(1).use(role);
                this.view.print("El" + this.getName() +" ha utlizado la pocion y ha incrementado su vida.");
            }
        }
    };

    public void setLife(int life) {
        this.life = life;
    }

    public View getView() {
        return view;
    }

    public void takeDamage(int damage) {
        this.life -= damage;
    }

    public void setAttackPower(int attackPower) {
        this.attackPower = attackPower;
    }
    public void increaseDamage(int attack) {
        this.attackPower += attack;
    }
    public void heal(int life) {
        this.life += life;
    }

    public void setItem(int number){
            if (number == 2){
                items.add(0,new Elixir());
                items.add(1, new Potion());
            }else{
                ArrayList<String> itemsList = new ArrayList<>();
                itemsList.add("Elixir");
                itemsList.add("Pocion");
                String option = this.view.selectOptions(itemsList);
                if (option.equalsIgnoreCase("Elixir")){
                    items.add(new Elixir());
                }else{
                    items.add(new Potion());
                }
            }

    }

}
