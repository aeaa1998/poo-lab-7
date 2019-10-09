public class Elixir implements Item {
    @Override
    public void use(Role model) {

        model.heal(50);
    }

}
