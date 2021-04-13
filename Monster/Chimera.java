package Monster;

public class Chimera extends Monster {

    public Chimera(String name) {
        super(name);
    }

    @Override
    public String attack() {
        return "ATTACK WITH FIRE, WATER, AND ROCKS!!!!!!";
    }
}
