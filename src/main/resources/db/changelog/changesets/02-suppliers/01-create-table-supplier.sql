-- liquibase formatted sql
-- changeset abishek.aryal:supplier-create-v1      context:dev
-- preconditions onFail:CONTINUE onError:HALT
CREATE TABLE IF NOT EXISTS`supplier`
(
    id               INT             AUTO_INCREMENT NOT NULL,
    name             VARCHAR(255)    NOT NULL,
    email            VARCHAR(255)    NOT NULL,
    phone            VARCHAR(255)    NOT NULL,
    address          VARCHAR(255)    NOT NULL,
    created_at        TIMESTAMP   NOT NULL,
    updated_at        TIMESTAMP   NOT NULL,
    is_deleted      BOOLEAN     DEFAULT FALSE,
    CONSTRAINT pk_suppliers PRIMARY KEY(id)

    );
