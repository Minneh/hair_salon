import org.junit.*;
import static org.junit.Assert.*;

public class ClientTest{
  public void Client_instantiateCorrectly_true(){
    Client myClient = new Client();
    assertEquals(true, myClient instanceof Client);
  }

}
