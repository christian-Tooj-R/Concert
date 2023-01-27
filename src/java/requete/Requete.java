package requete;

import connection.ConnectOracle;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.sql.Connection;
import java.sql.Date;
import java.sql.ResultSet;
import java.sql.Statement;

public class Requete {

    public Object[] select(Connection connect, String order) throws Exception {
        if (connect == null || connect.isClosed()) {
            connect = new ConnectOracle().getConnection();
        }
        String inorder = "";
        if (!order.equals("")) {
            inorder = " order by " + order;
        }
        Statement stmt = connect.createStatement();
        Statement stmt2 = connect.createStatement();
        Object[] o = null;
        try {
            stmt = connect.createStatement();
            stmt2 = connect.createStatement();
            Field[] field = this.getClass().getDeclaredFields();
            String requete="select * from " + this.getClass().getSimpleName();
            String counter="select count(*) from " + this.getClass().getSimpleName();
            String where="";
            for (int j = 0; j < field.length; j++) {
                if (field[j].getType().getName() == "int"
                        && ((int) this.getClass().getMethod("get" + field[j].getName()).invoke(this) != 0)) {
                    where= " where " + field[j].getName() + " = "+ this.getClass().getMethod("get" + field[j].getName()).invoke(this);
                } if (field[j].getType().getName() == "java.lang.String"
                        && (this.getClass().getMethod("get" + field[j].getName()).invoke(this) != null)) {
                    
                    if(where.length()<7){
                        where= " where  " + field[j].getName() + " = \'"+ this.getClass().getMethod("get" + field[j].getName()).invoke(this) + "\'";
                    }else{
                        where= where +" and "+ field[j].getName() + " = \'"+ this.getClass().getMethod("get" + field[j].getName()).invoke(this) + "\'";
                    }
                } if (field[j].getType().getName() == "java.sql.Date"
                        && (this.getClass().getMethod("get" + field[j].getName()).invoke(this) != null)) {
                    
                    if(where.length()<7){
                        where= " where  " + field[j].getName() + " = TO_Date(\'"+ this.getClass().getMethod("get" + field[j].getName()).invoke(this) + "\','YYYY-MM-DD')";
                    }else{
                        where= where +" and "+ field[j].getName() + " = TO_Date(\'"+ this.getClass().getMethod("get" + field[j].getName()).invoke(this) + "\','YYYY-MM-DD')";
                    }
                }
            }
            /*********/
            System.out.println(requete + where + inorder);
            ResultSet res = stmt.executeQuery(requete + where + inorder);
            /***** */
            ResultSet res2 = stmt2.executeQuery(counter + where);

            res2.next();
            o = new Object[res2.getInt(1)];
            Class cls = Class.forName(this.getClass().getName());
            Method fonct;
            int count = 0;
            /*********/
            while (res.next() && count < o.length) {
                o[count] = cls.newInstance();
                for (int i = 0; i < field.length; i++) {
                    if (field[i].getType().getName().equals("java.lang.String")) {
                        fonct = cls.getMethod("set" + field[i].getName(), String.class);
                        fonct.invoke(o[count], res.getString(i + 1));
                    } else if (field[i].getType().getName().equals("int")) {
                        fonct = cls.getMethod("set" + field[i].getName(), int.class);
                        fonct.invoke(o[count], res.getInt(i + 1));
                    } else if (field[i].getType().getName().equals("double")) {
                        fonct = cls.getMethod("set" + field[i].getName(), double.class);
                        fonct.invoke(o[count], res.getDouble(i + 1));
                    } else if (field[i].getType().getName().equals("java.sql.Date")) {
                        fonct = cls.getMethod("set" + field[i].getName(), Date.class);
                        fonct.invoke(o[count], res.getDate(i + 1));
                    }
                }
                count++;
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            connect.commit();
            stmt.close();
            stmt2.close();
            connect.close();
        }
        return o;
    }

    /******** insert */
    public void insert(Connection connect) throws Exception {
        if (connect == null || connect.isClosed()) {
            connect = new ConnectOracle().getConnection();
        }
        connect.setAutoCommit(false);
        Statement stmt = connect.createStatement();
        try {

            String tab_vaovao = "", values = ""; 

            Field[] field = this.getClass().getDeclaredFields();
            int verifSeq = 0;
            String sequence = this.getSequence(connect);
            if (!sequence.equals("")) {
                verifSeq = 1;
            }
            for (int j = verifSeq; j < field.length; j++) {
                if (field[j].getType().getName() == "int"
                        && ( this.getClass().getMethod("get" + field[j].getName()).invoke(this) != null)) {
                    if (j == verifSeq) {
                        values = values
                                .concat(String
                                        .valueOf(this.getClass().getMethod("get" + field[j].getName()).invoke(this)));
                        tab_vaovao = tab_vaovao.concat(field[j].getName());
                    } else {
                        values = values
                                .concat("," + this.getClass().getMethod("get" + field[j].getName()).invoke(this));
                        tab_vaovao = tab_vaovao.concat("," + field[j].getName());
                    }
                } else if (field[j].getType().getName() == "java.lang.String"
                        && (this.getClass().getMethod("get" + field[j].getName()).invoke(this) != null)) {
                    if (j == verifSeq) {
                        values = values
                                .concat("\'" + this.getClass().getMethod("get" + field[j].getName()).invoke(this)
                                        + "\'");
                        tab_vaovao = tab_vaovao.concat(field[j].getName());
                    } else {
                        values = values
                                .concat(",\'" + this.getClass().getMethod("get" + field[j].getName()).invoke(this)
                                        + "\'");
                        tab_vaovao = tab_vaovao.concat("," + field[j].getName());
                    }
                } else if (field[j].getType().getName() == "double"
                        && (this.getClass().getMethod("get" + field[j].getName()).invoke(this) != null)) {
                    if (j == verifSeq) {
                        values = values
                                .concat(String
                                        .valueOf(this.getClass().getMethod("get" + field[j].getName()).invoke(this)));
                        tab_vaovao = tab_vaovao.concat(field[j].getName());
                    } else {
                        values = values
                                .concat("," + this.getClass().getMethod("get" + field[j].getName()).invoke(this));
                        tab_vaovao = tab_vaovao.concat("," + field[j].getName());
                    }
                }else if (field[j].getType().getName() == "java.sql.Date"
                        && (this.getClass().getMethod("get" + field[j].getName()).invoke(this) != null)) {
                    if (j == verifSeq) {
                        values = values
                                .concat("TO_DATE("+"\'"+String
                                        .valueOf(this.getClass().getMethod("get" + field[j].getName()).invoke(this))+"\'"+",'YYYY-MM-DD')");
                        tab_vaovao = tab_vaovao.concat(field[j].getName());
                    } else {
                        values = values
                                .concat(",TO_DATE("+"\'"+String
                                        .valueOf(this.getClass().getMethod("get" + field[j].getName()).invoke(this))+"\'"+",'YYYY-MM-DD')");
                        tab_vaovao = tab_vaovao.concat("," + field[j].getName());
                    }
                }

            }

            if (verifSeq != 0) {
                System.out.println("insert into " + this.getClass().getSimpleName() + "(" + field[0].getName() + ","
                        + tab_vaovao + ") values(\'" + sequence + "\'," + values + ")");
                stmt.executeUpdate("insert into " + this.getClass().getSimpleName() + "(" + field[0].getName() + ","
                        + tab_vaovao + ") values(\'" + sequence + "\'," + values + ")");
            } else {
                System.out.println(
                        "insert into " + this.getClass().getSimpleName() + "(" + tab_vaovao + ") values(" + values
                                + ")");
                stmt.executeUpdate(
                        "insert into " + this.getClass().getSimpleName() + "(" + tab_vaovao + ") values(" + values
                                + ")");
            }

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            connect.commit();
            stmt.close();
            connect.close();
        }
    }

    /******* update */
    public void update(Connection connect, String where1, String where2) throws Exception {
        if (connect == null || connect.isClosed()) {
            connect = new ConnectOracle().getConnection();
        }
        connect.setAutoCommit(false);
        Statement stmt = null;
        String tab_vaovao = "", values = "";

        try {
            stmt = connect.createStatement();
            Field[] field = this.getClass().getDeclaredFields();

            for (int j = 1; j < field.length; j++) {
                if (field[j].getType().getName() == "int"
                        && ((int) this.getClass().getMethod("get" + field[j].getName()).invoke(this) >= 0)) {
                    if (j == 1) {
                        values = values
                                .concat("\'" + this.getClass().getMethod("get" + field[j].getName()).invoke(this)
                                        + "\'");
                        tab_vaovao = tab_vaovao.concat(field[j].getName());
                    } else {
                        values = values
                                .concat(",\'" + this.getClass().getMethod("get" + field[j].getName()).invoke(this)
                                        + "\'");
                        tab_vaovao = tab_vaovao.concat("," + field[j].getName());
                    }
                } else if (field[j].getType().getName() == "java.lang.String"
                        && (this.getClass().getMethod("get" + field[j].getName()).invoke(this) != null)) {
                    if (j == 1) {
                        values = values
                                .concat("\'" + this.getClass().getMethod("get" + field[j].getName()).invoke(this)
                                        + "\'");
                        tab_vaovao = tab_vaovao.concat(field[j].getName());
                    } else {
                        values = values
                                .concat(",\'" + this.getClass().getMethod("get" + field[j].getName()).invoke(this)
                                        + "\'");
                        tab_vaovao = tab_vaovao.concat("," + field[j].getName());
                    }
                } else if (field[j].getType().getName() == "double"
                        && (this.getClass().getMethod("get" + field[j].getName()).invoke(this) != null)) {
                    if (j == 1) {
                        values = values
                                .concat("\'" + this.getClass().getMethod("get" + field[j].getName()).invoke(this)
                                        + "\'");
                        tab_vaovao = tab_vaovao.concat(field[j].getName());
                    } else {
                        values = values
                                .concat(",\'" + this.getClass().getMethod("get" + field[j].getName()).invoke(this)
                                        + "\'");
                        tab_vaovao = tab_vaovao.concat("," + field[j].getName());
                    }
                } else if (field[j].getType().getName() == "java.sql.Date"
                        && (this.getClass().getMethod("get" + field[j].getName()).invoke(this) != null)) {
                    if (j == 1) {
                        values = values
                                .concat("\'" + this.getClass().getMethod("get" + field[j].getName()).invoke(this)
                                        + "\'");
                        tab_vaovao = tab_vaovao.concat(field[j].getName());
                    } else {
                        values = values
                                .concat(",\'" + this.getClass().getMethod("get" + field[j].getName()).invoke(this)
                                        + "\'");
                        tab_vaovao = tab_vaovao.concat("," + field[j].getName());
                    }
                }
            }

            String[] setting = tab_vaovao.split(",");
            String[] param = values.split(",");
            for (int i = 1; i < setting.length; i++) {
                stmt.executeUpdate("update " + this.getClass().getSimpleName() + " set " + setting[i] + " = "
                        + param[i] + " where " + where1 + " = \'" + where2 + "\'");
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        } finally {
            connect.close();
            stmt.close();
            connect.close();
        }
    }

    /**** delete */
    public void delete(Connection connect) throws Exception {
        if (connect == null || connect.isClosed()) {
            connect = new ConnectOracle().getConnection();
        }
        connect.setAutoCommit(false);
        Statement stmt = null;
        try {

            stmt = connect.createStatement();
            Field[] field = this.getClass().getDeclaredFields();

            for (int j = 0; j < field.length; j++) {
                if (field[j].getType().getName() == "int"
                        && ((int) this.getClass().getMethod("get" + field[j].getName()).invoke(this) != 0)) {
                    System.out.println("delete from " + this.getClass().getSimpleName() + " where " + field[j].getName()
                            + " = " + this.getClass().getMethod("get" + field[j].getName()).invoke(this));
                    stmt.executeUpdate("delete from " + this.getClass().getSimpleName() + " where " + field[j].getName()
                            + " = " + this.getClass().getMethod("get" + field[j].getName()).invoke(this));
                } else if (field[j].getType().getName() == "java.lang.String"
                        && (this.getClass().getMethod("get" + field[j].getName()).invoke(this) != null)) {
                    System.out.println("delete from " + this.getClass().getSimpleName() + " where " + field[j].getName()
                            + " = \'" + this.getClass().getMethod("get" + field[j].getName()).invoke(this) + "\'");
                    stmt.executeUpdate("delete from " + this.getClass().getSimpleName() + " where " + field[j].getName()
                            + " = \'" + this.getClass().getMethod("get" + field[j].getName()).invoke(this) + "\'");
                } else {
                    System.out.println("delete from " + this.getClass().getSimpleName());
                    stmt.executeUpdate("delete from " + this.getClass().getSimpleName());
                    break;
                }
            }

        } catch (Exception ex) {
            ex.printStackTrace();
        } finally {
            connect.close();
            stmt.close();
            connect.close();
        }
    }

    /***** sequence */
    public String getSequence(Connection connect) throws Exception {
        return "";
    }

    /************ Print */
    public void print(Object[] obj) throws Exception {
        Field[] field = obj[0].getClass().getDeclaredFields();
        for (int i = 0; i < obj.length; i++) {
            for (int j = 0; j < field.length; j++) {
                System.out.print(obj[i].getClass().getMethod("get" + field[j].getName()).invoke(obj[i]) + "\t");
            }
            System.out.print("\n");
        }
    }

}
