DROP database case_std_bookstore_db;
CREATE DATABASE IF NOT EXISTS case_std_bookstore_db;
USE case_std_bookstore_db;

CREATE TABLE Account (
    account_id INT PRIMARY KEY AUTO_INCREMENT,
    username VARCHAR(50) UNIQUE NOT NULL,
    password VARCHAR(255) NOT NULL,
    role ENUM('CUSTOMER','ADMIN') DEFAULT 'CUSTOMER',
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);

CREATE TABLE Customer (
    customer_id INT PRIMARY KEY AUTO_INCREMENT,
    account_id INT UNIQUE,
    name VARCHAR(100),
    email VARCHAR(100) UNIQUE,
    phone VARCHAR(20),
    address VARCHAR(255),
    FOREIGN KEY (account_id) REFERENCES Account(account_id)
);

CREATE TABLE Author (
    author_id INT PRIMARY KEY AUTO_INCREMENT,
    name VARCHAR(150) UNIQUE NOT NULL,
    bio TEXT
);

CREATE TABLE Publisher (
    publisher_id INT PRIMARY KEY AUTO_INCREMENT,
    name VARCHAR(150) UNIQUE NOT NULL,
    address VARCHAR(255),
    phone VARCHAR(20)
);

CREATE TABLE Category (
    category_id INT PRIMARY KEY AUTO_INCREMENT,
    name VARCHAR(100) NOT NULL
);

CREATE TABLE Book (
    book_id INT PRIMARY KEY AUTO_INCREMENT,
    category_id INT,
    author_id INT NOT NULL,
    publisher_id INT NOT NULL,
    title VARCHAR(150) NOT NULL,
    description TEXT,
    price DECIMAL(10,2) NOT NULL,
    stock INT DEFAULT 0,
    image_url VARCHAR(255),

    FOREIGN KEY (category_id) REFERENCES Category(category_id),
    FOREIGN KEY (author_id) REFERENCES Author(author_id),
    FOREIGN KEY (publisher_id) REFERENCES Publisher(publisher_id)
);

CREATE TABLE Orders (
    order_id INT PRIMARY KEY AUTO_INCREMENT,
    customer_id INT,
    status ENUM('CART','PENDING','CONFIRMED','SHIPPED','COMPLETED','CANCELLED')
        DEFAULT 'CART',
    total DECIMAL(10,2),
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    confirmed_by INT,
    FOREIGN KEY (customer_id) REFERENCES Customer(customer_id),
    FOREIGN KEY (confirmed_by) REFERENCES Account(account_id)
);

CREATE TABLE OrderItem (
    order_item_id INT PRIMARY KEY AUTO_INCREMENT,
    order_id INT,
    book_id INT,
    quantity INT NOT NULL,
    price DECIMAL(10,2) NOT NULL,
    FOREIGN KEY (order_id) REFERENCES Orders(order_id),
    FOREIGN KEY (book_id) REFERENCES Book(book_id)
);

INSERT INTO publisher (name, address, phone) VALUES
                                                 ('NXB Trẻ', 'TP. Hồ Chí Minh', '02838201234'),
                                                 ('NXB Kim Đồng', 'Hà Nội', '02439434730'),
                                                 ('NXB Giáo Dục', 'Hà Nội', '02437471234'),
                                                 ('NXB Lao Động', 'Hà Nội', '02438522345'),
                                                 ('NXB Văn Học', 'TP. Hồ Chí Minh', '02839301234');
INSERT INTO category (name) VALUES
                                ('Văn học'),
                                ('Khoa học'),
                                ('Thiếu nhi'),
                                ('Công nghệ thông tin'),
                                ('Kinh tế');
INSERT INTO author (name, bio) VALUES
                                   ('Nguyễn Nhật Ánh', 'Nhà văn nổi tiếng với các tác phẩm dành cho thiếu nhi.'),
                                   ('Paulo Coelho', 'Nhà văn người Brazil, tác giả của "Nhà giả kim".'),
                                   ('Dale Carnegie', 'Chuyên gia về phát triển bản thân và giao tiếp.'),
                                   ('J.K. Rowling', 'Tác giả bộ truyện Harry Potter nổi tiếng thế giới.'),
                                   ('Robert C. Martin', 'Chuyên gia phần mềm, tác giả Clean Code.');

INSERT INTO Account (username, password, role) VALUES
('user01', 'password_hash_01', 'CUSTOMER'),
('user02', 'password_hash_02', 'CUSTOMER'),
('admin01', 'password_hash_03', 'ADMIN'),
('user03', 'password_hash_04', 'CUSTOMER'),
('user04', 'password_hash_05', 'CUSTOMER');

-- Sample data cho bảng Customer
INSERT INTO Customer (account_id, name, email, phone, address) VALUES
(1, 'Nguyen Van A', 'a.nguyen@gmail.com', '0901234567', 'Da Nang'),
(2, 'Tran Thi B', 'b.tran@gmail.com', '0912345678', 'Ha Noi'),
(4, 'Le Van C', 'c.le@gmail.com', '0923456789', 'Ho Chi Minh'),
(5, 'Pham Thi D', 'd.pham@gmail.com', '0934567890', 'Hue'),
(3, 'Admin User', 'admin@gmail.com', '0999999999', 'System Office');


