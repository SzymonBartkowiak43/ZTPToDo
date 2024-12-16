INSERT INTO users (id, user_name, password)
VALUES (1000, 'test', '$2a$10$qJo.u0y4ibqFBAhmnGeqzOqw1yEvFgfjyyvDVGMAbkfUsRS20NYCq');

INSERT INTO category (id, name, user_id)
VALUES
    (110, 'Work', 1000),
    (211, 'Personal', 1000),
    (311, 'Shopping', 1000),
    (411, 'Fitness', 1000);

INSERT INTO task (id, title, description, status, category_id, user_id)
VALUES
    (1123, 'Complete project', 'Finish the final module', 'NEW', 110, 1000),
    (2123, 'Team meeting', 'Discuss project progress', 'IN_PROGRESS', 110, 1000),
    (3123, 'Buy groceries', 'Milk, bread, eggs', 'NEW', 311, 1000),
    (4123, 'Workout', '30-minute HIIT session', 'COMPLETED', 211,1000),
    (542, 'Call plumber', 'Fix kitchen sink', 'NEW', 411, 1000),
    (6124, 'Pay bills', 'Electricity and water', 'IN_PROGRESS', 211, 1000);
