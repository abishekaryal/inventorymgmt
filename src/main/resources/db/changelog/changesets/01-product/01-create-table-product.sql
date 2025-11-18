-- liquibase formatted sql
-- changeset abishek.aryal:product-create-v1      context:dev
-- preconditions onFail:CONTINUE onError:HALT
CREATE TABLE IF NOT EXISTS`product`
(
    id                 INT             AUTO_INCREMENT NOT NULL,
    name               VARCHAR(255)    NOT NULL,
    category           VARCHAR(255)    NOT NULL,
    price              DOUBLE          NOT NULL,
    quantity           VARCHAR(255)    NOT NULL,
    supplier_id        INT             NOT NULL,
    created_at         TIMESTAMP       NOT NULL,
    updated_at         TIMESTAMP       NULL,
    is_deleted         BOOLEAN         DEFAULT FALSE,
    CONSTRAINT pk_product PRIMARY KEY(id)

    );

