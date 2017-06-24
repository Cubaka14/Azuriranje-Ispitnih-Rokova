package DBconection;

import java.sql.*;

/**
 * Created by db2admin on 5/23/2017.
 */
public class Database {
    private static Database instance;
    private static final int MAX_CON = 1;
    private static final Connection[] bafer = new Connection[MAX_CON];
    private int first = 0, last = 0, free = MAX_CON;

    private Database() {

    }

    public void konektujSe() {
        try {
            Class.forName("com.ibm.db2.jcc.DB2Driver");
            for (int i = 0; i < MAX_CON; i++) {
                bafer[i] = DriverManager.getConnection(
                        "jdbc:db2://localhost:50000/VSTUD", "Aleksandar", "sm3d3r3v0");
            }
            System.out.println("Database.class : Connection successful!");

        } catch (Exception e) {
            System.out.println("Database.class : CONNECTION FAILED!");

            e.printStackTrace();
        }
    }

    public void diskonektujSe() {
        try {
            for (int i = 0; i < MAX_CON; i++)
                bafer[i].close();
            System.out.println("Database.class : Disconnection successful!");
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("Database.class : DISCONNECTION FAILED!");
        }
    }


    public static Database getInstance() {
        if (instance == null) {
            instance = new Database();
        }
        return instance;
    }

    public synchronized Connection getConnection() {
        if (free == 0) {
            return null;
        }
        //free--;
        Connection con = bafer[first];
        //first = (first + 1) % MAX_CON;
        return con;
    }

    public synchronized void putConnection(Connection con) {
        if (con == null) {
            return;
        }
        free++;
        bafer[last] = con;
        last = (last + 1) % MAX_CON;
    }

    public static ResultSet otvoriKursor(PreparedStatement ps) throws SQLException {
        ResultSet rs = ps.executeQuery();
        return rs;
    }

    public static ResultSet obradiCekanje(ResultSet rs, Connection con, PreparedStatement ps) throws SQLException {
        System.out.println("Objekat je zakljucan od strane druge transakcije! Sacekajte!");
        con.rollback();
        System.out.println("Rollback");
        return otvoriKursor(ps);
    }
    public static void obradiCekanjeUpdate(Statement st,String sqlUpdateQuery) throws  SQLException{
        System.out.println("Objekat je zakljucan od strane druge transakcije! Sacekajte!");
        bafer[0].rollback();
        System.out.println("Rollback");
        st.executeUpdate(sqlUpdateQuery);
    }
}
