CREATE TABLE codetable_contact_requests
(
    id          int             auto_increment primary key,
    code		varchar2(50)    not null,
    text        varchar2(255)   not null,
    priority    int             not null
);