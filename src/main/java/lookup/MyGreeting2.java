package lookup;

@Initializer(value = ServiceType.TWO)
public class MyGreeting2 implements MyGreeting {

    @Override
    public String getGreet() {
        return "Hiya!";
    }

}
