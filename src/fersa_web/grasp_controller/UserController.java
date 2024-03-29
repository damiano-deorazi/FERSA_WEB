package fersa_web.grasp_controller;

import fersa_web.bean.UserBean;
import fersa_web.dao.DAOQueryUser;
import fersa_web.model.User;

public class UserController {
    private static UserController userController = null;

    private UserController(){}

    public static UserController getInstance(){
        if (userController == null){
            userController = new UserController();
        }
        return userController;
    }

    public boolean validateLogin(UserBean userBean) {
        DAOQueryUser daoQueryUser = DAOQueryUser.getInstance();
        User user = daoQueryUser.getUser(userBean.getUsername(), userBean.getPassword());
        if (user == null) return false;
        userBean.setIsLessor(user.isLessor());
        return true;
    }

    public User getUser(String username) {
        DAOQueryUser daoQueryUser = DAOQueryUser.getInstance();
        return daoQueryUser.getUser(username);
    }
}
