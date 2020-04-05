package lookup;

@Initializer(value = ServiceType.ONE)
public class MyGreeting1 implements MyGreeting {

    @Override
    public String getGreet() {
        return "Ay-up!";
    }

}
