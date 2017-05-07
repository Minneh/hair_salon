import org.junit.*;
import static org.junit.Assert.*;
import org.sql2o.*;
import java.util.Arrays;

public class StylistTest{
  @Test
  public void stylist_instantiatesCorrectly_true() {
    Stylist testStylist = new Stylist("Jenna Marbles");
    assertEquals(true, testStylist instanceof Stylist);
  }

  @Test
  public void getName_stylistInstantiatesWithName_Home() {
    Stylist testStylist = new Stylist("Jenna Marbles");
    assertEquals("Jenna Marbles", testStylist.getName());
  }
}
