package com.module.dao.daoimpl;


import com.module.bean.Book;
import org.hibernate.SQLQuery;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;
import java.util.List;
import com.module.bean.User;
import com.module.dao.UserDAO;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.hibernate.Query;

public class UserDAOImpl extends HibernateDaoSupport implements UserDAO
{
    private String curusername;
    public void setCurusername(String curusername){
        this.curusername=curusername;
    }
    public String getCurusername(){
        return curusername;
    }
    @SuppressWarnings("unchecked")
//    @Override
    public int login(String username,String password)
    {

        String sql = "from User as user where user.username='"+username+"' and user.password='"+password+"'";

        try {
            List<User> users = (List<User>) this.getHibernateTemplate().find(sql);
            if (users.size()!=0){
                System.out.println("ok");
                System.out.println(users.get(0).getRoleid());
                setCurusername(username);
                return users.get(0).getRoleid();
            }

        }
        catch(Exception e){
            e.printStackTrace();
        }
        return -1;
    }
    public User query(String username){
        String sql = "from User as user where user.username='"+username+"'";

            List<User> result = (List<User>) this.getHibernateTemplate().find(sql);

        if(result!=null)return result.get(0);
        else
            return null;
    }
    public void delete(int id) {
       String sql = "from User as user where user.id='" + id + "'";
        List<User> result = (List<User>) this.getHibernateTemplate().find(sql);
        User temp = result.get(0);
        this.getHibernateTemplate().delete(temp);
        /*SessionFactory sessionFactory=new Configuration().configure().buildSessionFactory();
        Session session=sessionFactory.openSession();
        String hql="delete User as u where u.id=?";

        Query query=session.createQuery(hql);

        query.setInteger(0,id);

        query.executeUpdate();

        session.beginTransaction().commit();*/



    }
    public void update(User user){
        String sql = "from User as user where user.id='"+user.getId()+"'";
        List<User> result=(List<User>) this.getHibernateTemplate().find(sql);
        User temp=result.get(0);
        temp.setEmail(user.getEmail());
        temp.setPassword(user.getPassword());
        temp.setUsername(user.getUsername());
        temp.setPhone(user.getPhone());
        this.getHibernateTemplate().update(temp);


    }
    public boolean insert(User user){
        String sql = "from User as user where user.username='"+user.getUsername()+"'";
        List<User> result=(List<User>) this.getHibernateTemplate().find(sql);
        if(result.size()==0){
            user.setRoleid(2);
            this.getHibernateTemplate().save(user);
            return true;
        }
        else
            return false;
    }
    public void updatescore(String username){
        String sql = "from User as user where user.username='"+username+"'";
        List<User> result=(List<User>) this.getHibernateTemplate().find(sql);
        User temp=result.get(0);
        temp.setScore(temp.getScore()+1);
        this.getHibernateTemplate().update(temp);
    }
    public boolean register(User user){
        String sql = "from User as user where user.username='"+user.getUsername()+"'";
        List<User> result=(List<User>) this.getHibernateTemplate().find(sql);
        if(result.size()==0){
            user.setRoleid(2);
            this.getHibernateTemplate().save(user);
            return true;
        }
        else
            return false;


    }
    public List<User> userGet(){
        String sql="from User";
        List<User> result=(List<User>) this.getHibernateTemplate().find(sql);
        return result;
    }
}
