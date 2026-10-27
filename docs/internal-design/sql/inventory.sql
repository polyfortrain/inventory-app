CREATE TABLE inventory (
    id INTEGER GENERATED ALWAYS AS IDENTITY,
    name VARCHAR(100) NOT NULL,
    quantity INTEGER NOT NULL,

    CONSTRAINT pk_inventory
        PRIMARY KEY (id),

    CONSTRAINT chk_inventory_name_not_blank
        CHECK (name ~ '[^[:space:]]'),

    CONSTRAINT chk_inventory_quantity_range
        CHECK (quantity BETWEEN 0 AND 999999)
);