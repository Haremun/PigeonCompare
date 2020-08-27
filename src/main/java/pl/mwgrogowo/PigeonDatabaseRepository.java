package pl.mwgrogowo;

import java.sql.*;
import java.util.LinkedList;
import java.util.List;
import org.springframework.stereotype.Component;

@Component
class PigeonDatabaseRepository implements PigeonRepository {

  private Connection connection;

  public static final String DB_URL = "jdbc:sqlserver://localhost\\sqlexpress";
  private static final String USER_NAME = "sa";
  private static final String PASSWORD = "MWGRogowo3000";

  void startConnection() {
    try {
      connection = DriverManager.getConnection(DB_URL, USER_NAME, PASSWORD);
    } catch (SQLException ex) {
      ex.printStackTrace();
    }
  }

  @Override
  public List<PigeonDto> getPigeonsInBasket() throws SQLException {

    List<PigeonDto> pigeonDtos = new LinkedList<>();

    if (connection != null) {
      Statement stmt = connection.createStatement();
      ResultSet rs = stmt.executeQuery(
          "SELECT bskb_pgn_ID, pgn_ring, pgn_description " +
              "FROM [AMC-ESK.WG.2020].[dbo].[BSK2] " +
              "INNER JOIN [AMC-ESK.WG.2020].[dbo].[PGN1] ON bskb_pgn_ID=pgn_ID");
      while (rs.next()) {
        pigeonDtos.add(new PigeonDto(rs.getInt(1),
            rs.getString(2), rs.getString(3), null));
      }
    }
    return pigeonDtos;
  }
}
