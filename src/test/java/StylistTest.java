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

  @Test
   public void all_returnsAllInstancesOfStylist_true() {
    Stylist firstStylist = new Stylist("Yanti Adams");
    firstStylist.save();
    Stylist secondStylist = new Stylist("Kaylee Moore");
    secondStylist.save();
    assertEquals(true, Stylist.all().get(0).equals(firstStylist));
    assertEquals(true, Stylist.all().get(1).equals(secondStylist));
 }
}
