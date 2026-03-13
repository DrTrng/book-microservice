package com.example.product.db.migration;

import java.sql.Statement;
import org.flywaydb.core.api.migration.BaseJavaMigration;
import org.flywaydb.core.api.migration.Context;

public class V1__InitProductSchema extends BaseJavaMigration {

  @Override
  public void migrate(Context context) throws Exception {

    try (Statement statement = context.getConnection().createStatement()) {

      statement.execute("""
        CREATE EXTENSION IF NOT EXISTS "pgcrypto";
      """);

      statement.execute(
          """
        CREATE TABLE publishers (
          id UUID PRIMARY KEY DEFAULT gen_random_uuid(),
          created_at TIMESTAMP,
          updated_at TIMESTAMP,
          name VARCHAR(255),
          address VARCHAR(255),
          website VARCHAR(255)
        )
      """);

      statement.execute(
          """
        CREATE TABLE authors (
          id UUID PRIMARY KEY DEFAULT gen_random_uuid(),
          created_at TIMESTAMP,
          updated_at TIMESTAMP,
          name VARCHAR(255),
          biography VARCHAR(2000)
        )
      """);

      statement.execute(
          """
        CREATE TABLE categories (
          id UUID PRIMARY KEY DEFAULT gen_random_uuid(),
          created_at TIMESTAMP,
          updated_at TIMESTAMP,
          name VARCHAR(255),
          description VARCHAR(255),
          parent_id UUID,
          CONSTRAINT fk_category_parent
            FOREIGN KEY (parent_id)
            REFERENCES categories(id)
        )
      """);

      statement.execute(
          """
        CREATE TABLE books (
          id UUID PRIMARY KEY DEFAULT gen_random_uuid(),
          created_at TIMESTAMP,
          updated_at TIMESTAMP,
          title VARCHAR(255),
          description VARCHAR(2000),
          isbn VARCHAR(255) UNIQUE,
          price NUMERIC(10,2),
          published_date DATE,
          language VARCHAR(50),
          page_count INTEGER,
          publisher_id UUID,
          CONSTRAINT fk_book_publisher
            FOREIGN KEY (publisher_id)
            REFERENCES publishers(id)
        )
      """);

      statement.execute(
          """
        CREATE TABLE book_authors (
          book_id UUID NOT NULL,
          author_id UUID NOT NULL,
          PRIMARY KEY (book_id, author_id),
          CONSTRAINT fk_book_authors_book
            FOREIGN KEY (book_id)
            REFERENCES books(id)
            ON DELETE CASCADE,
          CONSTRAINT fk_book_authors_author
            FOREIGN KEY (author_id)
            REFERENCES authors(id)
            ON DELETE CASCADE
        )
      """);

      statement.execute(
          """
        CREATE TABLE book_categories (
          book_id UUID NOT NULL,
          category_id UUID NOT NULL,
          PRIMARY KEY (book_id, category_id),
          CONSTRAINT fk_book_categories_book
            FOREIGN KEY (book_id)
            REFERENCES books(id)
            ON DELETE CASCADE,
          CONSTRAINT fk_book_categories_category
            FOREIGN KEY (category_id)
            REFERENCES categories(id)
            ON DELETE CASCADE
        )
      """);

      statement.execute(
          """
        CREATE TABLE book_images (
          id UUID PRIMARY KEY DEFAULT gen_random_uuid(),
          created_at TIMESTAMP,
          updated_at TIMESTAMP,
          image_url VARCHAR(500),
          is_cover BOOLEAN,
          book_id UUID,
          CONSTRAINT fk_book_images_book
            FOREIGN KEY (book_id)
            REFERENCES books(id)
            ON DELETE CASCADE
        )
      """);

      statement.execute(
          """
        CREATE TABLE inventory (
          book_id UUID PRIMARY KEY,
          quantity INTEGER,
          CONSTRAINT fk_inventory_book
            FOREIGN KEY (book_id)
            REFERENCES books(id)
            ON DELETE CASCADE
        )
      """);
    }
  }
}
