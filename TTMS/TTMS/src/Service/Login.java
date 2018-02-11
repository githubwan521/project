package Service;

import Dao.FactoryDao;
import Model.User;
import Util.Constant;

public class Login {
    //校验用户名
    public static User check(String username, String password) {
        User user = new User();
        user.setUser_no(username);
        user.setUser_pass(password);

        User info = FactoryDao.creatUserDAO().findUserByNo(username);//数据验证

        if (info == null) {
            user.setUser_state(Constant.Unknown);
        } else if (user.getUser_pass().equals(info.getUser_pass())) {
            user.setUser_type(info.getUser_type());
            user.setUser_state(Constant.Success);
        } else {
            user.setUser_state(Constant.Error);
        }
        return user;
    }
}
