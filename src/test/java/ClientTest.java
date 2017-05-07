import org.junit.*;
import static org.junit.Assert.*;

// class constructor
public class ClientTest{
  // confirm we can successfully instantiate Client objects
  public void Client_instantiatesCorrectly_true(){
    Client myClient = new Client("Michael King");
    assertEquals(true, myClient instanceof Client);
  }
  // make sure we can assign each client a name and then retrieve it
  public void Client_instantiatesWithName_String(){
    Client myClient = new Client("Michael King");
    assertEquals("Michael King", myClient.getName());
  }

  @Test
  public void all_returnsAllInstancesOfClient_true(){
    Client firstClient = new Client("James Redford");
    Client secondClient = new Client("Kyle Jenkins");
    assertEquals(true, Client.all().contains(firstClient));
    assertEquals(true, Client.all().contains(secondClient));
    }

    @Test
    public void getId_clientsInstantiateWithAnID_1() {
      Client myClient = new Client("Harold Kumar", 1);
      myClient.save();
      assertTrue(myClient.getId() > 0);
  }


}
