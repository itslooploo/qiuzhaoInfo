package TestExample.LibraryManager;

import java.sql.SQLException;
import java.util.Collections;
import java.util.Scanner;

import static TestExample.LibraryManager.LibraryManagerMethod.getInformation;

/**
 * @author loop
 * @version 1.0
 */
public class Manager {
    public Manager(){
    }
    public static void ManagerDuty() throws SQLException {
        Scanner sc = new Scanner(System.in);
        //管理员有增删改查功能(图书和用户)
        System.out.println("请选择您要执行的对象\n1.查询\t2.编辑");
        int order_1 = sc.nextInt();
        //查询
        if (order_1 == 1){
            QueryForManager();
        } else if (order_1 == 2) { // 编辑  增删改查
            ADMForManager();
        } else System.out.println("输入错误");
    }
    // 管理员查询操作
    private static void QueryForManager() throws SQLException {
        Scanner sc = new Scanner(System.in);
        System.out.println("请输入查询的对象\n1.图书\t2.用户");
        int order_2 = sc.nextInt();
        if (order_2 == 1){
            System.out.println("请选择查询目标：\n1.在库图书\t2.借出图书");
            int order_3 = sc.nextInt();
            if (order_3 == 1){
                getInformation(Collections.singletonList(LibraryManagerMethod.QueryInLibraryInformation()));
            } else if (order_3 == 2) {
               getInformation(Collections.singletonList(LibraryManagerMethod.QueryInBorrowingBooks()));
            }else System.out.println("输入错误");
        } else if (order_2 == 2) {
            getInformation(Collections.singletonList(LibraryManagerMethod.QueryInReaders()));
        }  else System.out.println("输入错误");
    }
    // 管理员增删改操作
    private static void ADMForManager() throws SQLException {
        Scanner sc = new Scanner(System.in);
        System.out.println("请选择编辑目标：\n1.图书\t2.读者\t3.登录系统");
        int order_2 = sc.nextInt();
        if (order_2 == 1){
            System.out.println("请选择您要进行的操作\n1.新增图书\t2.删除图书\t3.修改图书信息");
            int order_3 = sc.nextInt();
            if (order_3 == 1){
                LibraryManagerMethod.AddToLibraryInformation();
            } else if (order_3 == 2) {
                LibraryManagerMethod.RemoveFromLibraryInformation();
            } else if (order_3 == 3){
                LibraryManagerMethod.ReviseInLibraryInformation();
            } else System.out.println("输入指令错误");
        } else if (order_2 == 2) {
            System.out.println("请选择您要进行的操作\n1.新增读者\t2.删除读者\t3.修改读者信息");
            int order_3 = sc.nextInt();
            if (order_3 == 1){
                LibraryManagerMethod.AddToReaderInformation();
            }else if (order_3 == 2){
                LibraryManagerMethod.RemoveFromReaderInformation();
            }else if (order_3 == 3){
                LibraryManagerMethod.ReviseInReaderInformation();
            } else System.out.println("输入错误");
        } else if (order_2 == 3) {
            System.out.println("请选择您要进行的操作\n1.新增用户\t2.删除用户\t3.修改用户信息");
            int order_3 = sc.nextInt();
            if (order_3 == 1){
                LibraryManagerMethod.AddToLogin();
            } else if (order_3 == 2) {
                LibraryManagerMethod.RemoveFromLogin();
            } else if (order_3 == 3) {
                LibraryManagerMethod.ReviseInLogin();
            }else System.out.println("输入错误");
        }else System.out.println("输入错误");
    }
}