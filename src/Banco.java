import java.sql.*;

public class Banco {
    private static Banco banco;
    
    Connection con;
    Statement statement;

    public Banco(){
        if(banco == null){
            banco = this;
        }
        inicializa();
    }

    public void inicializa(){
        try{
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/teste", "root", "OIphilOI");
            Statement statement =  con.createStatement();
        }catch(Exception ex){
            ex.printStackTrace();
        }
    }

    public void criacaoTabela(){
        try{
        String criacaoTabela = "create table localidade_faixa(" + 
                "loc_nu INT8 NOT NULL," + 
                "loc_cep_ini VARCHAR(8) NOT NULL," +
                "loc_cep_fim VARCHAR(8) NULL," + 
                "loc_tipo_cep VARCHAR(1) NOT NULL);";
        statement.execute(criacaoTabela);
        }catch(SQLException ex){
            ex.printStackTrace();
        }
    }

    public void insere(){

        //: : “91050210”, “93900000”, “92500000”, “99900000”, “123456789"
        try{
            statement.execute("insert into localidade_faixa(loc_nu, loc_cep_ini, loc_tipo_cep) values (122, '91050210', 'A');");
            statement.execute("insert into localidade_faixa(loc_nu, loc_cep_ini, loc_tipo_cep) values (122, '93900000', 'A');");
            statement.execute("insert into localidade_faixa(loc_nu, loc_cep_ini, loc_tipo_cep) values (122, '92500000', 'A');");
            statement.execute("insert into localidade_faixa(loc_nu, loc_cep_ini, loc_tipo_cep) values (122, '99900000', 'A');");
            statement.execute("insert into localidade_faixa(loc_nu, loc_cep_ini, loc_tipo_cep) values (122, '22241210', 'A');");
            statement.execute("insert into localidade_faixa(loc_nu, loc_cep_ini, loc_tipo_cep) values (122, '41535511', 'A');");
            statement.execute("insert into localidade_faixa(loc_nu, loc_cep_ini, loc_tipo_cep) values (122, '44465451', 'A');");
            statement.execute("insert into localidade_faixa(loc_nu, loc_cep_ini, loc_tipo_cep) values (122, '22248210', 'A');");
        }catch(SQLException ex){
            System.out.println(ex.getSQLState());
        } 
    }

    public ResultSet pesquisaCep(String cep){
        try{
            ResultSet res = statement.executeQuery("select * from localidade_faixa where loc_cep_ini = " + cep);
            return res;
        }catch(SQLException ex){
            System.out.println("Erro na busca!");
            ex.printStackTrace();
        }
        return null;
    }

    public Banco getBanco(){
        return banco;
    }
}
