import java.util.ArrayList;
import java.util.List;

public class Stylist{
  private String name;
  private int id;

  public Stylist(String name){
    this.name = name;
  }

  public String getName(){
    return name;
  }

  public static List<Stylist> all(){
    String sql = "SELECT id, name FROM stylists;";
    try(Connection con = DB.sql2o.open()){
      return con.createQuery(sql).executeAndFetch(Stylist.class);
    }
  }

  public int getId(){
    return id;
  }

  public static Stylist find(int id){
    try(Connection con = DB.sql2o.open()){
      String sql = "SELECT * FROM stylists where id=:id";
      Stylist stylist = con.createQuery(sql)
        .addParameter("id", id)
        .executeAndFetchFirst(Stylist.class);
        return stylist;
    }
  }

}
