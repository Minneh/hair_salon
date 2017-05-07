import java.util.ArrayList;
import java.util.List;
import org.sql2o.*;

public class Client{
  private String name;
  private int id;
  private int stylist_id;


  // class constructor
  public Client(String name, int stylist_id){
    this.name = name;
    this.stylist_id = stylist_id;
  }

  //get client name
  public String getName(){
    return name;
  }

  public int getstylist_id() {
    return stylist_id;
  }

  public int getId(){
    return id;
  }

  public static List<Client> all() {
    String sql = "SELECT id, name, stylist_id FROM clients;";
    try(Connection con = DB.sql2o.open()) {
     return con.createQuery(sql).executeAndFetch(Client.class);
    }
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

  @Override
  public boolean equals(Object otherClient){
    if (!(otherClient instanceof Client)) {
      return false;
    } else {
      Client newClient = (Client) otherClient;
      return this.getName().equals(newClient.getName()) &&
             this.getId() == newClient.getId() &&
             this.getstylist_id() == newClient.getstylist_id();
    }
  }

  public void save() {
    try(Connection con = DB.sql2o.open()) {
      String sql = "INSERT INTO clients(name, stylist_id) VALUES (:name, :stylist_id)";
      this.id = (int) con.createQuery(sql, true)
        .addParameter("name", this.name)
        .addParameter("stylist_id", this.stylist_id)
        .executeUpdate()
        .getKey();
    }
  }

  public void update(String name) {
  try(Connection con = DB.sql2o.open()) {
    String sql = "UPDATE clients SET name = :name WHERE id = :id";
    con.createQuery(sql)
      .addParameter("name", name)
      .addParameter("id", id)
      .executeUpdate();
    }
  }

  public static List<Client> getClientsByStylistId(int stylist_id) {
     try (Connection con = DB.sql2o.open()) {
      String sql = "SELECT * FROM clients WHERE stylist_id=:stylist_id;";
      return con.createQuery(sql)
        .addParameter("stylist_id", stylist_id)
        .executeAndFetch(Client.class);
      }
  }

  public static void deleteClientById(int id) {
    try (Connection con = DB.sql2o.open()) {
      String sql = "DELETE FROM clients WHERE id=:id;";
      con.createQuery(sql)
        .addParameter("id", id)
        .executeUpdate();
    }
  }

}
