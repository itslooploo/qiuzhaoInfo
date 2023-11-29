package TestExample.LibraryManager;

import java.sql.SQLException;
import java.util.Scanner;

/**
 * @author loop
 * @version 1.0
 *
 *  在这个类中构建供用户选择所需求功能的页面
 *
 */
public class LibraryManagerView {
    public static void App() throws SQLException {
        int order = LibraryManagerMethod.LoginView();
        // -1 登陆错误 0 为管理员 1 为普通读者
        // 以下为管理员的权限与功能
        if (order == 0){
            Manager.ManagerDuty();
        }
        // 以下为普通读者的权限与功能
        else if (order == 1) {
            Users.UserDuty();
        }
    }
}
