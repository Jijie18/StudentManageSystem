# 判断是否满足先修条件
create trigger choose_class_check
    after insert
    on class_selection
    for each row
begin
    if
            (select count(*)
             from (select *
                   from (select pre_course_id, pre_group
                         from prerequire
                                  join class on prerequire.course_id = class.course_id
                         where class.class_id = new.class_id) t1
                            left join (select course_id
                                       from student_class
                                                join class c on student_class.class_id = c.class_id
                                       where student_id = new.student_id) t2 on t1.pre_course_id = t2.course_id
                   group by pre_group) t3)
            =
            (select max(pre_group)
             from (select pre_course_id, pre_group
                   from prerequire
                            join class on prerequire.course_id = class.course_id
                   where class.class_id = new.class_id) t4)
    then
        begin
            delete from class_selection where student_id = new.student_id;
            signal sqlstate '45000'
                set message_text = 'pre-required is not satisfied';
        end;
    end if;
end;