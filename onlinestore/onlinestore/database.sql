CREATE DATABASE IF NOT EXISTS onlinestore;
USE onlinestore;

-- Products table
CREATE TABLE products (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(255) NOT NULL,
    description TEXT,
    price DECIMAL(10,2) NOT NULL,
    image_url VARCHAR(500),
    stock_quantity INT DEFAULT 0,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);

-- Cart table
CREATE TABLE cart (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    product_id BIGINT,
    quantity INT DEFAULT 1,
    session_id VARCHAR(100),
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    FOREIGN KEY (product_id) REFERENCES products(id)
);

-- Orders table
CREATE TABLE orders (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    customer_name VARCHAR(255) NOT NULL,
    customer_email VARCHAR(255) NOT NULL,
    total_amount DECIMAL(10,2) NOT NULL,
    status VARCHAR(50) DEFAULT 'pending',
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);

-- Order items table
CREATE TABLE order_items (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    order_id BIGINT,
    product_id BIGINT,
    quantity INT,
    price DECIMAL(10,2),
    FOREIGN KEY (order_id) REFERENCES orders(id),
    FOREIGN KEY (product_id) REFERENCES products(id)
);

-- Sample data
INSERT INTO products (name, description, price, image_url, stock_quantity) VALUES
('MacBook Pro', '16-inch MacBook Pro with M3 chip', 2399.99, 'https://via.placeholder.com/300x200?text=MacBook+Pro', 15),
('iPhone 15', 'Latest iPhone with A17 chip', 999.99, 'https://via.placeholder.com/300x200?text=iPhone+15', 25),
('AirPods Pro', 'Wireless noise-cancelling earbuds', 249.99, 'https://via.placeholder.com/300x200?text=AirPods+Pro', 30),
('iPad Air', '10.9-inch iPad Air with M1 chip', 599.99, 'https://via.placeholder.com/300x200?text=iPad+Air', 20),
('Apple Watch', 'Series 9 GPS + Cellular', 429.99, 'https://via.placeholder.com/300x200?text=Apple+Watch', 18);