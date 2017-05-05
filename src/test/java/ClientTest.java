import org.junit.*;
import static org.junit.Assert.*;

public class ClientTest{
  public void Client_instantiateCorrectly_true(){
    Client myClient = new Client("Michael King");
    assertEquals(true, myClient instanceof Client);
  }

  public void Client_instantiatesWithName_String(){
    Client myClient = new Client("Michael King");
    assertEquals("Michael King", myClient.getDescription();
  }

}
