insert into users(username, display_name, password, created_at, updated_at)
values ('user1', '홍길동', '1234', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP);

insert into articles(title, content, author_id, created_at, updated_at)
values ('제목1', '내용 1', 1, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP);
