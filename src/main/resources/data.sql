insert into user_name (user_id, created_date, create_order, e_mail, pass_word, pass_word_fb, pass_word_google, "role") values ('393ea4ff-5f28-4abb-8c02-f955b344a3d7', '2022-05-21 12:16:45.762', '2022-05-21 12:16:45.762', 'Admin', '$2a$10$PsXiejAY.pw3IcjMTwW8eeryHCvnTayrFaOOnKjTRC1POdGrLi4MS', '0', '0', 'ADMIN');

INSERT INTO public.expert_group_list
(expert_group_id, created_date, create_order, expert_path, expert_status)
VALUES('f0869a41-7efe-441f-904b-db8feb7c9bc9', '2022-05-12 13:31:14.227', '2022-05-12 13:31:14.227', 'หมอ', 1);
INSERT INTO public.expert_group_list
(expert_group_id, created_date, create_order, expert_path, expert_status)
VALUES('f2e1af53-8a96-44ff-98de-7f6cb741db01', '2022-05-12 13:31:27.755', '2022-05-12 13:31:27.755', 'วิศกร', 1);
INSERT INTO public.expert_group_list
(expert_group_id, created_date, create_order, expert_path, expert_status)
VALUES('31470ad5-7fe9-4b71-a931-bb53feb90c3e', '2022-05-12 13:31:31.349', '2022-05-12 13:31:31.349', 'โปรแกรมเมอร์', 1);
INSERT INTO public.expert_group_list
(expert_group_id, created_date, create_order, expert_path, expert_status)
VALUES('4cf383f5-d03c-49f0-98c6-0b4135253bde', '2022-05-12 15:04:47.266', '2022-05-12 15:04:47.266', 'ประยุทธ', 1);




INSERT INTO public.topic_group_list
(topic_group_id, created_date, create_order, topic_group_path, topic_group_status)
VALUES('1', '2022-05-12 16:09:39.813', '2022-05-12 16:09:39.813', 'สุขภาพ', 1);
INSERT INTO public.topic_group_list
(topic_group_id, created_date, create_order, topic_group_path, topic_group_status)
VALUES('2', '2022-05-12 16:09:43.286', '2022-05-12 16:09:43.286', 'การเงิน', 1);
INSERT INTO public.topic_group_list
(topic_group_id, created_date, create_order, topic_group_path, topic_group_status)
VALUES('3', '2022-05-12 16:09:46.309', '2022-05-12 16:09:46.309', 'การเมือง', 1);
INSERT INTO public.topic_group_list
(topic_group_id, created_date, create_order, topic_group_path, topic_group_status)
VALUES('4bbdbbb7-352d-4b72-917e-5c39f1ab8e28', '2022-05-16 17:50:26.964', '2022-05-16 17:50:26.964', 'การศึกษา', 1);
INSERT INTO public.topic_group_list
(topic_group_id, created_date, create_order, topic_group_path, topic_group_status)
VALUES('22bc1e84-77d9-4ec5-9b62-793e150be26a', '2022-05-16 17:49:17.161', '2022-05-16 17:49:17.161', 'เทคโนโลยี', 1);


INSERT INTO public.user_name
(user_id, created_date, create_order, e_mail, pass_word, pass_word_fb, pass_word_google, "role")
VALUES('425edbf8-c7bd-4141-8888-d1955449933d', '2022-07-09 12:32:42.997', '2022-07-09 12:32:42.997', 'Test1', '$2a$10$53bZhzVpo189Ds7eiguECuQez9I7.C61jH8iihXFF6.GB9NfUXmmS', '0', '0', 'USER');
INSERT INTO public.user_name
(user_id, created_date, create_order, e_mail, pass_word, pass_word_fb, pass_word_google, "role")
VALUES('a94d8787-3946-49ee-8518-28d6948a5c03', '2022-07-09 12:53:07.651', '2022-07-09 12:53:07.651', 'Test5', '$2a$10$tmQHzg5YCX6RvHoU6sgi2OQ1JFJY7Mm60V72E8u5cQ0Br4t0QGrpu', '0', '0', 'USER');

INSERT INTO public.user_info
(user_info_id, created_date, create_order, expert_group_id, first_name, last_name, like_count, live_link, price_call, price_video, profile_pic, tel_number, "token", token_count, user_caption, user_name, verify_status)
VALUES('425edbf8-c7bd-4141-8888-d1955449933d', '2022-07-09 12:32:43.161', '2022-07-09 12:32:43.161', 'f0869a41-7efe-441f-904b-db8feb7c9bc9', 'Test1', 'Test1', 0, 't.me', 100, 500, 'ID-425edbf8-c7bd-4141-8888-d1955449933d-T2022-07-09_12-33-08-R-b7f3a906639f40f98bc595d45fd239e8.png', '0942860030', 0.0, 0.0, 'Test1', 'Test1', false);
INSERT INTO public.user_info
(user_info_id, created_date, create_order, expert_group_id, first_name, last_name, like_count, live_link, price_call, price_video, profile_pic, tel_number, "token", token_count, user_caption, user_name, verify_status)
VALUES('a94d8787-3946-49ee-8518-28d6948a5c03', '2022-07-09 12:53:07.739', '2022-07-09 12:53:07.739', 'f0869a41-7efe-441f-904b-db8feb7c9bc9', 'Test5', 'Test5', 0, 't.me', 200, 1000, 'ID-a94d8787-3946-49ee-8518-28d6948a5c03-T2022-07-09_12-53-36-R-12f11f7b96ce4da892df4f58300f1661.png', '0942860031', 0.0, 0.0, 'Test5', 'Test5', false);


