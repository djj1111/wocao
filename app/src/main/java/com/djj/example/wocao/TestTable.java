package com.djj.example.wocao;

import org.xutils.db.annotation.Column;
import org.xutils.db.annotation.Table;

/**
 * Created by djj on 2016/11/13.
 */

@Table(name = "TestTable", onCreated = "CREATE UNIQUE INDEX index_name ON TestTable(name,address,phone)")
public class TestTable {

    @Column(name = "id", isId = true)
    private int id;

    @Column(name = "name")
    private String name;

    @Column(name = "address")
    private String address;

    @Column(name = "phone")
    private String phone;

    // 一对一
    //public Child getChild(DbManager db) throws DbException {
    //    return db.selector(Child.class).where("parentId", "=", this.id).findFirst();
    //}

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    @Override
    public String toString() {
        return "TestTable{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", addrress='" + address + '\'' +
                ", phone=" + phone +
                '}';
    }
}