ALTER TABLE "users"
    ADD FOREIGN KEY ("province_id") REFERENCES "provinces" ("id");

ALTER TABLE "users"
    ADD FOREIGN KEY ("district_id") REFERENCES "districts" ("id");

ALTER TABLE "users"
    ADD FOREIGN KEY ("ward_id") REFERENCES "wards" ("id");

ALTER TABLE "users"
    ADD FOREIGN KEY ("favorite_theater_id") REFERENCES "cinemas" ("id");

ALTER TABLE "user_role"
    ADD FOREIGN KEY ("user_id") REFERENCES "users" ("id");

ALTER TABLE "user_role"
    ADD FOREIGN KEY ("role_id") REFERENCES "roles" ("id");

ALTER TABLE "refresh_tokens"
    ADD FOREIGN KEY ("user_id") REFERENCES "users" ("id");

ALTER TABLE "districts"
    ADD FOREIGN KEY ("province_id") REFERENCES "provinces" ("id");

ALTER TABLE "wards"
    ADD FOREIGN KEY ("district_id") REFERENCES "districts" ("id");

ALTER TABLE "movies"
    ADD FOREIGN KEY ("genre_id") REFERENCES "movie_genres" ("id");

ALTER TABLE "cinemas"
    ADD FOREIGN KEY ("province_id") REFERENCES "provinces" ("id");

ALTER TABLE "cinemas"
    ADD FOREIGN KEY ("district_id") REFERENCES "districts" ("id");

ALTER TABLE "cinemas"
    ADD FOREIGN KEY ("ward_id") REFERENCES "wards" ("id");

ALTER TABLE "rooms"
    ADD FOREIGN KEY ("cinema_id") REFERENCES "cinemas" ("id");

ALTER TABLE "seats"
    ADD FOREIGN KEY ("room_id") REFERENCES "rooms" ("id");

ALTER TABLE "movie_time"
    ADD FOREIGN KEY ("movie_id") REFERENCES "movies" ("id");

ALTER TABLE "movie_time"
    ADD FOREIGN KEY ("cinema_id") REFERENCES "cinemas" ("id");

ALTER TABLE "movie_time"
    ADD FOREIGN KEY ("room_id") REFERENCES "rooms" ("id");

ALTER TABLE "ticket_orders"
    ADD FOREIGN KEY ("user_id") REFERENCES "users" ("id");

ALTER TABLE "ticket_orders"
    ADD FOREIGN KEY ("movie_showtimes") REFERENCES "movie_time" ("id");

ALTER TABLE "ticket_orders"
    ADD FOREIGN KEY ("seat_id") REFERENCES "seats" ("id");

ALTER TABLE "ticket_orders"
    ADD FOREIGN KEY ("discount_code") REFERENCES "discounts" ("id");

ALTER TABLE "payment_historys"
    ADD FOREIGN KEY ("order_id") REFERENCES "ticket_orders" ("id");

ALTER TABLE "employee_schedule"
    ADD FOREIGN KEY ("employee_id") REFERENCES "users" ("id");

ALTER TABLE "employee_schedule"
    ADD FOREIGN KEY ("cinema_id") REFERENCES "cinemas" ("id");

ALTER TABLE "employee_schedule"
    ADD FOREIGN KEY ("work_time_id") REFERENCES "work_shifts" ("id");
