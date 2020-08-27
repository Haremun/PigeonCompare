package pl.mwgrogowo;

import java.sql.SQLException;
import java.util.List;

public interface PigeonRepository {

  List<PigeonDto> getPigeonsInBasket() throws SQLException;
}
