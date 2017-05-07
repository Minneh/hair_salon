import org.sql2o.*;
import org.junit.*;
import static org.junit.Assert.*;

// class constructor
public class ClientTest{
  @Before
  public void setUp() {
    DB.sql2o = new Sql2o("jdbc:postgresql://localhost:5432/hair_salon_test", null, null);
  }

  @After
  public void tearDown() {
    try(Connection con = DB.sql2o.open()) {
      String sql = "DELETE FROM clients *;";
      con.createQuery(sql).executeUpdate();
    }
  }
  // confirm we can successfully instantiate Client objects
  public void client_instantiatesCorrectly_true(){
    Client myClient = new Client("Michael King", 1);
    assertEquals(true, myClient instanceof Client);
  }
  // make sure we can assign each client a name and then retrieve it
  public void client_instantiatesWithName_String(){
    Client myClient = new Client("Michael King", 1);
    assertEquals("Michael King", myClient.getName());
  }

  @Test
  public void all_returnsAllInstancesOfClient_true(){
    Client firstClient = new Client("James Redford", 1);
    firstClient.save();
    Client secondClient = new Client("Kyle Jenkins", 2);
    secondClient.save();
    assertEquals(true, Client.all().contains(firstClient));
    assertEquals(true, Client.all().contains(secondClient));
    }

    @Test
    public void getId_clientsInstantiateWithAnID_1() {
      Client myClient = new Client("Harold Kumar", 1);
      myClient.save();
      assertTrue(myClient.getId() > 0);
  }

  @Test
  public void find_returnsClientWithSameId_secondClient() {
    Client firstClient = new Client("James Redford", 1);
    firstClient.save();
    Client secondClient = new Client("Kyle Jenkins", 1);
    secondClient.save();
    assertEquals(Client.find(secondClient.getId()), secondClient);
  }

  @Test
  public void equals_returnsTrueIfNamesAretheSame() {
    Client firstClient = new Client("James Redford", 1);
    Client secondClient = new Client("James Redford", 1);
    assertTrue(firstClient.equals(secondClient));
  }

  @Test
  public void save_returnsTrueIfNamesAretheSame() {
    Client myClient = new Client("Marques Houston", 1);
    myClient.save();
    assertTrue(Client.all().get(0).equals(myClient));
  }

  @Test
  public void save_assignsIdToObject() {
    Client myClient = new Client("Oprah Winfrey", 1);
    myClient.save();
    Client savedClient = Client.all().get(0);
    assertEquals(myClient.getId(), savedClient.getId());
  }

  @Test
  public void update_updatesClientName_true() {
    Client myClient = new Client("Moesha Johnson", 1);
    myClient.save();
    myClient.update("Cinderella Jones");
    assertEquals("Cinderella Jones",  Client.find(myClient.getId()).getName());
  }

  @Test
  public void delete_deletesClient_true() {
    Client myClient = new Client("Melanie Griffin", 1);
    myClient.save();
    int myClientId = myClient.getId();
    myClient.delete();
    assertEquals(null, Client.find(myClientId));
  }

}
