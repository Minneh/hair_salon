import java.util.ArrayList;
import java.util.List;
import org.sql2o.*;

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

  public static Client find(int id) {
    try(Connection con = DB.sql2o.open()) {
      String sql = "SELECT * FROM clients where id=:id";
      Client client = con.createQuery(sql)
        .addParameter("id", id)
        .executeAndFetchFirst(Client.class);
      return client;
    }
  }

}
