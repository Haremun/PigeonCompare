package pl.mwgrogowo;

public class PigeonDto {
  private final int pigeonId;
  private final String name;
  private final String description;
  private final String surname;

  public PigeonDto(int pigeonId, String name, String description, String surname) {
    this.pigeonId = pigeonId;
    this.name = name;
    this.description = description;
    this.surname = surname;
  }

  public int getPigeonId() {
    return pigeonId;
  }

  public String getName() {
    return name;
  }

  public String getDescription() {
    return description;
  }

  public String getSurname() {
    return surname;
  }
}
