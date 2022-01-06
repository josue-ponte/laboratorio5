package main.identification.model;

public class Student {

  private String firstName;
  private String lastName;
  private String dni;
  private String code;
  private String incomeYear;
  private Career career;

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

  public String getDni() {
    return dni;
  }

  public void setDni(String dni) {
    this.dni = dni;
  }

  public String getCode() {
    return code;
  }

  public void setCode(String code) {
    this.code = code;
  }

  public String getIncomeYear() {
    return incomeYear;
  }

  public void setIncomeYear(String incomeYear) {
    this.incomeYear = incomeYear;
  }

  public Career getCareer() {
    return career;
  }

  public void setCareer(Career career) {
    this.career = career;
  }
}
