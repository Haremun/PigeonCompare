package pl.mwgrogowo;

import java.sql.SQLException;
import java.util.List;

class PigeonService {

  PigeonRepository pigeonRepository = new PigeonRepository();

  List<PigeonDto> getPigeonInBasket() throws SQLException {
    return pigeonRepository.getPigeonsInBasket();
  }
}
