package com.module.permission;

import java.security.Permission;

/**
 * Created by piglet on 2017/3/19.
 */
public class CategoryPemission extends Permission {
    private String category;

    @Override
    public String getActions() {
        return category;
    }

    public CategoryPemission(String roleid,String category){
        super(roleid);
        this.category=category;
    }
    @Override
    public String toString() {
        return super.toString();
    }

    @Override
    public int hashCode() {
        return getName().hashCode() + category.hashCode();


    }

    @Override
    public boolean implies(Permission other) {
        if (!(other instanceof CategoryPemission)) return false;
        CategoryPemission b = (CategoryPemission) other;
        if(b.getName().equals("1"))return true;
        if(b.getName().equals("2")){
            if(b.category.equals("english")||b.category.equals("chinese")||b.category.equals("math")){
                return true;
            }
            else{
                return false;
            }
        }
        if(b.getName().equals("4")){
            if(b.category.equals("skill")||b.category.equals("novel")){
                return true;
            }
            else{
                return false;
            }
        }
        return false;
    }

    @Override
    public boolean equals(Object other) {
        if (other == null) return false;
        if (!getClass().equals(other.getClass()))
            return false;
        CategoryPemission b = (CategoryPemission) other;
        if (category.equals(b.category) && getName().equals(b.getName()))
            return true;
        else
            return false;
    }



}
