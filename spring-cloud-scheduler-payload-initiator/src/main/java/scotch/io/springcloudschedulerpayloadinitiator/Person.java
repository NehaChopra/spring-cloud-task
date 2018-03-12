package scotch.io.springcloudschedulerpayloadinitiator;

public class Person {
  private String firstName;
  private String lastName;

  public String getFirstName() {
    return firstName;
  }
  public void setFirstName(String firstName) {
    this.firstName = firstName;
  }
  public String getLastName() {
    return lastName;
  }
  public void setLastName(String lastName) {
    this.lastName = lastName;
  }

  public String toString(){
    StringBuilder builder = new StringBuilder();
    builder.append("{first_name:"+this.getFirstName())
      .append(", last_name:")
      .append(this.getLastName()+"}");

      return builder.toString();
  }

}