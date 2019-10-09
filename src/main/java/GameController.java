import java.util.HashMap;
import java.util.Random;

public class GameController {
    private HashMap<Integer, PlayerModel> enemies = new HashMap<>();
    private HashMap<Integer, PlayerModel> bosses = new HashMap<>();
    private HashMap<Integer, PlayerModel> allies = new HashMap<>();
    private HashMap<Integer, Role> enemiesRoles = new HashMap<>();
    private HashMap<Integer, Role> alliesRoles = new HashMap<>();
    private HashMap<Integer, String> enemiesRolesString = new HashMap<>();
    private HashMap<Integer, String> alliesRolesString = new HashMap<>();
    private HashMap<Integer, String> playerMenu = new HashMap<>();
    private HashMap<Integer, String> mainMenu = new HashMap<>();
    private HashMap<Integer, String> atackMenu = new HashMap<>();
    private boolean mainMenuBool = true;
    private boolean playerMenuBool = true;
    private boolean message = true;


    private View view;

    public GameController() {
        this.view = new View();
        this.playerMenu.put(0, "Atacar");
        this.playerMenu.put(1, "Usar item");
        this.playerMenu.put(2, "Salir");
        this.mainMenu.put(0, "Jugar nivel");
        this.mainMenu.put(1, "Salir");
//        this.atackMenu.put(0, "Ataque normal");
//        this.atackMenu.put(1, "Ingerir item");
//        this.atackMenu.put(2, "Atras");
        this.enemiesRoles.put(0, new Troll());
        this.enemiesRoles.put(1, new NightElf());
        this.enemiesRoles.forEach((integer, role) -> this.enemiesRolesString.put(integer, role.getName()));



    }

    private void generateLevel(){
        Random rand = new Random();
        this.enemies.clear();
        this.bosses.clear();
        this.allies.put(0, this.setNewPlayer());

        int randomNumber = rand.nextInt(4) + 1;
        for (int i = 0; i < randomNumber; i++) {
            this.enemies.put(i,this.setNewEnemy());
        }
        int numberOfBosses = rand.nextInt(2);
        for (int i = 0; i < numberOfBosses; i++) {
            this.bosses.put(i, this.setBoss());
        }
    }

    public void main(){
        while (this.mainMenuBool){
            switch (this.mainMenu.get(this.view.selectKey(this.mainMenu))){
                case "Jugar nivel":
                    this.generateLevel();
                    this.playerMenuBool = true;
                    this.game();
                    break;
                default:
                    this.mainMenuBool= false;
                    this.view.print("Gracias por haber jugado");
                    break;
            }
        }
    }

    private void game(){
        int turn = 0;
        this.playerMenuBool = true;

        while (this.playerMenuBool){
            switch (this.playerMenu.get(this.view.selectKey(this.playerMenu))){
                case "Atacar":
                    if (this.enemies.size() > 0)
                        this.allies.get(0).attack(this.enemies);
                    else if (this.bosses.size() > 0 ){
                        if (this.message){
                            this.message = false;
                            this.view.print("Va a pelear con el boss.");
                        }
                        this.allies.get(0).attack(this.bosses);
                    }
                    if (this.enemies.isEmpty() && this.bosses.isEmpty()){
                        this.view.print("Ha ganado el juego.");
                        this.mainMenuBool = false;
                        this.playerMenuBool = false;
                    }
                    break;
                case "Usar item":
                    this.allies.get(0).specialAbility();

                    break;
                default:
                    this.playerMenuBool = false;
            }
            if (this.enemies.size() > 0)
                this.enemies.forEach((index, enemy) -> enemy.attack(this.allies));
            else if (this.bosses.size() > 0 ){
                this.bosses.forEach((index, enemy) -> enemy.attack(this.allies));
            }
            if (this.allies.isEmpty() || this.allies.get(0).getLife() == 0){
                this.view.print("Ha perdido el juego.");
                this.mainMenuBool = false;
            }
        }
    }


    private Player setNewPlayer(){
        String name = this.view.input("Ingrese el nombre del jugador");
        Role role = null;
//        this.alliesRoles.put(0, new Explorer());
//        this.alliesRoles.put(1, new Warrior());
        this.view.print("Ingrese el rol del jugador");

        this.alliesRolesString.put(0, "Guerrero");
        this.alliesRolesString.put(1, "Explorador");
        int index = this.view.selectKey(this.alliesRolesString);
        if (index == 0){
            role = new Warrior();
        }else{
            role = new Explorer();
        }

        return new Player(role, name, role.getName());

    }
    private Minion setNewEnemy(){
        Random rand = new Random();
        int randomRole = rand.nextInt(this.enemiesRoles.size());

        this.view.print("Se agrego un minion.");
        return new Minion(this.enemiesRoles.get(randomRole));

    }
    private Boss setBoss(){
        Random rand = new Random();
        int randomNumber = rand.nextInt(2);
        int randomRole = rand.nextInt(this.enemiesRoles.size());
            this.view.print("Se agrego un boss.");
            return new Boss(this.enemiesRoles.get(randomRole));

    }
}
