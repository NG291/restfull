
use restfullComputer;

delimiter //
create PROCEDURE  deleteCategory_id(in id_category int)
begin
update computers  set category_id = null where category_id=id_category;
delete from categories where id= id_category;
end //
delimiter ;
call deleteCategory_id(6);
