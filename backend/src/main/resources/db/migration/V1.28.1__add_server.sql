CREATE TYPE server_status as enum ('PENDING', 'APPROVED', 'REJECTED');
CREATE TYPE server_verification_status as enum ('PENDING', 'APPROVED', 'REJECTED');

CREATE TABLE servers
(
    id                  bigserial                          NOT NULL
        CONSTRAINT server_pkey
            PRIMARY KEY,
    uuid                uuid                               NOT NULL
        CONSTRAINT server_uuid_pkey
            UNIQUE,
    name                varchar(255)                       NOT NULL,
    ip                  integer                            NOT NULL DEFAULT 25565,
    port                integer                            NOT NULL,
    description         text                               NOT NULL,
    banner_image        varchar(255)                       NOT NULL,
    logo_image          varchar(255)                       NOT NULL,
    tags                text[]                             NOT NULL DEFAULT ARRAY []::text[],
    website_url         varchar(255)                       NOT NULL,
    discord_url         varchar(255)                       NOT NULL,
    minecraft_version   varchar(50)                        NOT NULL,
    player_count        integer                            NOT NULL DEFAULT 0,
    owner_id            bigint                             NOT NULL
        CONSTRAINT servers_owner_id_fkey
            REFERENCES users(id)
            ON DELETE CASCADE,
    created_at          timestamp WITH TIME ZONE           NOT NULL DEFAULT now(),
    updated_at          timestamp WITH TIME ZONE           NOT NULL DEFAULT now(),
    status              server_status                      NOT NULL DEFAULT 'PENDING',
    CONSTRAINT servers_ip_port_unique
        UNIQUE (id, port)
);

CREATE INDEX idx_servers_owner_id
    ON servers (owner_id);

CREATE INDEX idx_servers_status
    ON servers (status);

CREATE INDEX idx_servers_minecraft_version
    ON servers (minecraft_version);

CREATE INDEX idx_servers_created_at
    ON servers (created_at);


CREATE TABLE server_verifications
(
    id                  bigserial                          NOT NULL
        CONSTRAINT server_verifications_pkey
            PRIMARY KEY,
    uuid                uuid                               NOT NULL
        CONSTRAINT server_verifications_uuid_pkey
            UNIQUE,
    server_id           bigint                             NOT NULL
        CONSTRAINT server_verifications_server_id_fkey
            REFERENCES servers(id)
            ON DELETE CASCADE,
    reviewer_id         bigint
        CONSTRAINT server_verifications_reviewer_id_fkey
            REFERENCES users(id)
            ON DELETE CASCADE,
    status              server_verification_status         NOT NULL DEFAULT 'PENDING',
    notes               text                               ,
    created_at          timestamp WITH TIME ZONE           NOT NULL DEFAULT now(),
    updated_at          timestamp WITH TIME ZONE           NOT NULL DEFAULT now()
);

CREATE INDEX idx_server_verifications_server_id
    ON server_verifications (server_id);

CREATE INDEX idx_server_verifications_status
    ON server_verifications (status);

CREATE INDEX idx_server_verifications_reviewer_id
    ON server_verifications (reviewer_id);
