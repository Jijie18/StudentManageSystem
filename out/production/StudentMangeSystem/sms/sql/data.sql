use mydb;
-- 数据库中所有用户的密码均为123
-- 2个管理员
insert into admin (admin_id, admin_name, password) VALUES (1, 'Alice', '123');
insert into admin (admin_id, admin_name, password) VALUES (2, 'Bob', '123');
select * from admin;

-- 7个系
insert into department (department_id, department_name) VALUES (1, 'Computer Science and Engineering');
insert into department (department_id, department_name) VALUES (2, 'Electrical and Electronic Engineering');
insert into department (department_id, department_name) VALUES (3, 'Mathematics');
insert into department (department_id, department_name) VALUES (4, 'Physics');
insert into department (department_id, department_name) VALUES (5, 'Chemistry');
insert into department (department_id, department_name) VALUES (6, 'Biology');
insert into department (department_id, department_name) VALUES (7, 'Public Elementary Courses');
select * from department;
-- delete from department;

-- 8个专业
insert into major (major_id, major_name, department_id, optional_credits) VALUES (1, 'Computer Science and Technology', 1, 15);
insert into major (major_id, major_name, department_id, optional_credits) VALUES (2, 'Communication Engineering', 2, 16);
insert into major (major_id, major_name, department_id, optional_credits) VALUES (3, 'Information Engineering', 2, 15);
insert into major (major_id, major_name, department_id, optional_credits) values (4, 'Mathematics and Applied Mathematics', 3, 15);
insert into major (major_id, major_name, department_id, optional_credits) values (5, 'Physics', 4, 15);
insert into major (major_id, major_name, department_id, optional_credits) values (6, 'Chemistry', 5, 15);
insert into major (major_id, major_name, department_id, optional_credits) values (7, 'Biological Sciences', 6, 15);
insert into major (major_id, major_name, department_id, optional_credits) values (8, 'Public Courses', 7, 15);
select * from major;
-- delete from major;

-- 20位老师
insert into instructor (instructor_id, major_id, password, instructor_name, age, sex, joined_date) VALUES (1, 1, 123, 'cjd', 60, 'M','2018-01-01');
insert into instructor (instructor_id, major_id, password, instructor_name, age, sex, joined_date) VALUES (2, 1, 123, 'zj', 35, 'F','2014-01-01');
insert into instructor (instructor_id, major_id, password, instructor_name, age, sex, joined_date) VALUES (3, 1, 123, 'tb', 30, 'M','2017-01-01');
insert into instructor (instructor_id, major_id, password, instructor_name, age, sex, joined_date) values (4, 1, 123, 'syh', 50, 'M', '2016-01-01');
insert into instructor (instructor_id, major_id, password, instructor_name, age, sex, joined_date) values (5, 1, 123, 'wq', 30, 'M', '2014-01-01');
insert into instructor (instructor_id, major_id, password, instructor_name, age, sex, joined_date) values (6, 1, 123, 'hq', 40, 'M', '2014-01-01');
insert into instructor (instructor_id, major_id, password, instructor_name, age, sex, joined_date) values (7, 1, 123, 'lzw', 40, 'M', '2014-01-01');
insert into instructor (instructor_id, major_id, password, instructor_name, age, sex, joined_date) values (8, 1, 123, 'tk', 40, 'M', '2017-01-01');
insert into instructor (instructor_id, major_id, password, instructor_name, age, sex, joined_date) values (9, 1, 123, 'zf', 35, 'M', '2018-01-01');
insert into instructor (instructor_id, major_id, password, instructor_name, age, sex, joined_date) values (10, 1, 123, 'wf', 40, 'M', '2016-01-01');
insert into instructor (instructor_id, major_id, password, instructor_name, age, sex, joined_date) values (11, 4, 123, 'wr', 40, 'M', '2014-01-01');
insert into instructor (instructor_id, major_id, password, instructor_name, age, sex, joined_date) values (12, 5, 123, 'cl', 40, 'M', '2014-01-01');
insert into instructor (instructor_id, major_id, password, instructor_name, age, sex, joined_date) values (13, 6, 123, 'tx', 40, 'M', '2014-01-01');
insert into instructor (instructor_id, major_id, password, instructor_name, age, sex, joined_date) values (14, 2, 123, 'wf', 40, 'M', '2016-01-01');
insert into instructor (instructor_id, major_id, password, instructor_name, age, sex, joined_date) values (15, 7, 123, 'yc', 40, 'F', '2014-01-01');
insert into instructor (instructor_id, major_id, password, instructor_name, age, sex, joined_date) values (16, 4, 123, 'cym', 35, 'M', '2014-01-01');
insert into instructor (instructor_id, major_id, password, instructor_name, age, sex, joined_date) values (17, 8, 123, 'yyy', 35, 'F', '2014-01-01');
insert into instructor (instructor_id, major_id, password, instructor_name, age, sex, joined_date) values (18, 8, 123, 'zyp', 35, 'M', '2014-01-01');
insert into instructor (instructor_id, major_id, password, instructor_name, age, sex, joined_date) values (19, 4, 123, 'yj', 40, 'F', '2014-01-01');
insert into instructor (instructor_id, major_id, password, instructor_name, age, sex, joined_date) values (20, 1, 123, 'gt', 50, 'M', '2014-01-01');

select * from instructor;
-- delete from instructor;


-- 30位学生, 全都是计算机系的学生
insert into student (student_id, student_name, password, age, sex, admission_year, administrative_class, grade, instructor_id, major_id) values (1, 'Adam', 123, 20,'M', 2017, 1, 2, 3, 1);
insert into student (student_id, student_name, password, age, sex, admission_year, administrative_class, grade, instructor_id, major_id) values (2, 'Ada', 123, 21,'F', 2016, 1, 3, 6, 1);
insert into student (student_id, student_name, password, age, sex, admission_year, administrative_class, grade, instructor_id, major_id) values (3, 'Alice', 123, 20,'F', 2017, 5, 2, 10, 1);
insert into student (student_id, student_name, password, age, sex, admission_year, administrative_class, grade, instructor_id, major_id) values (4, 'Bill', 123, 20,'M', 2017, 18, 2, 3, 1);
insert into student (student_id, student_name, password, age, sex, admission_year, administrative_class, grade, instructor_id, major_id) values (5, 'Carrie', 123, 20,'F', 2017, 12, 2, 9, 1);
insert into student (student_id, student_name, password, age, sex, admission_year, administrative_class, grade, instructor_id, major_id) values (6, 'David', 123, 21,'M', 2016, 9, 3, 8, 1);
insert into student (student_id, student_name, password, age, sex, admission_year, administrative_class, grade, instructor_id, major_id) values (7, 'Edward', 123, 20,'M', 2017, 13, 2, 7, 1);
insert into student (student_id, student_name, password, age, sex, admission_year, administrative_class, grade, instructor_id, major_id) values (8, 'Ella', 123, 20,'F', 2017, 28, 2, 9, 1);
insert into student (student_id, student_name, password, age, sex, admission_year, administrative_class, grade, instructor_id, major_id) values (9, 'Emma', 123, 20,'F', 2017, 25, 2, 2, 1);
insert into student (student_id, student_name, password, age, sex, admission_year, administrative_class, grade, instructor_id, major_id) values (10, 'Jack', 123, 20,'M', 2017, 4, 2, 4, 1);
insert into student (student_id, student_name, password, age, sex, admission_year, administrative_class, grade, instructor_id, major_id) values (11, 'James', 123, 20,'M', 2017, 30, 2, 4, 1);
insert into student (student_id, student_name, password, age, sex, admission_year, administrative_class, grade, instructor_id, major_id) values (12, 'Jessica', 123, 20,'F', 2017, 29, 2, 2, 1);
insert into student (student_id, student_name, password, age, sex, admission_year, administrative_class, grade, instructor_id, major_id) values (13, 'Mary', 123, 20,'F', 2017, 15, 2, 4, 1);
insert into student (student_id, student_name, password, age, sex, admission_year, administrative_class, grade, instructor_id, major_id) values (14, 'Mike', 123, 21,'M', 2016, 8, 3, 5, 1);
insert into student (student_id, student_name, password, age, sex, admission_year, administrative_class, grade, instructor_id, major_id) values (15, 'Neil', 123, 20,'M', 2017, 14, 2, 7, 1);
insert into student (student_id, student_name, password, age, sex, admission_year, administrative_class, grade, instructor_id, major_id) values (16, 'Oliver', 123, 21,'M', 2016, 23, 3, 2, 1);
insert into student (student_id, student_name, password, age, sex, admission_year, administrative_class, grade, instructor_id, major_id) values (17, 'Peter', 123, 21,'M', 2016, 11, 3, 7, 1);
insert into student (student_id, student_name, password, age, sex, admission_year, administrative_class, grade, instructor_id, major_id) values (18, 'Paul', 123, 20,'M', 2017, 4, 2, 3, 1);
insert into student (student_id, student_name, password, age, sex, admission_year, administrative_class, grade, instructor_id, major_id) values (19, 'Polly', 123, 20,'F', 2017, 27, 2, 4, 1);
insert into student (student_id, student_name, password, age, sex, admission_year, administrative_class, grade, instructor_id, major_id) values (20, 'Rose', 123, 20,'F', 2017, 13, 2, 8, 1);
insert into student (student_id, student_name, password, age, sex, admission_year, administrative_class, grade, instructor_id, major_id) values (21, 'Sarah', 123, 20,'F', 2017, 19, 2, 7, 1);
insert into student (student_id, student_name, password, age, sex, admission_year, administrative_class, grade, instructor_id, major_id) values (22, 'Sam', 123, 20,'M', 2017, 5, 2, 5, 1);
insert into student (student_id, student_name, password, age, sex, admission_year, administrative_class, grade, instructor_id, major_id) values (23, 'Simon', 123, 20,'M', 2017, 7, 2, 6, 1);
insert into student (student_id, student_name, password, age, sex, admission_year, administrative_class, grade, instructor_id, major_id) values (24, 'Spark', 123, 20,'M', 2017, 18, 2, 9, 1);
insert into student (student_id, student_name, password, age, sex, admission_year, administrative_class, grade, instructor_id, major_id) values (25, 'Shirley', 123, 20,'F', 2017, 12, 2, 3, 1);
insert into student (student_id, student_name, password, age, sex, admission_year, administrative_class, grade, instructor_id, major_id) values (26, 'Susan', 123, 20,'F', 2017, 24, 2, 5, 1);
insert into student (student_id, student_name, password, age, sex, admission_year, administrative_class, grade, instructor_id, major_id) values (27, 'Tess', 123, 20,'F', 2017, 21, 2, 8, 1);
insert into student (student_id, student_name, password, age, sex, admission_year, administrative_class, grade, instructor_id, major_id) values (28, 'Tom', 123, 21,'M', 2016, 20, 3, 2, 1);
insert into student (student_id, student_name, password, age, sex, admission_year, administrative_class, grade, instructor_id, major_id) values (29, 'Thomas', 123, 20,'M', 2017, 10, 2, 3, 1);
insert into student (student_id, student_name, password, age, sex, admission_year, administrative_class, grade, instructor_id, major_id) values (30, 'Wendy', 123, 20,'F', 2017, 16, 2, 5, 1);
select * from student;
-- delete from student;


-- 20门必修公选课
insert into course (course_id, major_id, course_name, acronym, total_hour, credit) VALUES (1, 8, 'Military Theory and Training', 'GE100', 32, 2);
insert into course (course_id, major_id, course_name, acronym, total_hour, credit) VALUES (2, 4, 'Calculus I', 'GE101', 64, 4);
insert into course (course_id, major_id, course_name, acronym, total_hour, credit) VALUES (3, 5, 'General Physics I', 'PHY101', 64, 4);
insert into course (course_id, major_id, course_name, acronym, total_hour, credit) VALUES (4, 4, 'Linear Algebra I', 'MA103', 64, 4);
insert into course (course_id, major_id, course_name, acronym, total_hour, credit) VALUES (5, 1, 'Basics of Computer Programming Design', 'GE105', 64, 3);
insert into course (course_id, major_id, course_name, acronym, total_hour, credit) VALUES (6, 6, 'General Chemistry', 'CH101', 64, 4);
insert into course (course_id, major_id, course_name, acronym, total_hour, credit) VALUES (7, 4, 'Calculus II', 'GE102', 64, 4);
insert into course (course_id, major_id, course_name, acronym, total_hour, credit) VALUES (8, 5, 'General Physics II', 'PHY102', 64, 4);
insert into course (course_id, major_id, course_name, acronym, total_hour, credit) VALUES (9, 7, 'General Biology', 'BIO102', 64, 4);
insert into course (course_id, major_id, course_name, acronym, total_hour, credit) VALUES (10, 5, 'Experiment for Foundation of Physics', 'PHY104', 32, 2);
insert into course (course_id, major_id, course_name, acronym, total_hour, credit) VALUES (11, 8, 'English Reading and Writing Ⅰ', 'GE121', 32, 2);
insert into course (course_id, major_id, course_name, acronym, total_hour, credit) VALUES (12, 8, 'English Reading and Writing Ⅱ', 'GE122', 32, 2);
insert into course (course_id, major_id, course_name, acronym, total_hour, credit) VALUES (13, 8, 'English Reading and Writing Ⅲ', 'GE221', 32, 2);
insert into course (course_id, major_id, course_name, acronym, total_hour, credit) VALUES (14, 8, 'English Listening and Speaking Ⅰ', 'GE123', 16, 1);
insert into course (course_id, major_id, course_name, acronym, total_hour, credit) VALUES (15, 8, 'English Listening and Speaking Ⅱ', 'GE124', 16, 1);
insert into course (course_id, major_id, course_name, acronym, total_hour, credit) VALUES (16, 8, 'English Listening and Speaking Ⅲ', 'GE223', 16, 1);
insert into course (course_id, major_id, course_name, acronym, total_hour, credit) values (17, 8, 'Physic Education I', 'GE131', 32, 1);
insert into course (course_id, major_id, course_name, acronym, total_hour, credit) values (18, 8, 'Physic Education Ⅱ', 'GE132', 32, 1);
insert into course (course_id, major_id, course_name, acronym, total_hour, credit) values (19, 8, 'Physic Education Ⅲ', 'GE231', 32, 1);
insert into course (course_id, major_id, course_name, acronym, total_hour, credit) values (20, 8, 'Physic Education Ⅳ', 'GE232', 32, 1);




-- 20门专业课
insert into course (course_id, major_id, course_name, acronym, total_hour, credit) values (21, 4, 'Probability and Statistics', 'MA204', 64, 3);
insert into course (course_id, major_id, course_name, acronym, total_hour, credit) VALUES (22, 1, 'Discrete Mathematics', 'CS201', 48, 3);
insert into course (course_id, major_id, course_name, acronym, total_hour, credit) VALUES (23, 1, 'Computer Organization Principle', 'CS202', 64, 3);
insert into course (course_id, major_id, course_name, acronym, total_hour, credit) VALUES (24, 1, 'Data Structure and Algorithm Analysis', 'CS203', 64, 3);
insert into course (course_id, major_id, course_name, acronym, total_hour, credit) values (25, 1, 'Digital Logic', 'CS207', 64, 3);
insert into course (course_id, major_id, course_name, acronym, total_hour, credit) VALUES (26, 1, 'Algorithm Design and Analysis', 'CS208', 64, 3);
insert into course (course_id, major_id, course_name, acronym, total_hour, credit) VALUES (27, 1, 'Database System Principle', 'CS307', 64, 3);
insert into course (course_id, major_id, course_name, acronym, total_hour, credit) VALUES (28, 1, 'Operating System', 'CS302', 64, 3);
insert into course (course_id, major_id, course_name, acronym, total_hour, credit) VALUES (29, 1, 'Computer Networks', 'CS305', 64, 3);
insert into course (course_id, major_id, course_name, acronym, total_hour, credit) VALUES (30, 1, 'Object-oriented Analysis and Design', 'CS309', 64, 3);
insert into course (course_id, major_id, course_name, acronym, total_hour, credit) VALUES (31, 1, 'Software Engineering', 'CS306', 64, 3);
insert into course (course_id, major_id, course_name, acronym, total_hour, credit) VALUES (32, 1, 'Embedded System and Microcomputer Principle', 'CS309', 64, 3);
insert into course (course_id, major_id, course_name, acronym, total_hour, credit) VALUES (33, 2, 'Fundamentals of Electric Circuits', 'EE104', 32, 2);
insert into course (course_id, major_id, course_name, acronym, total_hour, credit) VALUES (34, 2, 'Analog Circuit', 'EE201', 64, 3);
insert into course (course_id, major_id, course_name, acronym, total_hour, credit) VALUES (35, 4, 'Mathematical Model', 'MA228', 32, 2);
insert into course (course_id, major_id, course_name, acronym, total_hour, credit) VALUES (36, 2, 'Signals and Systems', 'EE205', 64, 3);
insert into course (course_id, major_id, course_name, acronym, total_hour, credit) VALUES (37, 1, 'C/C++ Programming Design', 'CS205', 64, 3);
insert into course (course_id, major_id, course_name, acronym, total_hour, credit) VALUES (38, 1, 'Data Mining', 'CS310', 64, 3);
insert into course (course_id, major_id, course_name, acronym, total_hour, credit) VALUES (39, 1, 'Big Data Technology', 'CS314', 64, 3);
insert into course (course_id, major_id, course_name, acronym, total_hour, credit) VALUES (40, 1, 'Wireless Mobile Network', 'CS413', 32, 2);
select * from course;
-- delete from course;


-- 5栋教学楼
insert into building (building_name, area) VALUES ('First building', 'Lichi Park');
insert into building (building_name, area) VALUES ('Second building', 'Lichi Park');
insert into building (building_name, area) values ('Sixth buidlfing', 'Lichi Park');
insert into building (building_name, area) VALUES ('First teaching building', 'Shanxia');
insert into building (building_name, area) values ('Second teaching building', 'Shanxia');
select * from building;

-- 15间教室
insert into room (building_id, room_number, floor) VALUES (1, '101', 1);
insert into room (building_id, room_number, floor) VALUES (1, '203', 2);
insert into room (building_id, room_number, floor) VALUES (1, '102', 1);
insert into room (building_id, room_number, floor) VALUES (2, '101', 1);
insert into room (building_id, room_number, floor) VALUES (2, '102', 1);
insert into room (building_id, room_number, floor) VALUES (2, '201', 2);
insert into room (building_id, room_number, floor) VALUES (3, '402', 4);
insert into room (building_id, room_number, floor) VALUES (3, '404', 4);
insert into room (building_id, room_number, floor) VALUES (3, '406', 4);
insert into room (building_id, room_number, floor) VALUES (4, '110', 1);
insert into room (building_id, room_number, floor) VALUES (4, '107', 1);
insert into room (building_id, room_number, floor) VALUES (4, '306', 3);
insert into room (building_id, room_number, floor) VALUES (5, '101', 1);
insert into room (building_id, room_number, floor) VALUES (5, '204', 2);
insert into room (building_id, room_number, floor) VALUES (5, '205', 2);
select * from room;

-- 2个学期
insert into schedule (schedule_id, year, semester) VALUES (1, 2019, 1);
insert into schedule (schedule_id, year, semester) VALUES (2, 2019, 2);
select * from schedule;


-- 45门课的安排
insert into class values ( 1,  1, 2, 60);
insert into class VALUES ( 2,  2, 2, 50);
insert into class VALUES ( 3,  3, 2, 30);
insert into class VALUES ( 4,  4, 2, 60);
insert into class VALUES ( 5,  5, 2, 50);
insert into class VALUES ( 6,  5, 2, 40);
insert into class VALUES ( 7,  6, 2, 40);
insert into class VALUES ( 8,  7, 1, 30);
insert into class VALUES ( 9,  8, 1, 50);
insert into class VALUES (10,  8, 1, 50);
insert into class VALUES (11,  9, 1, 50);
insert into class VALUES (12, 10, 2, 50);
insert into class VALUES (13, 11, 1, 50);
insert into class VALUES (14, 12, 2, 50);
insert into class VALUES (15, 13, 2, 50);
insert into class VALUES (16, 14, 1, 50);
insert into class VALUES (17, 15, 2, 50);
insert into class VALUES (18, 16, 2, 50);
insert into class VALUES (19, 17, 1, 50);
insert into class VALUES (20, 17, 1, 50);
insert into class VALUES (21, 18, 2, 50);
insert into class VALUES (22, 19, 1, 50);
insert into class VALUES (23, 20, 2, 50);
insert into class VALUES (24, 21, 1, 50);
insert into class VALUES (25, 22, 1, 50);
insert into class VALUES (26, 23, 1, 50);
insert into class VALUES (27, 24, 2, 50);
insert into class VALUES (28, 25, 2, 50);
insert into class VALUES (29, 26, 1, 50);
insert into class VALUES (30, 27, 1, 50);
insert into class VALUES (31, 27, 1, 50);
insert into class VALUES (32, 28, 1, 50);
insert into class VALUES (33, 29, 2, 50);
insert into class VALUES (34, 30, 2, 50);
insert into class VALUES (35, 31, 1, 50);
insert into class VALUES (36, 32, 2, 50);
insert into class VALUES (37, 33, 2, 50);
insert into class VALUES (38, 34, 1, 50);
insert into class VALUES (39, 35, 1, 50);
insert into class VALUES (40, 35, 1, 50);
insert into class VALUES (41, 36, 2, 50);
insert into class VALUES (42, 37, 1, 50);
insert into class VALUES (43, 38, 1, 50);
insert into class VALUES (44, 39, 1, 50);
insert into class VALUES (45, 40, 2, 50);
select * from class;
-- delete from class;

-- 30个时间段
-- all, odd, even
insert into time (time_id, weekday, start, end, week) VALUES (1, 'Mon', 8, 10, 'A');
insert into time (time_id, weekday, start, end, week) VALUES (2, 'Mon', 10, 12, 'A');
insert into time (time_id, weekday, start, end, week) VALUES (3, 'Mon', 14, 16, 'A');
insert into time (time_id, weekday, start, end, week) VALUES (4, 'Mon', 16, 18, 'A');
insert into time (time_id, weekday, start, end, week) VALUES (5, 'Mon', 19, 21, 'A');
insert into time (time_id, weekday, start, end, week) VALUES (6, 'Mon', 10, 12, 'O');

insert into time (time_id, weekday, start, end, week) VALUES (7, 'Tues', 8, 10, 'A');
insert into time (time_id, weekday, start, end, week) VALUES (8, 'Tues', 10, 12, 'A');
insert into time (time_id, weekday, start, end, week) VALUES (9, 'Tues', 14, 16, 'A');
insert into time (time_id, weekday, start, end, week) VALUES (10, 'Tues', 16, 18, 'A');
insert into time (time_id, weekday, start, end, week) VALUES (11, 'Tues', 19, 21, 'A');
insert into time (time_id, weekday, start, end, week) VALUES (12, 'Tues', 16, 18, 'E');

insert into time (time_id, weekday, start, end, week) VALUES (13, 'Wed', 8, 10, 'A');
insert into time (time_id, weekday, start, end, week) VALUES (14, 'Wed', 10, 12, 'A');
insert into time (time_id, weekday, start, end, week) VALUES (15, 'Wed', 14, 16, 'A');
insert into time (time_id, weekday, start, end, week) VALUES (16, 'Wed', 16, 18, 'A');
insert into time (time_id, weekday, start, end, week) VALUES (17, 'Wed', 19, 21, 'A');
insert into time (time_id, weekday, start, end, week) VALUES (18, 'Wed', 16, 18, 'E');

insert into time (time_id, weekday, start, end, week) VALUES (19, 'Thur', 8, 10, 'A');
insert into time (time_id, weekday, start, end, week) VALUES (20, 'Thur', 10, 12, 'A');
insert into time (time_id, weekday, start, end, week) VALUES (21, 'Thur', 14, 16, 'A');
insert into time (time_id, weekday, start, end, week) VALUES (22, 'Thur', 16, 18, 'A');
insert into time (time_id, weekday, start, end, week) VALUES (23, 'Thur', 19, 21, 'A');
insert into time (time_id, weekday, start, end, week) VALUES (24, 'Thur', 8, 10, 'E');

insert into time (time_id, weekday, start, end, week) VALUES (25, 'Fri', 8, 10, 'A');
insert into time (time_id, weekday, start, end, week) VALUES (26, 'Fri', 10, 12, 'A');
insert into time (time_id, weekday, start, end, week) VALUES (27, 'Fri', 14, 16, 'A');
insert into time (time_id, weekday, start, end, week) VALUES (28, 'Fri', 16, 18, 'A');
insert into time (time_id, weekday, start, end, week) VALUES (29, 'Fri', 19, 21, 'A');
insert into time (time_id, weekday, start, end, week) VALUES (30, 'Fri', 19, 21, 'O');
select * from time;

-- 77 节课
insert into lesson (class_id, room_id, instructor_id, time_id) VALUES (2, 2, 11, 1);
insert into lesson (class_id, room_id, instructor_id, time_id) VALUES (2, 2, 11, 8);
insert into lesson (class_id, room_id, instructor_id, time_id) VALUES (3, 1, 12, 2);
insert into lesson (class_id, room_id, instructor_id, time_id) VALUES (3, 3, 12, 5);
insert into lesson (class_id, room_id, instructor_id, time_id) VALUES (4, 4, 16, 14);
insert into lesson (class_id, room_id, instructor_id, time_id) VALUES (4, 6, 16, 20);
insert into lesson (class_id, room_id, instructor_id, time_id) VALUES (5, 10, 6, 4);
insert into lesson (class_id, room_id, instructor_id, time_id) VALUES (5, 10, 6, 11);
insert into lesson (class_id, room_id, instructor_id, time_id) VALUES (6, 10, 6, 5);
insert into lesson (class_id, room_id, instructor_id, time_id) VALUES (6, 10, 6, 13);
insert into lesson (class_id, room_id, instructor_id, time_id) VALUES (7, 4, 13, 14);
insert into lesson (class_id, room_id, instructor_id, time_id) VALUES (7, 4, 13, 28);
insert into lesson (class_id, room_id, instructor_id, time_id) VALUES (8, 11, 11, 3);
insert into lesson (class_id, room_id, instructor_id, time_id) VALUES (8, 11, 11, 9);
insert into lesson (class_id, room_id, instructor_id, time_id) VALUES (9, 3, 12, 13);
insert into lesson (class_id, room_id, instructor_id, time_id) VALUES (9, 3, 12, 27);
insert into lesson (class_id, room_id, instructor_id, time_id) VALUES (10, 2, 12, 15);
insert into lesson (class_id, room_id, instructor_id, time_id) VALUES (10, 2, 12, 22);
insert into lesson (class_id, room_id, instructor_id, time_id) VALUES (11, 1, 15, 1);
insert into lesson (class_id, room_id, instructor_id, time_id) VALUES (11, 1, 15, 14);
insert into lesson (class_id, room_id, instructor_id, time_id) VALUES (12, 13, 12, 2);
insert into lesson (class_id, room_id, instructor_id, time_id) VALUES (13, 12, 17, 2);
insert into lesson (class_id, room_id, instructor_id, time_id) VALUES (14, 12, 17, 3);
insert into lesson (class_id, room_id, instructor_id, time_id) VALUES (15, 12, 17, 8);
insert into lesson (class_id, room_id, instructor_id, time_id) VALUES (16, 12, 17, 9);
insert into lesson (class_id, room_id, instructor_id, time_id) VALUES (17, 12, 17, 20);
insert into lesson (class_id, room_id, instructor_id, time_id) VALUES (18, 12, 17, 21);
insert into lesson (class_id, room_id, instructor_id, time_id) VALUES (19, 15, 18, 2);
insert into lesson (class_id, room_id, instructor_id, time_id) VALUES (20, 15, 18, 8);
insert into lesson (class_id, room_id, instructor_id, time_id) VALUES (21, 15, 18, 14);
insert into lesson (class_id, room_id, instructor_id, time_id) VALUES (22, 15, 18, 20);
insert into lesson (class_id, room_id, instructor_id, time_id) VALUES (23, 15, 18, 26);
insert into lesson (class_id, room_id, instructor_id, time_id) VALUES (24, 10, 19, 3);
insert into lesson (class_id, room_id, instructor_id, time_id) VALUES (24, 10, 19, 21);
insert into lesson (class_id, room_id, instructor_id, time_id) VALUES (25, 6, 5, 12);
insert into lesson (class_id, room_id, instructor_id, time_id) VALUES (25, 6, 5, 22);
insert into lesson (class_id, room_id, instructor_id, time_id) VALUES (26, 1, 2, 25);
insert into lesson (class_id, room_id, instructor_id, time_id) VALUES (26, 9, 2, 29);
insert into lesson (class_id, room_id, instructor_id, time_id) VALUES (27, 1, 3, 1);
insert into lesson (class_id, room_id, instructor_id, time_id) VALUES (27, 8, 3, 2);
insert into lesson (class_id, room_id, instructor_id, time_id) VALUES (28, 5, 10, 8);
insert into lesson (class_id, room_id, instructor_id, time_id) VALUES (28, 9, 10, 18);
insert into lesson (class_id, room_id, instructor_id, time_id) VALUES (29, 4, 4, 15);
insert into lesson (class_id, room_id, instructor_id, time_id) VALUES (29, 7, 4, 16);
insert into lesson (class_id, room_id, instructor_id, time_id) VALUES (30, 2, 1, 20);
insert into lesson (class_id, room_id, instructor_id, time_id) VALUES (30, 8, 1, 21);
insert into lesson (class_id, room_id, instructor_id, time_id) VALUES (31, 5, 1, 2);
insert into lesson (class_id, room_id, instructor_id, time_id) VALUES (31, 9, 1, 3);
insert into lesson (class_id, room_id, instructor_id, time_id) VALUES (32, 3, 3, 13);
insert into lesson (class_id, room_id, instructor_id, time_id) VALUES (32, 9, 3, 14);
insert into lesson (class_id, room_id, instructor_id, time_id) VALUES (33, 1, 2, 20);
insert into lesson (class_id, room_id, instructor_id, time_id) VALUES (33, 7, 2, 29);
insert into lesson (class_id, room_id, instructor_id, time_id) VALUES (34, 10, 7, 15);
insert into lesson (class_id, room_id, instructor_id, time_id) VALUES (34, 10, 7, 16);
insert into lesson (class_id, room_id, instructor_id, time_id) VALUES (35, 11, 7, 4);
insert into lesson (class_id, room_id, instructor_id, time_id) VALUES (35, 11, 7, 5);
insert into lesson (class_id, room_id, instructor_id, time_id) VALUES (36, 4, 4, 17);
insert into lesson (class_id, room_id, instructor_id, time_id) VALUES (36, 4, 4, 27);
insert into lesson (class_id, room_id, instructor_id, time_id) VALUES (37, 10, 8, 16);
insert into lesson (class_id, room_id, instructor_id, time_id) VALUES (37, 14, 8, 20);
insert into lesson (class_id, room_id, instructor_id, time_id) VALUES (38, 11, 10, 25);
insert into lesson (class_id, room_id, instructor_id, time_id) VALUES (38, 11, 10, 26);
insert into lesson (class_id, room_id, instructor_id, time_id) VALUES (39, 2, 11, 13);
insert into lesson (class_id, room_id, instructor_id, time_id) VALUES (39, 8, 11, 14);
insert into lesson (class_id, room_id, instructor_id, time_id) VALUES (40, 1, 16, 19);
insert into lesson (class_id, room_id, instructor_id, time_id) VALUES (40, 9, 16, 20);
insert into lesson (class_id, room_id, instructor_id, time_id) VALUES (41, 3, 10, 2);
insert into lesson (class_id, room_id, instructor_id, time_id) VALUES (41, 7, 10, 14);
insert into lesson (class_id, room_id, instructor_id, time_id) VALUES (42, 10, 9, 18);
insert into lesson (class_id, room_id, instructor_id, time_id) VALUES (42, 10, 9, 19);
insert into lesson (class_id, room_id, instructor_id, time_id) VALUES (43, 4, 6, 1);
insert into lesson (class_id, room_id, instructor_id, time_id) VALUES (43, 4, 6, 25);
insert into lesson (class_id, room_id, instructor_id, time_id) VALUES (44, 11, 4, 4);
insert into lesson (class_id, room_id, instructor_id, time_id) VALUES (44, 11, 4, 5);
insert into lesson (class_id, room_id, instructor_id, time_id) VALUES (45, 6, 2, 19);
insert into lesson (class_id, room_id, instructor_id, time_id) VALUES (45, 9, 2, 20);
select * from lesson;


-- 学生已选课程表, 着重于1门课CS307, 2位学生
-- 20位选择了CS307的学生
insert into student_class_score (student_id, class_id, score) VALUES (1, 30, 90);
insert into student_class_score (student_id, class_id, score) VALUES (2, 30, 85);
insert into student_class_score (student_id, class_id, score) VALUES (3, 31, 80);
insert into student_class_score (student_id, class_id, score) VALUES (4, 30, 70);
insert into student_class_score (student_id, class_id, score) VALUES (5, 31, 75);
insert into student_class_score (student_id, class_id, score) VALUES (6, 31, 60);
insert into student_class_score (student_id, class_id, score) VALUES (7, 30, 80);
insert into student_class_score (student_id, class_id, score) VALUES (8, 31, 80);
insert into student_class_score (student_id, class_id, score) VALUES (9, 30, 95);
insert into student_class_score (student_id, class_id, score) VALUES (10, 30, 100);
insert into student_class_score (student_id, class_id, score) VALUES (11, 31, 85);
insert into student_class_score (student_id, class_id, score) VALUES (12, 30, 75);
insert into student_class_score (student_id, class_id, score) VALUES (13, 31, 70);
insert into student_class_score (student_id, class_id, score) VALUES (14, 31, 80);
insert into student_class_score (student_id, class_id, score) VALUES (15, 30, 85);
insert into student_class_score (student_id, class_id, score) VALUES (16, 31, 90);
insert into student_class_score (student_id, class_id, score) VALUES (17, 30, 80);
insert into student_class_score (student_id, class_id, score) VALUES (18, 30, 85);
insert into student_class_score (student_id, class_id, score) VALUES (19, 31, 70);
insert into student_class_score (student_id, class_id, score) VALUES (20, 30, 65);

-- 2位学生的过往课程, 第二位同学挂了离散
insert into student_class_score (student_id, class_id, score) VALUES (1, 1, 100);
insert into student_class_score (student_id, class_id, score) VALUES (1, 2, 95);
insert into student_class_score (student_id, class_id, score) VALUES (1, 3, 93);
insert into student_class_score (student_id, class_id, score) VALUES (1, 4, 88);
insert into student_class_score (student_id, class_id, score) VALUES (1, 5, 91);
insert into student_class_score (student_id, class_id, score) VALUES (1, 7, 85);
insert into student_class_score (student_id, class_id, score) VALUES (1, 8, 98);
insert into student_class_score (student_id, class_id, score) VALUES (1, 9, 90);
insert into student_class_score (student_id, class_id, score) VALUES (1, 11, 82);
insert into student_class_score (student_id, class_id, score) VALUES (1, 12, 90);
insert into student_class_score (student_id, class_id, score) VALUES (1, 13, 88);
insert into student_class_score (student_id, class_id, score) VALUES (1, 14, 86);
insert into student_class_score (student_id, class_id, score) VALUES (1, 15, 92);
insert into student_class_score (student_id, class_id, score) VALUES (1, 16, 93);
insert into student_class_score (student_id, class_id, score) VALUES (1, 17, 87);
insert into student_class_score (student_id, class_id, score) VALUES (1, 18, 90);
insert into student_class_score (student_id, class_id, score) VALUES (1, 20, 91);
insert into student_class_score (student_id, class_id, score) VALUES (1, 21, 89);
insert into student_class_score (student_id, class_id, score) VALUES (1, 22, 83);
insert into student_class_score (student_id, class_id, score) VALUES (1, 23, 96);
insert into student_class_score (student_id, class_id, score) VALUES (1, 24, 90);
insert into student_class_score (student_id, class_id, score) VALUES (1, 25, 80);
insert into student_class_score (student_id, class_id, score) VALUES (1, 26, 88);
insert into student_class_score (student_id, class_id, score) VALUES (1, 27, 89);
insert into student_class_score (student_id, class_id, score) VALUES (1, 28, 86);
insert into student_class_score (student_id, class_id, score) VALUES (1, 29, 90);

insert into student_class_score (student_id, class_id, score) VALUES (2, 1, 100);
insert into student_class_score (student_id, class_id, score) VALUES (2, 2, 90);
insert into student_class_score (student_id, class_id, score) VALUES (2, 3, 80);
insert into student_class_score (student_id, class_id, score) VALUES (2, 4, 78);
insert into student_class_score (student_id, class_id, score) VALUES (2, 5, 72);
insert into student_class_score (student_id, class_id, score) VALUES (2, 7, 88);
insert into student_class_score (student_id, class_id, score) VALUES (2, 8, 78);
insert into student_class_score (student_id, class_id, score) VALUES (2, 9, 83);
insert into student_class_score (student_id, class_id, score) VALUES (2, 11, 84);
insert into student_class_score (student_id, class_id, score) VALUES (2, 12, 80);
insert into student_class_score (student_id, class_id, score) VALUES (2, 13, 78);
insert into student_class_score (student_id, class_id, score) VALUES (2, 14, 76);
insert into student_class_score (student_id, class_id, score) VALUES (2, 15, 82);
insert into student_class_score (student_id, class_id, score) VALUES (2, 16, 93);
insert into student_class_score (student_id, class_id, score) VALUES (2, 17, 84);
insert into student_class_score (student_id, class_id, score) VALUES (2, 18, 88);
insert into student_class_score (student_id, class_id, score) VALUES (2, 20, 92);
insert into student_class_score (student_id, class_id, score) VALUES (2, 21, 87);
insert into student_class_score (student_id, class_id, score) VALUES (2, 22, 84);
insert into student_class_score (student_id, class_id, score) VALUES (2, 23, 91);
insert into student_class_score (student_id, class_id, score) VALUES (2, 24, 80);
insert into student_class_score (student_id, class_id, score) VALUES (2, 25, 59);
insert into student_class_score (student_id, class_id, score) VALUES (2, 26, 80);
insert into student_class_score (student_id, class_id, score) VALUES (2, 27, 76);
insert into student_class_score (student_id, class_id, score) VALUES (2, 28, 84);
insert into student_class_score (student_id, class_id, score) VALUES (2, 29, 85);
select * from student_class_score;


-- 专业先修课，在同一个course_id下，对相同的pre_group做或运算，再对所有项做与运算
insert into prerequire (course_id, pre_course_id, pre_group) values (32, 25, 1);
insert into prerequire (course_id, pre_course_id, pre_group) values (33, 21, 1);
insert into prerequire (course_id, pre_course_id, pre_group) values (33, 22, 2);
insert into prerequire (course_id, pre_course_id, pre_group) values (33, 4, 3);
insert into prerequire (course_id, pre_course_id, pre_group) values (33, 7, 3);
insert into prerequire (course_id, pre_course_id, pre_group) values (29, 23, 1);
insert into prerequire (course_id, pre_course_id, pre_group) values (30, 23, 1);
insert into prerequire (course_id, pre_course_id, pre_group) values (30, 24, 1);
insert into prerequire (course_id, pre_course_id, pre_group) values (28, 32, 1);
insert into prerequire (course_id, pre_course_id, pre_group) values (31, 30, 1);
select * from prerequire;


-- type_require:
-- BX : 必修课，全部修完方能毕业
-- X : 专业选修课，修够学分方能毕业
-- Z : 专业先修课，全部修完方能进专业， 当然也属于专业必修的范畴
insert into course_major (major_id, course_id, admission_year, type_require) VALUES (1, 1, 2013, 'BX');
insert into course_major (major_id, course_id, admission_year, type_require) VALUES (1, 2, 2013, 'BX');
insert into course_major (major_id, course_id, admission_year, type_require) VALUES (1, 3, 2013, 'BX');
insert into course_major (major_id, course_id, admission_year, type_require) VALUES (1, 4, 2013, 'BX');
insert into course_major (major_id, course_id, admission_year, type_require) VALUES (1, 5, 2013, 'BX');
insert into course_major (major_id, course_id, admission_year, type_require) VALUES (1, 6, 2013, 'BX');
insert into course_major (major_id, course_id, admission_year, type_require) VALUES (1, 7, 2013, 'BX');
insert into course_major (major_id, course_id, admission_year, type_require) VALUES (1, 8, 2013, 'BX');
insert into course_major (major_id, course_id, admission_year, type_require) VALUES (1, 9, 2013, 'BX');
insert into course_major (major_id, course_id, admission_year, type_require) VALUES (1, 10, 2013, 'BX');
insert into course_major (major_id, course_id, admission_year, type_require) VALUES (1, 11, 2013, 'BX');
insert into course_major (major_id, course_id, admission_year, type_require) VALUES (1, 12, 2013, 'BX');
insert into course_major (major_id, course_id, admission_year, type_require) VALUES (1, 13, 2013, 'BX');
insert into course_major (major_id, course_id, admission_year, type_require) VALUES (1, 14, 2013, 'BX');
insert into course_major (major_id, course_id, admission_year, type_require) VALUES (1, 15, 2013, 'BX');
insert into course_major (major_id, course_id, admission_year, type_require) VALUES (1, 16, 2013, 'BX');
insert into course_major (major_id, course_id, admission_year, type_require) VALUES (1, 17, 2013, 'BX');
insert into course_major (major_id, course_id, admission_year, type_require) VALUES (1, 18, 2013, 'BX');
insert into course_major (major_id, course_id, admission_year, type_require) VALUES (1, 19, 2013, 'BX');
insert into course_major (major_id, course_id, admission_year, type_require) VALUES (1, 20, 2013, 'BX');
insert into course_major (major_id, course_id, admission_year, type_require) VALUES (1, 21, 2013, 'Z');
insert into course_major (major_id, course_id, admission_year, type_require) VALUES (1, 22, 2013, 'Z');
insert into course_major (major_id, course_id, admission_year, type_require) VALUES (1, 23, 2013, 'Z');
insert into course_major (major_id, course_id, admission_year, type_require) VALUES (1, 24, 2013, 'Z');
insert into course_major (major_id, course_id, admission_year, type_require) VALUES (1, 25, 2013, 'Z');
insert into course_major (major_id, course_id, admission_year, type_require) VALUES (1, 26, 2013, 'Z');
insert into course_major (major_id, course_id, admission_year, type_require) VALUES (1, 27, 2013, 'Z');
insert into course_major (major_id, course_id, admission_year, type_require) VALUES (1, 28, 2013, 'BX');
insert into course_major (major_id, course_id, admission_year, type_require) VALUES (1, 29, 2013, 'BX');
insert into course_major (major_id, course_id, admission_year, type_require) VALUES (1, 30, 2013, 'BX');
insert into course_major (major_id, course_id, admission_year, type_require) VALUES (1, 31, 2013, 'BX');
insert into course_major (major_id, course_id, admission_year, type_require) VALUES (1, 32, 2013, 'BX');
insert into course_major (major_id, course_id, admission_year, type_require) VALUES (1, 33, 2013, 'BX');
insert into course_major (major_id, course_id, admission_year, type_require) VALUES (1, 34, 2013, 'X');
insert into course_major (major_id, course_id, admission_year, type_require) VALUES (1, 35, 2013, 'X');
insert into course_major (major_id, course_id, admission_year, type_require) VALUES (1, 36, 2013, 'X');
insert into course_major (major_id, course_id, admission_year, type_require) VALUES (1, 37, 2013, 'X');
insert into course_major (major_id, course_id, admission_year, type_require) VALUES (1, 38, 2013, 'X');
insert into course_major (major_id, course_id, admission_year, type_require) VALUES (1, 39, 2013, 'X');
insert into course_major (major_id, course_id, admission_year, type_require) VALUES (1, 40, 2013, 'X');
select * from course_major;

insert into notice (notice_id, text, publish_time) values (1, 'Welcome to CS307', '2019-03-01 14:00:00');
insert into notice (notice_id, text, publish_time) values (2, 'Please pay attention to the ddl of 2nd homework.', '2019-05-01 14:00:00');
select * from notice;

insert into class_notice (class_id, notice_id) VALUES (1, 1);
insert into class_notice (class_id, notice_id) VALUES (1, 2);
select * from class_notice;


-- 预选课表，着重于一门课CS303，和两位学生
insert into class_selection (student_id, class_id, wage_number) VALUES (5, 37, 30);
insert into class_selection (student_id, class_id, wage_number) VALUES (6, 37, 20);
insert into class_selection (student_id, class_id, wage_number) VALUES (7, 37, 35);
insert into class_selection (student_id, class_id, wage_number) VALUES (8, 37, 30);
insert into class_selection (student_id, class_id, wage_number) VALUES (9, 37, 20);
insert into class_selection (student_id, class_id, wage_number) VALUES (10, 37, 35);
insert into class_selection (student_id, class_id, wage_number) VALUES (11, 37, 25);
insert into class_selection (student_id, class_id, wage_number) VALUES (12, 37, 30);
insert into class_selection (student_id, class_id, wage_number) VALUES (13, 37, 20);
insert into class_selection (student_id, class_id, wage_number) VALUES (14, 37, 10);
insert into class_selection (student_id, class_id, wage_number) VALUES (15, 37, 25);
insert into class_selection (student_id, class_id, wage_number) VALUES (16, 37, 40);
insert into class_selection (student_id, class_id, wage_number) VALUES (17, 37, 30);
insert into class_selection (student_id, class_id, wage_number) VALUES (18, 37, 35);
insert into class_selection (student_id, class_id, wage_number) VALUES (19, 37, 30);
insert into class_selection (student_id, class_id, wage_number) VALUES (20, 37, 30);
insert into class_selection (student_id, class_id, wage_number) VALUES (21, 37, 35);
insert into class_selection (student_id, class_id, wage_number) VALUES (22, 37, 30);
insert into class_selection (student_id, class_id, wage_number) VALUES (23, 37, 25);
insert into class_selection (student_id, class_id, wage_number) VALUES (24, 37, 30);

insert into class_selection (student_id, class_id, wage_number) VALUES (1, 33, 30);
insert into class_selection (student_id, class_id, wage_number) VALUES (1, 34, 20);

insert into class_selection (student_id, class_id, wage_number) VALUES (2, 33, 25);
insert into class_selection (student_id, class_id, wage_number) VALUES (2, 34, 25);
select * from class_selection;





















