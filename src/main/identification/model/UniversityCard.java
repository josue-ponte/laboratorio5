package main.identification.model;

public class UniversityCard {

  private String university;
  private String code;
  private String dni;
  private String lastName;
  private String firstName;
  private String faculty;
  private String career;
  private int year;
  private String incomeYear;
  private String expirationDate;

  public String getUniversity() {
    return university;
  }

  public void setUniversity(String university) {
    this.university = university;
  }

  public String getCode() {
    return code;
  }

  public void setCode(String code) {
    this.code = code;
  }

  public String getDni() {
    return dni;
  }

  public void setDni(String dni) {
    this.dni = dni;
  }

  public String getLastName() {
    return lastName;
  }

  public void setLastName(String lastName) {
    this.lastName = lastName;
  }

  public String getFirstName() {
    return firstName;
  }

  public void setFirstName(String firstName) {
    this.firstName = firstName;
  }

  public String getFaculty() {
    return faculty;
  }

  public void setFaculty(String faculty) {
    this.faculty = faculty;
  }

  public String getCareer() {
    return career;
  }

  public void setCareer(String career) {
    this.career = career;
  }

  public int getYear() {
    return year;
  }

  public void setYear(int year) {
    this.year = year;
  }

  public String getIncomeYear() {
    return incomeYear;
  }

  public void setIncomeYear(String incomeYear) {
    this.incomeYear = incomeYear;
  }

  public String getExpirationDate() {
    return expirationDate;
  }

  public void setExpirationDate(String expirationDate) {
    this.expirationDate = expirationDate;
  }

  @Override
  public String toString() {
    return "UniversityCard{" +
        "university='" + university + '\'' +
        ", code='" + code + '\'' +
        ", dni='" + dni + '\'' +
        ", lastName='" + lastName + '\'' +
        ", firstName='" + firstName + '\'' +
        ", faculty='" + faculty + '\'' +
        ", career='" + career + '\'' +
        ", year=" + year +
        ", incomeYear='" + incomeYear + '\'' +
        ", expirationDate='" + expirationDate + '\'' +
        '}';
  }
}
