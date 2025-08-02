-- Insert data into category table
insert into category (id, name, description) values (1, 'Electronics', 'Devices and gadgets');
insert into category (id, name, description) values (51, 'Clothing', 'Apparel and accessories');
insert into category (id, name, description) values (101, 'Books', 'Printed and digital books');
insert into category (id, name, description) values (151, 'Home & Kitchen', 'Appliances and utensils');
insert into category (id, name, description) values (201, 'Sports', 'Sporting goods and accessories');
insert into category (id, name, description) values (251, 'Beauty', 'Cosmetics and skincare');
insert into category (id, name, description) values (301, 'Toys', 'Toys and games for children');
insert into category (id, name, description) values (351, 'Furniture', 'Home and office furniture');
insert into category (id, name, description) values (401, 'Groceries', 'Everyday food and essentials');
insert into category (id, name, description) values (451, 'Automotive', 'Car accessories and tools');

-- Insert data into product table
insert into product (id, name, description, available_quantity, price, category_id)
values (1, 'Smartphone', 'Latest model with advanced features', 100, 699.99, 1);

insert into product (id, name, description, available_quantity, price, category_id)
values (51, 'T-Shirt', 'Cotton t-shirt with print', 500, 19.99, 51);

insert into product (id, name, description, available_quantity, price, category_id)
values (101, 'Science Fiction Novel', 'Popular sci-fi book', 200, 12.49, 101);

insert into product (id, name, description, available_quantity, price, category_id)
values (151, 'Blender', 'High-speed kitchen blender', 150, 89.99, 151);

insert into product (id, name, description, available_quantity, price, category_id)
values (201, 'Football', 'Standard size outdoor football', 250, 29.95, 201);

insert into product (id, name, description, available_quantity, price, category_id)
values (251, 'Lipstick', 'Long-lasting matte lipstick', 300, 15.75, 251);

insert into product (id, name, description, available_quantity, price, category_id)
values (301, 'Lego Set', 'Creative building blocks for kids', 180, 59.99, 301);

insert into product (id, name, description, available_quantity, price, category_id)
values (351, 'Office Chair', 'Ergonomic mesh back chair', 80, 139.00, 351);

insert into product (id, name, description, available_quantity, price, category_id)
values (401, 'Organic Rice', '5kg bag of organic basmati rice', 400, 22.49, 401);

insert into product (id, name, description, available_quantity, price, category_id)
values (451, 'Car Vacuum Cleaner', 'Portable cleaner for car interiors', 120, 34.95, 451);
