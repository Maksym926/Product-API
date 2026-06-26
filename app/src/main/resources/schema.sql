CREATE TABLE IF NOT EXISTS products (
    id VARCHAR(255) PRIMARY KEY DEFAULT gen_random_uuid()::text,
    name VARCHAR(255)   NOT NULL,
    description TEXT,
    brand VARCHAR(255),
    category VARCHAR(255),
    price BIGINT,
    product_available BOOLEAN DEFAULT true,
    stock_quantity BIGINT DEFAULT 0
);