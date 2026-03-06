#!/bin/bash
set -e

psql -v ON_ERROR_STOP=1 --username "$POSTGRES_USER" --d "postgres" <<-EOSQL

    -- =====================
    -- Create Databases
    -- =====================
    CREATE DATABASE auth_db;
    CREATE DATABASE user_db;
    CREATE DATABASE product_db;
    CREATE DATABASE order_db;
    CREATE DATABASE payment_db;

    -- notification_service and core_service are database-less
    -- configuration_service uses Git/filesystem, no DB needed

EOSQL

psql -v ON_ERROR_STOP=1 --username "$POSTGRES_USER" --d "postgres" <<-EOSQL

    CREATE USER auth_user WITH PASSWORD 'auth_pass';
    CREATE USER user_user WITH PASSWORD 'user_pass';
    CREATE USER product_user WITH PASSWORD 'product_pass';
    CREATE USER order_user WITH PASSWORD 'order_pass';
    CREATE USER payment_user WITH PASSWORD 'payment_pass';

EOSQL

psql -v ON_ERROR_STOP=1 --username "$POSTGRES_USER" -d auth_db <<-EOSQL
    GRANT ALL PRIVILEGES ON DATABASE auth_db TO auth_user;
    GRANT ALL PRIVILEGES ON SCHEMA public TO auth_user;
EOSQL

psql -v ON_ERROR_STOP=1 --username "$POSTGRES_USER" -d user_db <<-EOSQL
    GRANT ALL PRIVILEGES ON DATABASE user_db TO user_user;
    GRANT ALL PRIVILEGES ON SCHEMA public TO user_user;
EOSQL

psql -v ON_ERROR_STOP=1 --username "$POSTGRES_USER" -d product_db <<-EOSQL
    GRANT ALL PRIVILEGES ON DATABASE product_db TO product_user;
    GRANT ALL PRIVILEGES ON SCHEMA public TO product_user;
EOSQL

psql -v ON_ERROR_STOP=1 --username "$POSTGRES_USER" -d order_db <<-EOSQL
    GRANT ALL PRIVILEGES ON DATABASE order_db TO order_user;
    GRANT ALL PRIVILEGES ON SCHEMA public TO order_user;
EOSQL

psql -v ON_ERROR_STOP=1 --username "$POSTGRES_USER" -d payment_db <<-EOSQL
    GRANT ALL PRIVILEGES ON DATABASE payment_db TO payment_user;
    GRANT ALL PRIVILEGES ON SCHEMA public TO payment_user;
EOSQL
