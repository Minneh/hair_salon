import org.junit.*;
import static org.junit.Assert.*;

// class constructor
public class ClientTest{
  // confirm we can successfully instantiate Task objects
  public void Client_instantiateCorrectly_true(){
    Client myClient = new Client("Michael King");
    assertEquals(true, myClient instanceof Client);
  }
  // make sure we can assign each client a name and then retrieve it
  public void Client_instantiatesWithName_String(){
    Client myClient = new Client("Michael King");
    assertEquals("Michael King", myClient.getName());
  }

}
