package com.example.auth.db.migration;

import java.sql.Statement;
import org.flywaydb.core.api.migration.BaseJavaMigration;
import org.flywaydb.core.api.migration.Context;

public class V1__init_roles_permissions extends BaseJavaMigration {

  @Override
  public void migrate(Context context) throws Exception {

    try (Statement statement = context.getConnection().createStatement()) {

      // Enable UUID generation
      statement.execute(
          """
                CREATE EXTENSION IF NOT EXISTS "pgcrypto";
            """);

      // Create tables
      statement.execute(
          """
                CREATE TABLE IF NOT EXISTS roles (
                    id UUID PRIMARY KEY DEFAULT gen_random_uuid(),
                    name VARCHAR(50) UNIQUE NOT NULL
                )
            """);

      statement.execute(
          """
                CREATE TABLE IF NOT EXISTS permissions (
                    id UUID PRIMARY KEY DEFAULT gen_random_uuid(),
                    name VARCHAR(50) UNIQUE NOT NULL
                )
            """);

      statement.execute(
          """
                CREATE TABLE IF NOT EXISTS role_permissions (
                    role_id UUID REFERENCES roles(id),
                    permission_id UUID REFERENCES permissions(id),
                    PRIMARY KEY(role_id, permission_id)
                )
            """);

      // Insert permissions
      statement.execute(
          """
                INSERT INTO permissions(name)
                VALUES
                    ('READ'),
                    ('WRITE'),
                    ('DELETE'),
                    ('AUDIT_LOG')
                ON CONFLICT (name) DO NOTHING
            """);

      // Insert roles
      statement.execute(
          """
                INSERT INTO roles(name)
                VALUES
                    ('USER'),
                    ('ADMIN'),
                    ('SYSTEM_ADMIN')
                ON CONFLICT (name) DO NOTHING
            """);

      // USER → READ
      statement.execute(
          """
                INSERT INTO role_permissions(role_id, permission_id)
                SELECT r.id, p.id
                FROM roles r, permissions p
                WHERE r.name = 'USER'
                AND p.name = 'READ'
                ON CONFLICT DO NOTHING
            """);

      // ADMIN → READ, WRITE, DELETE
      statement.execute(
          """
                INSERT INTO role_permissions(role_id, permission_id)
                SELECT r.id, p.id
                FROM roles r, permissions p
                WHERE r.name = 'ADMIN'
                AND p.name IN ('READ','WRITE','DELETE')
                ON CONFLICT DO NOTHING
            """);

      // SYSTEM_ADMIN → ALL PERMISSIONS
      statement.execute(
          """
                INSERT INTO role_permissions(role_id, permission_id)
                SELECT r.id, p.id
                FROM roles r, permissions p
                WHERE r.name = 'SYSTEM_ADMIN'
                ON CONFLICT DO NOTHING
            """);
    }
  }
}
