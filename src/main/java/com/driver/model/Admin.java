package com.driver.model;

import net.bytebuddy.dynamic.loading.InjectionClassLoader;

import javax.persistence.*;

@Entity
@Table(name = "admin")
public class Admin{

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
   private int AdminId;
   private String userName;
   private String password;

    public Admin() {
    }

    public Admin(int adminId, String userName, String password) {
        AdminId = adminId;
        this.userName = userName;
        this.password = password;
    }

    public int getAdminId() {
        return AdminId;
    }

    public void setAdminId(int adminId) {
        AdminId = adminId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}