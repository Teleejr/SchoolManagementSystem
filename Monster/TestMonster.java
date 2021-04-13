package Monster;

public class TestMonster {
    public static void main(String[] args) {
// Program at the "interface" defined in the superclass.
// Declare instances of the superclass, substituted by subclasses.
        Monster m1 = new FireMonster("r2u2"); // upcast
        Monster m2 = new WaterMonster("u2r2"); // upcast
        Monster m3 = new StoneMonster("r2r2"); // upcast
        Monster m5 = new Chimera("R1L1");
// Invoke the actual implementation
        System.out.println(m1.attack()); // Run FireMonster&#39;s attack()
        System.out.println(m2.attack()); // Run WaterMonster&#39;s attack()
        System.out.println(m3.attack()); // Run StoneMonster&#39;s attack()
// m1 dies, generate a new instance and re-assign to m1.
        m1 = new StoneMonster("a2b2"); // upcast
        System.out.println(m1.attack()); // Run StoneMonster&#39;s attack()
// We have a problem here!!!
        Monster m4 = new Monster("u2u2");
        System.out.println(m4.attack()); // garbage!!!

        System.out.println(m5.attack());

    }

}
