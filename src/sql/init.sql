use shop;

truncate table purchase_order;
truncate table order_line;
truncate table category;
truncate table product_category;
truncate table product;
truncate table image;
truncate table member;
truncate table member_authorities;
truncate table article;
truncate table article_content;
truncate table evententry;

insert into member (member_id, name, password, blocked) values ('user1', '사용자1', '1234', false);
insert into member (member_id, name, password, blocked) values ('user2', '사용자2', '5678', false);
insert into member (member_id, name, password, blocked) values ('admin', '운영자', 'admin1234', false);
insert into member_authorities values ('user1', 'ROLE_USER');
insert into member_authorities values ('user2', 'ROLE_USER');
insert into member_authorities values ('admin', 'ROLE_ADMIN');

insert into category values (1001, '전자제품');
insert into category values (2001, '필기구');

insert into product values ('prod-001', '라즈베리파이3 모델B', 56000, '모델B');
insert into image (product_id, list_idx, image_type, image_path, upload_time) values
  ('prod-001', 0, 'II', 'rpi3.jpg', now());
insert into image (product_id, list_idx, image_type, image_path, upload_time) values
  ('prod-001', 1, 'EI', 'http://external/image/path', now());

insert into product_category values ('prod-001', 1001);

insert into product values ('prod-002', '어프로치 휴대용 화이트보드 세트', 11920, '화이트보드');
insert into image (product_id, list_idx, image_type, image_path, upload_time) values
  ('prod-002', 0, 'II', 'wbp.png', now());

insert into product_category values ('prod-002', 2001);

insert into product values ('prod-003', '볼펜 겸용 터치펜', 9000, '볼펜과 터치펜을 하나로!');
insert into image (product_id, list_idx, image_type, image_path, upload_time) values
  ('prod-003', 0, 'II', 'pen.jpg', now());
insert into image (product_id, list_idx, image_type, image_path, upload_time) values
  ('prod-003', 1, 'II', 'pen2.jpg', now());

insert into product_category values ('prod-003', 1001);
insert into product_category values ('prod-003', 2001);