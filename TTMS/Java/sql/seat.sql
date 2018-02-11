delimiter $$
drop procedure if exists insertData;
create procedure insertData()
begin
    declare i int;
    declare j int;
    set i=1;
    set j=1;
    while i<=9 do
        while j<=14 do
            INSERT INTO seat(seat_row,seat_column,seat_status,studio_id) VALUES(i,j,0,1);
	    set j=j+1;
        end while;
        set i=i+1;
	set j=1;
    end while;
end $$
delimiter ;
call insertData();