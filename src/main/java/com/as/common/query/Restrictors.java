package com.as.common.query;

/**
 * Created by Newbody on 2016/3/11.
 */
public class Restrictors {
    public static Restrictor create(){
        Restrictor restrictor = null;
        try {
            restrictor = (Restrictor) Class.forName("com.sm.query.hibernate.HibernateRestrictor").newInstance();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
        return restrictor;
    }
}
