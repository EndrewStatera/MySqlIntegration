import java.sql.*;
import java.util.ArrayList;
import java.util.LinkedList;

public class App {
    public static void main(String[] args) throws Exception {
        BinarySearchTree binaryTree = new BinarySearchTree();
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/teste", "root", "OIphilOI");
            Statement statement = con.createStatement();
            ResultSet query = statement.executeQuery("Select * from localidade_faixa");

            while (query.next()) {
                LocalidadeFaixa lf = new LocalidadeFaixa();
                lf.setLocNu(Integer.parseInt(query.getString(1)));
                lf.setLocCepIni(Integer.parseInt(query.getString(2)));
                String locCepFim = query.getString(3);
                if(!(locCepFim == null))
                    lf.setLocCepFim(Integer.parseInt(query.getString(3)));
                lf.setLocTipoCep(query.getString(4));
                binaryTree.add(lf);
            }
        } catch (Exception ex) {

        }
        LinkedList<LocalidadeFaixa> lista = binaryTree.positionsCentral();
        for (LocalidadeFaixa linha : lista) {
            System.out.println(linha);
        }

        System.out.println("Pesquisa de ceps: ");
        //CEPs : “91050210”, “93900000”, “92500000”, “99900000”, “123456789”
        System.out.println("91050210 Cidade: " + searchByCep(binaryTree, 91050210));
        System.out.println("93900000 Cidade: " + searchByCep(binaryTree, 93900000));
        System.out.println("92500000 Cidade: " + searchByCep(binaryTree, 92500000));
        System.out.println("99900000 Cidade: " + searchByCep(binaryTree, 99900000));
        System.out.println("123456789 Cidade: " + searchByCep(binaryTree, 123456789));
        System.out.println("Ended up!");
    }

    public static int searchByCep(BinarySearchTree binaryTree, int cep) {
        LocalidadeFaixa finded = binaryTree.get(cep);
        return finded != null ? finded.getLocNu() : -1;
    }
}
