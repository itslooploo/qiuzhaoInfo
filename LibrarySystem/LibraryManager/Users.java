package TestExample.LibraryManager;

import java.sql.SQLException;
import java.util.Collections;
import java.util.Scanner;

import static TestExample.LibraryManager.LibraryManagerMethod.getInformation;

/**
 * @author loop
 * @version 1.0
 */
public class Users {
    
    // 普通用户只有查询功能和借书还书功能
    public Users(){
    }

    public static void UserDuty() throws SQLException {
        Scanner sc = new Scanner(System.in);
        System.out.println("请输入您想行使的功能\n1. 查询在馆图书\t 2. 借阅书籍\t 3. 还书");
        int order = sc.nextInt();
        if (order == 1){
            getInformation(Collections.singletonList(LibraryManagerMethod.QueryInLibraryInformation()));
        } else if (order == 2) {
            LibraryManagerMethod.Borrowing();
        } else if (order == 3) {
            LibraryManagerMethod.ReturnBook();
        } else {
            System.out.println("输入错误");
        }
    }
}
