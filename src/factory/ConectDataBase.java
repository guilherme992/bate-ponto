package factory;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class ConectDataBase {


    public static void main(String[] args) {
        var url = "jdbc:mysql://localhost:3306/BatePonto";
        var user = "root";
        var password = "univille";
        // try-with-resources
        try(var con = DriverManager.getConnection(url,user,password);
            var st = con.createStatement();) {

            System.out.println("Conectado!");

            var insert = """
                insert into pessoa(nome,idade) values('Tom Cruise',57);
            """;
            st.execute(insert);
            var update = """
                    update pessoa set nome = 'Tom Holland' where id = 1
                    """;
            //st.executeUpdate(update);
            var delete = """
                    delete from pessoa;
                    """;
            //st.execute(delete);
        }catch (SQLException e){
            System.out.println("Deu erro!");
            e.printStackTrace();
        }
    }

}