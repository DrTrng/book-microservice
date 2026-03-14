package com.example.order.db.migration;

import java.sql.Statement;
import org.flywaydb.core.api.migration.BaseJavaMigration;
import org.flywaydb.core.api.migration.Context;

public class V1__create_orders_table extends BaseJavaMigration {

  @Override
  public void migrate(Context context) throws Exception {

    try (Statement statement = context.getConnection().createStatement()) {

      statement.execute(
          """
                CREATE TABLE orders (
                    id UUID PRIMARY KEY,
                    book_id UUID NOT NULL,
                    user_id UUID NOT NULL,
                    created TIMESTAMP NOT NULL,
                    updated TIMESTAMP NOT NULL
                )
            """);

      statement.execute(
          """
                CREATE INDEX idx_orders_book_id ON orders(book_id)
            """);

      statement.execute(
          """
                CREATE INDEX idx_orders_user_id ON orders(user_id)
            """);

      statement.execute(
          """
                CREATE INDEX idx_orders_created ON orders(created)
            """);
    }
  }
}
