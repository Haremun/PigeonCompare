package pl.mwgrogowo;

import java.sql.SQLException;
import java.util.List;

public class PigeonInMemoryRepository implements PigeonRepository {
  @Override
  public List<PigeonDto> getPigeonsInBasket() throws SQLException {
    return List.of(
        new PigeonDto(0, "test", "desc", "surname"),
        new PigeonDto(1, "luzik", "opis", "lolo"));
  }
}
