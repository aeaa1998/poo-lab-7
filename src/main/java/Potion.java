public class Potion implements Item  {
    @Override
    public void use(Role model) {
        model.heal(50);
    }
}
