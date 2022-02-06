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
insert into member (member_id, name, password, blocked) values ('user3', '사용자3', '5678', true);
insert into member (member_id, name, password, blocked) values ('user4', '사용자4', '5678', true);
insert into member (member_id, name, password, blocked) values ('user5', '사용자5', '5678', false);
insert into member (member_id, name, password, blocked) values ('user6', '사용자6', '5678', false);
insert into member (member_id, name, password, blocked) values ('user7', '사용자7', '5678', false);
insert into member (member_id, name, password, blocked) values ('user8', '사용자8', '5678', false);
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

insert into purchase_order values (
'ORDER-001', 1, 'user1', '사용자1', 4000,
'123456', '서울시', '관악구', '메시지',
'사용자1', '010-1234-5678', 'PREPARING', '2022-01-01 15:30:00'
);

insert into order_line values ('ORDER-001', 0, 'prod-001', 1000, 2, 2000);
insert into order_line values ('ORDER-001', 1, 'prod-002', 2000, 1, 2000);

insert into purchase_order values (
'ORDER-002', 2, 'user1', '사용자1', 5000,
'123456', '서울시', '관악구', '메시지',
'사용자1', '010-1234-5678', 'PREPARING', '2022-01-02 09:18:21'
);
insert into order_line values ('ORDER-002', 0, 'prod-001', 1000, 5, 5000);

insert into purchase_order values (
'ORDER-003', 3, 'user2', '사용자2', 5000,
'123456', '서울시', '관악구', '메시지',
'사용자1', '010-1234-5678', 'SHIPPED', '2016-01-03 09:00:00'
);
insert into order_line values ('ORDER-003', 0, 'prod-001', 1000, 5, 5000);

insert into article (title) values ('제목');
insert into article_content values (1, 'content', 'type');

insert into evententry (type, content_type, payload, timestamp) values
  ('com.myshop.eventstore.infra.SampleEvent', 'application/json', '{"name": "name1", "value": 11}', now());
insert into evententry (type, content_type, payload, timestamp) values
  ('com.myshop.eventstore.infra.SampleEvent', 'application/json', '{"name": "name2", "value": 12}', now());
insert into evententry (type, content_type, payload, timestamp) values
  ('com.myshop.eventstore.infra.SampleEvent', 'application/json', '{"name": "name3", "value": 13}', now());
insert into evententry (type, content_type, payload, timestamp) values
  ('com.myshop.eventstore.infra.SampleEvent', 'application/json', '{"name": "name4", "value": 14}', now());
