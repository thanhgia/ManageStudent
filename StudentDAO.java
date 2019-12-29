/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gia.student;

import gia.connection.MyConnection;
import java.io.Serializable;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.util.ArrayList;
import java.util.List;
import javax.naming.NamingException;

/**
 *
 * @author Administrator
 */
public class StudentDAO implements Serializable {

    private List<StudentDTO> listStudent;

    public List<StudentDTO> getListStudent() {
        return listStudent;

    }

    public void showAllStudent() throws SQLException, NamingException {
        Connection con = null;
        PreparedStatement stm = null;
        ResultSet rs = null;

        try {
            con = MyConnection.makeConnection();
            if (con != null) {
                String sql = "select id, firstname, middlename, lastname, class, status, address1, address2, phone, sex from tbl_Student";
                stm = con.prepareCall(sql);
                rs = stm.executeQuery();
                while (rs.next()) {
                    String id = rs.getString("id");
                    String firstname = rs.getString("firstname");
                    String middlename = rs.getString("middlename");
                    String lastname = rs.getString("lastname");
                    String classes = rs.getString("class");
                    int status = rs.getInt("status");
                    String address1 = rs.getString("address1");
                    String address2 = rs.getString("address2");
                    String phone = rs.getString("phone");
                    boolean sex = rs.getBoolean("sex");

                    StudentDTO dto = new StudentDTO(id, firstname, middlename, lastname, classes, status, address1, address2, phone, sex);
                    if (this.listStudent == null) {
                        this.listStudent = new ArrayList<>();
                    }
                    this.listStudent.add(dto);
                }
            }
        } finally {
            if (rs != null) {
                rs.close();
            }
            if (stm != null) {
                stm.close();
            }
            if (con != null) {
                con.close();
            }
        }

    }

    public void searchStudentById(String searchValue) throws SQLException, NamingException {
        Connection con = null;
        PreparedStatement stm = null;
        ResultSet rs = null;

        try {
            con = MyConnection.makeConnection();
            if (con != null) {
                String sql = "select id, firstname, middlename, lastname, class, status, address1, address2, phone, sex from tbl_Student "
                        + "where id = ?";
                stm = con.prepareCall(sql);
                stm.setString(1, searchValue);
                rs = stm.executeQuery();
                while (rs.next()) {
                    String id = rs.getString("id");
                    String firstname = rs.getString("firstname");
                    String middlename = rs.getString("middlename");
                    String lastname = rs.getString("lastname");
                    String classes = rs.getString("class");
                    int status = rs.getInt("status");
                    String address1 = rs.getString("address1");
                    String address2 = rs.getString("address2");
                    String phone = rs.getString("phone");
                    boolean sex = rs.getBoolean("sex");

                    StudentDTO dto = new StudentDTO(id, firstname, middlename, lastname, classes, status, address1, address2, phone, sex);
                    if (this.listStudent == null) {
                        this.listStudent = new ArrayList<>();
                    }
                    this.listStudent.add(dto);
                }
            }
        } finally {
            if (rs != null) {
                rs.close();
            }
            if (stm != null) {
                stm.close();
            }
            if (con != null) {
                con.close();
            }
        }

    }

    public void searchStudentByFullName(String searchValue) throws SQLException, NamingException {
        Connection con = null;
        PreparedStatement stm = null;
        ResultSet rs = null;

        try {
            con = MyConnection.makeConnection();
            if (con != null) {
                String sql = "select id, firstname, middlename, lastname, class, status, address1, address2, phone, sex from tbl_Student "
                        + "where firstname + ' ' + middlename + ' ' + lastname like ?";
                stm = con.prepareCall(sql);
                stm.setString(1, "%" + searchValue + "%");
                rs = stm.executeQuery();
                while (rs.next()) {
                    String id = rs.getString("id");
                    String firstname = rs.getString("firstname");
                    String middlename = rs.getString("middlename");
                    String lastname = rs.getString("lastname");
                    String classes = rs.getString("class");
                    int status = rs.getInt("status");
                    String address1 = rs.getString("address1");
                    String address2 = rs.getString("address2");
                    String phone = rs.getString("phone");
                    boolean sex = rs.getBoolean("sex");

                    StudentDTO dto = new StudentDTO(id, firstname, middlename, lastname, classes, status, address1, address2, phone, sex);
                    if (this.listStudent == null) {
                        this.listStudent = new ArrayList<>();
                    }
                  
                    
                    //check student exist in list
                    int checkDup = 0;
                    for (int i = 0; i < listStudent.size(); i++) {
                        if (listStudent.get(i).getId().equalsIgnoreCase(id)) {
                            checkDup++;
                        }
                    }
                    if (checkDup == 0) {
                        this.listStudent.add(dto);
                    }

                }
            }
        } finally {
            if (rs != null) {
                rs.close();
            }
            if (stm != null) {
                stm.close();
            }
            if (con != null) {
                con.close();
            }
        }

    }
    public void searchStudentByFirstAndLastName(String searchValue) throws SQLException, NamingException {
        Connection con = null;
        PreparedStatement stm = null;
        ResultSet rs = null;

        try {
            con = MyConnection.makeConnection();
            if (con != null) {
                String sql = "select id, firstname, middlename, lastname, class, status, address1, address2, phone, sex from tbl_Student "
                        + "where firstname + ' ' + lastname like ?";
                stm = con.prepareCall(sql);
                stm.setString(1, "%" + searchValue + "%");
                rs = stm.executeQuery();
                while (rs.next()) {
                    String id = rs.getString("id");
                    String firstname = rs.getString("firstname");
                    String middlename = rs.getString("middlename");
                    String lastname = rs.getString("lastname");
                    String classes = rs.getString("class");
                    int status = rs.getInt("status");
                    String address1 = rs.getString("address1");
                    String address2 = rs.getString("address2");
                    String phone = rs.getString("phone");
                    boolean sex = rs.getBoolean("sex");

                    StudentDTO dto = new StudentDTO(id, firstname, middlename, lastname, classes, status, address1, address2, phone, sex);
                    if (this.listStudent == null) {
                        this.listStudent = new ArrayList<>();
                    }
                  
                    
                    //check student exist in list
                    int checkDup = 0;
                    for (int i = 0; i < listStudent.size(); i++) {
                        if (listStudent.get(i).getId().equalsIgnoreCase(id)) {
                            checkDup++;
                        }
                    }
                    if (checkDup == 0) {
                        this.listStudent.add(dto);
                    }

                }
            }
        } finally {
            if (rs != null) {
                rs.close();
            }
            if (stm != null) {
                stm.close();
            }
            if (con != null) {
                con.close();
            }
        }

    }

    public void searchStudentByFirstName(String searchValue) throws SQLException, NamingException {
        Connection con = null;
        PreparedStatement stm = null;
        ResultSet rs = null;

        try {
            con = MyConnection.makeConnection();
            if (con != null) {
                String sql = "select id, firstname, middlename, lastname, class, status, address1, address2, phone, sex from tbl_Student "
                        + "where firstname like ?";
                stm = con.prepareCall(sql);
                stm.setString(1, "%" + searchValue + "%");
                rs = stm.executeQuery();
                while (rs.next()) {
                    String id = rs.getString("id");
                    String firstname = rs.getString("firstname");
                    String middlename = rs.getString("middlename");
                    String lastname = rs.getString("lastname");
                    String classes = rs.getString("class");
                    int status = rs.getInt("status");
                    String address1 = rs.getString("address1");
                    String address2 = rs.getString("address2");
                    String phone = rs.getString("phone");
                    boolean sex = rs.getBoolean("sex");

                    StudentDTO dto = new StudentDTO(id, firstname, middlename, lastname, classes, status, address1, address2, phone, sex);
                    if (this.listStudent == null) {
                        this.listStudent = new ArrayList<>();
                    }

                    //check student exist in list
                    int checkDup = 0;
                    for (int i = 0; i < listStudent.size(); i++) {
                        if (listStudent.get(i).getId().equalsIgnoreCase(id)) {
                            checkDup++;
                        }
                    }
                    if (checkDup == 0) {
                        this.listStudent.add(dto);
                    }

                }
            }
        } finally {
            if (rs != null) {
                rs.close();
            }
            if (stm != null) {
                stm.close();
            }
            if (con != null) {
                con.close();
            }
        }

    }

    public void searchStudentByMiddleName(String searchValue) throws SQLException, NamingException {
        Connection con = null;
        PreparedStatement stm = null;
        ResultSet rs = null;

        try {
            con = MyConnection.makeConnection();
            if (con != null) {
                String sql = "select id, firstname, middlename, lastname, class, status, address1, address2, phone, sex from tbl_Student "
                        + "where middlename like ?";
                stm = con.prepareCall(sql);
                stm.setString(1, "%" + searchValue + "%");
                rs = stm.executeQuery();
                while (rs.next()) {
                    String id = rs.getString("id");
                    String firstname = rs.getString("firstname");
                    String middlename = rs.getString("middlename");
                    String lastname = rs.getString("lastname");
                    String classes = rs.getString("class");
                    int status = rs.getInt("status");
                    String address1 = rs.getString("address1");
                    String address2 = rs.getString("address2");
                    String phone = rs.getString("phone");
                    boolean sex = rs.getBoolean("sex");

                    StudentDTO dto = new StudentDTO(id, firstname, middlename, lastname, classes, status, address1, address2, phone, sex);
                    if (this.listStudent == null) {
                        this.listStudent = new ArrayList<>();
                    }

                    //check student exist in list
                    int checkDup = 0;
                    for (int i = 0; i < listStudent.size(); i++) {
                        if (listStudent.get(i).getId().equalsIgnoreCase(id)) {
                            checkDup++;
                        }
                    }
                    if (checkDup == 0) {
                        this.listStudent.add(dto);
                    }

                }
            }
        } finally {
            if (rs != null) {
                rs.close();
            }
            if (stm != null) {
                stm.close();
            }
            if (con != null) {
                con.close();
            }
        }

    }

    public void searchStudentByLastName(String searchValue) throws SQLException, NamingException {
        Connection con = null;
        PreparedStatement stm = null;
        ResultSet rs = null;

        try {
            con = MyConnection.makeConnection();
            if (con != null) {
                String sql = "select id, firstname, middlename, lastname, class, status, address1, address2, phone, sex from tbl_Student "
                        + "where lastname like ?";
                stm = con.prepareCall(sql);
                stm.setString(1, "%" + searchValue + "%");
                rs = stm.executeQuery();
                while (rs.next()) {
                    String id = rs.getString("id");
                    String firstname = rs.getString("firstname");
                    String middlename = rs.getString("middlename");
                    String lastname = rs.getString("lastname");
                    String classes = rs.getString("class");
                    int status = rs.getInt("status");
                    String address1 = rs.getString("address1");
                    String address2 = rs.getString("address2");
                    String phone = rs.getString("phone");
                    boolean sex = rs.getBoolean("sex");

                    StudentDTO dto = new StudentDTO(id, firstname, middlename, lastname, classes, status, address1, address2, phone, sex);
                    if (this.listStudent == null) {
                        this.listStudent = new ArrayList<>();
                    }

                    //check student exist in list
                    int checkDup = 0;
                    for (int i = 0; i < listStudent.size(); i++) {
                        if (listStudent.get(i).getId().equalsIgnoreCase(id)) {
                            checkDup++;
                        }
                    }
                    if (checkDup == 0) {
                        this.listStudent.add(dto);
                    }

                }
            }
        } finally {
            if (rs != null) {
                rs.close();
            }
            if (stm != null) {
                stm.close();
            }
            if (con != null) {
                con.close();
            }
        }

    }

    public void searchStudentByStatus(int searchValue) throws SQLException, NamingException {
        Connection con = null;
        PreparedStatement stm = null;
        ResultSet rs = null;

        try {
            con = MyConnection.makeConnection();
            if (con != null) {
                String sql = "select id, firstname, middlename, lastname, class, status, address1, address2, phone, sex from tbl_Student "
                        + "where status = ?";
                stm = con.prepareCall(sql);
                stm.setInt(1, searchValue);
                rs = stm.executeQuery();
                while (rs.next()) {
                    String id = rs.getString("id");
                    String firstname = rs.getString("firstname");
                    String middlename = rs.getString("middlename");
                    String lastname = rs.getString("lastname");
                    String classes = rs.getString("class");
                    int status = rs.getInt("status");
                    String address1 = rs.getString("address1");
                    String address2 = rs.getString("address2");
                    String phone = rs.getString("phone");
                    boolean sex = rs.getBoolean("sex");

                    StudentDTO dto = new StudentDTO(id, firstname, middlename, lastname, classes, status, address1, address2, phone, sex);
                    if (this.listStudent == null) {
                        this.listStudent = new ArrayList<>();
                    }

                    //check student exist in list
                    int checkDup = 0;
                    for (int i = 0; i < listStudent.size(); i++) {
                        if (listStudent.get(i).getId().equalsIgnoreCase(id)) {
                            checkDup++;
                        }
                    }
                    if (checkDup == 0) {
                        this.listStudent.add(dto);
                    }

                }
            }
        } finally {
            if (rs != null) {
                rs.close();
            }
            if (stm != null) {
                stm.close();
            }
            if (con != null) {
                con.close();
            }
        }

    }

    public boolean updateStudent(String id, String classes, String addr1, String addr2, int status, String phone) throws SQLException, NamingException {
        Connection con = null;
        PreparedStatement stm = null;
        ResultSet rs = null;

        try {
            con = MyConnection.makeConnection();
            if (con != null) {
                String sql = "update tbl_Student set class = ?, address1 = ?, address2 = ?, status = ?, phone = ? where id = ?";
                stm = con.prepareCall(sql);
                stm.setString(1, classes);
                stm.setString(2, addr1);
                if(!addr2.isEmpty()){
                    stm.setString(3, addr2);
                }else{
                    stm.setNull(3, Types.NULL);
                }
                
                stm.setInt(4, status);
                stm.setString(5, phone);
                stm.setString(6, id);
                int check = stm.executeUpdate();
                if (check > 0) {
                    return true;
                }
            }
        } finally {
            if (rs != null) {
                rs.close();
            }
            if (stm != null) {
                stm.close();
            }
            if (con != null) {
                con.close();
            }
        }
        return false;
    }

    public boolean insertStudent(StudentDTO dto) throws SQLException, NamingException {
        Connection con = null;
        PreparedStatement stm = null;
        ResultSet rs = null;
        try {
            con = MyConnection.makeConnection();
            if (con != null) {
                String sql = "insert into tbl_Student(id, firstname, middlename, lastname, class, status, address1, address2, phone, sex) values(?,?,?,?,?,?,?,?,?,?)";
                stm = con.prepareCall(sql);
                stm.setString(1, dto.getId());
                stm.setString(2, dto.getFirstName());
                if (dto.getMiddleName().isEmpty()) {
                    stm.setNull(3, Types.NULL);
                } else {
                    stm.setString(3, dto.getMiddleName());
                }
                stm.setString(4, dto.getLastName());
                stm.setString(5, dto.getClasses());
                stm.setInt(6, dto.getStatus());
                stm.setString(7, dto.getAddress1());
                if (dto.getAddress2().isEmpty()) {
                    stm.setNull(8, Types.NULL);
                } else {
                    stm.setString(8, dto.getAddress2());
                }
                stm.setString(9, dto.getPhone());
                stm.setBoolean(10, dto.isSex());
                int check = stm.executeUpdate();
                if (check > 0) {
                    return true;
                }
            }
        } finally {
            if (rs != null) {
                rs.close();
            }
            if (stm != null) {
                stm.close();
            }
            if (con != null) {
                con.close();
            }
        }
        return false;
    }
}
