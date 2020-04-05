package lookup;

import static org.jboss.shrinkwrap.api.ShrinkWrap.create;
import static org.junit.Assert.assertEquals;

import javax.enterprise.inject.Any;
import javax.enterprise.inject.Instance;
import javax.enterprise.inject.literal.NamedLiteral;
import javax.inject.Inject;
import org.jboss.arquillian.container.test.api.Deployment;
import org.jboss.arquillian.junit.Arquillian;
import org.jboss.shrinkwrap.api.spec.JavaArchive;
import org.junit.Test;
import org.junit.runner.RunWith;

/**
 * @author Arjan Tijms
 *
 */
@RunWith(Arquillian.class)
public class QualifiedLookupTest {

  @Deployment
  public static JavaArchive createDeployment() {
    return create(JavaArchive.class)
        .addClasses(
            MyGreeting.class, MyGreeting1.class, MyGreeting2.class, ServiceType.class, Initializer.class)
        .addAsManifestResource("beans.xml");
  }

  @Inject @Any
  Instance<MyGreeting> myGreetings;

  @Test
  public void test() {

    // Instance<MyGreeting> myGreetings = CDI.current().select(MyGreeting.class);

    myGreetings.stream()
        .forEach(System.out::println);


    MyGreeting myGreeting =
        myGreetings.stream()
            .filter(e -> e.getClass().getAnnotation(Initializer.class).value()== ServiceType.TWO)
            .findFirst().orElse(null);

    System.out.println(myGreeting.getClass().getName());

//        assertEquals("Ay-up!", myGreetings.select(NamedLiteral.of("northern")).get().getGreet());
//

    //assertEquals("Hiya!", myGreetings.get().getClass().getSimpleName());
    assertEquals("Hiya!", myGreeting.getGreet());

    assertEquals(false, myGreetings.select(NamedLiteral.of("formal")).isResolvable());
  }
}
