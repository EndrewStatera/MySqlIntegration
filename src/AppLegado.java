import java.sql.*;
public class AppLegado {
    public static void main(String[] args) throws Exception {
        try{
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/teste", "root", "OIphilOI");
            Statement statement =  con.createStatement();
            // ResultSet query = statement.executeQuery("Select * from localidade_faixa");

            // while(query.next()){
            //     System.out.println(query.getString(1));
            // }
            String criacaoTabela = "create table localidade_faixa(" + 
                "loc_nu INT8 NOT NULL," + 
                "loc_cep_ini VARCHAR(8) NOT NULL," +
                "loc_cep_fim VARCHAR(8) NULL," + 
                "loc_tipo_cep VARCHAR(1) NOT NULL);";
            statement.execute(criacaoTabela);

            con.commit();
            statement.close();
        }catch(Exception ex){

        }
        System.out.println("Ended up!");
    }
}
