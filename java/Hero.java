import java.util.ArrayList;
import java.util.List;

public class Hero{
  //member property to hold hero name
  private String mName;
  private int mAge;
  private String mSpecialPower;
  private String mWeakness;
  private static List<Hero> instances = new ArrayList<Hero>();
  private int mId;

  //Hero class with a constructor that (for now) takes only one argument: the superhero's name
  public Hero(String name, int age, String specialPower, String weakness){
    mName = name;
    mAge = age;
    mSpecialPower = specialPower;
    mWeakness = weakness;
    instances.add(this);
    mId = instances.size();

  }

  //function to return a hero's name
  public String getName(){
    return mName;
  }

  public int getAge(){
    return mAge;
  }

  public String getSpecialPower(){
    return mSpecialPower;
  }

  public String getWeakness(){
    return mWeakness;
  }

  public static List<Hero> all() {
    return instances;
  }

  public static void clear() {
    instances.clear();
  }

  public int getId() {
    return mId;
  }

  public static Hero find(int id) {
    return instances.get(id - 1);
  }
}
