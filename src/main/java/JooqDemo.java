import org.jooq.generated.Tables;

import java.sql.Connection;
import java.sql.SQLException;

import static java.sql.DriverManager.getConnection;
import static org.jooq.generated.Tables.*;
import static org.jooq.impl.DSL.using;

public class JooqDemo {
  public static void main(String[] args) {
    try (Connection c = getConnection("jdbc:mysql://localhost:3306", "root", "dijkstra")) {
      using(c)
          .select(ACTOR.FIRST_NAME, ACTOR.LAST_NAME, FILM.TITLE)
          .from(ACTOR)
          .join(Tables.FILM_ACTOR).on(FILM_ACTOR.ACTOR_ID.equal(ACTOR.ACTOR_ID))
          .join(Tables.FILM).on(FILM.FILM_ID.equal(FILM_ACTOR.FILM_ID))
          .where()
          .fetch()
      .stream()
          .forEach(System.out::println);
    } catch (SQLException sqlException) {
      //TODO

    }
  }
}
