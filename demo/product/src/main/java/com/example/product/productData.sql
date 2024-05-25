-- Insert parts
INSERT INTO part (name, tray_Id, description)
VALUES ('Small Wheel', 1, 'Wheel used for small skateboard'),
       ('Small Truck', 2, 'Truck used for small skateboard'),
       ('Small Board', 3, 'Board used for small skateboard'),
       ('Medium Wheel', 4, 'Wheel used for medium skateboard'),
       ('Medium Truck', 5, 'Truck used for medium skateboard'),
       ('Medium Board', 6, 'Board used for medium skateboard'),
       ('Large Wheel', 7, 'Wheel used for large skateboard'),
       ('Large Truck', 8, 'Truck used for large skateboard'),
       ('Large Board', 9, 'Board used for large skateboard'),
       ('Wheel Bearing', 10, 'Bearing used in skateboard wheels');

-- Insert recipes
INSERT INTO recipe (product_name)
VALUES ('Small skateboard'),
       ('Medium skateboard'),
       ('Large skateboard');

-- Insert products and link them to recipe IDs
INSERT INTO product (name, description,recipe_id)
VALUES ('Small skateboard', 'A small-sized skateboard suitable for children',1),
       ('Medium skateboard', 'A medium-sized skateboard',2),
       ('Large skateboard', 'A large-sized skateboard',3);





-- Insert recipe parts
INSERT INTO recipe_part (recipe_id, part_id, quantity)
VALUES (1, 1, 4),  -- 4 Small Wheels for a Small skateboard
       (1, 2, 2),  -- 2 Small Trucks for a Small skateboard
       (1, 3, 1),  -- 1 Small Board for a Small skateboard
       (1, 10, 8), -- 8 Wheel Bearings for a Small skateboard
       (2, 4, 4),  -- 4 Medium Wheels for a Medium skateboard
       (2, 5, 2),  -- 2 Medium Trucks for a Medium skateboard
       (2, 6, 1),  -- 1 Medium Board for a Medium skateboard
       (2, 10, 8), -- 8 Wheel Bearings for a Medium skateboard
       (3, 7, 4),  -- 4 Large Wheels for a Large skateboard
       (3, 8, 2),  -- 2 Large Trucks for a Large skateboard
       (3, 9, 1),  -- 1 Large Board for a Large skateboard
       (3, 10, 8); -- 8 Wheel Bearings for a Large skateboard
