package pl.mwgrogowo;

import java.sql.SQLException;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
class PigeonService {

  private final PigeonRepository pigeonRepository;

  List<PigeonDto> getPigeonInBasket() throws SQLException {
    return pigeonRepository.getPigeonsInBasket();
  }
}
