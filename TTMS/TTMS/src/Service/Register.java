package Service;

import Dao.FactoryDao;
import Model.User;


public class Register {
    /**
     * 检测用户名是否重名
     *
     * @return true 重名
     */
    public static boolean check(String username) {
        User info = FactoryDao.creatUserDAO().findUserByNo(username);
        boolean flag = false;
        if(info == null){
            return flag;
        }
        if(info.getUser_no().equals(username)){
            flag = true;
        }
        return flag;
    }
}
