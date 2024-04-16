CREATE TABLE "users" (
  "id" BIGSERIAL PRIMARY KEY NOT NULL,
  "email" varchar(255) UNIQUE NOT NULL,
  "password" varchar(255) NOT NULL,
  "name" varchar(255) NOT NULL,
  "gender" varchar(10),
  "phone" varchar(20) UNIQUE NOT NULL,
  "date_of_birth" timestamp NOT NULL,
  "province_id" bigint,
  "district_id" bigint,
  "ward_id" bigint,
  "favorite_theater_id" bigint,
  "is_first_login" boolean DEFAULT true,
  "is_active" boolean NOT NULL DEFAULT true,
  "created_at" timestamp NOT NULL DEFAULT (now()),
  "updated_at" bigint NOT NULL DEFAULT (now())
);

CREATE TABLE "roles" (
  "id" BIGSERIAL PRIMARY KEY NOT NULL,
  "role_name" varchar(100) NOT NULL,
  "is_active" boolean NOT NULL DEFAULT true,
  "created_at" timestamp NOT NULL DEFAULT (now()),
  "updated_at" timestamp NOT NULL DEFAULT (now())
);

CREATE TABLE "user_role" (
  "id" BIGSERIAL PRIMARY KEY NOT NULL,
  "user_id" bigint NOT NULL,
  "role_id" bigint NOT NULL,
  "is_active" boolean NOT NULL DEFAULT true,
  "created_at" timestamp NOT NULL DEFAULT (now()),
  "updated_at" timestamp NOT NULL DEFAULT (now())
);

CREATE TABLE "refresh_tokens" (
  "id" BIGSERIAL PRIMARY KEY NOT NULL,
  "user_id" bigint NOT NULL,
  "refresh_token" text NOT NULL,
  "is_active" boolean NOT NULL DEFAULT true,
  "created_at" timestamp NOT NULL DEFAULT (now()),
  "updated_at" timestamp NOT NULL DEFAULT (now())
);

CREATE TABLE "provinces" (
  "id" BIGSERIAL PRIMARY KEY NOT NULL,
  "province_name" nvarchar(100) NOT NULL,
  "is_active" boolean NOT NULL DEFAULT true,
  "created_at" timestamp NOT NULL DEFAULT (now()),
  "updated_at" timestamp NOT NULL DEFAULT (now())
);

CREATE TABLE "districts" (
  "id" BIGSERIAL PRIMARY KEY NOT NULL,
  "district_name" nvarchar(100) NOT NULL,
  "province_id" bigint,
  "is_active" boolean NOT NULL DEFAULT true,
  "created_at" timestamp NOT NULL DEFAULT (now()),
  "updated_at" timestamp NOT NULL DEFAULT (now())
);

CREATE TABLE "wards" (
  "id" BIGSERIAL PRIMARY KEY NOT NULL,
  "ward_name" nvarchar(100) NOT NULL,
  "district_id" bigint,
  "is_active" boolean NOT NULL DEFAULT true,
  "created_at" timestamp NOT NULL DEFAULT (now()),
  "updated_at" timestamp NOT NULL DEFAULT (now())
);

CREATE TABLE "movie_genres" (
  "id" BIGSERIAL PRIMARY KEY NOT NULL,
  "genre" varchar(100) NOT NULL,
  "is_active" boolean NOT NULL DEFAULT true,
  "created_at" timestamp NOT NULL DEFAULT (now()),
  "updated_at" timestamp NOT NULL DEFAULT (now())
);

CREATE TABLE "movies" (
  "id" BIGSERIAL PRIMARY KEY NOT NULL,
  "name" varchar(100) NOT NULL,
  "director" nvarchar(100) NOT NULL,
  "casts" text NOT NULL,
  "premiere" datetime NOT NULL,
  "duration" int NOT NULL,
  "language" nvarchar(255) NOT NULL,
  "age_rated" int NOT NULL,
  "description" text,
  "trailer" varchar(255),
  "genre_id" bigint,
  "is_active" boolean NOT NULL DEFAULT true,
  "created_at" timestamp NOT NULL DEFAULT (now()),
  "updated_at" timestamp NOT NULL DEFAULT (now())
);

CREATE TABLE "cinemas" (
  "id" BIGSERIAL PRIMARY KEY NOT NULL,
  "cinema_name" nvarchar(100) NOT NULL,
  "province_id" bigint NOT NULL,
  "district_id" bigint NOT NULL,
  "ward_id" bigint NOT NULL,
  "is_active" boolean NOT NULL DEFAULT true,
  "created_at" timestamp NOT NULL DEFAULT (now()),
  "updated_at" timestamp NOT NULL DEFAULT (now())
);

CREATE TABLE "rooms" (
  "id" BIGSERIAL PRIMARY KEY NOT NULL,
  "room_code" varchar(10) NOT NULL,
  "cinema_id" bigint NOT NULL,
  "room_type" varchar(50) NOT NULL,
  "is_active" boolean NOT NULL DEFAULT true,
  "created_at" timestamp NOT NULL DEFAULT (now()),
  "updated_at" timestamp NOT NULL DEFAULT (now())
);

CREATE TABLE "seats" (
  "id" BIGSERIAL PRIMARY KEY NOT NULL,
  "seat_code" varchar(3) NOT NULL,
  "seat_type" nvarchar(50) NOT NULL,
  "is_status" bool NOT NULL DEFAULT false,
  "room_id" bigint,
  "price" long NOT NULL,
  "is_active" boolean NOT NULL DEFAULT true,
  "created_at" timestamp NOT NULL DEFAULT (now()),
  "updated_at" timestamp NOT NULL DEFAULT (now())
);

CREATE TABLE "movie_screens" (
  "id" BIGSERIAL PRIMARY KEY NOT NULL,
  "movie_id" bigint NOT NULL,
  "cinema_id" bigint NOT NULL,
  "room_id" bigint NOT NULL,
  "showtime" datetime NOT NULL,
  "is_active" boolean NOT NULL DEFAULT true,
  "created_at" timestamp NOT NULL DEFAULT (now()),
  "updated_at" timestamp NOT NULL DEFAULT (now())
);

CREATE TABLE "ticket_orders" (
  "id" BIGSERIAL PRIMARY KEY NOT NULL,
  "user_id" bigint NOT NULL,
  "movie_showtimes" bigint NOT NULL,
  "seat_id" bigint NOT NULL,
  "is_payment" boolean NOT NULL DEFAULT false,
  "discount_code" bigint,
  "discount_amount" bigint,
  "is_active" boolean NOT NULL DEFAULT true,
  "created_at" timestamp NOT NULL DEFAULT (now()),
  "updated_at" timestamp NOT NULL DEFAULT (now())
);

CREATE TABLE "discounts" (
  "id" BIGSERIAL PRIMARY KEY NOT NULL,
  "discount_code" nvarchar(50) NOT NULL,
  "quantity" int,
  "expiration_date" timestamp,
  "discount_percentage" int NOT NULL,
  "max_discount_amount" bigint NOT NULL,
  "is_active" boolean NOT NULL DEFAULT true,
  "created_at" timestamp NOT NULL DEFAULT (now()),
  "updated_at" timestamp NOT NULL DEFAULT (now())
);

CREATE TABLE "payment_historys" (
  "id" BIGSERIAL PRIMARY KEY NOT NULL,
  "order_id" bigint NOT NULL,
  "is_active" boolean NOT NULL DEFAULT true,
  "created_at" timestamp NOT NULL DEFAULT (now()),
  "updated_at" timestamp NOT NULL DEFAULT (now())
);

CREATE TABLE "employee_schedule" (
  "id" BIGSERIAL PRIMARY KEY NOT NULL,
  "employee_id" bigint,
  "cinema_id" bigint,
  "work_date" datetime,
  "work_time_id" bigint,
  "is_active" boolean NOT NULL DEFAULT true,
  "created_at" timestamp NOT NULL DEFAULT (now()),
  "updated_at" timestamp NOT NULL DEFAULT (now())
);

CREATE TABLE "work_shifts" (
  "id" BIGSERIAL PRIMARY KEY NOT NULL,
  "start_time" time NOT NULL,
  "end_time" time NOT NULL,
  "is_active" boolean NOT NULL DEFAULT true,
  "created_at" timestamp NOT NULL DEFAULT (now()),
  "updated_at" timestamp NOT NULL DEFAULT (now())
);

ALTER TABLE "users" ADD FOREIGN KEY ("province_id") REFERENCES "provinces" ("id");

ALTER TABLE "users" ADD FOREIGN KEY ("district_id") REFERENCES "districts" ("id");

ALTER TABLE "users" ADD FOREIGN KEY ("ward_id") REFERENCES "wards" ("id");

ALTER TABLE "users" ADD FOREIGN KEY ("favorite_theater_id") REFERENCES "cinemas" ("id");

ALTER TABLE "user_role" ADD FOREIGN KEY ("user_id") REFERENCES "users" ("id");

ALTER TABLE "user_role" ADD FOREIGN KEY ("role_id") REFERENCES "roles" ("id");

ALTER TABLE "refresh_tokens" ADD FOREIGN KEY ("user_id") REFERENCES "users" ("id");

ALTER TABLE "districts" ADD FOREIGN KEY ("province_id") REFERENCES "provinces" ("id");

ALTER TABLE "wards" ADD FOREIGN KEY ("district_id") REFERENCES "districts" ("id");

ALTER TABLE "movies" ADD FOREIGN KEY ("genre_id") REFERENCES "movie_genres" ("id");

ALTER TABLE "cinemas" ADD FOREIGN KEY ("province_id") REFERENCES "provinces" ("id");

ALTER TABLE "cinemas" ADD FOREIGN KEY ("district_id") REFERENCES "districts" ("id");

ALTER TABLE "cinemas" ADD FOREIGN KEY ("ward_id") REFERENCES "wards" ("id");

ALTER TABLE "rooms" ADD FOREIGN KEY ("cinema_id") REFERENCES "cinemas" ("id");

ALTER TABLE "seats" ADD FOREIGN KEY ("room_id") REFERENCES "rooms" ("id");

ALTER TABLE "movie_screens" ADD FOREIGN KEY ("movie_id") REFERENCES "movies" ("id");

ALTER TABLE "movie_screens" ADD FOREIGN KEY ("cinema_id") REFERENCES "cinemas" ("id");

ALTER TABLE "movie_screens" ADD FOREIGN KEY ("room_id") REFERENCES "rooms" ("id");

ALTER TABLE "ticket_orders" ADD FOREIGN KEY ("user_id") REFERENCES "users" ("id");

ALTER TABLE "ticket_orders" ADD FOREIGN KEY ("movie_showtimes") REFERENCES "movie_screens" ("id");

ALTER TABLE "ticket_orders" ADD FOREIGN KEY ("seat_id") REFERENCES "seats" ("id");

ALTER TABLE "ticket_orders" ADD FOREIGN KEY ("discount_code") REFERENCES "discounts" ("id");

ALTER TABLE "payment_historys" ADD FOREIGN KEY ("order_id") REFERENCES "ticket_orders" ("id");

ALTER TABLE "employee_schedule" ADD FOREIGN KEY ("employee_id") REFERENCES "users" ("id");

ALTER TABLE "employee_schedule" ADD FOREIGN KEY ("cinema_id") REFERENCES "cinemas" ("id");

ALTER TABLE "employee_schedule" ADD FOREIGN KEY ("work_time_id") REFERENCES "work_shifts" ("id");
