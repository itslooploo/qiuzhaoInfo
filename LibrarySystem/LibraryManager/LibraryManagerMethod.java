package TestExample.LibraryManager;

import TestExample.LibraryManager.Pojo.Books;
import TestExample.LibraryManager.Pojo.BorrowingBooks;
import TestExample.LibraryManager.Pojo.Reader;
import TestExample.LibraryManager.Pojo.User;
import com.alibaba.druid.pool.DruidDataSourceFactory;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import org.apache.commons.dbutils.handlers.MapHandler;

import javax.sql.DataSource;
import java.io.FileInputStream;
import java.sql.*;
import java.util.*;

/**
 * @author loop
 * @version 1.0
 *
 * 在这个类中定义管理系统所需要的所有方法
 *
 */
public class LibraryManagerMethod {

   private static DataSource ds;
   static {
       Properties properties = new Properties();
       try {
           properties.load(new FileInputStream("druid.properties"));
           ds = DruidDataSourceFactory.createDataSource(properties);
       }catch (Exception e){
           System.out.println("执行错误");
       }
   }

   // 获取连接
    public static Connection getConnection() throws SQLException {
       System.out.println("连接成功");
       return ds.getConnection();
   }
   // 执行关闭连接操作
    public static void getClose(ResultSet resultSet, Statement statement, Connection connection) {
       try {
           if (resultSet != null) resultSet.close();
           if (statement != null) statement.close();
           if (connection != null) connection.close();
       }catch (Exception e){
           System.out.println("关闭失败");
       }
    }

    //输出查询结果
    public static void getInformation(List<Object> list){
        if (list.isEmpty()) System.out.println("没有检索到您输入信息对应的信息");
        else {
            for (Object object : list){
                System.out.println(object.toString());
            }
        }
    }
    // 此函数用户用户登录界面
    public static int LoginView() throws SQLException {
        Scanner sc = new Scanner(System.in);
        System.out.println("欢迎使用图书馆管理系统");
        System.out.println("请输入您的用户名");
        String username = sc.next();
        System.out.println("请输入您的密码");
        String password = sc.next();
        String identity  = LibraryManagerMethod.Login(username, password);
        if (identity.equals("error")) {
            System.out.println("登陆失败");
            return -1;
        }
        else if (identity.equals("Manager")) {
            System.out.println("尊敬的管理员欢迎使用本系统");
            return 0;
        }else {
            System.out.println("您好,欢迎使用本系统");
            return 1;
        }
    }
    //此函数用于用户传入用户名和密码登录管理系统
    private static String Login(String user, String password) throws SQLException {
        List<User> list =  QueryInLogin(user, password);
        if (list.isEmpty()){
            System.out.println("用户名或密码错误， 登陆失败");
            return "error";
        }else {
            if (list.get(0).getPermission().equals("R")) return "Reader";
            else return "Manager";
        }
    }
    // 用于在Login中查询用户
    private static List<User> QueryInLogin(String user, String password) throws SQLException {
        Connection connect = getConnection();
        String sql = "select * from Login where user=? and password=?";
        QueryRunner queryRunner = new QueryRunner();
        List<User> list = queryRunner.query(connect, sql, new BeanListHandler<>(User.class), user, password);
        getClose(null, null, connect);
        return list;
    }
    // 在LibraryInformation中查找图书信息QueryInLogin
    // 问题是仍然无法处理中文字符
    public static List<Books> QueryInLibraryInformation() throws SQLException{
       Scanner sc = new Scanner(System.in);
       Connection connect = getConnection();
       String sql = "select * from LibraryInformation where ";
       QueryRunner queryRunner = new QueryRunner();
       System.out.println("请选择\n1.全部输出\t2.条件输出");
       int order = sc.nextInt();
       if (order == 1){
           sql = sql.replace("where", "");
       } else if (order == 2) {
           System.out.println("请输入欲检索书记的ISBN");
           sql = sql + "ISBN=" + sc.next();
       }else {
           System.out.println("输入有误");
           return new ArrayList<>();
       }
       List<Books> list = new ArrayList<>();
       try {
           list = queryRunner.query(connect, sql, new BeanListHandler<>(Books.class));
       }catch (Exception e){
           System.out.println("输入sql有误");
       }
       getClose(null, null, connect);
       return list;
    }
    // 在ReaderInformation 中查找读者信息
    public static List<Reader> QueryInReaders() throws SQLException {
        Scanner sc = new Scanner(System.in);
        Connection connect = getConnection();
        String sql = "select * from ReaderInformation ";
        QueryRunner queryRunner = new QueryRunner();
        System.out.println("请选择\n1. 全部输出\t2.条件输出");
        int order = sc.nextInt();
        if (order == 2){
            sql = sql +"where ";
            System.out.println("请选择\n1. 借书卡索引\t2.读者名索引\t3.电话号码索引");
            int order_1 = sc.nextInt();
            if (order_1 == 1){
                System.out.println("请输入借书卡号");
                sql = sql + "LibraryCardNumber=" + sc.next();
            } else if (order_1 == 2) {
                System.out.println("请输入读者名");
                sql += "ReaderName='" + sc.next() + "'";
            } else if (order_1 == 3) {
                System.out.println("请输入电话号码");
                sql += "PhoneNumber=" + sc.next();
            }else {
                System.out.println("输入错误");
                return new ArrayList<>();
            }
        } else if (order != 1) {
            System.out.println("输入有误");
            return new ArrayList<>();
        }
        List<Reader> list;
        try{
            list = queryRunner.query(connect, sql, new BeanListHandler<>(Reader.class));
        }catch (Exception e){
            System.out.println("输入sql有误");
            return new ArrayList<>();
        }
        getClose(null, null, connect);
        return list;
    }
    //以下为查询借出书籍BorrowingInformation
    public static List<BorrowingBooks> QueryInBorrowingBooks() throws SQLException {
        Scanner sc = new Scanner(System.in);
        Connection connect = getConnection();
        String sql = "select * from BorrowingInformation ";
        QueryRunner queryRunner = new QueryRunner();
        System.out.println("请选择\n1. 全部输出\t2.条件输出");
        int order = sc.nextInt();
        if (order == 2){
            sql += "where ";
            System.out.println("请选择\n1.借书卡号索引\t2.ISBN索引");
            int order_1 = sc.nextInt();
            if (order_1 == 1){
                System.out.println("请输入借书卡号");
                sql = sql + "LibraryCardNumber=" + sc.next();
            } else if (order_1 == 2) {
                System.out.println("请输入欲检索书记的ISBN");
                sql = sql + "ISBN=" + sc.next();
            } else {
                System.out.println("输入错误");
                return new ArrayList<>();
            }
        }
        List<BorrowingBooks> list;
        try{
            list = queryRunner.query(connect, sql, new BeanListHandler<>(BorrowingBooks.class));
        } catch (Exception e){
            System.out.println("输入sql有误");
            return new ArrayList<>();
        }
        getClose(null, null, connect);
        return list;
    }

    // 以下为向LibraryInformation表中添加书籍的操作
    public static void AddToLibraryInformation() throws SQLException {
        Scanner sc = new Scanner(System.in);
        Connection connect = getConnection();
        String sql = "insert into LibraryInformation value(?,?,?,?,?,?,?) ";
        QueryRunner queryRunner = new QueryRunner();
        Books book = new Books();
        System.out.println("以下信息请输入英文");
        System.out.println("请输入图书的ISBN");
        book.setISBN(sc.next());
        System.out.println("请输入图书的书名");
        book.setLibraryName(sc.next());
        System.out.println("请输入图书的出版社");
        book.setPublishingHouse(sc.next());
        System.out.println("请输入图书的作者");
        book.setAuthor(sc.next());
        System.out.println("请输入图书的馆藏量");
        book.setCollectionNumber(sc.nextDouble());
        System.out.println("请输入图书的可借量");
        book.setBorrowableNumber(sc.nextDouble());
        System.out.println("请输入图书是否可借(Y/N)");
        book.setBorrowable(sc.next());
        try {
            int i = queryRunner.update(connect, sql, book.getISBN(), book.getLibraryName(), book.getPublishingHouse(),
                    book.getAuthor(), book.getCollectionNumber(), book.getBorrowableNumber(), book.getBorrowable());
            if (i != 0) System.out.println("成功添加");
            else System.out.println("输入信息有误，添加失败");
        }catch (Exception e){
            System.out.println("输入有误，添加失败");
        }
        getClose(null, null, connect);
    }
    // 以下为从LibraryInformation中删除图书的操作
    public static void RemoveFromLibraryInformation() throws SQLException {
        Scanner sc = new Scanner(System.in);
        Connection connect = getConnection();
        QueryRunner queryRunner = new QueryRunner();
        String sql = "delete from LibraryInformation where ";
        System.out.println("请输入待删除图书的ISBN");
        String ISBN = sc.next();
        sql += "ISBN="+ISBN;
        System.out.println("请问您是否要删除ISBN为" + ISBN + "的图书(Y/N)");
        String order = sc.next();
        if (order.equals("Y")){
            try {
                int i = queryRunner.update(connect, sql);
                if (i != 0) System.out.println("删除成功");
                else System.out.println("输入sql有误，删除失败");
            }catch (Exception e){
                System.out.println("删除失败");
            }
        }
        getClose(null, null, connect);
    }
    // 以下为修改LibraryInformation中图书信息的函数
    public static void ReviseInLibraryInformation() throws SQLException {
       Scanner sc = new Scanner(System.in);
       List<String> list = List.of("ISBN", "LibraryName", "PublishingHouse", "Author", "Borrowable", "CollectionNumber", "BorrowableNumber");
       Connection connect = getConnection();
       QueryRunner queryRunner = new QueryRunner();
       System.out.println("请输入待修改图书的ISBN");
       String ISBN = sc.next();
       String sql = "update LibraryInformation set ";
       System.out.println("请选择将要修改的信息");
       int i = 1;
       for (String s : list){
           System.out.println(i + "." + s + "\t");
           i++;
       }
       int order = sc.nextInt();
       if (order <= 0 || order > list.size()) System.out.println("输入错误");
       else {
           System.out.println("请输入修改后的信息");
           sql += list.get(order - 1) + "='"+ sc.next() +"' where ISBN=" + ISBN;
           try{
               int j = queryRunner.update(connect, sql);
               if (j != 0) System.out.println("修改成功");
               else System.out.println("输入sql有误修改失败");
           }catch (Exception e){
               System.out.println("修改失败");
           }
       }
       getClose(null, null, connect);
    }

    // 以下为向读者表中加入读者的函数
    public static void AddToReaderInformation() throws SQLException {
       Scanner sc = new Scanner(System.in);
       Connection connect = getConnection();
       QueryRunner queryRunner = new QueryRunner();
       List<String> list = List.of("LibraryCardNumber", "ReaderName", "Gender", "JobTitle","ReaderBorrowableNumber",
               "ReaderBorrowedNumber", "Department", "PhoneNumber");
       String sql = "insert into ReaderInformation value(?,?,?,?,?,?,?,?)";
       List<String> InputList = new ArrayList<>();
       for (String s : list){
           System.out.println("请输入" + s);
           InputList.add(sc.next());
       }
       try{
           int i = queryRunner.update(connect, sql, InputList.get(0),InputList.get(1),InputList.get(2), InputList.get(3),
                   Double.parseDouble(InputList.get(4)),Double.parseDouble(InputList.get(5)),
                   InputList.get(6),InputList.get(7));
           if (i != 0) System.out.println("添加成功");
           else System.out.println("添加失败");
       } catch (Exception e){
           System.out.println("输入sql有误，添加失败");
       }
       getClose(null, null, connect);
    }

    // 以下为删除读者信息的函数
    public static void RemoveFromReaderInformation() throws SQLException {
        Scanner sc = new Scanner(System.in);
        Connection connect = getConnection();
        QueryRunner queryRunner = new QueryRunner();
        String sql = "delete from ReaderInformation where ";
        System.out.println("请输入您的索引方式\n1. 借书卡号索引\t2. 读者名索引\t3. 电话号码索引");
        int order = sc.nextInt();
        if (order == 1){
            System.out.println("请输入欲删除读者的读书卡号");
            sql += "LibraryCardNumber=" + sc.next();
        } else if (order == 2) {
            System.out.println("请输入欲删除读者的用户名");
            sql += "ReaderName='" + sc.next() + "'";
        } else if (order == 3) {
            System.out.println("请输入欲删除读者的电话号码");
            sql += "PhoneNumber=" + sc.next();
        } else System.out.println("输入错误");
        System.out.println("请问是否删除该读者(Y/N)");
        String order_1 = sc.next();
        if (order_1.equals("Y")){
            try{
                int i  = queryRunner.update(connect, sql);
                System.out.println("删除成功");
            }catch (Exception e) {
                System.out.println("输入sql有误，删除失败");
            }
        }
        getClose(null, null, connect);
    }
    // 以下为修改读者信息的函数
    public static void ReviseInReaderInformation() throws SQLException {
        Scanner sc = new Scanner(System.in);
        List<String> list = List.of("LibraryCardNumber", "ReaderName", "Gender", "JobTitle","ReaderBorrowableNumber",
                "ReaderBorrowedNumber", "Department", "PhoneNumber");
        Connection connect = getConnection();
        QueryRunner queryRunner = new QueryRunner();
        String sql = "update ReaderInformation set ";
        System.out.println("请输入您的索引方式\n1. 借书卡号索引\t2. 读者名索引\t3. 电话号码索引");
        int order = sc.nextInt();
        String condition = "";
        if (order == 1){
            System.out.println("请输入欲更改信息读者的借书卡号");
            condition += " where LibraryCardNumber=" + sc.next();
        } else if (order == 2) {
            System.out.println("请输入欲更改信息读者用户名");
            condition += " where ReaderName='" + sc.next()+"'";
        } else if (order == 3) {
            System.out.println("请输入欲更改读者的电话号码");
            condition += " where PhoneNumber=" + sc.next();
        } else System.out.println("输入错误");
        int i = 1;
        System.out.println("请选择将要修改的信息");
        for(String s : list){
            System.out.println(i + "." + s);
            i++;
        }
        int order_1 = sc.nextInt();
        if (order_1 <= 0 || order_1 > list.size()) System.out.println("输入错误");
        else {
            try{
                System.out.println("请输入修改后的信息");
                sql += list.get(order_1 - 1) + "='" + sc.next() + "'" + condition;
                int j = queryRunner.update(connect, sql);
                if (j != 0) System.out.println("修改成功");
                else System.out.println("修改失败");
            } catch (Exception e){
                System.out.println("输入sql语句有误，修改失败");
            }
        }
        getClose(null, null, connect);
    }
    // 以下为向登录系统中添加用户的函数
    public static void AddToLogin() throws SQLException {
       Scanner sc = new Scanner(System.in);
       Connection connect = getConnection();
       QueryRunner queryRunner = new QueryRunner();
       List<String> list = List.of("user", "password", "permission");
       String sql = "insert into Login value(?,?,?)";
       List<String> ValueList = new ArrayList<>();
       for (String s : list){
           System.out.println("请输入" + s);
           ValueList.add(sc.next());
       }
       try{
           int i = queryRunner.update(connect, sql, ValueList.get(0), ValueList.get(1), ValueList.get(2));
           if (i != 0) System.out.println("添加成功");
           else System.out.println("添加失败");
       }catch (Exception e){
           System.out.println("输入sql语句有误，添加失败");
       }
       getClose(null, null, connect);
    }
    // 以下为从登录系统中删除用户的操作
    public static void RemoveFromLogin() throws SQLException {
       Scanner sc = new Scanner(System.in);
       Connection connect = getConnection();
       QueryRunner queryRunner = new QueryRunner();
       String sql = "delete from Login where user=";
       System.out.println("请输入将删除用户的用户名");
       sql += "'" + sc.next() + "'";
       try{
           int i = queryRunner.update(connect ,sql);
           if (i != 0) System.out.println("删除成功");
           else System.out.println("删除失败");
       }catch (Exception e){
           System.out.println("输入sql有误，删除失败");
       }
       getClose(null, null, connect);
    }
    // 以下为管理员修改用户登录信息的函数
    public static void ReviseInLogin() throws SQLException {
        Scanner sc = new Scanner(System.in);
        Connection connect = getConnection();
        QueryRunner queryRunner = new QueryRunner();
        List<String> list = List.of("user", "password", "permission");
        String sql = "update Login set ";
        System.out.println("请输入将修改用户的用户名");
        String user = " where user='" + sc.next() + "'";
        int i = 1;
        System.out.println("请选择将要修改的信息");
        for(String s : list){
            System.out.println(i + "." + s);
            i++;
        }
        int order = sc.nextInt();
        System.out.println("请输入修改后的信息");
        String s = sc.next();
        sql += list.get(order - 1) + "='" + s + "'" + user;
        try{
            int j = queryRunner.update(connect, sql);
            if (j != 0) System.out.println("更改完成");
            else System.out.println("更改失败");
        }catch (Exception e){
            System.out.println("输入的sql有误， 更改失败");
        }
        getClose(null, null, connect);
    }
    // 以下为普通用户向图书馆借阅书籍的函数
    public static void Borrowing() throws SQLException {
       Scanner sc = new Scanner(System.in);
       System.out.println("请输入被借书籍的ISBN");
       String ISBN = sc.next();
       Connection connect = getConnection();
       QueryRunner queryRunner = new QueryRunner();
       String sqlLibrary = "update LibraryInformation set BorrowableNumber = BorrowableNumber - 1.0 where ISBN=" + ISBN;
       String sqlBorrowing = "insert into BorrowingInformation value(?,?,Now(),25,Now(),100)";
       String sql = "select * from LibraryInformation where ISBN=" + ISBN;
       Map<String, Object> map = null;
       try {
           map = queryRunner.query(connect, sql, new MapHandler());
       }catch (Exception e){
           System.out.println("输入sql语句有误");
       }
        if (map != null && !map.isEmpty()) {
            if (map.get("BorrowableNumber").equals("0")) {
                System.out.println("很抱歉，该图书已全部借出");
            } else {
                System.out.println("该图书库存" + map.get("CollectionNumber") + "本，请问是否借阅(Y/N)");
                String order = sc.next();
                if (order.equals("Y")) {
                    System.out.println("请输入您的借书卡号");
                    String CardNumber = sc.next();
                    connect.setAutoCommit(false);
                    PreparedStatement preparedStatement = connect.prepareStatement(sqlLibrary);
                    preparedStatement.executeUpdate();
                    preparedStatement = connect.prepareStatement(sqlBorrowing);
                    preparedStatement.setString(1, CardNumber);
                    preparedStatement.setString(2, ISBN);
                    preparedStatement.executeUpdate();
                    try {
                        connect.commit();
                        System.out.println("借阅成功");
                    } catch (Exception e) {
                        System.out.println("输入信息有误借阅失败");
                    }
                    getClose(null, preparedStatement, null);
                }
            }
        }
        getClose(null, null, connect);
    }
    // 以下方法为用户向图书馆归还图书的函数
    public static void ReturnBook() throws SQLException {
        Scanner sc = new Scanner(System.in);
        Connection connect = getConnection();
        System.out.println("请输入归还图书的ISBN");
        String ISBN = sc.next();
        String sqlBorrowing = "delete from BorrowingInformation where ISBN=" + ISBN;
        String sqlLibrary = "update LibraryInformation set BorrowableNumber = BorrowableNumber + 1 where ISBN=" + ISBN;
        connect.setAutoCommit(false);
        PreparedStatement preparedStatement = connect.prepareStatement(sqlLibrary);
        preparedStatement.executeUpdate(sqlLibrary);
        preparedStatement = connect.prepareStatement(sqlBorrowing);
        preparedStatement.executeUpdate(sqlBorrowing);
        try {
            connect.commit();
            System.out.println("操作成功");
        }catch (Exception e){
            connect.rollback();
            System.out.println("操作失败");
        }
        getClose(null, preparedStatement, connect);
   }
}
