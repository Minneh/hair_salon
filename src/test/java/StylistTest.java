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

 @Test
  public void getId_stylistsInstantiateWithAnId_1() {
    Stylist testStylist = new Stylist("Shaniqua Johnson");
    testStylist.save();
    assertTrue(testStylist.getId() > 0);
  }

  @Test
  public void find_returnsStylistWithSameId_secondStylist() {
    Stylist firstStylist = new Stylist("James Dean");
    firstStylist.save();
    Stylist secondStylist = new Stylist("George DeJungle");
    secondStylist.save();
    assertEquals(Stylist.find(secondStylist.getId()), secondStylist);
  }

  @Test
  public void equals_returnsTrueIfNamesAretheSame() {
    Stylist firstStylist = new Stylist("Peter Griffin");
    Stylist secondStylist = new Stylist("Peter Griffin");
    assertTrue(firstStylist.equals(secondStylist));
  }

  @Test
  public void save_savesIntoDatabase_true() {
    Stylist myStylist = new Stylist("Peter Griffin");
    myStylist.save();
    assertTrue(Stylist.all().get(0).equals(myStylist));
  }

  public void save(){
    try (Connection con = DB.sql2o.open()){
      String sql = "INSERT INTO stylists (name) VALUES (:name);";
      this.id = (int) con.createQuery(sql, true)
        .addParameter("name", this.name)
        .executeUpdate()
        .getKey();
    }
  }

  @Test
  public void save_assignsIdToObject() {
    Stylist myStylist = new Stylist("Stan Smith");
    myStylist.save();
    Stylist savedStylist = Stylist.all().get(0);
    assertEquals(myStylist.getId(), savedStylist.getId());
  }

  @Test
  public void getClients_retrievesAllClientsFromDatabase_clientList() {
    Stylist myStylist = new Stylist("Shimano Kyousuke");
    myStylist.save();
    Client firstClient = new Client("Yamada Hiragawa", myStylist.getId());
    firstClient.save();
    Client secondClient = new Client("Urahara Jin", myStylist.getId());
    secondClient.save();
    Client[] clients = new Client[] { firstClient, secondClient };
    assertTrue(myStylist.getClients().containsAll(Arrays.asList(clients)));
  }
}
