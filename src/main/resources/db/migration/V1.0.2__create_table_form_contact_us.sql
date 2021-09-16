CREATE TABLE form_contact_us
(
    id              int             auto_increment primary key,
    kindOfRequest	varchar2(50)    not null,
    policyNumber    varchar2(50)    not null,
    name            varchar2(50)    not null,
    surname         varchar2(50)    not null,
    request         clob            not null,
    createdAt       datetime        not null,
    foreign key (kindOfRequest) references codetable_contact_requests(code)
);