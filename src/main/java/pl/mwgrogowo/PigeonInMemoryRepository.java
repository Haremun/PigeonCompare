package pl.mwgrogowo;

import java.sql.SQLException;
import java.util.List;

public class PigeonInMemoryRepository implements PigeonRepository {
  @Override
  public List<PigeonDto> getPigeonsInBasket() throws SQLException {
    return List.of(
        new PigeonDto(0, "PL-0123-20-10333", "", "Surname"),
        new PigeonDto(1, "OLEK", "PL-0123-20-123", "Test"),
        new PigeonDto(2, "PL-0123-20-4040 (R2)", "", "Testowy"));
  }
}
