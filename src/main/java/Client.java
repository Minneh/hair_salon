import java.util.ArrayList;
import java.util.List;

public class Client{
  private String name;
  private int id;
  private int stylistId;


  // class constructor
  public Client(String name, int stylistId){
    this.name = name;
    this.stylistId = stylistId;
  }

  //get client name
  public String getName(){
    return name;
  }

  public static List<Hero> all() {
    return instances;
  }

  public int getId(){
    return id;
  }

}
