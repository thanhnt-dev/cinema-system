CREATE TABLE "users"
(
    "id"                  BIGSERIAL PRIMARY KEY NOT NULL,
    "email"               varchar(255) UNIQUE   NOT NULL,
    "password"            varchar(255)          NOT NULL,
    "name"                varchar(255)          NOT NULL,
    "gender"              varchar(10),
    "phone"               varchar(20) UNIQUE    NOT NULL,
    "date_of_birth"       bigint                NOT NULL,
    "province_id"         bigint,
    "district_id"         bigint,
    "ward_id"             bigint,
    "favorite_theater_id" bigint,
    "is_first_login"      boolean                        DEFAULT true,
    "is_active"           boolean               NOT NULL DEFAULT true,
    "created_at"          bigint                NOT NULL DEFAULT (EXTRACT(epoch FROM now()) * 1000::numeric),
    "updated_at"          bigint                NOT NULL DEFAULT (EXTRACT(epoch FROM now()) * 1000::numeric)
);

CREATE TABLE "roles"
(
    "id"         BIGSERIAL PRIMARY KEY NOT NULL,
    "role_name"  varchar(100)          NOT NULL,
    "is_active"  boolean               NOT NULL DEFAULT true,
    "created_at" bigint                NOT NULL DEFAULT (EXTRACT(epoch FROM now()) * 1000::numeric),
    "updated_at" bigint                NOT NULL DEFAULT (EXTRACT(epoch FROM now()) * 1000::numeric)
);

CREATE TABLE "user_role"
(
    "id"         BIGSERIAL PRIMARY KEY NOT NULL,
    "user_id"    bigint                NOT NULL,
    "role_id"    bigint                NOT NULL,
    "is_active"  boolean               NOT NULL DEFAULT true,
    "created_at" bigint                NOT NULL DEFAULT (EXTRACT(epoch FROM now()) * 1000::numeric),
    "updated_at" bigint                NOT NULL DEFAULT (EXTRACT(epoch FROM now()) * 1000::numeric)
);

CREATE TABLE "refresh_tokens"
(
    "id"            BIGSERIAL PRIMARY KEY NOT NULL,
    "user_id"       bigint                NOT NULL,
    "refresh_token" text                  NOT NULL,
    "is_active"     boolean               NOT NULL DEFAULT true,
    "created_at"    bigint                NOT NULL DEFAULT (EXTRACT(epoch FROM now()) * 1000::numeric),
    "updated_at"    bigint                NOT NULL DEFAULT (EXTRACT(epoch FROM now()) * 1000::numeric)
);

CREATE TABLE "provinces"
(
    "id"            BIGSERIAL PRIMARY KEY NOT NULL,
    "province_name" varchar(100)          NOT NULL,
    "is_active"     boolean               NOT NULL DEFAULT true,
    "created_at"    bigint                NOT NULL DEFAULT (EXTRACT(epoch FROM now()) * 1000::numeric),
    "updated_at"    bigint                NOT NULL DEFAULT (EXTRACT(epoch FROM now()) * 1000::numeric)
);

CREATE TABLE "districts"
(
    "id"            BIGSERIAL PRIMARY KEY NOT NULL,
    "district_name" varchar(100)          NOT NULL,
    "province_id"   bigint,
    "is_active"     boolean               NOT NULL DEFAULT true,
    "created_at"    bigint                NOT NULL DEFAULT (EXTRACT(epoch FROM now()) * 1000::numeric),
    "updated_at"    bigint                NOT NULL DEFAULT (EXTRACT(epoch FROM now()) * 1000::numeric)
);

CREATE TABLE "wards"
(
    "id"          BIGSERIAL PRIMARY KEY NOT NULL,
    "ward_name"   varchar(100)          NOT NULL,
    "district_id" bigint,
    "is_active"   boolean               NOT NULL DEFAULT true,
    "created_at"  bigint                NOT NULL DEFAULT (EXTRACT(epoch FROM now()) * 1000::numeric),
    "updated_at"  bigint                NOT NULL DEFAULT (EXTRACT(epoch FROM now()) * 1000::numeric)
);

CREATE TABLE "movie_genres"
(
    "id"         BIGSERIAL PRIMARY KEY NOT NULL,
    "genre"      varchar(100)          NOT NULL,
    "is_active"  boolean               NOT NULL DEFAULT true,
    "created_at" bigint                NOT NULL DEFAULT (EXTRACT(epoch FROM now()) * 1000::numeric),
    "updated_at" bigint                NOT NULL DEFAULT (EXTRACT(epoch FROM now()) * 1000::numeric)
);

CREATE TABLE "movies"
(
    "id"          BIGSERIAL PRIMARY KEY NOT NULL,
    "name"        varchar(100)          NOT NULL,
    "director"    varchar(100)          NOT NULL,
    "casts"       text                  NOT NULL,
    "premiere"    bigint                NOT NULL,
    "duration"    int                   NOT NULL,
    "language"    varchar(255)          NOT NULL,
    "age_rated"   int                   NOT NULL,
    "description" text,
    "trailer"     varchar(255),
    "genre_id"    bigint,
    "is_active"   boolean               NOT NULL DEFAULT true,
    "created_at"  bigint                NOT NULL DEFAULT (EXTRACT(epoch FROM now()) * 1000::numeric),
    "updated_at"  bigint                NOT NULL DEFAULT (EXTRACT(epoch FROM now()) * 1000::numeric)
);

CREATE TABLE "cinemas"
(
    "id"          BIGSERIAL PRIMARY KEY NOT NULL,
    "cinema_name" varchar(100)          NOT NULL,
    "province_id" bigint                NOT NULL,
    "district_id" bigint                NOT NULL,
    "ward_id"     bigint                NOT NULL,
    "is_active"   boolean               NOT NULL DEFAULT true,
    "created_at"  bigint                NOT NULL DEFAULT (EXTRACT(epoch FROM now()) * 1000::numeric),
    "updated_at"  bigint                NOT NULL DEFAULT (EXTRACT(epoch FROM now()) * 1000::numeric)
);

CREATE TABLE "rooms"
(
    "id"         BIGSERIAL PRIMARY KEY NOT NULL,
    "room_code"  varchar(10)           NOT NULL,
    "cinema_id"  bigint                NOT NULL,
    "room_type"  varchar(50)           NOT NULL,
    "is_active"  boolean               NOT NULL DEFAULT true,
    "created_at" bigint                NOT NULL DEFAULT (EXTRACT(epoch FROM now()) * 1000::numeric),
    "updated_at" bigint                NOT NULL DEFAULT (EXTRACT(epoch FROM now()) * 1000::numeric)
);

CREATE TABLE "seats"
(
    "id"         BIGSERIAL PRIMARY KEY NOT NULL,
    "seat_code"  varchar(3)            NOT NULL,
    "seat_type"  varchar(50)           NOT NULL,
    "is_blocked" bool                  NOT NULL DEFAULT false,
    "room_id"    bigint,
    "price"      bigint                NOT NULL,
    "is_active"  boolean               NOT NULL DEFAULT true,
    "created_at" bigint                NOT NULL DEFAULT (EXTRACT(epoch FROM now()) * 1000::numeric),
    "updated_at" bigint                NOT NULL DEFAULT (EXTRACT(epoch FROM now()) * 1000::numeric)
);

CREATE TABLE "movie_time"
(
    "id"         BIGSERIAL PRIMARY KEY NOT NULL,
    "movie_id"   bigint                NOT NULL,
    "cinema_id"  bigint                NOT NULL,
    "room_id"    bigint                NOT NULL,
    "showtime"   bigint                NOT NULL,
    "is_active"  boolean               NOT NULL DEFAULT true,
    "created_at" bigint                NOT NULL DEFAULT (EXTRACT(epoch FROM now()) * 1000::numeric),
    "updated_at" bigint                NOT NULL DEFAULT (EXTRACT(epoch FROM now()) * 1000::numeric)
);

CREATE TABLE "ticket_orders"
(
    "id"              BIGSERIAL PRIMARY KEY NOT NULL,
    "user_id"         bigint                NOT NULL,
    "movie_showtimes" bigint                NOT NULL,
    "seat_id"         bigint                NOT NULL,
    "is_payment"      boolean               NOT NULL DEFAULT false,
    "discount_code"   bigint,
    "discount_amount" bigint,
    "is_active"       boolean               NOT NULL DEFAULT true,
    "created_at"      bigint                NOT NULL DEFAULT (EXTRACT(epoch FROM now()) * 1000::numeric),
    "updated_at"      bigint                NOT NULL DEFAULT (EXTRACT(epoch FROM now()) * 1000::numeric)
);

CREATE TABLE "discounts"
(
    "id"                  BIGSERIAL PRIMARY KEY NOT NULL,
    "discount_code"       varchar(50)           NOT NULL,
    "quantity"            int,
    "expiration_date"     bigint,
    "discount_percentage" double precision      NOT NULL,
    "max_discount_amount" bigint                NOT NULL,
    "is_active"           boolean               NOT NULL DEFAULT true,
    "created_at"          bigint                NOT NULL DEFAULT (EXTRACT(epoch FROM now()) * 1000::numeric),
    "updated_at"          bigint                NOT NULL DEFAULT (EXTRACT(epoch FROM now()) * 1000::numeric)
);

CREATE TABLE "payment_historys"
(
    "id"         BIGSERIAL PRIMARY KEY NOT NULL,
    "order_id"   bigint                NOT NULL,
    "is_active"  boolean               NOT NULL DEFAULT true,
    "created_at" bigint                NOT NULL DEFAULT (EXTRACT(epoch FROM now()) * 1000::numeric),
    "updated_at" bigint                NOT NULL DEFAULT (EXTRACT(epoch FROM now()) * 1000::numeric)
);

CREATE TABLE "employee_schedule"
(
    "id"           BIGSERIAL PRIMARY KEY NOT NULL,
    "employee_id"  bigint,
    "cinema_id"    bigint,
    "work_date"    bigint,
    "work_time_id" bigint,
    "is_active"    boolean               NOT NULL DEFAULT true,
    "created_at"   bigint                NOT NULL DEFAULT (EXTRACT(epoch FROM now()) * 1000::numeric),
    "updated_at"   bigint                NOT NULL DEFAULT (EXTRACT(epoch FROM now()) * 1000::numeric)
);

CREATE TABLE "work_shifts"
(
    "id"         BIGSERIAL PRIMARY KEY NOT NULL,
    "start_time" time                  NOT NULL,
    "end_time"   time                  NOT NULL,
    "is_active"  boolean               NOT NULL DEFAULT true,
    "created_at" bigint                NOT NULL DEFAULT (EXTRACT(epoch FROM now()) * 1000::numeric),
    "updated_at" bigint                NOT NULL DEFAULT (EXTRACT(epoch FROM now()) * 1000::numeric)
)
